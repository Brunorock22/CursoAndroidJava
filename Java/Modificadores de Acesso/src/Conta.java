public class Conta {
    private int numeroConta;
    private double saldo=100;



    public void depositar(double valorDepositar){
        this.setSaldo(this.getSaldo() + valorDepositar);
    }
    public void sacar (double valorSacar){
        this.setSaldo(this.getSaldo() - valorSacar);
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
