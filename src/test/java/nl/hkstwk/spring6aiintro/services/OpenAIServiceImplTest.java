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

    @Test
    void getPythonAnswer() {
        String answer = openAIService.getAnswer("Write a python script to output numbers from 1 to 100");
        System.out.print("OpenAI answered with: ");
        System.out.println(answer);
    }

    @Test
    void getPythonSnakeAnswer() {
        String answer = openAIService.getAnswer("Write the game snake in python");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getReasoningDryingShirtsAnswer(){
        String answer = openAIService.getAnswer("If we lay 5 Shirts out in the sun and it takes 4 hours to dry, how long would it take to dry 20 shirts? Explain your reasoning step by step.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getReasoningWhosFasterAnswer(){
        String answer = openAIService.getAnswer("Jane is faster than Joe. Joe is faster than Sam. Is Sam faster than Jane? Explain your reasoning step by step.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getSimpleMathAnswer(){
        String answer = openAIService.getAnswer("4 + 4 = ?");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getComplexMathAnswer(){
        String answer = openAIService.getAnswer("25 - 4 * 2 + 3 = ?");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getWordCountAnswer(){
        String answer = openAIService.getAnswer("How many words are in your response to this prompt?");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getKillerCountAnswer(){
        String answer = openAIService.getAnswer("There are 3 killers in a room. Someone enters the room and kills one of them. How many killers are left in the room? Explain your reasoning step by step.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }

    @Test
    void getJsonAnswer(){
        String answer = openAIService.getAnswer("Create JSON for the following: There are 3 people, two males. One is named Mark. Another is named Joe. And a third person is a woman named Sam. The woman is age 20 and the two men are both 19.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }


    @Test
    void getMarbleMicrowaveAnswer(){
        String answer = openAIService.getAnswer("Assume the laws of physics on Earth. A small marble is put into a normal cup and the cup is placed upside down on a table. Someone then takes the cup and puts it inside the microwave. Where is the ball now. Explain your reasoning step by step.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }


    @Test
    void getMarbleMicrowaveExtendedAnswer(){
        String answer = openAIService.getAnswer("Assume the laws of physics on Earth. A small marble is put into a normal open cup and the cup is placed upside down on a table. Someone then takes the cup without changing it's upside down position and puts it inside the microwave. Where is the ball now. Explain your reasoning step by step.");
        System.out.println("OpenAI answered with");
        System.out.println(answer);
    }
}