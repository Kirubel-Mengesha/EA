package bank.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Profile("jms")
public class JMSSender implements IJMSSender{

	private Logger logger = LoggerFactory.getLogger(JMSSender.class);
	
	public void sendJMSMessage (String text){
		logger.debug("JMSSender: sending JMS message ="+text);
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
