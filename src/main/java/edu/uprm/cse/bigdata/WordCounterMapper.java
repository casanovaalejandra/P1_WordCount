package edu.uprm.cse.bigdata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.io.IOException;

public class WordCounterMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //ESta clase lo que deber hacer es leer cada tweet y si
        // aparece la palabra trumhace un key value pair de trump y 1
        //paso 1 converir el json a un string
        String tweet = value.toString();
        String word = null;
        //usando twitter4j se convierte el string jason ( el twitter object) a un Status object
        //y con este puedes seleccionar el texto como un field a leer
        //fuente: https://flanaras.wordpress.com/2016/01/11/twitter4j-status-object-string-json/
        Status status = null;
        try {
            status = TwitterObjectFactory.createStatus(tweet);
            word = status.getText();
            if (word.contains("Trump")) {
                context.write(new Text("Trump"), new IntWritable(1));
            } else if (word.contains("Flu")) {
                context.write(new Text("Flu"), new IntWritable(1));
            } else if (word.contains("Zika")) {
                context.write(new Text("Zika"), new IntWritable(1));
            } else if (word.contains("Diarrhea")) {
                context.write(new Text("Ebola"), new IntWritable(1));
            } else if (word.contains("Headache")) {
                context.write(new Text("Headache"), new IntWritable(1));
            } else if (word.contains("Measles")) {
                context.write(new Text("Measles"), new IntWritable(1));
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
