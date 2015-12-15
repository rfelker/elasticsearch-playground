package de.codecentric.elastic;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class ImportDataGenerator {
    public static void main(String[] args) throws IOException, ParseException {

        String jsonGeneratorOutput = "json-generator-output.json";
        String importDataElasticsearch = "import-data-elasticsearch.json";

        if (args != null) {
            if (args.length == 2) {
                jsonGeneratorOutput = args[0];
                importDataElasticsearch = args[1];

            } else if (args.length == 1) {
                importDataElasticsearch = args[1];
            }
        }

        ImportDataGenerator app = new ImportDataGenerator();
        app.createElasticsearchDataFile(jsonGeneratorOutput, importDataElasticsearch);

    }

    private void createElasticsearchDataFile(String jsonGeneratorOutput, String importDataElasticsearch) throws IOException, ParseException {

        File inputFile = new File(jsonGeneratorOutput);
        File outputFile = new File(importDataElasticsearch);

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {

            JSONParser parser = new JSONParser();
            JSONObject parseData = (JSONObject) parser.parse(reader);

            JSONArray indices = (JSONArray) parseData.get("indices");
            JSONArray data = (JSONArray) parseData.get("data");

            System.out.println(String.format("reading from file %s...", inputFile.getName()));
            System.out.println(String.format("writing %d data sets to file %s...", indices.size(), outputFile.getName()));

            Iterator indizesIterator = indices.iterator();
            Iterator dataIterator = data.iterator();

            while (indizesIterator.hasNext() && dataIterator.hasNext()) {

                JSONObject index = (JSONObject) indizesIterator.next();
                JSONObject object = (JSONObject) dataIterator.next();

                writer.write(index.toString());
                writer.newLine();
                writer.write(object.toString());
                writer.newLine();
            }

            System.out.println("done!");
        }

    }
}