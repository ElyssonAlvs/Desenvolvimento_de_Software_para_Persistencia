import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carros implements Serializable {
    private List<Carro> carros;

    // O construtor da classe Carros é responsável por inicializar a lista de carros.
    public Carros() {
        carros = new ArrayList<>();
    }

    // Este método permite adicionar um objeto da classe Carro à lista de carros.
    public void adicionarCarro(Carro carro) {
        carros.add(carro);
    }

    // Este método retorna a lista de carros.
    public List<Carro> getCarros() {
        return carros;
    }
}
