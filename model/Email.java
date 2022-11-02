package model;

import java.io.Serializable;

public class Email implements Serializable{
     /*
     * Template do e-mail básico pode add mais coisa se quiser
    */

    String tittle, msg, to, sender, tamanho;

    public Email(String tittle, String msg, String to, String sender, String tamanho) {
        this.tittle = tittle;
        this.msg = msg;
        this.to = to;
        this.sender = sender;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "\nDe: "+sender+"// Para: "+to+"\n Assunto: "+tittle+"\n"+msg+"\n.";
    }

}
