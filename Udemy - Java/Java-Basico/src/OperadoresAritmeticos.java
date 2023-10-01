public class OperadoresAritmeticos {
    public static void main(String[] args) {
        // Operações Aritméticas
        int a = 10;
        int b = 5;
        int soma = a + b;
        int subtracao = a - b;
        int multiplicacao = a * b;
        int divisao = a / b;
        int resto = a % b;

        System.out.println("Operações Aritméticas:");
        System.out.println("Soma: " + soma);
        System.out.println("Subtração: " + subtracao);
        System.out.println("Multiplicação: " + multiplicacao);
        System.out.println("Divisão: " + divisao);
        System.out.println("Resto da Divisão: " + resto);

        // Operadores de Comparação
        System.out.println("\nOperadores de Comparação:");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));

        // Operadores Lógicos
        boolean x = true;
        boolean y = false;
        System.out.println("\nOperadores Lógicos:");
        System.out.println("x && y: " + (x && y));
        System.out.println("x || y: " + (x || y));
        System.out.println("!x: " + !x);

        // Operadores de Incremento e Decremento
        int contador = 5;
        System.out.println("\nOperadores de Incremento e Decremento:");
        System.out.println("Valor Inicial: " + contador);
        contador++; // Incremento
        System.out.println("Após Incremento: " + contador);
        contador--; // Decremento
        System.out.println("Após Decremento: " + contador);

        // Operador Ternário
        int idade = 18;
        String resultado = (idade >= 18) ? "Maior de Idade" : "Menor de Idade";
        System.out.println("\nOperador Ternário:");
        System.out.println("Resultado: " + resultado);
    }
}
