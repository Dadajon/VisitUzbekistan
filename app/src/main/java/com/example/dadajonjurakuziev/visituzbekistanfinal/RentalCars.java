package com.example.dadajonjurakuziev.visituzbekistanfinal;

public class RentalCars {
    private String rentalcar_image, rentalcar_title, key, car_type, car_type_desc, car_type_details;

    public RentalCars(){
        //empty constructor needed
    }


    public RentalCars(String car_type, String car_type_desc, String car_type_details, String key, String rentalcar_image, String rentalcar_title) {
        this.rentalcar_image = rentalcar_image;
        this.rentalcar_title = rentalcar_title;
        this.key = key;
        this.car_type = car_type;
        this.car_type_desc = car_type_desc;
        this.car_type_details = car_type_details;
    }

    public String getRentalcar_image() {
        return rentalcar_image;
    }

    public String getRentalcar_title() {
        return rentalcar_title;
    }

    public String getKey() {
        return key;
    }

    public String getCar_type() {
        return car_type;
    }

    public String getCar_type_desc() {
        return car_type_desc;
    }

    public String getCar_type_details() {
        return car_type_details;
    }
}
