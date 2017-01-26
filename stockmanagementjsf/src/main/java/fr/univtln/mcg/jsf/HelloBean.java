package fr.univtln.mcg.jsf;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message = "Hello world";

    private String newMessage;

    private int compteur = 0;


    public String updateMessage() {
        message = newMessage;
        return "welcome";
    }


    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public String getMessage() {
        return message + " " + compteur++;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
