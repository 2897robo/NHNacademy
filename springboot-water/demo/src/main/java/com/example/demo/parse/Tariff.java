package com.example.demo.parse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tariff {
    private int id;
    private String city;
    private String sector;
    private int level;
    private int start;
    private int end;
    private int sectionPrice;
    private String levelPrice;

    public Tariff(int id, String city, String sector, int level, int start, int end, int sectionPrice, String levelPrice) {
        this.id = id;
        this.city = city;
        this.sector = sector;
        this.level = level;
        this.start = start;
        this.end = end;
        this.sectionPrice = sectionPrice;
        this.levelPrice = levelPrice;
    }
}