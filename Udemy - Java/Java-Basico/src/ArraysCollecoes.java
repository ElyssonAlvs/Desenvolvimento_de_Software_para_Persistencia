import java.util.ArrayList;

public class ArraysCollecoes {
    public static void main(String[] args) {
        // Declarando e inicializando um array de inteiros com tamanho 5
        int[] numeros = new int[5];

        // Inserindo valores no array de inteiros
        numeros[0] = 15;
        numeros[1] = 2;
        numeros[2] = 18;
        numeros[3] = 25;
        numeros[4] = 200;

        // Imprimindo o valor no índice 3 do array (índices iniciam em 0)
        System.out.println(numeros[3]);

        // Declarando e inicializando um ArrayList de Strings chamado listNomes
        ArrayList<String> listNomes = new ArrayList<>();

        // Adicionando nomes à lista
        listNomes.add("Elysson");
        listNomes.add("Jhony");
        listNomes.add("Maria");
        listNomes.add("João");

        // Imprimindo a lista de nomes
        System.out.println("Os nomes são estes : " + listNomes);

        // Removendo o nome "João" da lista
        listNomes.remove("João");

        // Imprimindo a lista atualizada após a remoção
        System.out.println("Nomes atualizados " + listNomes);

        // Obtendo e imprimindo o tamanho da lista
        System.out.println("O tamanho da lista é : " + listNomes.size());
    }
}
