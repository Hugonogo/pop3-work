import java.util.ArrayList;
import java.util.List;

// testando o pull foda-se
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
    public void caixaPostal(int n){
        System.out.println(caixaPostal.get(n).toString());
        
    }
    public int stat(){
        int n = caixaPostal.size();
        return n;
    }
    public void retr(int n){
        if (n < 0 | n > caixaPostal.size()) {
            System.out.println("-Erro- Impossível acessar essa informação\ne-mails não lidos = "
                    + caixaPostal.size()+
                    "\ntentou acessar a posição "+n+" da caixa postal");
        }else{
            n--;
           caixaPostal(n);
        }
    }
    public void list(){

        for (int i = 0; i < caixaPostal.size(); i++) {
          
            System.out.println(""+ (i+1)+" - "+caixaPostal.get(i).getTamanho() + "KB");//Imprime o indice dos emails e seu tamanho em KB, colocar o tamanho no final

        }
        System.out.println(".");
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
