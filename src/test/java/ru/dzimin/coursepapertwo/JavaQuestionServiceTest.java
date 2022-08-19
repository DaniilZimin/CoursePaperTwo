package ru.dzimin.coursepapertwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.dzimin.coursepapertwo.exception.ValidationException;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.service.impl.JavaQuestionService;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaQuestionServiceTest {

    JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    public void shouldReturnQuestionOnSuccessfulAddByQuestionObject() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");

        assertEquals(question, javaQuestionService.add(question));
    }

    @Test
    public void shouldReturnQuestionOnSuccessfulAddByQuestionAndAnswer() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");

        assertEquals(question, javaQuestionService.add("Что такоке Java?", "Это язык програмирования!"));
    }

    @Test
    public void shouldThrowAnErrorIfOneOfTheParametersIsEmpty() {
        assertThrows(ValidationException.class, () -> javaQuestionService.add("", "Это язык програмирования!"));

    }

    @Test
    public void mustThrowAnExceptionIfNullIsPassedInTheParameter() {
        assertThrows(ValidationException.class, () -> javaQuestionService.add(null));
    }

    @Test
    public void shouldReturnAnObjectIfItHasBeenDeleted() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");
        javaQuestionService.add(question);
        assertEquals(question, javaQuestionService.remove(question));
    }

    @Test
    public void removeMustThrowAnExceptionIfNullIsPassedInTheParameter() {
        assertThrows(ValidationException.class, () -> javaQuestionService.remove(null));
    }

    @Test
    public void shouldThrowAnExceptionIfTheObjectHasNotBeenDeleted() {
        assertThrows(ValidationException.class, () -> javaQuestionService.remove(new Question("Что такоке Java?", "Это язык програмирования!")));
    }

    @Test
    public void shouldReturnListOfAllQuestions() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");
        Question question1 = new Question("Что такоке Spring?", "Это Framework!");
        Collection<Question> questions = new HashSet<>();
        questions.add(question);
        questions.add(question1);
        javaQuestionService.add(question);
        javaQuestionService.add(question1);

        assertEquals(questions, javaQuestionService.getAll());
    }

    @Test
    public void mustNotReturnNull() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");
        javaQuestionService.add(question);
        Assertions.assertNotNull(javaQuestionService.getRandomQuestion());
    }

    @Test
    public void shouldReturnRandomObjects() {
        Question question = new Question("Что такоке Java?", "Это язык програмирования!");
        Question question1 = new Question("Что такоке Spring?", "Это Framework!");
        javaQuestionService.add(question);
        javaQuestionService.add(question1);
        boolean a = true;
        Question question2 = javaQuestionService.getRandomQuestion();
        while (a) {
            Question question3 = javaQuestionService.getRandomQuestion();
            if (question2 != question3) {
                a = false;
            }
        }
        Assertions.assertFalse(a);
    }
}
