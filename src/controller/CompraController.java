package controller;

import entity.Cliente;
import entity.Compra;
import entity.Producto;
import model.ClienteModel;
import model.CompraModel;
import model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class CompraController {

    CompraModel compraModel;

    ProductoModel productoModel;

    ClienteModel clienteModel;

    public CompraController(CompraModel compraModel, ProductoModel productoModel, ClienteModel clienteModel) {
        this.compraModel = new CompraModel();
        this.productoModel = new ProductoModel();
        this.clienteModel = new ClienteModel();
    }

    public void delete() {

        List<Compra> compraList = compraModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de compras:\n" + compraList + "\nIngrese el ID de la compra a eliminar\n"));
        compraList.stream().filter(compra -> compra.getId().equals(id)).findFirst().get();

        this.compraModel.delete(id);

    }

    public void update() {

        List<Compra> compraList = compraModel.findAll();
        List<Producto> productoList = productoModel.findAll();


        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de compras:\n" + compraList + "\nIngrese el ID de la compra a actualizar"));

        Compra compraFiltro = compraList.stream().filter(compra1 -> compra1.getId().equals(id)).findFirst().get();

        Object[] opcionesProducto = productoList.stream().map(Producto::getNombre).toArray();
        String seleccionFiltroProducto =
                (String) JOptionPane.showInputDialog(null, "Seleccione el producto\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opcionesProducto, opcionesProducto[0]);

        Integer cantidad = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad del producto a comprar ", compraFiltro.getCantidad()));
        Producto producto = productoList.stream().filter(productoFilter -> productoFilter.getNombre().equals(seleccionFiltroProducto)).findFirst().get();

        Compra compra = new Compra();

        compra.setIdCliente(compraFiltro.getIdCliente());
        compra.setIdProducto(producto.getId());
        compra.setFechaCompra(compraFiltro.getFechaCompra());
        compra.setCantidad(cantidad);
        compra.setId(id);

        this.compraModel.update(compra);

    }

    public void findByFilter() {

        String[] options = {"ID", "Producto"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedFilter == "Producto") {
            List<Producto> productoList = productoModel.findAll();
            Object[] opcionesProducto = productoList.stream().map(Producto::getNombre).toArray();
            String selectedFilterProducto = (String) JOptionPane.showInputDialog(null, "Seleccione el producto\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opcionesProducto, opcionesProducto[0]);

            Producto producto = productoList.stream().filter(producto1 -> producto1.getNombre().equals(selectedFilterProducto)).findFirst().get();

            List<Compra> compraList = this.compraModel.findByFilter(selectedFilter, String.valueOf(producto.getId()));
        } else {

            String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n");

            List<Compra> compraList = this.compraModel.findByFilter(selectedFilter, valueFilter);
        }


    }

    public void findAll() {

        List<Compra> compraList = this.compraModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de compras:\n" + compraList);
    }

    public void create() {

        Compra compra = new Compra();

        List<Cliente> clienteList = clienteModel.findAll();
        List<Producto> productoList = productoModel.findAll();

        Object[] opcionesCliente = clienteList.stream().map(Cliente::getNombre).toArray();
        String seleccionFiltroCliente =
                (String) JOptionPane.showInputDialog(null, "Seleccione el cliente\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opcionesCliente, opcionesCliente[0]);

        Object[] opcionesProducto = productoList.stream().map(Producto::getNombre).toArray();
        String seleccionFiltroProducto =
                (String) JOptionPane.showInputDialog(null, "Seleccione el producto\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opcionesProducto, opcionesProducto[0]);

        Cliente cliente = clienteList.stream().filter(clienteFilter -> clienteFilter.getNombre().equals(seleccionFiltroCliente)).findFirst().get();
        Producto producto = productoList.stream().filter(productoFilter -> productoFilter.getNombre().equals(seleccionFiltroProducto)).findFirst().get();

        Integer cantidad = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad: )"));

        if (cantidad > producto.getStock()) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor al stock disponible");
            cantidad = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad: )"));
            compra.setIdCliente(cliente.getId());
            compra.setIdProducto(producto.getId());
            compra.setCantidad(cantidad);

            this.compraModel.create(compra);
        }else {

            compra.setIdCliente(cliente.getId());
            compra.setIdProducto(producto.getId());
            compra.setCantidad(cantidad);

            this.compraModel.create(compra);
        }


    }


}
