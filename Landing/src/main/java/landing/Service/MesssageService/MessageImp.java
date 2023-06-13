package landing.Service.MesssageService;

import landing.Dao.Message;

public interface MessageImp {
    //增
    Boolean insert(Message Message) throws InterruptedException;
    //删
    void Delete(String id);
    //改
    void Change(Message Message);
}
