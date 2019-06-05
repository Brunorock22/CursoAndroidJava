public class ClassePrincipal {
    public static void main(String[]args){
        Conta conta = new Conta();
        conta.depositar(150);
        System.out.println(conta.getSaldo());

    }
}
