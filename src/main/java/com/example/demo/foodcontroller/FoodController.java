package com.example.demo.foodcontroller;

import com.example.demo.entity.Food;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired // GET
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }



    // 상위 10개의 음식 목록을 반환
    @GetMapping("/top10") // POST
    public List<Food> getTop10Foods() {
        return foodService.getTop10Foods();
    }

    // 음식의 추천수를 1 증가
    @PostMapping("/{id}/increment") // 특정음식의 추천수를 1증가
    public Food incrementFoodCount(@PathVariable Long id) {
        return foodService.incrementFoodCount(id);
    }

    @PostMapping("/addFromApi")
    public ResponseEntity<String> addFoodFromApi(@RequestParam String foodName) {
        foodService.addFoodFromExternalApi(foodName);
        return ResponseEntity.ok("Food data added from external API successfully");
    }


}
