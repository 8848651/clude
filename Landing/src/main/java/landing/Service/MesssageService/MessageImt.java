package landing.Service.MesssageService;
import com.alibaba.fastjson.JSON;
import landing.Dao.Message;
import landing.Dao.MessageMappers;
import landing.MQ.MQPUSH;
import landing.Tool.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MessageImt implements MessageImp {

    @Autowired
    private MQPUSH MH;
    @Autowired
    private MessageMappers MessageMapper;


    @Override
    public Boolean insert(Message Message){
        String name = Message.getName();
        String RandomString = RandomStringUtil.RandomString();
        String string = name + RandomString;
        Message.setName(string);
        MessageMapper.insert(Message);
        List<Message> messages =MessageMapper.selectOne2(string);
        Message message = messages.get(0);
        message.setName(name);
        MessageMapper.Update1(message);
        MH.Add(JSON.toJSONString(message));
        return true;
    }

    @Override
    public void Change(Message Message){
        MessageMapper.Update2(Message);
    }

    @Override
    public void Delete(String id){
        MessageMapper.deleteById(id);
    }

}
