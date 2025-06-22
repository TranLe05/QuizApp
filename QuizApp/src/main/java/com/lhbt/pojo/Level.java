package com.lhbt.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {
    private int id;
    private String name;

    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
