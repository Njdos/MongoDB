package company.Mediator;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class FirstSpeaker implements Speaker {

    final String MONGO_URI = "mongodb+srv://root:1111@cluster0.1fv2o.mongodb.net/test";

    MongoCollection<Document> collection = new MongoClient(new MongoClientURI(MONGO_URI)).getDatabase("data").getCollection("njdos");
    List<String> users = new ArrayList<>();

    Chat chat;
    String name;

    public FirstSpeaker(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {

        Bson searchByName = Filters.exists("name");
        Bson searchByPlatformElse = Filters.gte("platform",3);

        if (users.isEmpty()) {
            for (Document document : collection.find(Filters.and(searchByName,searchByPlatformElse))) {
                users.add((String) document.get("name"));
            }
        }

        System.out.println("Other passengers receiving message: " + message );
        users.forEach(System.out::println);
    }
}
