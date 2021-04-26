package company.Observer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends AllRoads implements Database {

    private static final String MONGO_URI = "mongodb+srv://root:1111@cluster0.1fv2o.mongodb.net/test";

    MongoCollection<Document> collection = new MongoClient(new MongoClientURI(MONGO_URI)).getDatabase("data").getCollection("njdos");

    @Override
    public void insertUser(String userName) {
        addObserver();
        collection.insertOne(new Document("name",userName));
    }

    @Override
    public void removeUser(String userName) {
        removeObserver();
        collection.deleteOne(new Document("name",userName));
    }

    @Override
    public void insert(String road) {
        collection.insertOne(new Document("road",road));
        addRoad();
        List<String> users = new ArrayList<>();

        for (Document document : collection.find(Filters.exists("name"))) {
            users.add((String) document.get("name"));
        }
        System.out.println("============================================");

        for (String user : users){
            System.out.println(user);
            for(Document document : collection.find(Filters.exists("road"))) {
                System.out.println(document.toJson());

            }
        }
    }

    @Override
    public void remove(String road) {
        collection.deleteOne(new Document("road",road));
        removeRoad();


        List<String> users = new ArrayList<>();

        for (Document document : collection.find(Filters.exists("name"))) {
            users.add((String) document.get("name"));
        }
        
        System.out.println("============================================");

        for (String user : users){
            System.out.println(user);
            for(Document document : collection.find(Filters.exists("road"))) {
                System.out.println(document.toJson());
            }
        }
    }
}
