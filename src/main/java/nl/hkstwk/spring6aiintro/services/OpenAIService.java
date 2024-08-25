package nl.hkstwk.spring6aiintro.services;

import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);
}
