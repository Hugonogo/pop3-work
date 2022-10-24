import java.util.ArrayList;
import java.util.List;

public class Client {
    private String user;
    private String password;
    private List<Email> caixaPostal = new ArrayList<>();
    
    public Client() {
    }
    
    public void amazenarMailDoServe(List<Email> mailServeList){
        for (Email e : mailServeList){
            caixaPostal.add(e);
        }
    }
    
    public Client(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
