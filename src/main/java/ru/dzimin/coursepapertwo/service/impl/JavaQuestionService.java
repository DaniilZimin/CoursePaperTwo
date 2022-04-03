package ru.dzimin.coursepapertwo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.dzimin.coursepapertwo.exception.ValidationException;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if (!StringUtils.hasLength(question) || !StringUtils.hasLength(answer)) {
            throw new ValidationException("Некорректные входные данные!");
        }
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new ValidationException("Некорректные входные данные!");
        }

        questions.add(question);
        return question;
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

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());

        int i = 0;
        for (Question question : questions) {
            if (i++ == randomIndex) {
                return question;
            }
        }
        return null;
    }
}
