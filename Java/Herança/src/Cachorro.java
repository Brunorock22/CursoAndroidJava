public class Cachorro extends Animal {
    public void latir (){
        System.out.println("LATIR");
    }
    public void correr(){
        super.correr();
        System.out.println("Ésta correndo CACHORRO");
    }
}
