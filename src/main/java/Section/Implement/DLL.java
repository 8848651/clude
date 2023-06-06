package Section.Implement;

import org.springframework.stereotype.Component;


@Component
public class DLL implements Section.Interface.DLL {

    @Override
    public void Save1() {}
    @Override
    public int Save2(int a,int b) {
        return a+b;
    }
    @Override
    public int Save3(int a,int b) {
       return a+b;
    }
}
