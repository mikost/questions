package name.mikkoostlund.questions.service;

import name.mikkoostlund.questions.model.ID;
import name.mikkoostlund.questions.model.Question;
import name.mikkoostlund.questions.model.QuestionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class QuestionService {

    private final QuestionRepository qr;

    public QuestionService(QuestionRepository qr) {
        this.qr = qr;
    }

    public void createQuestion(String questionText, String answerText) {
        Question question = qr.questionBuilder()
                .withQuestion(questionText)
                .withAnswer(answerText)
                .build();

        qr.add(question);
    }

    public List<QuestionDTO> listQuestions() {
        return qr.query(qr.all()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private QuestionDTO toDto(Question question) {
        return new QuestionDTO(qr.idToString(question.getID()), question.getQuestionText(), question.getAnswerText());
    }

    public void deleteQuestion(String idAsString) {
        ID id = qr.stringToId(idAsString);
        List<Question> list = qr.query(qr.byId(id));
        if (list.isEmpty()) {
            throw new NoSuchElementException(idAsString);
        }
        Question question = list.get(0);
        qr.remove(question);
    }
}
