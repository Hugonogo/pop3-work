import java.util.ArrayList;
import java.util.List;
public class Server {
    public String usuario = "aluno";
    public String senha = "123456";
    public String endereco = "pop.mail.com";
    public String porta = "110";
    List<Email> emails = new ArrayList<>();//arraylist de emails
   
    // public int n = 4;
    // public String[] lista = new String[n];
    
     public void caixaDeEntrada(int n){//Simula uma caixa de entrado de um email
        emails.clear();
        //emails
        emails.add(new Email("Lucas", usuario, "bom dia","\n Olá, tudo bem.", "1009" ));
        emails.add(new Email("ShopOnline", usuario, "Promoção do shopOnline", "\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!", "3553"));
        emails.add(new Email("ClaroServiços@mail.com", usuario," Pacote Claro","\nOlá, Boa tarde, aluno\nGostaria do de comprar o novo pacote da claro?\nNosas promoções:\n3G R$20,00 ao mês\n4G R$40,00 ao mês\n5G 60,00 ao mês", "5799"));
        // this.lista[0] = "Assunto: oi, como vai?\nOi Hugo, bom dia.\nAté Logo.";
        // this.lista[1] = "Assunto: bom dia\n Olá, tudo bem.";
        // this.lista[2] = "Assunto: Promoção do shopOnline\nOlá, aluno\nA ShopOnline está com ótimas promoções\nvenha conferir!!!";
        // this.lista[3] = "Assunto: Pocote claro\nOlá, Boa tarde, aluno\nGostaria do de comprar o novo pacote da claro?\nNosas promoções:\n3G R$20,00 ao mês\n4G R$40,00 ao mês\n5G 60,00 ao mês";
      
        System.out.println(emails.get(n));
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
        if (n < 1 | n > emails.size()) {
            System.out.println("-Err");
        }else{
            caixaDeEntrada(n);
            // n = n - 1;
            // String[] array = emails.get(n).split("\n");
            // if (x == 0) {
            //     System.out.println(array[0]); 
            // }
            // else{
            //     for (int i = 0; i < array.length; i++) {
            //         num++;
            //     }
            //     if (x > num) {
            //         System.out.println("-Err excedeu o numero de linhas");
            //     }
            //     else if(x > 0){
            //         System.out.println(array[0]);
            //         for (int i = 1; i <= x; i++) {
            //             System.out.println(array[i]);
            //         }
            //     }
            // }
        }
    }

    public void list(){

        for (int i = 0; i < emails.size(); i++) {
          
            System.out.println(""+ (i+1)+" ");//Imprime o indice dos emails e seu tamanho em KB, colocar o tamanho no final

        }
        System.out.println(".");
    }
   


}
