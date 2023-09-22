public class Medico extends Funcionario implements AbonoSalarial {
    private String especialidade;
    private String crm;
    private Double abono;

    // Construtor do objeto médico, adquerindo as características do Funcionário
    public Medico(String nome, Integer idade, Double salario, String especialidade, String crm) {
        // Características do funcionário
        super(nome, idade, salario);
        this.especialidade = especialidade;
        this.crm = crm;
        this.abono = calculaAbonoSalario(salario);
    }


    @Override
    public String toString() {
        return "Medico{" +
                "especialidade='" + especialidade + '\'' +
                ", crm='" + crm + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", salario=" + salario +
                '}';
    }

    public static void main(String[] args){
        Medico medico = new Medico(
                "Joana",
                19,
                10000.00,
                "Ortopedia",
                "0001");
        Double decimoTerceiro = medico.CalcularDecimoTerceiroSalario(10000.00);
        System.out.println("Médico : " + medico);
        System.out.println("Décimo terceiro do médico " + medico.nome + " será " + decimoTerceiro );
        System.out.println("Abono do médico será de " + medico.abono);
    }

    @Override
    public Double calculaAbonoSalario(Double salario) {
        Double abono = salario + salario + salario * 0.50;
        return abono;
    }
}
