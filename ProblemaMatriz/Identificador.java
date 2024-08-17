package ProblemaMatriz;

public class Identificador {

    private int i;
    

    public Identificador(int DIM) {
        this.i = -1;
    }

    public synchronized int darId(){
        i++;

        return i;
    }
    
}
