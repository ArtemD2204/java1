package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static int reverseDigits(int number) {
        return (number % 10)*100 + (number / 10 % 10)*10 + (number / 100);
    }
}
