package check.View;

import check.Dao.Message;
import check.Dao.PageResult;
import check.ES.Estart;
import check.MQ.MQPUSH;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/Check")
@ResponseBody
public class FormController {

    @Autowired
    private Landclient LI;

    @Autowired
    private Estart es;

    @Autowired
    private MQPUSH MH;

    @RequestMapping("/save")
    public String Formsave(){
        System.out.println("Checkdome");
        return "ABCDEFG";
    }

    //全部查找
    @RequestMapping("/{check}/{content}")
    public PageResult check(@PathVariable String check, @PathVariable String content) throws IOException {
        if (check.equals("1")) {
            return es.termMatch(content);
        }
        return es.queryMatch(content);
    }

    //个人删除
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) throws IOException {
        es.testDeleteById(id);
        MH.Delete(id);
    }

    //个人修改
    @PostMapping("/change")
    public  void change(@RequestBody Message Message) throws InterruptedException, IOException {
        String jsonString = JSON.toJSONString(Message);
        es.testExchange(jsonString,Message.getId());
        MH.Change(jsonString);
    }

    //添加
    @PostMapping("/insert")
    public  Boolean insert(@RequestBody Message Message) throws InterruptedException {
        System.out.println(Message.toString());
        return LI.insert1(Message);
    }

    @PostMapping("/CS")
    public  Boolean inserts(){
        Message Message=new Message("王三","查看哪一个容器正在运行");
        System.out.println(Message.toString());
        return LI.insert2(Message);
    }
}
