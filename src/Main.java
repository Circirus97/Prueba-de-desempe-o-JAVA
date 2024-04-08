import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import database.ConfigDB;
import model.ClienteModel;
import model.CompraModel;
import model.ProductoModel;
import model.TiendaModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        ClienteModel clienteModel = new ClienteModel();
        ClienteController clienteController = new ClienteController(clienteModel);

        TiendaModel tiendaModel = new TiendaModel();

        ProductoModel productoModel = new ProductoModel();
        ProductoController productoController = new ProductoController(productoModel, tiendaModel);

        CompraModel compraModel = new CompraModel();
        CompraController compraController = new CompraController(compraModel, productoModel, clienteModel, tiendaModel);



        String opcion;

        do {
            opcion = JOptionPane.showInputDialog("""
                    Bienvenido al Centro Comercial De Modal Outlet
                                        
                    Seleccione una opcion
                                        
                    1. Administrador de productos.
                    2. Administrador de clientes.
                    3. Administrador de compras.
                    4. Salir.
                    """);

            switch (opcion) {
                case "1":
                    do {
                        opcion = JOptionPane.showInputDialog("""
                                Administrador de productos
                                                                
                                Seleccione una opcion.
                                1. Agregar un nuevo producto.
                                2. Filtrar productos.
                                3. Actualizar informacion del producto.
                                4. Eliminar producto.
                                5. Mostrar todos los productos
                                6. Salir.
                                """);
                        switch (opcion) {
                            case "1":
                                productoController.create();
                                break;
                            case "2":
                                productoController.findByFilters();
                                break;
                            case "3":
                                productoController.update();
                                break;
                            case "4":
                                productoController.delete();
                                break;
                            case "5":
                                productoController.findAll();
                                break;
                        }
                    } while (!opcion.equals("6"));
                    break;
                case "2":
                    do {
                        opcion = JOptionPane.showInputDialog("""
                                Administrador de clientes
                                                                
                                Seleccione una opcion:

                                    1. Agregar un nuevo clientes.
                                    2. Filtrar clientes.
                                    3. Actualizar informacion del clientes.
                                    4. Eliminar clientes.
                                    5. Mostrar todos lo clientes.
                                    6. Salir.
                                """);
                        switch (opcion) {
                            case "1":
                                clienteController.create();
                                break;
                            case "2":
                                clienteController.findByFilters();
                                break;
                            case "3":
                                clienteController.update();
                                break;
                            case "4":
                                clienteController.delete();
                                break;
                            case "5":
                                clienteController.findAll();
                                break;
                        }
                    } while (!opcion.equals("6"));
                    break;
                case "3":
                    do {
                        opcion = JOptionPane.showInputDialog("""
                                Administrador de Compras
                                                                
                                Seleccione una opcion:
                                    1. Agregar una nueva compras.
                                    2. Filtrar por compras.
                                    3. Actualizar informacion de la compras.
                                    4. Eliminar una compras.
                                    5. Mostrar todas las compras.
                                    6. Mostrar facturas
                                    7. Salir.
                                """);
                        switch (opcion) {
                            case "1":
                                compraController.create();
                                break;
                            case "2":
                                compraController.findByFilter();
                                break;
                            case "3":
                                compraController.update();
                                break;
                            case "4":
                                compraController.delete();
                                break;
                            case "5":
                                compraController.findAll();
                                break;
                            case "6":
                                compraController.showCompra();
                                break;
                        }
                    } while (!opcion.equals("7"));
                    break;
            }

        }while (!opcion.equals("4")) ;
    }
}