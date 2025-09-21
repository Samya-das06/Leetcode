class MovieRentingSystem {

    private Map<Integer, TreeSet<Movie>> available;
    private Map<String, Movie> movieMap;
    private TreeSet<Movie> rented;

    static class Movie {
        int shop;
        int movie;
        int price;

        Movie(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }

        String key() {
            return shop + "_" + movie;
        }
    }

    Comparator<Movie> movieComparator = (a, b) -> {
        if (a.price != b.price) return Integer.compare(a.price, b.price);
        if (a.shop != b.shop) return Integer.compare(a.shop, b.shop);
        return Integer.compare(a.movie, b.movie);
    };

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        movieMap = new HashMap<>();
        rented = new TreeSet<>(movieComparator);

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            Movie m = new Movie(shop, movie, price);
            movieMap.put(m.key(), m);
            available.computeIfAbsent(movie, x -> new TreeSet<>(movieComparator)).add(m);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        if (available.containsKey(movie)) {
            Iterator<Movie> it = available.get(movie).iterator();
            int count = 0;
            while (it.hasNext() && count < 5) {
                result.add(it.next().shop);
                count++;
            }
        }
        return result;
    }

    public void rent(int shop, int movie) {
        String key = shop + "_" + movie;
        Movie m = movieMap.get(key);
        available.get(movie).remove(m);
        rented.add(m);
    }

    public void drop(int shop, int movie) {
        String key = shop + "_" + movie;
        Movie m = movieMap.get(key);
        rented.remove(m);
        available.get(movie).add(m);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        Iterator<Movie> it = rented.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            Movie m = it.next();
            result.add(Arrays.asList(m.shop, m.movie));
            count++;
        }
        return result;
    }
}