import java.util.*;

class FoodRatings {
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, PriorityQueue<Food>> cuisineToPQ;

    private static class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToPQ = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToPQ.putIfAbsent(cuisine, new PriorityQueue<>((a, b) -> {
                if (b.rating != a.rating) return b.rating - a.rating;
                return a.name.compareTo(b.name);
            }));

            cuisineToPQ.get(cuisine).offer(new Food(food, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        foodToRating.put(food, newRating);
        cuisineToPQ.get(cuisine).offer(new Food(food, newRating));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineToPQ.get(cuisine);

        while (true) {
            Food top = pq.peek();
            if (top.rating == foodToRating.get(top.name)) {
                return top.name;
            }
            pq.poll(); // lazy removal of outdated entries
        }
    }
}