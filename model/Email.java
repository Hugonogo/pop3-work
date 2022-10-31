package model;

import java.io.Serializable;

public class Email implements Serializable{
    /*
     * Template do e-mail básico pode add mais coisa se quiser
    */

    String tittle, msg, to, sender;

    public Email(String tittle, String msg, String to, String sender) {
        this.tittle = tittle;
        this.msg = msg;
        this.to = to;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Email [tittle=" + tittle + ", msg=" + msg + ", to=" + to + ", sender=" + sender + "]";
    }

}
