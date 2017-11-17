package name.mikkoostlund.questions.impl.inmemory;

import name.mikkoostlund.questions.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryQuestionRepository implements QuestionRepository {

    private static final AtomicLong nextId = new AtomicLong();

    private static final InMemoryQuestionSpecification ALL = new InMemoryQuestionSpecification() {};

    private final Map<InMemoryID, InMemoryQuestion> questions = new HashMap<>();

    public InMemoryQuestionRepository() {
    }

    @Override
    public QuestionBuilder questionBuilder() {
        return new InMemoryQuestionBuilder();
    }

    @Override
    public String idToString(name.mikkoostlund.questions.model.ID id) {
        if (!(id instanceof InMemoryID)) {
            throw new IllegalArgumentException("not an InMemoryID");
        }
        return String.valueOf(((InMemoryID) id).getId());
    }

    @Override
    public InMemoryID stringToId(String aString) {
        try {
            return new InMemoryID(Long.valueOf(aString));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("not a valid ID: " + aString, e);
        }
    }

    @Override
    public void add(Question question) {
        if (!(question instanceof InMemoryQuestion)) {
            throw new IllegalArgumentException("not an InMemoryQuestion");
        }
        InMemoryQuestion inMemoryQuestion = (InMemoryQuestion)question;
        if (inMemoryQuestion.id != null) {
            throw new IllegalArgumentException("already persisted");
        }
        InMemoryID id = new InMemoryID(nextId.getAndIncrement());
        inMemoryQuestion.id = id;
        questions.put(id, inMemoryQuestion);
    }

    @Override
    public void update(Question question) {
        if (!(question instanceof InMemoryQuestion)) {
            throw new IllegalArgumentException("not an InMemoryQuestion");
        }
        InMemoryQuestion inMemoryQuestion = (InMemoryQuestion)question;
        InMemoryID id = inMemoryQuestion.id;
        if (id == null) {
            throw new IllegalArgumentException("cannot update not persisted question");
        }
        questions.put(id, inMemoryQuestion);
    }

    @Override
    public void remove(Question question) {
        if (!(question instanceof InMemoryQuestion)) {
            throw new IllegalArgumentException("not an InMemoryQuestion");
        }
        InMemoryQuestion inMemoryQuestion = (InMemoryQuestion) question;
        if (inMemoryQuestion.id == null) {
            throw new IllegalArgumentException("not a persisted question");
        }
        if (questions.remove(inMemoryQuestion.id) == null) {
            throw new Error("internal error in " + this.getClass().getName());
        }
        inMemoryQuestion.id = null;
    }

    @Override
    public List<Question> query(QuestionSpecification specification) {
        if (!(specification instanceof InMemoryQuestionSpecification)) {
            throw new IllegalArgumentException("not an InMemoryQuestionSpecification: "+ specification);
        }
        if (specification == ALL) {
            return new ArrayList<>(questions.values());
        } else if (specification instanceof ByIdQuestionSpecification) {
            InMemoryID id = ((ByIdQuestionSpecification) specification).getId();
            Question question = questions.get(id);
            return question == null
                    ? Collections.emptyList()
                    : Collections.singletonList(question);
        }
        throw new IllegalStateException();
    }

    public InMemoryQuestionSpecification all() {
        return ALL;
    }

    @Override
    public QuestionSpecification byId(ID id) {
        if (!(id instanceof InMemoryID)) {
            throw new IllegalArgumentException("not an InMemoryID");
        }
        return new ByIdQuestionSpecification((InMemoryID) id);
    }
}
