package com.aston;


public class App {
    public static void main(String[] args) {
        try {
            ExceptionTest(4);
            ExceptionTest(8);
        } catch (MyException e) {
            System.out.println("!!!Exception!!! message: " + e.getMessage());
        }
    }

    public static void ExceptionTest(int value) throws MyException {
        if (value > 5) {
            throw new MyException("Value is too large!");
        } else {
            System.out.println("Value is fine =)");
        }
    }
}
