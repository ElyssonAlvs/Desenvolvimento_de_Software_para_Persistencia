public class Livro {
    private String ano;
    private String autor;
    private int anoLancamento;

    public Livro(String ano, String autor, int anoLancamento) {
        this.ano = ano;
        this.autor = autor;
        this.anoLancamento = anoLancamento;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "ano='" + ano + '\'' +
                ", autor='" + autor + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
