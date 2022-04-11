package ru.dzimin.coursepapertwo.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dzimin.coursepapertwo.exception.ValidationException;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.service.ExaminerService;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService serviceJava;

    private final QuestionService serviceMath;

    public ExaminerServiceImpl(@Qualifier("Java") QuestionService serviceJava, @Qualifier("Math") QuestionService serviceMath) {
        this.serviceJava = serviceJava;
        this.serviceMath = serviceMath;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int size = serviceJava.getAll().size() + serviceMath.getAll().size();

        if (amount > size || amount <= 0) {
            throw new ValidationException("Недостаточно количества вопросов по запросу!");
        }

        Set<Question> questions = new HashSet<>();

        Random random = new Random();

        while (questions.size() < amount) {
            int randomIndex = random.nextInt(2);
            if (randomIndex == 0) {
                questions.add(serviceMath.getRandomQuestion());
            } else {
                questions.add(serviceJava.getRandomQuestion());
            }
        }
        return questions;
    }
}
