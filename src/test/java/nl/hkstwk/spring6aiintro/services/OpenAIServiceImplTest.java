package nl.hkstwk.spring6aiintro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    private OpenAIService openAIService;

    @Test
    void getAnswer() {
        String answer = openAIService.getAnswer("tell me a dad joke");
        System.out.print("OpenAI answered with: ");
        System.out.println(answer);
    }
}