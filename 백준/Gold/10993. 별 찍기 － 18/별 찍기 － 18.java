import java.util.Scanner;

public class Main {

    static int[][] stars;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int width = getWidth(n);
        int height = getHeight(n);
        //System.out.println(width);
        //System.out.println(height);
        stars = new int[height][width];
        if (n % 2 == 1)
            draw(n, 0, height - 1);
        else
            draw(n, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < width; j++) {
                if (stars[i][j] == 1) line.append("*");
                else line.append(" ");
            }
            sb.append(line.toString().stripTrailing());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int getWidth(int n){
        if (n == 1) return 1;
        int w = getWidth(n - 1);
        return (w + 1) * 2 + 1;
    }

    static int getHeight(int n){
        if (n == 1) return 1;
        int h = getHeight(n - 1);
        return (h + 1) + h;
    }

    static void draw(int n, int wStart, int hStart){
        if (n == 1){
            //System.out.println(wStart + " " + hStart);
            stars[hStart][wStart] = 1;
            return;
        }
        int width = getWidth(n);
        int height = getHeight(n);
        int cnt1 = wStart + 1;
        int cnt2 = 1;
        if (n % 2 == 1){
            //System.out.println(height);
            for (int i = hStart; i >= hStart - height + 1; i--){
                if (i == hStart){
                    for (int j = wStart; j < wStart + width; j++){
                        stars[i][j] = 1;
                    }
                }
                else if (i == hStart - height){
                    stars[i][wStart + width / 2] = 1;
                }
                else {
                    stars[i][cnt1] = 1;
                    stars[i][(wStart + width - 1) - cnt2] = 1;
                    cnt1++;
                    cnt2++;
                }
            }
        }
        else {
            for (int i = hStart; i < hStart + height; i++){
                if (i == hStart){
                    for (int j = wStart; j < wStart + width; j++){
                        stars[i][j] = 1;
                    }
                }
                else if (i == hStart + height){
                    stars[i][wStart + width / 2] = 1;
                }
                else {
                    stars[i][cnt1] = 1;
                    stars[i][(wStart + width - 1) - cnt2] = 1;
                    cnt1++;
                    cnt2++;
                }
            }
        }
        //System.out.println(hStart + " " + height);
        if ((n - 1) % 2 == 1)
            draw(n - 1, wStart + width / 4 + 1, hStart + height / 2);
        else
            draw(n - 1, wStart + width / 4 + 1,  (hStart - height + 1) + height / 2);
    }
}
