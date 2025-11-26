
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(1, "Hamburguesa", 500.0));
        menu.add(new Menu(2, "Papas", 250.0));
        menu.add(new Menu(3, "Gaseosa", 100.0));
        menu.add(new Menu(4, "Helado", 200.0));
        menu.add(new Menu(5, "Jugo", 100.0));
        menu.add(new Menu(6, "Pollo", 100.0));

        Printer printer = new Printer();
        Calculadora calculadora = new Calculadora();
        Reader reader = new Reader();

        double ivaPorcentaje = 10.0;     // IVA configurable
        double propinaPorcentaje = 5.0;  // Propina configurable

        List<String> resumen = new ArrayList<>();
        double subtotal = 0.0;
        boolean continuar = true;

        while (continuar) {

            printer.printMenu(menu);
            System.out.println("Inserte la ID del producto que desea comprar: ");
            int id = reader.readInt();

            Menu seleccionado = null;
            for (Menu item : menu) {
                if (item.getId() == id) {
                    seleccionado = item;
                    break;
                }
            }

            if (seleccionado == null) {
                System.out.println("El producto seleccionado no existe.");
            } else {
                System.out.println("Inserte la cantidad: ");
                int cantidad = reader.readInt();
                double totalItem = calculadora.multiplicar(seleccionado.getPrecio(), cantidad);
                subtotal = calculadora.suma(subtotal, totalItem);
                resumen.add(seleccionado.getNombre() + " x" + cantidad + " = $" + totalItem);
            }
            System.out.println("¿Desea agregar otro producto? (s/n): ");
            String respuesta = reader.readString();
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }

        // Calcular IVA y propina
        double iva = subtotal * (ivaPorcentaje / 100);
        double propina = subtotal * (propinaPorcentaje / 100);
        double totalFinal = subtotal + iva + propina;

        // Imprimir resumen
        System.out.println("\n--- RESUMEN DE COMPRA ---");
        for (String linea : resumen) {
            System.out.println(linea);
        }
        System.out.println("----------------------------");
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA (" + ivaPorcentaje + "%): $" + iva);
        System.out.println("Propina (" + propinaPorcentaje + "%): $" + propina);
        printer.printTotal(totalFinal);
    }
}