package company.Observer;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

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
        collection.deleteOne(new Document("road", road));
        removeRoad();


        List<String> users1 = new ArrayList<>();
        List<String> users2 = new ArrayList<>();

        Bson filterByName = Filters.exists("name");
/*        Bson filterByFirstPlatform = Filters.eq("platform",1);
        Bson filterBySecondPlatform = Filters.eq("platform",2);*/

        for (Document document1 : collection.find(filterByName)) {
            if (document1.get("platform").equals(1)) {
                users1.add((String) document1.get("name"));
            }
        }
        for (Document document2 : collection.find(Filters.exists("name"))) {
            if (document2.get("platform").equals(2)) {
                users2.add((String) document2.get("name"));
            }
        }

        System.out.println("============================================");

        for (String user : users1) {
            System.out.println(user);
            for (Document document : collection.find(Filters.exists("road"))) {
                if (document.get("platform").equals(1)) {
                    System.out.println(document.toJson());
                }
            }
        }
        for (String user : users2) {
            System.out.println(user);
            for (Document document : collection.find(Filters.exists("road"))) {
                if (document.get("platform").equals(2)) {
                    System.out.println(document.toJson());
                }
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

        List<String> users1 = new ArrayList<>();
        List<String> users2 = new ArrayList<>();

        Bson filterByName = Filters.exists("name");
/*        Bson filterByFirstPlatform = Filters.eq("platform",1);
        Bson filterBySecondPlatform = Filters.eq("platform",2);*/

        for (Document document1 : collection.find(filterByName)) {
            if (document1.get("platform").equals(1)) {
                users1.add((String) document1.get("name"));
            }
        }
        for (Document document2 : collection.find(Filters.exists("name"))) {
            if (document2.get("platform").equals(2)) {
                users2.add((String) document2.get("name"));
            }
        }

        System.out.println("============================================");

        for (String user : users1) {
            System.out.println(user);
            for (Document document : collection.find(Filters.exists("road"))) {
                if (document.get("platform").equals(1)) {
                    System.out.println(document.toJson());
                }
            }
        }
        for (String user : users2) {
            System.out.println(user);
            for (Document document : collection.find(Filters.exists("road"))) {
                if (document.get("platform").equals(2)) {
                    System.out.println(document.toJson());
                }
            }
        }
    }
}
