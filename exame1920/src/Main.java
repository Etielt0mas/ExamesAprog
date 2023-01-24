import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static final int FILAS = 24;

    static final int COLUNAS = 30;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] lugares;

        final String FILENAME = "ocupacaoSala2020-01-14.txt";

        lugares = lerOcupacaoDaSala(FILENAME);
        double money = calcularBilheteira(lugares);
        System.out.println("Dinheiro: "+money);
        final int qntLugares = 4;
        int fila = informarLugares(lugares, qntLugares);
        System.out.println("Fila: " +fila);
    }

    private static int informarLugares(int[][] lugares, int qntLugares) {
        for (int i = FILAS-1; i >= 0; i--) {
            for (int j = 0; j < COLUNAS-3; j++) {
                if(lugares[i][j] == 0 && lugares[i][j+1] == 0 && lugares[i][j+2] == 0 && lugares[i][j+3] == 0){
                    return i+1;
                }
            }
        }
        return -1;
    }

    private static double calcularBilheteira(int[][] lugares) {
        final double p1 = 20;
        final double p2 = 15;
        final double p3 = 10;
        double money = 0;

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                if (lugares[i][j] == 1) {
                    if (i < 8) {
                        money += p1;
                    } else if (i >= 8 && i < 15) {
                        money += p2;
                    } else {
                        money += p3;
                    }
                }
            }
        }

        return money;
    }

    public static int[][] lerOcupacaoDaSala(String filename) throws FileNotFoundException {
        int[][] lugares = new int[FILAS][COLUNAS];
        int[][] auxi = new int[FILAS][COLUNAS + 1];
        int i = 0;
        Scanner scanner = new Scanner(new File("./" + filename));

        while (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            String[] auxiliar = aux.split(";");

            for (int j = 0; j < auxiliar.length; j++) {
                auxi[i][j] = Integer.parseInt(auxiliar[j]);
            }

            i++;
        }

        for (int j = 0; j < FILAS; j++) {
            for (int k = 0; k < FILAS; k++) {
                if (auxi[j][0] == auxi[k][0]) {
                    for (int l = 0; l < COLUNAS-1 && auxi[j][0] != 0; l++) {
                        lugares[auxi[j][0] - 1][l] = auxi[k][l+1];
                    }
                }
            }
        }

        return lugares;
    }
}