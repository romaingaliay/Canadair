package com.knadr.util;

public enum Language {
    FR("fr"),
    EN("en");

    private String language;

    Language(String language) { this.language = language; }

    @Override
    public String toString() { return this.language; }
}
