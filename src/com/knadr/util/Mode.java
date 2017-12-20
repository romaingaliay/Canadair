package com.knadr.util;

public enum Mode {
    CLASSIC("Classic mode"),
    ADVENTURE("Adventure mode"),
    ANY("Any");

    private String description;

    Mode(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
