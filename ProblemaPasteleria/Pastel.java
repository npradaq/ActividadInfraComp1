package ProblemaPasteleria;

public class Pastel {

    private int alto;
    private int radio;
    private String sabor;
    private String colorCubierta;

    private boolean isPedidoListo;

    
    public Pastel() {
        this.isPedidoListo = false;
    }

    public synchronized void hacerPedido(){
        System.out.println("***Ingrese los detalles del pastel***");
        System.out.println("Ingrese el alto del pastel:");
        this.alto = Integer.parseInt(System.console().readLine());
        System.out.println("Ingrese el radio del pastel:");
        this.radio = Integer.parseInt(System.console().readLine());
        System.out.println("Ingrese el sabor del pastel:");
        this.sabor = System.console().readLine();
        System.out.println("Ingrese el color de la cubierta del pastel:");
        this.colorCubierta = System.console().readLine();
        
        this.isPedidoListo = true;
    }

    public synchronized int getAlto() {
        return alto;
    }



    public synchronized int getRadio() {
        return radio;
    }



    public synchronized String getSabor() {
        return sabor;
    }



    public synchronized String getColorCubierta() {
        return colorCubierta;
    }

    public synchronized boolean consultarEstadoPedido(){

        return this.isPedidoListo;
    
    }

    public synchronized void comerPastel(){
        this.alto = 0;
        this.radio = 0;
        this.colorCubierta = "";
        this.sabor = "";
        this.isPedidoListo = false;
    }



    
    
}
