import java.util.Scanner;

public class ManipulacaoExcecoes {
    public static void main(String[] args) throws NumberFormatException {
        try (Scanner scanner = new Scanner(System.in)) {
            try{
                System.out.println("Digite um número para a soma");
                var valor1 = Integer.parseInt(scanner.nextLine());

                System.out.println("Digite outro valor para a soma");
                var valor2 = Integer.parseInt(scanner.nextLine());

                var valor = valor1 + valor2;
                System.out.println("A soma dos  números é: " + valor);
            }catch (NumberFormatException e){
                System.out.println("Erro : " + e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
