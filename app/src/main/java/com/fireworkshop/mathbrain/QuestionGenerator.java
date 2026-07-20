package com.fireworkshop.mathbrain;

import java.util.Random;

/**
 * Generates arithmetic questions for the game.
 */
public class QuestionGenerator {
    private final Random random;

    public QuestionGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generate a new question for the given question number.
     *
     * @param qn the question number (1-30)
     * @return the generated Question
     */
    public Question generateQuestion(int qn) {
        int op = rnd(1, 5);
        int first, second, result;
        String text;
        if (op == 1) { // addition
            if (qn <= 5) {
                first = rnd(0, 11);
                second = rnd(0, 11);
            } else if (qn <= 10) {
                first = rnd(10, 21);
                second = rnd(0, 11);
            } else if (qn <= 15) {
                first = rnd(25, 51);
                second = rnd(0, 101);
            } else if (qn <= 20) {
                first = rnd(50, 151);
                second = rnd(0, 101);
            } else if (qn <= 25) {
                first = rnd(150, 301);
                second = rnd(0, 201);
            } else {
                first = rnd(300, 500);
                second = rnd(0, 401);
            }
            result = first + second;
            text = first + " + " + second;
        } else if (op == 2) { // subtraction
            if (qn <= 10) {
                second = rnd(0, 10);
                first = rnd(second, 11);
            } else if (qn <= 15) {
                second = rnd(10, 51);
                first = rnd(second, 71);
            } else if (qn <= 20) {
                second = rnd(20, 51);
                first = rnd(second, 151);
            } else if (qn <= 25) {
                second = rnd(100, 201);
                first = rnd(second, 301);
            } else {
                second = rnd(0, 500);
                first = rnd(((second > 300) ? second : 300), 501);
            }
            result = first - second;
            text = first + " - " + second;
        } else if (op == 3) { // multiplication
            if (qn <= 10) {
                first = rnd(0, 6);
                second = rnd(0, 6);
            } else if (qn <= 15) {
                first = rnd(10, 16);
                second = rnd(0, 16);
            } else if (qn <= 20) {
                first = rnd(15, 21);
                second = rnd(0, 16);
            } else if (qn <= 25) {
                first = rnd(20, 26);
                second = rnd(0, 21);
            } else {
                first = rnd(20, 26);
                second = rnd(15, 21);
            }
            result = first * second;
            text = first + " x " + second;
        } else { // division
            if (qn <= 10) {
                second = rnd(1, 6);
                result = rnd(1, 6);
            } else if (qn <= 15) {
                second = rnd(1, 11);
                result = rnd(5, 11);
            } else if (qn <= 20) {
                second = rnd(1, 11);
                result = rnd(10, 16);
            } else if (qn <= 25) {
                second = rnd(10, 21);
                result = rnd(10, 21);
            } else {
                second = rnd(15, 31);
                result = rnd(15, 26);
            }
            first = result * second;
            text = first + " / " + second;
        }
        return new Question(text, result);
    }

    private int rnd(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static class Question {
        private final String text;
        private final int answer;

        public Question(String text, int answer) {
            this.text = text;
            this.answer = answer;
        }

        public String getText() {
            return text;
        }

        public int getAnswer() {
            return answer;
        }
    }
}

