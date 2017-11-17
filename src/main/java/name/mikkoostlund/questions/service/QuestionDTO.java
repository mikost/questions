package name.mikkoostlund.questions.service;

public class QuestionDTO {
    private final String id;
    private final String questionText;
    private final String answerText;

    QuestionDTO(String id, String questionText, String answerText) {
        this.id = id;
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public String getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }
}
