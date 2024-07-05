package customers.Aspect;

import customers.service.ILogger;
import customers.service.Impl.EmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class EmailAspect {
    private final ILogger logger;

    @Autowired
    public EmailAspect(ILogger logger) {
        this.logger = logger;
    }

    @After("execution(* customers.service.Impl.EmailSender.sendEmail(..)) && args(email, message)")
    public void logAfterEMail(JoinPoint joinpoint ,String email, String message) {
        EmailSender emailSender = (EmailSender) joinpoint.getTarget();
        String outgoingMailServer = emailSender.getOutgoingMailServer();
        logger.log("method=" + joinpoint.getSignature().getName() + " address=" + email + " message=" + message  + " outgoing mail server=" + outgoingMailServer);
    }

}
