package ru.dzimin.coursepapertwo.repository;

import ru.dzimin.coursepapertwo.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    void init();

    Question remove(Question question);

    Collection<Question> getAll();
}
