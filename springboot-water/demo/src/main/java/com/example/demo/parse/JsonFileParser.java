package com.example.demo.parse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "app.data-format", havingValue = "json")
public class JsonFileParser implements FileParser {

    @Value("${app.account-json-file-path}")
    private String accountJsonFilePath;

    @Value("${app.tariff-json-file-path}")
    private String tariffJsonFilePath;

    @Override
    public List<User> parseUsers() {
        // JSON 파일 파싱 로직 구현
        return null;
    }

    @Override
    public List<Tariff> parseTariffs() {
        // JSON 파일 파싱 로직 구현
        return null;
    }
}