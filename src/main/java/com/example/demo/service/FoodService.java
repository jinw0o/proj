package com.example.demo.service;

import com.example.demo.dto.FoodDto;
import com.example.demo.entity.Food;
import com.example.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service // 클래서 선언
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestTemplate restTemplate;

    //@Autowired
    public FoodService(FoodRepository foodRepository,
                       RestTemplate restTemplate) {
        this.foodRepository = foodRepository;
        this.restTemplate = restTemplate;
    }

    public void addFoodFromExternalApi(String foodName) {
        String apiUrl = "http://13.236.178.37:8080/recipes/search/byFoodName?foodName=" + foodName;
        // API 요청을 보내고 응답을 받음
        FoodDto foodDto = restTemplate.getForObject(apiUrl, FoodDto.class);

        Food food = new Food();
        food.setName(foodDto.getFoodName());

        foodRepository.save(food);
    }

    public void addFood(String name, int count) {
        Food food = new Food(name, count);
        foodRepository.save(food); // 데이터베이스에 추가
    }

    // 상위 10개의 음식 목록을 반환
    public List<Food> getTop10Foods() {
        return foodRepository.findTop10ByOrderByCountDesc();
    }

    // 음식의 추천수를 1 증가
    public Food incrementFoodCount(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid food Id:" + id));
        food.setCount(food.getCount() + 1);
        return foodRepository.save(food);
    }

    /*public void addFoodFromExternalApi(String foodName) {
        String apiUrl = "http://13.236.178.37:8080/recipes/search/byFoodName?foodName=" + foodName;
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

        if (response != null && response.containsKey("id") && response.containsKey("foodName")) {
            String name = (String) response.get("foodName");
            addFood(name, 0);  // count 초기값은 0으로 설정
        }
    }*/
}
