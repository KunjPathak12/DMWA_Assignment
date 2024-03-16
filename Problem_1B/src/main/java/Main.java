import Impl.ReutReaderImpl;
import Impl.TextExtractorImpl;
import Interfaces.ReutReader;
import Interfaces.TextExtractor;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ReutReader reader = new ReutReaderImpl();
        TextExtractor textExtractor = new TextExtractorImpl();
        SparkSession spark = SparkSession.builder().config("spark.some.config.option","some-value").config("spark.master", "local[*]").getOrCreate();
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        JavaRDD<String> input = jsc.parallelize(textExtractor.mappedData(reader.readFile("reut2-009.sgm")));
        JavaRDD<String> words = input.flatMap(line -> Arrays.asList(line.split("\\s+")).iterator());
        JavaRDD<Map.Entry<String, Integer>> wordCounts = words
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b)
                .map(pair -> new AbstractMap.SimpleEntry<>(pair._1(), pair._2()));

        List<Map.Entry<String, Integer>> result = wordCounts.collect();

        for (Map.Entry<String, Integer> entry : result) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
