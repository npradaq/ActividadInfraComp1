package Main;

import java.util.concurrent.ThreadLocalRandom;

public class MaximoMatriz extends Thread {
    private final static int INT_MAX = 20;

    private final static int DIM = 3;

    private static int[][] matriz = new int[DIM][DIM];

    private static int mayor = -1;

    private static int countUltimoThread = DIM;

    private int mayorFila = -1;

    private int idThread;

    private int fila;

    public MaximoMatriz(int pIdThread, int pFila){
        this.idThread = pIdThread;
        this.fila = pFila;
    }

    public static void crearMatriz(){
        for (int i = 0; i < DIM; i++){
            for (int j = 0; j < DIM; j++) {
                matriz[i][j] = ThreadLocalRandom.current().nextInt(0, INT_MAX);
            }
        }

        System.out.println("Matriz");
        System.out.println("-------------------------------");
        imprimirMatriz();
    }

    private static void imprimirMatriz() {
        for (int i = 0; i < DIM; i++){
            for (int j = 0; j < DIM; j++) {
                System.out.println(matriz[i][j] + "\t");  
            }
            System.out.println();
        }
    }

    private synchronized void cambiarMayorGlobal(){
        if(mayorFila > mayor){
            mayor = mayorFila;
            
            String warn = String.format(
                    "**********NUEVO MAXIMO ENCONTRADO********** \n" +
                    "ID THREAD: %d - Maximo local actual: %d - Maximo global: %d \n" +
                    "\n",
                    this.idThread,
                    mayor,
                    this.mayorFila
            );
            System.out.println(warn);
        }

        

    }

    @Override
    public void  run() {
        for (int j = 0; j < DIM; j++){
            if(this.mayorFila < matriz[this.fila][j]) {
                this.mayorFila = matriz[this.fila][j];
            }
        }

        cambiarMayorGlobal();

        countUltimoThread --;

        if (countUltimoThread == 0) {

            String msg = String.format("ID THREAD: %d - Maximo Local: %d - Maximo Global: %d \n",
                        this.idThread,
                        this.mayorFila,
                        mayor
            );
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println(msg);
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println();
        }
    
        

    }

    public static void main(String[] args) {
        
        MaximoMatriz.crearMatriz();
        System.out.println();
        System.out.println("INICIANDO BUSQUEDA \n");
        

        MaximoMatriz[] bThreads = new MaximoMatriz[DIM];

        for (int i = 0; i < DIM; i++) {
            bThreads[i] = new MaximoMatriz(i, i);
            bThreads[i].start();
        }
    }   
}



