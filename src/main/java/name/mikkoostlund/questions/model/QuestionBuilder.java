package name.mikkoostlund.questions.model;

public interface QuestionBuilder {
    QuestionBuilder withQuestion(String question);

    QuestionBuilder withAnswer(String answerText);

    Question build();
}
