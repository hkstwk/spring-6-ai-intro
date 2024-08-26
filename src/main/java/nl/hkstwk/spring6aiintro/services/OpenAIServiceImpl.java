package nl.hkstwk.spring6aiintro.services;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.GetCapitalRequest;
import nl.hkstwk.spring6aiintro.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuider) {
        this.chatClient = chatClientBuider.build();
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        log.info("Received Question for OpenAI {}", question.question());
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getAnswer(GetCapitalRequest getCapitalRequest) {
        log.info("Received GetCapitalRequest: {}", getCapitalRequest.stateOrCountry());

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        log.info("Prompt: {}", prompt.getContents());
        ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
