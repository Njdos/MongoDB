package company;

import company.Mediator.FirstSpeaker;
import company.Mediator.SecondSpeaker;
import company.Mediator.Speaker;
import company.Mediator.TextChat;
import company.Observer.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Platforms
public class Main {
    public static void main(String[] args) {

        TextChat textChat = new TextChat();

        Speaker first = new FirstSpeaker(textChat,"Admin");
        Speaker second1 = new SecondSpeaker(textChat,"2");
        Speaker second2 = new SecondSpeaker(textChat,"1");

        textChat.setFirst(first);
        textChat.addSpeakerToChat(second1);
        textChat.addSpeakerToChat(second2);


        first.sendMessage("We are close all trips");

        System.out.println("=================================");

        second1.sendMessage("Hello train #1 is late !");

        System.out.println("=================================");

        Database database = new Adapter();

        database.insertUser(new Person("Oleh")
                .setArrivel("Kiev")
                .setTicketNumber(75)
                .setPlatform(String.valueOf(Platforms.somePlatform.Firstly)));

        database.removeUser("Oleh");

        System.out.println("=================================");

        database.insertRoad(new Roads("Harkiv")
                .setLarge(72)
                .setLocalDate(LocalDate.now()
                        .plusDays(7))
                .setPlatform(String.valueOf(Platforms.somePlatform.Secondary)));
        database.removeRoad("Harkiv");

        System.out.println("============================================");

        database.searchSomeRoad("Harkiv");
    }
}