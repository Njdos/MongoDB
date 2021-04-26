package company;

import company.Mediator.FirstSpeaker;
import company.Mediator.SecondSpeaker;
import company.Mediator.Speaker;
import company.Mediator.TextChat;
import company.Observer.Adapter;
import company.Observer.Database;

public class Main {
    public static void main(String[] args) {


        TextChat textChat = new TextChat();

        Speaker first = new FirstSpeaker(textChat,"Admin");
        Speaker second1 = new SecondSpeaker(textChat,"1");
        Speaker second2 = new SecondSpeaker(textChat,"2");

        textChat.setFirst(first);
        textChat.addSpeakerToChat(second1);
        textChat.addSpeakerToChat(second2);

        first.sendMessage("We are close all trips");

        System.out.println("=================================");
        
        second1.sendMessage("Hello train #1 is late !");

        Database database = new Adapter();
        database.insert("Kiev");
        database.insertUser("Dima");
        database.remove("Kiev");
        database.removeUser("Dima");
    }
}
