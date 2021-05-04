package company.Mediator;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import company.Platforms;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Platforms
public class SecondSpeaker implements Speaker {

    final String MONGO_URI = "mongodb+srv://root:1111@cluster0.1fv2o.mongodb.net/test";

    MongoCollection<Document> collection = new MongoClient(new MongoClientURI(MONGO_URI)).getDatabase("data").getCollection("njdos");
    List<String> users1 = new ArrayList<>();
    List<String> users2 = new ArrayList<>();

    Chat chat;
    String name;

    public SecondSpeaker(Chat chat, String name) {
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
    public void getMessage(String message, Speaker speaker) {

        Bson searchByName = Filters.exists("name");
        Bson searchByPlatform1 = Filters.eq("platform", String.valueOf(Platforms.somePlatform.Firstly));
        Bson searchByPlatform2 = Filters.eq("platform",String.valueOf(Platforms.somePlatform.Secondary));
        if (this.getName().equalsIgnoreCase("1")) {
            if (users1.isEmpty()) {
                for (Document document : collection.find(Filters.and(searchByName, searchByPlatform1))) {
                    users1.add((String) document.get("name"));
                }
            }
            System.out.println("1 Platform receiving message: " + message);
            users1.forEach(System.out::println);
        }

        if (this.getName().equalsIgnoreCase("2")) {
            if (users2.isEmpty()) {
                for (Document document : collection.find(Filters.and(searchByName, searchByPlatform2))) {
                    users2.add((String) document.get("name"));
                }
            }
            System.out.println("2 Platform receiving message: " + message);
            users2.forEach(System.out::println);
        }
    }
}