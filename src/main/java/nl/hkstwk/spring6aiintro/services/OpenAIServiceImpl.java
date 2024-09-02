package nl.hkstwk.spring6aiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.spring6aiintro.model.Answer;
import nl.hkstwk.spring6aiintro.model.GetCapitalRequest;
import nl.hkstwk.spring6aiintro.model.GetCapitalResponse;
import nl.hkstwk.spring6aiintro.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuider, ObjectMapper objectMapper) {
        this.chatClient = chatClientBuider.build();
        this.objectMapper = objectMapper;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;


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
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        log.info("Format:  {}", format);

        log.info("Received GetCapitalRequest: {}", getCapitalRequest.stateOrCountry());
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(), "capitalFormat", format));
        log.info("Prompt: {}", prompt.getContents());
        ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();
        log.info(chatResponse.getResult().getOutput().getContent());

        return converter.convert(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
