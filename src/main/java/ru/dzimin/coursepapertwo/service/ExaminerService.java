package ru.dzimin.coursepapertwo.service;

import ru.dzimin.coursepapertwo.model.Question;

import java.util.Collection;

public interface ExaminerService {
    public Collection<Question> getQuestions(int amount);
}
