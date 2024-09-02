package nl.hkstwk.spring6aiintro.services;

import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.GetCapitalRequest;
import nl.hkstwk.spring6aiintro.model.GetCapitalResponse;
import nl.hkstwk.spring6aiintro.model.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);
    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
