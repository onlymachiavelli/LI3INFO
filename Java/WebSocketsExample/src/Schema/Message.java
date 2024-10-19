package Schema;
import java.io.Serializable;
public class Message implements Serializable  {

    private int id;

    private String message;
    private String sender ;
    private String content ;



    //constructor

    public Message(int id, String message, String sender, String content) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.content = content;
    }



    //getters and setters


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }


    public String getSender() {
        return sender;
    }

    public void setSender (String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    //method to return the content as one built schema
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                '}';
    }




}
