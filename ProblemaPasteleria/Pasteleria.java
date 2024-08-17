package ProblemaPasteleria;

import java.util.Random;

public class Pasteleria extends Thread{
    
    private boolean isCliente;
    
    private static Pastel pastel = new Pastel();
    private static Object o1 = new Object();
    

    public Pasteleria(boolean isCliente) {
        this.isCliente = isCliente;
    }

    @Override
    public void run() {
        if (isCliente) {
            pastel.hacerPedido();

            System.out.println("¡Pedido especificado!");

            try {
                synchronized (o1) {
                    o1.wait();
                }

                System.out.println("¡Espera Terminada! ¡Hora de pastel!");

                pastel.comerPastel();

                System.out.println("Mmmmmmmm ¡Delicioso!");


                
            } catch (InterruptedException e) {
            }

            
        }
        else {
            try {
                while (!pastel.consultarEstadoPedido()) {
                    sleep(5000);
                    System.out.println("Pastel aún no está listo");
                }
                
                System.out.println("Haciendo Pastel....");

                Random rand = new Random();
                int secs = rand.nextInt(11) + 5;

                sleep(secs*1000);

                System.out.println("¡Pastel listo!");

                synchronized (o1){
                    o1.notify();
                }
                
            } catch (InterruptedException e) {
                
            }

        }
    }

    public static void main(String[] args) {
        Pasteleria chef = new Pasteleria(false);
        Pasteleria cliente = new Pasteleria(true);
        
        chef.start();
        cliente.start();
        
    }

    
    
}
