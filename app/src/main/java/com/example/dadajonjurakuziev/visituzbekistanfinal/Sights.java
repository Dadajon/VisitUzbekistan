package com.example.dadajonjurakuziev.visituzbekistanfinal;

public class Sights {
    private int id;
    private String sights_bg, sights_title, sights_desc, sights_details, sights_fullDesc;

    private Sights() {
        //empty constructor needed
    }

    public Sights(int id, String sights_bg, String sights_desc, String sights_details, String sights_fullDesc, String sights_title) {
        this.id = id;
        this.sights_bg = sights_bg;
        this.sights_desc = sights_desc;
        this.sights_details = sights_details;
        this.sights_fullDesc = sights_fullDesc;
        this.sights_title = sights_title;
    }

    public int getId() {
        return id;
    }

    public String getSights_bg() {
        return sights_bg;
    }

    public String getSights_title() {
        return sights_title;
    }

    public String getSights_desc() {
        return sights_desc;
    }

    public String getSights_details() {
        return sights_details;
    }

    public String getSights_fullDesc() {
        return sights_fullDesc;
    }
}
