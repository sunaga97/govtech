package com.example.demo.helper;

import com.example.demo.exception.CSVFormatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "name", "salary" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<User> csvToUsers(InputStream is) throws CSVFormatException{
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader());) {

            List<User> users = new ArrayList<User>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                if(csvRecord.size() != 2) {
                    throw new CSVFormatException("CSV Format is incorrect. Please check number of columns of header");
                }
                User user = new User(
                        csvRecord.get("name"),
                        Float.parseFloat(csvRecord.get("salary"))
                );

                users.add(user);
            }

            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
