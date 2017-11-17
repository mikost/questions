package name.mikkoostlund.questions;

import name.mikkoostlund.questions.impl.inmemory.InMemoryQuestionRepository;
import name.mikkoostlund.questions.model.QuestionRepository;
import name.mikkoostlund.questions.service.QuestionDTO;
import name.mikkoostlund.questions.service.QuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        QuestionRepository questionRepository = new InMemoryQuestionRepository();
        QuestionService questionService = new QuestionService(questionRepository);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("What now? ");
                String action = br.readLine();
                if (action == null) {
                    return;
                }
                switch (action) {
                    case "ny fråga":
                        System.out.print("Skriv en fråga: ");
                        String fraga = br.readLine();
                        System.out.print("Skriv svaret på frågan: ");
                        String svar = br.readLine();
                        questionService.createQuestion(fraga, svar);
                        System.out.println("Lade till frågan \"" + fraga + "\"");
                        System.out.println("med svaret \"" + svar + "\".");
                        break;
                    case "lista":
                        List<QuestionDTO> questions = questionService.listQuestions();
                        if (questions.isEmpty()) {
                            System.out.println("<Inga frågor skapade>");
                        } else {
                            questions.forEach(fr -> {
                                System.out.println(" ID: \"" + fr.getId() + "\"");
                                System.out.println("   Fråga: \"" + fr.getQuestionText() + "\"");
                                System.out.println("    Svar: \"" + fr.getAnswerText() + "\"");
                            });
                        }
                        break;
                    case "radera fråga":
                        System.out.print("Skriv ID för frågan som ska raderas: ");
                        String id = br.readLine();
                        String message;
                        try {
                            questionService.deleteQuestion(id);
                            message = "Raderade fråga med ID " + id;
                        } catch (NoSuchElementException e) {
                            message = "Finns ingen fråga med ID " + id;

                        }
                        System.out.println(message);
                        break;
                    default:
                        System.out.println("förstår inte \"" + action + "\"");
                        break;
                }
            }
        }
    }
}