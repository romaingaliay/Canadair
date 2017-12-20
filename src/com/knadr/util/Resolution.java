package com.knadr.util;

public enum Resolution {
    WQHD("2560*1440"),
    FULLHD("1920*1080"),
    AHD("1600*900"),
    HD("1280*720");

    private String resolution;

    Resolution(String resolution) { this.resolution = resolution; }

    @Override
    public String toString() { return this.resolution; }
}
