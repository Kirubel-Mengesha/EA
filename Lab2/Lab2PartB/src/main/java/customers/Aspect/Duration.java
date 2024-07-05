package customers.Aspect;

import customers.service.ILogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class Duration {
    private  ILogger logger;

    @Autowired
    public Duration(ILogger logger) {
        this.logger = logger;
    }

    @Around("execution(* customers.Repository..*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Method " + call.getSignature().getName() + " took " + totalTime + " ms");
        return retVal;
    }
}
