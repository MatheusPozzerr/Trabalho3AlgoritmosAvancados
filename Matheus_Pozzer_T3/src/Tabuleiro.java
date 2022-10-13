import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private final int tamTabuleiro;
    private final int porquinhos;
    private final int galinhas;
    private final int numeroGalinha = 1;
    private final int numeroPorco = 2;

    public Tabuleiro(int porquinhos, int galinhas, int tamTabuleiro) {
        this.porquinhos = porquinhos;
        this.galinhas = galinhas;
        this.tamTabuleiro = tamTabuleiro;
    }

    public void solve() {

        int[][] tabuleiro = criaTabuleiro();
        List<String> solutions = new ArrayList<>();

        if (!solve(tabuleiro, 0, this.porquinhos, this.galinhas, solutions, "Galinha")) {
            System.out.print("nenhuma solução encontrada");
            return;
        }
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("solução: " + i + "\n");
            System.out.println(solutions.get(i));
            System.out.println();
        }
    }

    private int[][] criaTabuleiro() {
        int[][] tabuleiro = new int[tamTabuleiro][tamTabuleiro];
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiro[i][j] = 0;
            }
        }
        return tabuleiro;
    }

    private boolean solve(int[][] tabuleiro, int col, int numeroPorquinhos, int numeroGalinhas, List<String> solutions,
            String animalType) {
        if (numeroGalinhas == 0 && numeroPorquinhos == 0) {
            solutions.add(printSolution(tabuleiro));
            return true;
        }
        int numeroChecador = 0 ;
        if( animalType.equals("Galinha") ){
            numeroChecador = 2;
        }
        for (int i = 0; i < tamTabuleiro; i++) {
            if (verificaCasa(tabuleiro, i, col, numeroChecador)) {
                tabuleiro[i][col] = 2;
                solve(tabuleiro, col + 1, numeroPorquinhos - 1, numeroGalinhas, solutions, "Galinha");
                tabuleiro[i][col] = 0;
            }
        }
        return solutions.size() > 0;
    }

    private boolean verificaCasa(int[][] tabuleiro, int linha, int col, int numeroChecar) {
        int i, j;

        if(tabuleiro[linha][col] != 0 )
            return false;

        for (i = 0; i < col; i++)
            if (tabuleiro[linha][i] == numeroChecar)
                return false;

        for (i = linha, j = col; i >= 0 && j >= 0; i--, j--)
            if (tabuleiro[i][j] == numeroChecar)
                return false;

        for (i = linha, j = col; j >= 0 && i < tamTabuleiro; i++, j--)
            if (tabuleiro[i][j] == numeroChecar)
                return false;

        for (i = linha, j = col; i < tamTabuleiro && j < tamTabuleiro; i++, j++)
            if (tabuleiro[i][j] == numeroChecar)
                return false;

        for (i = linha, j = col; j < tamTabuleiro && i <= 0; i--, j++)
            if (tabuleiro[i][j] == numeroChecar)
                return false;

        return true;
    }

    private String printSolution(int[][] tabuleiro) {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < tamTabuleiro; i++) {
            for (int j = 0; j < tamTabuleiro; j++)
                msg.append(" " + tabuleiro[i][j] + " ");
            msg.append("\n");
        }
        return msg.toString();
    }

}
