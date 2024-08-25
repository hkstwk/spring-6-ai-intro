package nl.hkstwk.spring6aiintro.services;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuider) {
        this.chatClient = chatClientBuider.build();
    }

    @SuppressWarnings("removal")
    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    @SuppressWarnings("removal")
    @Override
    public Answer getAnswer(Question question) {
        log.info("Received Question for OpenAI {}", question.question());
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return new Answer(response.getResult().getOutput().getContent());
    }
}
