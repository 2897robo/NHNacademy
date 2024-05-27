package com.example.demo.parse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "app.data-format", havingValue = "csv")
public class CsvFileParser implements FileParser {

    @Value("${app.account-csv-file-path}")
    private String accountCsvFilePath;

    @Value("${app.tariff-csv-file-path}")
    private String tariffCsvFilePath;

    @Override
    public List<User> parseUsers() {
        List<User> users = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(accountCsvFilePath));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    int id = Integer.parseInt(fields[0].trim());
                    int password = Integer.parseInt(fields[1].trim());
                    String name = fields[2].trim();
                    users.add(new User(id, password, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public List<Tariff> parseTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(tariffCsvFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 8) {
                    int serialNumber = Integer.parseInt(fields[0].trim());
                    String city = fields[1].trim();
                    String industry = fields[2].trim();
                    int stage = Integer.parseInt(fields[3].trim());
                    int startRange = Integer.parseInt(fields[4].trim());
                    int endRange = Integer.parseInt(fields[5].trim());
                    int amount = Integer.parseInt(fields[6].trim());
                    String baseFee = fields[7].trim();
                    tariffs.add(new Tariff(serialNumber, city, industry, stage, startRange, endRange, amount, baseFee));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tariffs;
    }
}