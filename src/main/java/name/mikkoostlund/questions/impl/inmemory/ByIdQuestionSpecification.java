package name.mikkoostlund.questions.impl.inmemory;

public class ByIdQuestionSpecification implements InMemoryQuestionSpecification {
    private final InMemoryID id;

    public ByIdQuestionSpecification(InMemoryID id) {
        this.id = id;
    }

    public InMemoryID getId() {
        return id;
    }
}
