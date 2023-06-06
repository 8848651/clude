package CS;

import dom.Config.Config;
import Section.Interface.DLL;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

public class CS3 {
    @Test
    public void test1(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        DLL bean1 = ctx.getBean(DLL.class);
        System.out.println(bean1.Save2(10,10));
        System.out.println(bean1.Save3(10,10));

    }
    @Test
    public void test2(){

        Section.Implement.DLL bean=new Section.Implement.DLL();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        DLL bean1 = ctx.getBean(DLL.class);

        System.out.println(bean.getClass());
        System.out.println(bean1.getClass());
        System.out.println("--------------------------------------------");
        Class aClass = bean1.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method: methods) {
            System.out.println(method);
        }
        System.out.println("--------------------------------------------");
        System.out.println(ClassLayout.parseInstance(bean).toPrintable());
        System.out.println("--------------------------------------------");
        System.out.println(ClassLayout.parseInstance(bean1).toPrintable());

    }
}
