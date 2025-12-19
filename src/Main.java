import entity.Menu;
import entity.Product;
import entity.Stock;
import utils.Calculadora;
import utils.Printer;
import utils.Reader;
import entity.Sell;
import entity.SellItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Hamburguesa", 500, LocalDateTime.now()));
        productList.add(new Product("Papas", 250, LocalDateTime.now()));
        productList.add(new Product("Gaseosa", 100, LocalDateTime.now()));
        productList.add(new Product("Pollo", 600, LocalDateTime.now()));
        productList.add(new Product("Helado", 150, LocalDateTime.now()));
        productList.add(new Product("Jugo", 100, LocalDateTime.now()));
        productList.add(new Product("Milanesa", 300, LocalDateTime.now()));
        productList.add(new Product("Galletas", 200, LocalDateTime.now()));
        productList.add(new Product("Agua", 120, LocalDateTime.now()));
        productList.add(new Product("Pancho", 180, LocalDateTime.now()));

        // PRELOAD STOCK
        Stock.preloadProducts(productList);

        Printer printer = new Printer();
        Calculadora calculadora = new Calculadora();
        Reader reader = new Reader();
        InventoryManager inventory = new InventoryManager();

        boolean appRunning = true;

        while (appRunning) {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar productos al kiosko");
            System.out.println("2. Venta de Productos (En construcción)");
            System.out.println("3. Pagar pedido (En construcción)");
            System.out.println("4. Mostrar productos (En construcción)");
            System.out.println("5. Mostrar ventas (En construcción)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    agregarProductosKiosko(reader);
                    break;

                case 2:
                    ventaProductos(reader);
                    break;

                case 3:
                    System.out.println("\n--- Pagar (Mockup) ---");
                    break;

                case 4:
                    mostrarProductosMenu(reader);
                    break;

                case 5:
                    for (Sell s : Sell.getSells()) {
                        System.out.println(s);
                    }
                    break;

                case 6:
                    appRunning = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // ============================================================
    //  ADD PRODUCTS MENU
    // ============================================================

    private static void agregarProductosKiosko(Reader reader) {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n--- Agregar productos al kiosko ---");
            System.out.println("1. Nuevo producto");
            System.out.println("2. Producto existente (Ver Productos)");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    System.out.println("Ingrese nombre del producto:");
                    String name = reader.readString();

                    System.out.println("Ingrese precio:");
                    double price = reader.readDouble();

                    Product newProd = new Product(name, price, LocalDateTime.now());
                    Stock.registerProduct(newProd);

                    System.out.println("Producto registrado correctamente (sin stock).");
                    break;

                case 2:
                    Stock.showProductsWithoutStock();

                    break;

                case 3:
                    submenu = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // ==============================================================
    // 2. VENTA DE PRODUCTOS
    // ==============================================================

    private static void ventaProductos(Reader reader) {

        Sell currentSell = new Sell();
        boolean selling = true;

        while (selling) {

            System.out.println("\n--- Productos disponibles ---");

            List<Stock> available = Stock.getAvailableStock();

            if (available.isEmpty()) {
                System.out.println("No hay productos con stock.");
                return;
            }

            int index = 1;
            for (Stock s : available) {
                System.out.println(index + ". " +
                        s.getProduct().getName() +
                        " | Stock: " + s.getQuantity() +
                        " | $" + s.getProduct().getPrice());
                index++;
            }

            System.out.print("Seleccione producto: ");
            int choice = reader.readInt();

            if (choice < 1 || choice > available.size()) {
                System.out.println("Selección inválida.");
                continue;
            }

            Stock selectedStock = available.get(choice - 1);

            System.out.print("Ingrese cantidad: ");
            int qty = reader.readInt();

            if (qty <= 0 || qty > selectedStock.getQuantity()) {
                System.out.println("Cantidad inválida.");
                continue;
            }

            // Deduct stock
            Stock.deductStock(selectedStock.getProduct(), qty);

            // Add to sell
            currentSell.addItem(
                    new SellItem(selectedStock.getProduct(), qty)
            );

            System.out.print("¿Agregar otro producto? (s/n): ");
            String opt = reader.readString();

            if (!opt.equalsIgnoreCase("s")) {
                selling = false;
            }
        }

        // Register sale
        currentSell.register();

        System.out.println("\nVENTA REGISTRADA");
        System.out.println(currentSell);
    }

    // ==============================================================
    // 3. PAGAR PEDIDO (Mockup only)
    // ==============================================================

    private static void pagarPedidoMockup() {
        System.out.println("\n--- Pagar pedido (En construcción) ---");

        System.out.println("Resumen del pedido:");
        System.out.println("--------------------------");
        System.out.println("hamburguesas pollo x3 $1500");
        System.out.println("gaseosa cocacola  x3 $900");
        System.out.println("papas             x3 $450");
        System.out.println("--------------------------");
        System.out.println("Subtotal:");
        System.out.println("IVA:");
        System.out.println("Total:");
        System.out.println("--------------------------");

        System.out.println("Tipo de pago");
        System.out.println("* Efectivo");
        System.out.println("* Tarjeta débito");
        System.out.println("   1- VISA");
        System.out.println("   2- MASTER");
        System.out.println("* Mercado Pago");
        System.out.println("* Tarjeta crédito");
        System.out.println("   1- AMEX");
        System.out.println("   2- VISA");
        System.out.println("   3- MASTER");

        System.out.println("\n*** Funcionalidad en construcción ***");
    }

    // ==============================================================
    // 4. MOSTRAR PRODUCTOS (Semi-Working)
    // ==============================================================

    private static void mostrarProductosMenu(Reader reader) {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n---- Mostrar Productos ----");
            System.out.println("1. Ver Stock");
            System.out.println("2. Ver productos sin Stock");
            System.out.println("3. Agregar Stock al producto");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    Stock.showStock();
                    break;

                case 2:
                    Stock.showProductsWithoutStock();
                    break;

                case 3:
                    agregarStock(reader);
                    break;

                case 4:
                    submenu = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    //--------------- Add Stock Functionality ------------------//

    private static void agregarStock(Reader reader) {

        boolean submenu = true;

        while (submenu) {
            System.out.println("\n--- Agregar Stock ---");
            System.out.println("1. Seleccionar producto");
            System.out.println("2. Agregar productos Aleatorios");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            int op = reader.readInt();

            switch (op) {
                case 1:
                    int index = 1;
                    for (Product p : Stock.getRegisteredProducts()) {
                        System.out.println(index + ". " + p.getName());
                        index++;
                    }

                    System.out.print("Seleccione producto: ");
                    int choice = reader.readInt();

                    if (choice < 1 || choice > Stock.getRegisteredProducts().size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    Product selected = Stock.getRegisteredProducts().get(choice - 1);

                    System.out.print("Ingrese cantidad: ");
                    int qty = reader.readInt();

                    Stock.addStock(selected, qty);

                    System.out.println("Stock agregado correctamente.");
                    break;

                case 2:
                    Stock.addRandomStockToAllProducts();
                    break;

                case 3:
                    submenu = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // ==============================================================
    // 5. MOSTRAR VENTAS (Mockup only)
    // ==============================================================

    private static void mostrarVentasMockup() {
        System.out.println("\n--- Mostrar ventas (En construcción) ---");
        System.out.println("(Listado de ventas vendrá aquí)");
    }
}