package ru.dzimin.coursepapertwo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dzimin.coursepapertwo.exception.ValidationException;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.service.ExaminerService;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService service;

    @Autowired
    public ExaminerServiceImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int size = service.getAll().size();
        if (size == amount) {
            return service.getAll();
        }
        if (amount > size || amount <= 0) {
            throw new ValidationException("Недостаточно количества вопросов по запросу!");
        }
        Set<Question> questions = new HashSet<>();

        while (questions.size() < amount) {
            questions.add(service.getRandomQuestion());
        }
        return questions;
    }
}
