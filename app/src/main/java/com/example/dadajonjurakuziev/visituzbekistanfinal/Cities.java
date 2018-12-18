package com.example.dadajonjurakuziev.visituzbekistanfinal;

public class Cities {
    private String card_bg;
    private String card_title;
    private String city_desc;
    private int id;

    public Cities() {
        //empty constructor needed
    }

    public Cities(String card_bg, String card_title, String city_desc, int id) {
        this.card_bg = card_bg;
        this.card_title = card_title;
        this.city_desc = city_desc;
        this.id = id;
    }

    public String getCard_bg() {
        return card_bg;
    }

    public String getCard_title() {
        return card_title;
    }

    public String getCity_desc() {
        return city_desc;
    }

    public int getId() {
        return id;
    }
}

