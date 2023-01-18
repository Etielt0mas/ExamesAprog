import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static final int MAX_MESES = 6;
    static final int COLUNAS = 4;

    public static void main(String[] args) throws FileNotFoundException {
        String[] meses = new String[MAX_MESES];
        int[][] jogos = new int[MAX_MESES][COLUNAS];

        readInformationFromFile(jogos, meses);
        String mostVictoriousMonth = getTheMostVictoriousMonth(jogos, meses);
        System.out.println("O mês mais vitorioso foi "+mostVictoriousMonth+".\n");
        double[] average = getAverages(jogos);

        writeStatistic(average, jogos, meses);
    }

    private static void writeStatistic(double[] average, int[][] jogos, String[] meses) {
        System.out.printf("Meses com mais de %.1f jogos%n", average[0]);
        for (int i = 0; i < meses.length; i++) {
            if(jogos[i][0] > average[0]){
                System.out.printf("%s : %d%n", meses[i], jogos[i][0]);
            }
        }
    }

    private static double[] getAverages(int[][] jogos) {
        double[] average = new double[COLUNAS];
        int sum;
        for (int i = 0; i < average.length; i++) {
            sum = 0;
            for (int j = 0; j < jogos.length; j++) {
                sum = sum + jogos[j][i];
            }
            average[i] = (double) sum/jogos.length;
        }
        return average;
    }

    private static String getTheMostVictoriousMonth(int[][] jogos, String[] meses) {
        int max = -1;
        int id = -1;
        for (int i = 0; i < meses.length; i++) {
            if (max < jogos[i][0]) {
                max = jogos[i][0];
            }
        }

        for (int i = 0; i < meses.length; i++) {
            if(jogos[i][0] == max){
                return meses[i];
            }
        }
        return "";
    }

    private static void readInformationFromFile(int[][] jogos, String[] meses) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./xadrez1a6.txt"));
        for (int i = 0; i < MAX_MESES; i++) {
            String aux = scanner.nextLine();
            String[] aux1 = aux.split(",");

            meses[i] = aux1[0];
            for (int j = 1; j <= COLUNAS; j++) {
                jogos[i][j-1] = Integer.parseInt(aux1[j]);
            }
        }
    }


}
