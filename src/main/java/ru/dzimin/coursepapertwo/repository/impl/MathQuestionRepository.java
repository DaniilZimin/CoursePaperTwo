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
public class MathQuestionRepository implements QuestionRepository {
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
        questions.add(new Question("Вопрос1 Math?", "Ответ1 Math!"));
        questions.add(new Question("Вопрос2 Math?", "Ответ2 Math!"));
        questions.add(new Question("Вопрос3 Math?", "Ответ3 Math!"));
        questions.add(new Question("Вопрос4 Math?", "Ответ4 Math!"));
        questions.add(new Question("Вопрос5 Math?", "Ответ5 Math!"));
        questions.add(new Question("Вопрос6 Math?", "Ответ6 Math!"));
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
