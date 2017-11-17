package name.mikkoostlund.questions.impl.inmemory;

import name.mikkoostlund.questions.model.Question;

class InMemoryQuestion implements Question {
    InMemoryID id;
    private final String questionText;
    private final String answerText;

    InMemoryQuestion(InMemoryQuestionBuilder inMemoryQuestionBuilder) {
        this.questionText = inMemoryQuestionBuilder.questionText;
        this.answerText = inMemoryQuestionBuilder.answerText;
    }

    @Override
    public InMemoryID getID() {
        return id;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public String getAnswerText() {
        return answerText;
    }
}
