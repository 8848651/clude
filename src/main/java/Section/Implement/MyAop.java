package Section.Implement;

import Section.Interface.DLL;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAop {

    @Autowired
    private DLL DLL;
    public void SSS(){
        DLL.Save1();
    }

    @Pointcut("execution(int Section.Interface.DLL.Save3(..))")
    private void pt3(){}

    @Before("pt3()")
    public void beforeSave3(){}

    @Around("pt3()")
    public int aroundSave3(ProceedingJoinPoint JP) throws Throwable {
        long start = System.currentTimeMillis();
        //JP.getArgs() 方法来获取目标方法的参数
        Object[] args = JP.getArgs();
        System.out.println(Arrays.toString(args));

        //JP.getTarget() 方法来获取目标对象，通过

        //JP.proceed() 方法来获取目标方法 调用的方法是save2()
        //Object tt=JP.proceed();
        //调用原始操作JP.proceed()无参
        //JP.proceed(args)有参
        int add= (int) JP.proceed(args);

        //JP.getSignature() 方法来获取方法的签名等信息
        Signature signature = JP.getSignature();
        //Section.Interface.DI
        String name1=signature.getDeclaringTypeName();
        //方法名Save3
        String name2=signature.getName();

        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println(name1 + "."+name2 + ":Time"+": " + l + "ms");
        return add+100;
    }

    //获取Save3()的返回值str必须和实参一样
    //@AfterReturning(valu="pt3()" returning=str)
    @After("pt3()")
    public void afterSave3(){}

}
