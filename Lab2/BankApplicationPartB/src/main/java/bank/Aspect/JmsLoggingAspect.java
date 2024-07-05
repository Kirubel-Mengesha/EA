package bank.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import bank.logging.ILogger;

@Aspect
@Configuration
public class JmsLoggingAspect {

    private final ILogger logger;

    @Autowired
    public JmsLoggingAspect(ILogger logger) {
        this.logger = logger;
    }

    @Before("execution(* bank.jms.JMSSender.sendJMSMessage(..)) && args(message)")
    public void logJmsMessage(String message) {
        logger.log("JMS message sent: " + message);
    }
}

