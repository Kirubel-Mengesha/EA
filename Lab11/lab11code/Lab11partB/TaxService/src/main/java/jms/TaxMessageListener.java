package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TaxMessageListener {
 
    @JmsListener(destination = "testQueue")
    public void receiveMessage(final String text) {
        System.out.println("Tax-service receives message: "+text);

     }
}

