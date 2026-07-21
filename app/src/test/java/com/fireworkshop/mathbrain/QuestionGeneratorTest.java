package com.fireworkshop.mathbrain;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class QuestionGeneratorTest {
    @Test
    public void answerMatchesDisplayedExpression() {
        QuestionGenerator generator = new QuestionGenerator(new Random(0));
        for (int qn = 1; qn <= 30; qn++) {
            QuestionGenerator.Question q = generator.generateQuestion(qn);
            int computed = evaluate(q.getText());
            assertEquals(computed, q.getAnswer());
        }
    }

    private int evaluate(String text) {
        String[] parts = text.split(" ");
        int first = Integer.parseInt(parts[0]);
        String op = parts[1];
        int second = Integer.parseInt(parts[2]);
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "x":
                return first * second;
            case "/":
                return first / second;
            default:
                throw new IllegalArgumentException("Unknown op " + op);
        }
    }
}

