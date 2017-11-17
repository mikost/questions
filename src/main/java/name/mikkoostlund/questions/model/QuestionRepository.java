package name.mikkoostlund.questions.model;

public interface QuestionRepository extends Repository<Question, QuestionSpecification> {
    QuestionBuilder questionBuilder();
    QuestionSpecification all();
    QuestionSpecification byId(ID id);
    String idToString(ID id);
    ID stringToId(String aString);

}
