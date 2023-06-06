package Affairs.Implement;

import DAO.Implement.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//一定不要忘记写不然不会代理
@Component
@Aspect
public class changesAOP {
    @Pointcut("execution(void Affairs.Interface.Change.changed(..))")
    private void pt(){}

    @Before("pt()")
    public void before(){}

    @Around("pt()")
    public Object aroundSave3(ProceedingJoinPoint JP) throws Throwable {
        Object[] args = JP.getArgs();
        User user= (User) args[0];
        user.setPassword("0000");
        args[0]=user;
        System.out.println(args[0].toString());
        JP.proceed(args);
        return null;
    }
}
