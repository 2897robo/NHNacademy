package com.nhnacademy.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCommonsCsvMovieParser implements MovieParser {
    BufferedReader br = new BufferedReader(new InputStreamReader(getMovieFileAsStream()));
    List<Movie> moiveList = new ArrayList<>();

    @Override
    public List<Movie> parse() throws IOException {
        CSVParser parser = CSVParser.parse(br, CSVFormat.EXCEL);

        List<CSVRecord> csvRecordList = parser.getRecords();
        
        for(int record_i=1; record_i<csvRecordList.size(); record_i++){
            CSVRecord csvRecord = csvRecordList.get(record_i);
        
            long movieId = Long.parseLong(csvRecord.get(0));
            String title = csvRecord.get(1);
            Set<String> genres =  Arrays.stream(csvRecord.get(2).split("\\|")).collect(Collectors.toSet());
            moiveList.add(new Movie(movieId, title, genres));
        }

        return moiveList;
    }
}