package by.epam.kunitski.travelagency.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * by.epam.kunitski.travelagency.dao.EntityDAO.*(Object))")
    public void logDAOCreateMethods() {
    }

    @Pointcut("execution(public * by.epam.kunitski.travelagency.dao.EntityDAO.get*(..))")
    public void logDAOGetMethods() {
    }

    @Pointcut("execution(public * by.epam.kunitski.travelagency.dao.EntityDAO.delete(..)) && args(id)")
    public void logDAODeleteMethods(int id) {
    }

    @AfterReturning(pointcut = "logDAOCreateMethods()", returning = "retVal")
    public void logAfterCreateUpdateMethods(JoinPoint joinPoint, Object retVal) {
        logger.info(joinPoint.getSignature().getName() + " entity " + retVal.getClass().getSimpleName() + " is " + retVal);
    }

    @AfterReturning(pointcut = "logDAODeleteMethods(id)", returning = "result")
    public void logAfterDeleteMethods(JoinPoint joinPoint, int id, Object result) {
        logger.info("result deleting entity with id " + id + " is " + result);
    }

    @AfterReturning(pointcut = "logDAOGetMethods()", returning = "retVal")
    public void logAfterGetMethods(JoinPoint joinPoint, Object retVal) {
        logger.info(joinPoint.getSignature().getName() + " entity is " + retVal);
    }
}
