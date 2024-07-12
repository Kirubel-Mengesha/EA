package jms;


public class Calculator {
    private int number;
    private String operator;


    public Calculator() {
    }

    public Calculator(int number, String operator) {
        this.number = number;
        this.operator = operator;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
