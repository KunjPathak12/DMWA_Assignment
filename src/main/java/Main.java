import Interfaces.ReutReader;
import Interfaces.TextExtractor;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import impl.ReutReaderImpl;
import impl.TextExtractorImpl;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        ReutReader reader = new ReutReaderImpl();
        TextExtractor textExtractor = new TextExtractorImpl();
        String fileName = "reut2-009.sgm";
        String file = reader.readFile(fileName);
        String uri = "mongodb+srv://kunj:kunj12345@assignment.4ogqk51.mongodb.net/" ;
        try(MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("ReuterDb");
            MongoCollection<Document> collection = database.getCollection("Reuter's Data");
            try {
                for(String i: textExtractor.mappedData(file)){
                    String Title = "";
                    String Body = "";
                    if(i.contains("Title: ")){
                        Title = i.replace("Title: ","");
                    }
                    if(i.contains("Body: ")){
                        Body = i.replace("Body: ", "");
                    }
                    InsertOneResult result = collection.insertOne(new Document()
                            .append("Title", Title)
                            .append("Body", Body));
                    // Prints the ID of the inserted document
                    System.out.println("Success! Inserted document id: " + result.getInsertedId());
                }

            }
            catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
}
