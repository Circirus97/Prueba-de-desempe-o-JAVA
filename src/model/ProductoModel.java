package model;

import database.ConfigDB;
import database.ProductoCRUD;
import entity.Compra;
import entity.Producto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductoModel implements ProductoCRUD {
    @Override
    public Producto create(Producto producto) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO productos(nombre, precio, id_tienda, stock) VALUES (?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, producto.getNombre());
            prepareCall.setDouble(2, producto.getPrecio());
            prepareCall.setInt(3, producto.getIdTienda());
            prepareCall.setInt(4, producto.getStock());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();
            while (result.next()) {
                producto.setId(result.getInt(1));
            }


            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente.\n" + producto);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error agregando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return producto;
    }

    @Override
    public List<Producto> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM productos;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));
                producto.setStock(result.getInt("stock"));


                productoList.add(producto);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoList;
    }

    @Override
    public List<Producto> findByFilter(String filter, String value) {

        String sql;

        List<Producto> productoList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM productos WHERE id = ?;";

                productoList = findById(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM productos WHERE nombre LIKE ?;";

                productoList = findByName(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoList;
    }

    public List<Producto> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));
                producto.setStock(result.getInt("stock"));


                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de producto:\n" + productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return productoList;

    }

    private List<Producto> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setIdTienda(result.getInt("id_tienda"));
                producto.setStock(result.getInt("stock"));


                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de productos:\n" + productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return productoList;

    }

    @Override
    public void update(Producto producto) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?;";


            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, producto.getNombre());
            prepareCall.setDouble(2, producto.getPrecio());
            prepareCall.setInt(3, producto.getStock());
            prepareCall.setInt(4, producto.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.\n" + producto);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM productos WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();

            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.\n");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error eliminando \n" + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    public void updateProduct(Integer cantidad, Integer id) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE productos SET stock = ? WHERE id = ?;";


            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setInt(1, cantidad);
            prepareCall.setInt(2, id);

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

}

