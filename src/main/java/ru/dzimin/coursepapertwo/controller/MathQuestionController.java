package ru.dzimin.coursepapertwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dzimin.coursepapertwo.model.Question;
import ru.dzimin.coursepapertwo.service.QuestionService;

import java.util.Collection;


@RestController
@RequestMapping("exam/math")
public class MathQuestionController {

    private final QuestionService service;

    @Autowired
    public MathQuestionController(@Qualifier("Math") QuestionService service) {
        this.service = service;
    }

    @GetMapping("add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping("remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}
