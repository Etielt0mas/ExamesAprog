import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String[] musics = {"On my way", "Memories", "Perfect", "Havana"};
        double[][] info = {{201.6, 3.25},
                {201.6, 2.80},
                {256.8, 2.75},
                {202.8, 2.35}
        };

        String mostExpensiveMusic = findMostExpensiveMusic(musics, info);
        sort(musics, info);
        double diff = calculateBiggestDifference(info);
        writeToFile(musics, info);
        System.out.println("Música mais cara: " + mostExpensiveMusic);
        System.out.printf("Maior diferença: %.2f", diff);
    }

    private static void sort(String[] musics, double[][] info) {
        for (int i = 0; i < musics.length; i++) {
            for (int j = 0; j < musics.length - 1; j++) {
                if (info[j + 1][0] < info[j][0]) {
                    double temp = info[j + 1][0];
                    info[j + 1][0] = info[j][0];
                    info[j][0] = temp;

                    double temp2 = info[j + 1][1];
                    info[j + 1][1] = info[j][1];
                    info[j][1] = temp2;

                    String tempS = musics[j + 1];
                    musics[j + 1] = musics[j];
                    musics[j] = tempS;
                } else if (info[j + 1][0] == info[j][0]) {
                    if (musics[j + 1].compareTo(musics[j]) < 0){
                        double temp = info[j + 1][0];
                        info[j + 1][0] = info[j][0];
                        info[j][0] = temp;

                        double temp2 = info[j + 1][1];
                        info[j + 1][1] = info[j][1];
                        info[j][1] = temp2;

                        String tempS = musics[j + 1];
                        musics[j + 1] = musics[j];
                        musics[j] = tempS;
                    }
                }
            }
        }
    }

    private static void writeToFile(String[] musics, double[][] info) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("./musics.txt");

        for (int i = 0; i < musics.length; i++) {
            printWriter.printf("%s / %.2fs / %.2f€%n", musics[i], info[i][0], info[i][1]);
        }
        printWriter.close();
    }

    private static double calculateBiggestDifference(double[][] info) {
        double min = info[1][0];
        double max = -1;
        for (int i = 0; i < info.length; i++) {
            if (info[i][0] > max) {
                max = info[i][0];
            }
            if (info[i][0] < min) {
                min = info[i][0];
            }
        }

        return max - min;
    }

    private static String findMostExpensiveMusic(String[] musics, double[][] info) {
        int id = -1;
        double max = -1;
        for (int i = 0; i < info.length; i++) {
            if (info[i][1] > max) {
                max = info[i][1];
                id = i;
            }
        }

        return musics[id];
    }
}
