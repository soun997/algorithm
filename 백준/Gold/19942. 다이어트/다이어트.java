import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] minNutrients = new int[4];
    static Ingredient[] ingredients;
    static boolean[] visited;
    static int minPrice = Integer.MAX_VALUE;
    static String minVisited = "";

    static class Ingredient {

        int protein;
        int fat;
        int carb;
        int vitamin;
        int price;

        public Ingredient(int protein, int fat, int carb, int vitamin, int price) {
            this.protein = protein;
            this.fat = fat;
            this.carb = carb;
            this.vitamin = vitamin;
            this.price = price;
        }

        static Ingredient of(Ingredient ingredient) {

            return new Ingredient(
                    ingredient.protein,
                    ingredient.fat,
                    ingredient.carb,
                    ingredient.vitamin,
                    ingredient.price);
        }

        Ingredient add(Ingredient ingredient) {

            this.protein += ingredient.protein;
            this.fat += ingredient.fat;
            this.carb += ingredient.carb;
            this.vitamin += ingredient.vitamin;
            this.price += ingredient.price;

            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNutrients[i] = Integer.parseInt(st.nextToken());
        }
        ingredients = new Ingredient[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int protein = Integer.parseInt(st.nextToken());
            int fat = Integer.parseInt(st.nextToken());
            int carb = Integer.parseInt(st.nextToken());
            int vitamin = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            ingredients[i] = new Ingredient(protein, fat, carb, vitamin, price);
        }

        powerSet(0, new Ingredient(0, 0, 0, 0, 0));
        if (minPrice == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(minPrice);
        System.out.println(minVisited);
//        System.out.println("1".compareTo("12"));
    }

    static void powerSet(int idx, Ingredient total) {

        if (idx == N) {
            if (isPossible(total)) {
                String curVisited = "";
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        curVisited += (i + 1);
                        curVisited += " ";
                    }
                }
//                System.out.println(curVisited);
//                System.out.println(minVisited);
                if (total.price == minPrice) {
                    if (curVisited.compareTo(minVisited) < 0) {
                        minVisited = curVisited;
                    }
                } else {
                    minPrice = total.price;
                    minVisited = curVisited;
                }
            }
            return;
        }

        visited[idx] = true;
        powerSet(idx + 1, Ingredient.of(total).add(ingredients[idx]));
        visited[idx] = false;
        powerSet(idx + 1, Ingredient.of(total));
    }

    static boolean isPossible(Ingredient total) {

        return total.protein >= minNutrients[0] &&
                total.fat >= minNutrients[1] &&
                total.carb >= minNutrients[2] &&
                total.vitamin >= minNutrients[3] &&
                total.price <= minPrice;
    }
}