Claro, aqui está o código Markdown que inclui as explicações e os códigos Java em um único documento:


# Classes, Objetos e Métodos em Java

Neste documento, vamos discutir os conceitos de classes, objetos e métodos em Java, usando dois exemplos de código Java.

## Classe `Livro.java`

O arquivo `Livro.java` define uma classe chamada `Livro` que representa um livro com três atributos: ano, autor e ano de lançamento.

```java
public class Livro {
    private String ano;
    private String autor;
    private int anoLancamento;

    // Construtor da classe Livro
    public Livro(String ano, String autor, int anoLancamento) {
        this.ano = ano;
        this.autor = autor;
        this.anoLancamento = anoLancamento;
    }

    // Método para obter o ano de lançamento do livro
    public int getAnoLancamento() {
        return anoLancamento;
    }

    // Sobrescrevendo o método toString() para exibir informações sobre o livro
    @Override
    public String toString() {
        return "Livro{" +
                "ano='" + ano + '\'' +
                ", autor='" + autor + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
```

### Explicação da Classe `Livro`

- A classe `Livro` possui três atributos: `ano`, `autor` e `anoLancamento`, que são definidos como privados para encapsulamento.
- O construtor `Livro` é usado para criar instâncias da classe `Livro` e inicializa os atributos.
- O método `getAnoLancamento` permite acessar o ano de lançamento do livro.
- O método `toString` é sobrescrito para fornecer uma representação de string personalizada do objeto `Livro`.

## Classe `Biblioteca.java`

O arquivo `Biblioteca.java` demonstra como usar a classe `Livro` para criar objetos e chamar métodos.

```java
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    public static void main(String[] args) {
        Livro livro1 = new Livro("Backend Java", "João Pedro", 2022);
        Livro livro2 = new Livro("Microsserviços", "Maria", 2021);
        Livro livro3 = new Livro("BI Analytics", "Elysson", 2022);

        List<Livro> livros = new ArrayList<>();
        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3);

        var livrosPorAno = livrosPorAno(livros, 2021);
        System.out.println("Estes são os livros de acordo com o ano informado : " + livrosPorAno);
    }

    // Um método que vai retornar uma lista de livros, recebendo com parâmetro, uma lista e o ano específico
    public static List<Livro> livrosPorAno(List<Livro> livros, int ano){
        List<Livro> listaAtualizada = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getAnoLancamento() == ano){
                listaAtualizada.add(livro);
            }
        }
        return listaAtualizada;
    }
}
```

### Explicação da Classe `Biblioteca`

- A classe `Biblioteca` possui um método `main` onde objetos da classe `Livro` são criados e armazenados em uma lista.
- O método `livrosPorAno` é chamado, passando a lista de livros e um ano como parâmetros. Esse método filtra e retorna os livros lançados no ano especificado.

A combinação de classes, objetos e métodos permite criar estruturas de código organizadas e reutilizáveis em Java.