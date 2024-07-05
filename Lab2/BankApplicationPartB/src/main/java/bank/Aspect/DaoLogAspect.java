package bank.Aspect;

import bank.logging.ILogger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DaoLogAspect {
    private final ILogger logger;

    @Autowired
    public DaoLogAspect(ILogger logger) {
        this.logger = logger;
    }
    @Before("execution(* bank.dao..*(..))")
    public void logAny(){
        logger.log("A method in the bank.dao package was called.");
    }

}
