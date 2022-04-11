package ru.dzimin.coursepapertwo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.repository.impl.JavaQuestionRepository;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.Collection;
import java.util.Random;

@Service
@Component("Java")
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return javaQuestionRepository.add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(javaQuestionRepository.getAll().size());

        int i = 0;
        for (Question question : javaQuestionRepository.getAll()) {
            if (i++ == randomIndex) {
                return question;
            }
        }
        return null;
    }
}
