import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private final int tamTabuleiro;
    private final int porquinhos;
    private final int galinhas;
    private int numeroSolucoes = 0;

    enum AnimalType {GALINHA, PORQUINHO};

    public Tabuleiro(int porquinhos, int galinhas, int tamTabuleiro) {
        this.porquinhos = porquinhos;
        this.galinhas = galinhas;
        this.tamTabuleiro = tamTabuleiro;
    }

    public void jogar() {

        int[][] tabuleiro = criaTabuleiro();
        int solutions = this.numeroSolucoes;

        if (porquinhos == 0 && galinhas == 0) {
            System.out.println("Nenhum animal inserido");
            return;
        }

        if (galinhas > 0) {
            if (!resolve(tabuleiro, 0, 0, 0, 0, this.porquinhos, this.galinhas, solutions, AnimalType.GALINHA)) {
                System.out.print("nenhuma solução encontrada");
                return;
            }
        } else {
            if (!resolve(tabuleiro, 0, 0, 0, 0, this.porquinhos, this.galinhas, solutions, AnimalType.PORQUINHO)) {
                System.out.print("nenhuma solução encontrada");
                return;
            }
        }

        System.out.println("Solucoes encontradas: " + this.numeroSolucoes);

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
            int numeroPorquinhos, int numeroGalinhas, int solutions,
            AnimalType animalType) {
        if (numeroGalinhas == 0 && numeroPorquinhos == 0) {
            this.numeroSolucoes++;
            solutions++;
            return true;
        }
        int linha = 0;
        int coluna = 0;
        if (animalType == AnimalType.PORQUINHO && numeroPorquinhos > 0) {
            coluna = colunaPorquinho;
            linha = linhaPorquinho;
        } else if (animalType == AnimalType.GALINHA && numeroGalinhas > 0) {
            linha = linhaGalinha;
            coluna = colunaGalinha;
        }

        if (animalType == AnimalType.PORQUINHO) {
            for (int i = linha; i < tamTabuleiro; i++) {
                for (int j = coluna; j < tamTabuleiro; j++) {
                    if (verificaCasa(tabuleiro, i, j,animalType)) {
                        tabuleiro[i][j] = 2;
                        if (numeroGalinhas > 0) {
                            resolve(tabuleiro, i, j, linhaGalinha, colunaGalinha, numeroPorquinhos - 1, numeroGalinhas,
                                    solutions, AnimalType.GALINHA);
                        } else {
                            resolve(tabuleiro, i, j, linhaGalinha, colunaGalinha, numeroPorquinhos - 1, numeroGalinhas,
                                    solutions, AnimalType.PORQUINHO);
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
                                    solutions, AnimalType.PORQUINHO);
                        } else {
                            resolve(tabuleiro, linhaPorquinho, colunaPorquinho, i, j, numeroPorquinhos,
                                    numeroGalinhas - 1,
                                    solutions, AnimalType.GALINHA);
                        }
                        tabuleiro[i][j] = 0;
                    }
                }
            }
        }
        return this.numeroSolucoes > 0;
    }

    private boolean verificaCasa(int[][] tabuleiro, int linha, int col ,AnimalType tipoAnimal) {
        int numeroChecar = 0;
        if (tipoAnimal == AnimalType.GALINHA) {
            numeroChecar = 2;
        }
        if (tipoAnimal == AnimalType.PORQUINHO) {
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

    // private String printaSolucao(int[][] tabuleiro) {
    //     StringBuilder mensagem = new StringBuilder();
    //     for (int i = 0; i < tamTabuleiro; i++) {
    //         for (int j = 0; j < tamTabuleiro; j++)
    //         mensagem.append(" " + tabuleiro[i][j] + " ");
    //         mensagem.append("\n");
    //     }
    //     return mensagem.toString();
    // }

}
