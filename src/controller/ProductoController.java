package controller;

import database.ProductoCRUD;
import entity.Producto;
import entity.Tienda;
import model.ProductoModel;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class ProductoController {

    ProductoModel productoModel;

    TiendaModel tiendaModel;

    public ProductoController(ProductoModel productoModel, TiendaModel tiendaModel) {
        this.productoModel = new ProductoModel();
        this.tiendaModel = new TiendaModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de productos: \n" + productoModel.findAll() + "\nIngrese el ID del producto a eliminar"));

        this.productoModel.delete(id);

    }

    public void update(){

        List<Producto> productoList = productoModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de productos:\n" + productoModel.findAll() + "\nIngrese el ID del producto a actualizar"));
        Producto productoFiltro = productoList.stream().filter(producto1 -> producto1.getId().equals(id)).findFirst().get();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del producto: ", productoFiltro.getNombre());
        Double precio = Double.valueOf(JOptionPane.showInputDialog(null, "Ingrese el nuevo precio: ", productoFiltro.getPrecio()));
        Integer stock = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el nuevo stock: )", productoFiltro.getStock()));

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setIdTienda(productoFiltro.getIdTienda());
        producto.setStock(stock);
        producto.setId(id);

        this.productoModel.update(producto);
    }

    public void findByFilters() {

        String[] opciones = {"ID", "Nombre"};

        String seleccionarFiltro = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        String valorFiltro = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID del producto, nombre del producto)");

        List<Producto> productoList = this.productoModel.findByFilter(seleccionarFiltro, valorFiltro);

    }
    public void findAll() {

        List<Producto> productoList = this.productoModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de productos:\n" + productoList);
    }

    public void create() {

        Producto producto = new Producto();

        List<Tienda> tiendaList = tiendaModel.findAll();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto: ");
        Double precio = Double.valueOf(JOptionPane.showInputDialog(null, "Ingrese el precio del producto: "));
        Integer stock = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el stock: )"));

        Object[] opcionesTienda = tiendaList.stream().map(Tienda::getNombre).toArray();
        String seleccionFiltroTienda =
                (String) JOptionPane.showInputDialog(null, "Seleccione la tienda\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opcionesTienda, opcionesTienda[0]);

        Tienda tienda = tiendaList.stream().filter(tiendaFilter -> tiendaFilter.getNombre().equals(seleccionFiltroTienda)).findFirst().get();


        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setIdTienda(tienda.getId());


        this.productoModel.create(producto);

    }
}
