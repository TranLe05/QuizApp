package com.lhbt.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {
    private int id;
    private String name;
    private String note;

    public Level(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }
    
    public Level(String name, String note) {
        this.name = name;
        this.note = note;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
