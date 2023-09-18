import java.io.Serializable;

/*
Crie uma classe Java de entidade.
        Escolha uma entidade que você já propôs para seu Trabalho Prático.
        Exemplo: classe Filme (id, titulo, sinopse, diretor).
        A classe deve implementar a interface java.io.Serializable.
        Crie também uma classe que possua uma lista de objetos da entidade escolhida.
        Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes).

 No caso do projeto prático, foram escolhidos carros, por isso o nome das classes
 */


public class Carro implements Serializable {
    private int idCarro;
    private String modelo;
    private String marca;
    private int anoFabricacao;

    // Contrutor padrão sem argumento (necessário para desserializar no JSON)
    public Carro(){}

    // Contrutor do objeto Carro
    public Carro(int idCarro, String modelo, String marca, int anoFabricacao) {
        this.idCarro = idCarro;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
    }


    // Getters e setters

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "idCarro=" + idCarro +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                '}';
    }

}