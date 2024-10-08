package nl.hkstwk.spring6aiintro.controllers;

import lombok.RequiredArgsConstructor;
import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.GetCapitalRequest;
import nl.hkstwk.spring6aiintro.model.GetCapitalResponse;
import nl.hkstwk.spring6aiintro.model.GetCapitalWithInfoResponse;
import nl.hkstwk.spring6aiintro.model.Question;
import nl.hkstwk.spring6aiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public GetCapitalWithInfoResponse getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
        return openAIService.getCapitalWithInfo(getCapitalRequest);
    }
}
