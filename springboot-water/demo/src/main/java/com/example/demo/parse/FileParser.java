package com.example.demo.parse;

import java.util.List;

public interface FileParser {
    List<User> parseUsers();
    List<Tariff> parseTariffs();
}