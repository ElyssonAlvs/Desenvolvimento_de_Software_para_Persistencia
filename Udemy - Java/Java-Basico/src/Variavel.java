public class Variavel {
    public static void main(String[] args) {
        // Variáveis por valor
        String nomeEstudante;
        int idade = 19;
        double salario = 1500.00;

        nomeEstudante = "Elysson";

        System.out.println("O nome do(a) estudante é : "
                + nomeEstudante + " e ele(a) tem " + idade +
                " e recebe " + salario + " reais");

        System.out.println("O tamanho dos Tipos Primitivos em Java :");
        System.out.println("---------------------------------------------------");

        System.out.println("Tipo byte:");
        System.out.println("Mínimo: " + Byte.MIN_VALUE);
        System.out.println("Máximo: " + Byte.MAX_VALUE);

        System.out.println("\nTipo short:");
        System.out.println("Mínimo: " + Short.MIN_VALUE);
        System.out.println("Máximo: " + Short.MAX_VALUE);

        System.out.println("\nTipo int:");
        System.out.println("Mínimo: " + Integer.MIN_VALUE);
        System.out.println("Máximo: " + Integer.MAX_VALUE);

        System.out.println("\nTipo long:");
        System.out.println("Mínimo: " + Long.MIN_VALUE);
        System.out.println("Máximo: " + Long.MAX_VALUE);

        System.out.println("\nTipo float:");
        System.out.println("Mínimo: " + Float.MIN_VALUE);
        System.out.println("Máximo: " + Float.MAX_VALUE);

        System.out.println("\nTipo double:");
        System.out.println("Mínimo: " + Double.MIN_VALUE);
        System.out.println("Máximo: " + Double.MAX_VALUE);

        System.out.println("\nTipo char:");
        System.out.println("Mínimo: " + (int) Character.MIN_VALUE);
        System.out.println("Máximo: " + (int) Character.MAX_VALUE);

        System.out.println("\nTipo boolean:");
        System.out.println("Não possui valores mínimos e máximos definidos.");

        /*
        Exemplos : 
        byte tamanho = 127;
        short valorCurto = 32767;
        char caracter = 'A';
        float valorPontoFlutuante = 3.13131451F;
        double valorPontoFlutuanteMaior = 1.79780478473256;
        int inteiro = 2111244326;
        long inteiroLongo = 92832074239084244L;
        boolean vardadeiroFalso = true;
         */

        Funcionario funcionario = new Funcionario("Elysson", 19, 12000);

        System.out.println("O funcionario é :" + funcionario);

    }
}
