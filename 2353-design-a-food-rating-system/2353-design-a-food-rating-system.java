class FoodRatings {
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, TreeSet<String>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            foodToCuisine.put(foods[i], cuisines[i]);
            foodToRating.put(foods[i], ratings[i]);

            cuisineToFoods.putIfAbsent(cuisines[i], new TreeSet<>((a, b) -> {
                int ratingDiff = foodToRating.get(b) - foodToRating.get(a);
                if (ratingDiff != 0) return ratingDiff;
                return a.compareTo(b);
            }));

            cuisineToFoods.get(cuisines[i]).add(foods[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> set = cuisineToFoods.get(cuisine);

        set.remove(food);
        foodToRating.put(food, newRating);
        set.add(food);
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first();
    }
}