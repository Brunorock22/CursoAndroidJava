public class Animal {
    private String cor;
    private double tamnho;
    private double peso;



    public void correr(){

        System.out.println("Ésta correndo");
    }
    public void dormir(){
        System.out.println("Ésta dormindo");
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
