public class Carro {
    private int IdCarro;
    private String modelo;
    private String marca;
    private String configuracao;
    private int AnoFabricacao;
    private String TipoCombustivel;

    // Construtor com todos os atributos
    public Carro(int id_carro, String modelo, String marca, String configuracao, int AnoFabricacao, String TipoCombustivel) {
        this.IdCarro = id_carro;
        this.modelo = modelo;
        this.marca = marca;
        this.configuracao = configuracao;
        this.AnoFabricacao = AnoFabricacao;
        this.TipoCombustivel = TipoCombustivel;
    }

    // Construtor padrão (default)
    public Carro() {
        // Você pode inicializar valores padrão aqui, se necessário.
    }

    // Getters e setters para todos os atributos
    public int getIdCarro() {
        return IdCarro;
    }

    public void setIdCarro(int IdCarro) {
        this.IdCarro = IdCarro;
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

    public String getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(String configuracao) {
        this.configuracao = configuracao;
    }

    public int getAnoFabricacao() {
        return AnoFabricacao;
    }

    public void setAno_fabricacao(int AnoFabricacao) {
        this.AnoFabricacao = AnoFabricacao;
    }

    public String getTipoCombustivel() {
        return TipoCombustivel;
    }

    public void setTipo_combustivel(String TipoCombustivel) {
        this.TipoCombustivel = TipoCombustivel;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "IdCarro=" + IdCarro +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", configuracao='" + configuracao + '\'' +
                ", AnoFabricacao=" + AnoFabricacao +
                ", TipoCombustivel='" + TipoCombustivel + '\'' +
                '}';
    }
}
