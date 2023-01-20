import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.StandardWatchEventKinds;
import java.util.Scanner;

public class Main {

    static final int WEEKS = 52;
    static final int TYPES_OF_MOVEMENTS = 2;

    public static void main(String[] args) throws FileNotFoundException {
        final String FILENAME = "myAccount2022.txt";
        double[][] arr = readInformationFromFile(FILENAME);
        showExtractByWeek(arr);
        double valorRef = 5.00;
        int weeksLowerValorRef = howManyWeeksWithBalanceLessThanValue(arr, valorRef);
        System.out.println("\nSemanas com saldo inferior ao de referência: " + weeksLowerValorRef);
        double saldoALevantar = 100;
        System.out.println(isItPossibleToWithdrawThisAmount(saldoALevantar, arr));
    }

    private static boolean isItPossibleToWithdrawThisAmount(double saldoALevantar, double[][] arr) {
        double saldo = arr[0][0] - arr[0][1];
        for (int i = 0; i < WEEKS - 1; i++) {
            saldo += arr[i + 1][0];
            saldo -= arr[i][1];
        }
        if (saldo >= saldoALevantar) {
            return true;
        } else {
            return false;
        }
    }

    private static int howManyWeeksWithBalanceLessThanValue(double[][] arr, double valorRef) {
        int semanas = 0;

        for (int i = 0; i < WEEKS; i++) {
            double saldoSemanal = arr[i][0] - arr[i][1];
            if (saldoSemanal < valorRef) {
                semanas++;
            }
        }

        return semanas;
    }

    private static void showExtractByWeek(double[][] arr) {
        double saldo = arr[0][0] - arr[0][1];
        double sum = 0;
        int j = 0;
        for (int i = 0; i < WEEKS - 1; i++) {
            System.out.printf("Week#%d : %.2f€%n", i + 1, saldo);
            saldo += arr[i + 1][0];
            sum += saldo;
            saldo -= arr[i][1];
            j++;

        }
        System.out.printf("Average balance per week : %.2f €", sum / j);
    }

    private static double[][] readInformationFromFile(String filename) throws FileNotFoundException {
        double[][] dados = new double[WEEKS][TYPES_OF_MOVEMENTS];
        double[][] info = new double[WEEKS][TYPES_OF_MOVEMENTS + 1];

        Scanner scanner = new Scanner(new File("./myAccount2022.txt"));
        int i = 0;
        while (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            String[] aux1 = aux.split(";");
            for (int j = 0; j < aux1.length; j++) {
                info[i][j] = Double.parseDouble(aux1[j]);
            }
            i++;
        }

        for (int j = 0; j < WEEKS; j++) {
            for (int k = 0; k < WEEKS; k++) {
                if (info[k][0] == j + 1) {
                    dados[j][0] += info[k][1];
                    dados[j][1] += info[k][2];
                }
            }

        }

        return dados;
    }


}
