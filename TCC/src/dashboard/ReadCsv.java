/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marcelocogo
 */
public class ReadCsv {

    public class Record {
        //Assume each record have 6 elements, all String

        private SimpleStringProperty f1, f2, f3, f4, f5, f6;

        public String getF1() {
            return f1.get();
        }

        public String getF2() {
            return f2.get();
        }

        public String getF3() {
            return f3.get();
        }

        public String getF4() {
            return f4.get();
        }

        public String getF5() {
            return f5.get();
        }

        public String getF6() {
            return f6.get();
        }

        Record(String f1, String f2, String f3, String f4,
                String f5, String f6) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
        }

    }

    private ObservableList readCSV() {

        String CsvFile = "/Users/marcelocogo/Desktop/teste.csv";
        String FieldDelimiter = ";";

        BufferedReader br;

        File folder = new File("/Users/marcelocogo/Desktop/professores");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);

                Record record = new Record(fields[0], fields[1], fields[2],
                        fields[3], fields[4], fields[5]);

                 dataList.add(record);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadCsv.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadCsv.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return dataList;

    }
    
        private final ObservableList<ReadCsv.Record> dataList
            = FXCollections.observableArrayList();

}
