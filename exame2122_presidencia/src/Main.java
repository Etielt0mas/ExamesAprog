import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static final int WEEKS = 52;
    static final int TYPES_OF_MOVEMENTS = 2;

    public static void main(String[] args) throws FileNotFoundException {
        final String FILENAME = "myAccount2022.txt";
        double[][] arr = readInformationFromFile();
        showExtractByWeek(arr);
    }

    private static void showExtractByWeek(double[][] arr) {
        double sum = 0;
        for (int i = 0; i < WEEKS; i++) {
            if (i == arr[i][0] - 1) {
                sum = arr[i][1];
                for (int j = 0; j < WEEKS && i != j; j++) {
                    if (arr[i][0] == arr[j][0]) {
                        sum += arr[j][1];
                    }
                }
                System.out.printf("Week#%d : %.2f%n", i + 1, sum);
            }
        }
    }

    private static double[][] readInformationFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./myAccount2022.txt"));
        double[][] array = new double[WEEKS][TYPES_OF_MOVEMENTS];
        int i = 0;
        while (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            String[] aux1 = new String[3];
            aux1 = aux.split(";");
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = Double.parseDouble(aux1[j]);
            }
            i++;
        }


        return array;
    }
}