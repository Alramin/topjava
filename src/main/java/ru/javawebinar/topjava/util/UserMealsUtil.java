package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {



        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

     //   .toLocalDate();
      //  .toLocalTime();


        List<UserMealWithExceed>  list =  getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(list.toString());


    }



    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumOfCaloriesPerDate = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            sumOfCaloriesPerDate.put(mealDate, sumOfCaloriesPerDate.getOrDefault(mealDate, 0) + meal.getCalories());
        }

        List<UserMealWithExceed> userMealExceeded = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                userMealExceeded.add(new UserMealWithExceed(dateTime, meal.getDescription(), meal.getCalories(),
                        sumOfCaloriesPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
        return userMealExceeded;
    }

}
