package company.Mediator;

import java.util.ArrayList;
import java.util.List;

public class TextChat implements Chat{

    Speaker first;
    List<Speaker> speakers = new ArrayList<>();

    public void setFirst(Speaker first) {
        this.first = first;
    }

    public void addSpeakerToChat(Speaker speaker){
        this.speakers.add(speaker);
    }

    @Override
    public void sendMessage(String message, Speaker speaker) {

        for (Speaker s : speakers){
            if (s != speaker){
                s.getMessage(message);
            }
        }
        first.getMessage(message);
    }
}
