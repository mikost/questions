package name.mikkoostlund.questions.impl.inmemory;

import name.mikkoostlund.questions.model.Question;
import name.mikkoostlund.questions.model.QuestionBuilder;

class InMemoryQuestionBuilder implements QuestionBuilder {
    String answerText;
    String questionText;

    public QuestionBuilder withQuestion(String questionText) {
        this.questionText = questionText;
        return this;
    }

    public QuestionBuilder withAnswer(String answerText) {
        this.answerText = answerText;
        return this;
    }

    public Question build() {
        return new InMemoryQuestion(this);
    }
}
