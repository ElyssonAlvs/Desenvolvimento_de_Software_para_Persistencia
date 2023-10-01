import java.util.Scanner;

public class ControleFluxo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite sua idade: ");
        int idade = input.nextInt();

        if (idade >= 18) {
            System.out.println("Você é maior de idade.");

            System.out.print("Em qual dia da semana você estuda (1 a 7, onde 1 é domingo): ");
            int diaSemana = input.nextInt();

            switch (diaSemana) {
                case 1:
                    System.out.println("Você estuda no domingo.");
                    break;
                case 2:
                    System.out.println("Você estuda na segunda-feira.");
                    break;
                case 3:
                    System.out.println("Você estuda na terça-feira.");
                    break;
                case 4:
                    System.out.println("Você estuda na quarta-feira.");
                    break;
                case 5:
                    System.out.println("Você estuda na quinta-feira.");
                    break;
                case 6:
                    System.out.println("Você estuda na sexta-feira.");
                    break;
                case 7:
                    System.out.println("Você estuda no sábado.");
                    break;
                default:
                    System.out.println("Dia da semana inválido.");
            }
        } else {
            System.out.println("Você é menor de idade.");
        }

        input.close();
    }
}
