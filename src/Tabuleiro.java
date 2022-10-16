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

    public void jogar() {

        int[][] tabuleiro = criaTabuleiro();
        List<String> solutions = new ArrayList<>();

        if (porquinhos == 0 && galinhas == 0) {
            System.out.println("Nenhum animal inserido");
            return;
        }

        if (galinhas > 0) {
            if (!resolve(tabuleiro, 0, 0, 0, 0, this.porquinhos, this.galinhas, solutions, "Galinha")) {
                System.out.print("nenhuma solução encontrada");
                return;
            }
        } else {
            if (!resolve(tabuleiro, 0, 0, 0, 0, this.porquinhos, this.galinhas, solutions, "Porquinho")) {
                System.out.print("nenhuma solução encontrada");
                return;
            }
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

    private boolean resolve(int[][] tabuleiro, int linhaPorquinho, int colunaPorquinho, int linhaGalinha,
            int colunaGalinha,
            int numeroPorquinhos, int numeroGalinhas, List<String> solutions,
            String animalType) {
        if (numeroGalinhas == 0 && numeroPorquinhos == 0) {
            solutions.add(printaSolucao(tabuleiro));
            return true;
        }
        int linha = 0;
        int coluna = 0;
        int numeroChecador = 0;
        if (animalType.equals("Porquinho") && numeroPorquinhos > 0) {
            coluna = colunaPorquinho;
            linha = linhaPorquinho;
        } else if (animalType.equals("Galinha") && numeroGalinhas > 0) {
            linha = linhaGalinha;
            coluna = colunaGalinha;
        }

        if (animalType.equals("Porquinho")) {
            for (int i = linha; i < tamTabuleiro; i++) {
                for (int j = coluna; j < tamTabuleiro; j++) {
                    if (verificaCasa(tabuleiro, i, j,animalType)) {
                        tabuleiro[i][j] = 2;
                        if (numeroGalinhas > 0) {
                            resolve(tabuleiro, i, j, linhaGalinha, colunaGalinha, numeroPorquinhos - 1, numeroGalinhas,
                                    solutions, "Galinha");
                        } else {
                            resolve(tabuleiro, i, j, linhaGalinha, colunaGalinha, numeroPorquinhos - 1, numeroGalinhas,
                                    solutions, "Porquinho");
                        }
                        tabuleiro[i][j] = 0;
                    }
                }
            }
        } else {
            for (int i = linha; i < tamTabuleiro; i++) {
                for (int j = coluna; j < tamTabuleiro; j++) {
                    if (verificaCasa(tabuleiro, i, j, animalType)) {
                        tabuleiro[i][j] = 1;
                        if (numeroGalinhas > 0) {
                            resolve(tabuleiro, linhaPorquinho, colunaPorquinho, i, j, numeroPorquinhos,
                                    numeroGalinhas - 1,
                                    solutions, "Porquinho");
                        } else {
                            resolve(tabuleiro, linhaPorquinho, colunaPorquinho, i, j, numeroPorquinhos,
                                    numeroGalinhas - 1,
                                    solutions, "Galinha");
                        }
                        tabuleiro[i][j] = 0;
                    }
                }
            }
        }
        return solutions.size() > 0;
    }

    private boolean verificaCasa(int[][] tabuleiro, int linha, int col ,String tipoAnimal) {
        int numeroChecar = 0;
        if (tipoAnimal.equals("Galinha")) {
            numeroChecar = 2;
        }
        if (tipoAnimal.equals("Porquinho")) {
            numeroChecar = 1;
        }
        int i, j;

        if (tabuleiro[linha][col] != 0)
            return false;

        for (i = 0; i < tamTabuleiro; i++)
            if (tabuleiro[linha][i] == numeroChecar)
                return false;

        for (i = 0; i < tamTabuleiro; i++)
            if (tabuleiro[i][col] == numeroChecar)
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

        for (i = linha, j = col; j < tamTabuleiro && i >= 0; i--, j++)
            if (tabuleiro[i][j] == numeroChecar)
                return false;

        return true;
    }

    private String printaSolucao(int[][] tabuleiro) {
        StringBuilder mensagem = new StringBuilder();
        for (int i = 0; i < tamTabuleiro; i++) {
            for (int j = 0; j < tamTabuleiro; j++)
            mensagem.append(" " + tabuleiro[i][j] + " ");
            mensagem.append("\n");
        }
        return mensagem.toString();
    }

}
