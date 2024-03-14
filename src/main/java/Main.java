import Interfaces.ReutReader;
import Interfaces.TextExtractor;
import impl.ReutReaderImpl;
import impl.TextExtractorImpl;

public class Main {
    public static void main(String[] args) {
        ReutReader reader = new ReutReaderImpl();
        TextExtractor textExtractor = new TextExtractorImpl();
        String fileName = "reut2-009.sgm";
        String file = reader.readFile(fileName);
        System.out.println(textExtractor.mappedData(file).toString());
//        System.out.println(textExtractor.mappedData(file));
    }
}
