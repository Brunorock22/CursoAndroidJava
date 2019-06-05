public class Heranca {
    public static void main(String []args){
        Passaro passaro= new Passaro();
        passaro.correr();
        passaro.setCor("Rosa");
        System.out.println(passaro.getCor());

        Cachorro cachorro= new Cachorro();
        cachorro.correr();
    }
}
