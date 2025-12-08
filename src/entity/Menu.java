package entity;

public class Menu {
    private int id;
    private String nombre;
    private double precio;
    public String anuncio = "ESTA VARIABLE SE PUEDE VER";
    private String mensajeSecreto = "Es un secreto....";

    public Menu(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}

    public String getMensajeSecreto() {
        return mensajeSecreto;
    }

    public String toString() {
        return id + " - " + nombre + " ($" + precio + ")";
    }
}
