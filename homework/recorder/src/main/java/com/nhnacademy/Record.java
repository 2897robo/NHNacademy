package com.nhnacademy;

public class Record {
    int game_count;
    int win_count;

    public Record(int game_count, int win_count) {
        this.game_count = game_count;
        this.win_count = win_count;
    }

    public int getGame_count() {
        return game_count;
    }

    public int getWin_count() {
        return win_count;
    }

    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    public void setWin_count(int win_count) {
        this.win_count = win_count;
    }
}
