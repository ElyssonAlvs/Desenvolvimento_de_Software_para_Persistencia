public class EstruturaRepeticao {
    public static void main(String[] args) {
        System.out.println("Exemplo de estruturas de repetição:");

        // Usando um loop for para contar de 1 a 10
        System.out.println("Contagem com for:");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Usando um loop while para contar de 10 a 1
        System.out.println("Contagem com while:");
        int j = 10;
        while (j >= 1) {
            System.out.print(j + " ");
            j--;
        }
        System.out.println();

        // Usando um loop do-while para contar de 1 a 5
        System.out.println("Contagem com do-while:");
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 5);
        System.out.println();
    }
}
