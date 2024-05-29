package com.example.demo.dto;

public class FoodDto {

    private Long id;
    private String foodName;
    private String image;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    // 생성자, getter, setter 등 필요한 메서드 생략
}