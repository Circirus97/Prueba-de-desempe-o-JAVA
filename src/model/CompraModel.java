package model;

import database.CompraCRUD;
import database.ConfigDB;
import entity.Compra;
import entity.Producto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CompraCRUD {
    @Override
    public Compra create(Compra compra) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO compras(id_cliente, id_producto, fecha_compra, cantidad) VALUES (?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setInt(1, compra.getIdCliente());
            prepareCall.setInt(2, compra.getIdProducto());
            prepareCall.setDate(3, Date.valueOf(compra.getFechaCompra()));
            prepareCall.setInt(4, compra.getCantidad());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();
            while (result.next()) {
                compra.setId(result.getInt(1));
            }


            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Compra agregada correctamente.\n" + compra);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error agregando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compra;
    }

    @Override
    public List<Compra> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM compras;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Compra compra = new Compra();

                compra.setId(result.getInt("id"));
                compra.setNombre(result.getString("nombre"));
                compra.setPrecio(result.getDouble("precio"));
                compra.setIdTienda(result.getInt("id_tienda"));
                compra.setStock(result.getInt("stock"));


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
    public List<Compra> findByFilter(String filter, String value) {
        return null;
    }

    @Override
    public void update(Compra compra) {

    }

    @Override
    public void delete(Integer id) {

    }
}
