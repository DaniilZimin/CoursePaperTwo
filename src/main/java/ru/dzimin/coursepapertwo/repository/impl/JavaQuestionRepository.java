package ru.dzimin.coursepapertwo.repository.impl;

import org.springframework.stereotype.Repository;
import ru.dzimin.coursepapertwo.exception.ValidationException;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.repository.QuestionRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new ValidationException("Некорректные входные данные!");
        }

        questions.add(question);
        return question;
    }

    @Override
    @PostConstruct
    public void init() {
        questions.add(new Question("Вопрос1 Java?", "Ответ1 Java!"));
        questions.add(new Question("Вопрос2 Java?", "Ответ2 Java!"));
        questions.add(new Question("Вопрос3 Java?", "Ответ3 Java!"));
        questions.add(new Question("Вопрос4 Java?", "Ответ4 Java!"));
        questions.add(new Question("Вопрос5 Java?", "Ответ5 Java!"));
        questions.add(new Question("Вопрос6 Java?", "Ответ6 Java!"));
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new ValidationException("Некорректные входные данные!");
        }

        boolean wasRemoved = questions.remove(question);

        if (!wasRemoved) {
            throw new ValidationException("Данного объекта нет в базе! Удаление не выполнено");
        }

        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }
}
