import java.io.*;

// Um objeto abstrato da vida real/ protected garante que apenas o objeto pai tenha os atributos e também os filhos
public class Funcionario {
    protected String nome;
    protected Integer idade;
    protected Double salario;

    // Contrutores
    public Funcionario(String nome, Integer idade, Double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }

    public static Double CalcularDecimoTerceiroSalario(Double salario) {
        return salario + (salario * 0.10);
    }

    public static void main (String[] args){
        Funcionario funcionario = new Funcionario("Stephanie", 24, 2000.00);

        //System.out.println("Funcionario " + funcionario);

        System.out.println("Funcionario: Nome= "
                            + funcionario.nome + "  Idade= "
                            + funcionario.idade + " Salario= "
                            + funcionario.salario);

        Double decimoTerceiro = CalcularDecimoTerceiroSalario(funcionario.salario);

        /*
        System.out.println("O decimo terceiro salario do funcionario " +
                            funcionario.nome + " sera " + decimoTerceiro);
         */
    }
}