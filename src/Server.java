
import java.util.ArrayList;
import java.util.List;
public class Server {
    public String usuario = "aluno";
    public String senha = "123456";
    public String endereco = "pop.mail.com";
    public String porta = "110";
    Client c = new Client();
    


    //emails
    List<Email> emails = new ArrayList<>(); //arraylist de emails do servidor


    public void carregarEmails(){
        emails.add(new Email("Lucas", usuario, " bom dia","\n Olá, tudo bem.", "1009" ));
        emails.add(new Email("ShopOnline", usuario, "Promoção do shopOnline", "\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!", "3553"));
        emails.add(new Email("ClaroServiços@mail.com", usuario," Pacote Claro","\nOlá, Boa tarde, aluno\nGostaria do de comprar o novo pacote da claro?\nNosas promoções:\n3G R$20,00 ao mês\n4G R$40,00 ao mês\n5G 60,00 ao mês", "5799"));
    }
    public List<Email> caixaDeEntrada(){
        return emails;
    }
    //Comando de login
    public String user(Client pass){

        if (pass.getUser().equals(this.usuario)) {
            return "+OK usário reconhecido";
        }else{
            return "-ERR usuário não reconhecido";
        }
        
    }
    public boolean pass(Client Pass){
        if (Pass.getPassword().equals(this.senha)) {
           emails.clear();
            return true;
        }else{
            return false;
        }
       
    }
    
}
