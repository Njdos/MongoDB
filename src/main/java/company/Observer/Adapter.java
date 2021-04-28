package company.Observer;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Adapter extends AllRoads implements Database {

    private static final String MONGO_URI = "mongodb+srv://root:1111@cluster0.1fv2o.mongodb.net/test";

    MongoCollection<Document> collection = new MongoClient(new MongoClientURI(MONGO_URI)).getDatabase("data").getCollection("njdos");


    ConnectionString connectionString = new ConnectionString(MONGO_URI);
    CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    MongoClientSettings clientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();
    com.mongodb.client.MongoClient mongoClient =  MongoClients.create(clientSettings);
    MongoDatabase db = mongoClient.getDatabase("data");

    MongoCollection<Roads> collectionClass = db.getCollection("njdos", Roads.class);
    MongoCollection<Person> collectionClassForPerson = db.getCollection("njdos", Person.class);


    @Override
    public void insertUser(Person person) {
        addObserver();
        collectionClassForPerson.insertOne(person);
    }

    @Override
    public void removeUser(String userName) {
        removeObserver();
        collection.deleteOne(new Document("name",userName));
    }

    @Override
    public void removeRoad(String road) {
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

    @Override
    public void searchSomeRoad(String searchRoad) {
        searchRoad();
        for(Document document : collection.find(new Document("road",searchRoad)).sort(new BasicDBObject("large",1))){
            System.out.println(document.toJson());
        }

    }

    @Override
    public void insertRoad(Roads roads) {
        collectionClass.insertOne(roads);
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
}
