package check.Dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private String name;
    private String message;

    public Message(String name,String message){
        this.name=name;
        this.message=message;
    }
}
