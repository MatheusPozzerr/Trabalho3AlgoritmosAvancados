import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        int quantidadeLinhas = Integer.parseInt(args[0]);
        int porquinhos = Integer.parseInt(args[1]);
        int galinhas = Integer.parseInt(args[2]);
        SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss,SSS");
        long start = System.currentTimeMillis();
        Tabuleiro tabuleiro = new Tabuleiro(porquinhos, galinhas, quantidadeLinhas);
        tabuleiro.jogar();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("o metodo executou em " + t.format(elapsed));
    }
}
