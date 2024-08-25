package nl.hkstwk.spring6aiintro.controllers;

import lombok.RequiredArgsConstructor;
import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping
    public Answer askQuestion(String question) {
        return openAIService.getAnswer(question);
    }
}
