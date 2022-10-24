import java.util.ArrayList;
import java.util.List;
public class Server {
    public String usuario = "aluno";
    public String senha = "123456";
    public String endereco = "pop.mail.com";
    public String porta = "110";


    //emails
    List<Email> emails = new ArrayList<>(); //arraylist de emails do servidor


    public void carregarEmails(){
            emails.add(new Email("Lucas", usuario, "bom dia","\n Olá, tudo bem.", "1009" ));
        emails.add(new Email("ShopOnline", usuario, "Promoção do shopOnline", "\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!", "3553"));
    emails.add(new Email("ClaroServiços@mail.com", usuario," Pacote Claro","\nOlá, Boa tarde, aluno\nGostaria do de comprar o novo pacote da claro?\nNosas promoções:\n3G R$20,00 ao mês\n4G R$40,00 ao mês\n5G 60,00 ao mês", "5799"));
    }
    public void caixaDeEntrada(int n){
        System.out.println(emails.get(n).toString());
        emails.remove(n);
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
            return true;
        }else{
            return false;
        }
       
    }
    // Mostra o número de emails dp usuário
    public int stat(){
        int n = emails.size();
        return n;
    }
    //Recebe como parâmetro o indice do email e a quantidade de linhas que deveraão ser mostradas
    public void retr(int n){
        if (n < 0 | n > emails.size()) {
            System.out.println("-Erro- Impossível acessar essa informação\ne-mails não lidos = "
                    + emails.size()+
                    "\ntentou acessar a posição "+n+" da caixa postal");
        }else{
            caixaDeEntrada((n-1));
        }
    }

    public void list(){

        for (int i = 0; i < emails.size(); i++) {
          
            System.out.println(""+ (i+1)+" - "+emails.get(i).getTamanho() + "KB");//Imprime o indice dos emails e seu tamanho em KB, colocar o tamanho no final

        }
        System.out.println(".");
    }
   


}
