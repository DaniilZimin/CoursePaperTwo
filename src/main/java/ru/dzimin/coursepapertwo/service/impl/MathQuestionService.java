package ru.dzimin.coursepapertwo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.repository.impl.MathQuestionRepository;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.Collection;
import java.util.Random;

@Service
@Component("Math")
public class MathQuestionService implements QuestionService {

    private final MathQuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return mathQuestionRepository.add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        return mathQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(mathQuestionRepository.getAll().size());

        int i = 0;
        for (Question question : mathQuestionRepository.getAll()) {
            if (i++ == randomIndex) {
                return question;
            }
        }
        return null;
    }
}
