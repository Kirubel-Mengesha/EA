package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {

    @JmsListener(destination = "testQueue")
    public void receiveMessage(final String numberAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Calculator number = objectMapper.readValue(numberAsString, Calculator.class);
            String operation = number.getOperator();
            int num = number.getNumber();
            int baseNum=10;

            switch (operation){
                case "+":
                    System.out.println("JMS receiver received message: "+num+" + "+baseNum+" = "+(num+baseNum));
                    break;
                case "-":
                    System.out.println("JMS receiver received message: "+num+" - "+baseNum+" = "+(num-baseNum));
                    break;
                case "*":
                    System.out.println("JMS receiver received message: "+num+" * "+baseNum+" = "+(num*baseNum));
                    break;
                case "/":
                    System.out.println("JMS receiver received message: "+num+" / "+baseNum+" = "+(num/baseNum));
                    break;
                default:
                    System.out.println("JMS receiver received message: "+num+" + "+"operation: "+operation);
                    break;
            }
        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + numberAsString+" to a calculator object");
        }
    }
}