public class ContaCorrente{
    private String agencia;
    private String numConta;
    private Double saldo;

    public ContaCorrente(String agencia, String numConta, Double saldo) {
        this.agencia = agencia;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public Double getSaldo() { return saldo; }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia='" + agencia + '\'' +
                ", numConta='" + numConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }


    public static ContaCorrente deposita(ContaCorrente contaCorrente, double valor){
        if(valor <= 0.00){
            System.out.println("Não foi possível realizar o depósito, o valor de depósito deve ser maior que 0");
            return null;
        }
        // Saldo atual da conta mais o valor a ser depositado
        Double saldoAtualizado = contaCorrente.getSaldo() + valor;
        System.out.println("Depósito realizado com sucesso !");
        ContaCorrente cc = new ContaCorrente(contaCorrente.getAgencia(), contaCorrente.getNumConta(),saldoAtualizado);
        return cc;
    }
}
