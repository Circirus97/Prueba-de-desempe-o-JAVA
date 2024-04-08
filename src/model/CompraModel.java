package model;

import database.CompraCRUD;
import database.ConfigDB;
import entity.Compra;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class CompraModel implements CompraCRUD {
    @Override
    public Compra create(Compra compra) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO compras(id_cliente, id_producto, cantidad) VALUES (?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setInt(1, compra.getIdCliente());
            prepareCall.setInt(2, compra.getIdProducto());
            prepareCall.setInt(3, compra.getCantidad());

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
                compra.setIdCliente(result.getInt("id_cliente"));
                compra.setIdProducto(result.getInt("id_producto"));
                compra.setFechaCompra(result.getTimestamp("fecha_compra"));
                compra.setCantidad(result.getInt("cantidad"));

                compraList.add(compra);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compraList;
    }

    @Override
    public List<Compra> findByFilter(String filter, String value) {

        String sql;

        List<Compra> compraList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM compras WHERE id = ?;";

                compraList = findById(sql, value);

            }
            if (Objects.equals(filter, "Producto")) {

                sql = "SELECT * FROM compras c JOIN productos p ON c.id_producto = p.id WHERE p.id = ?;";

                compraList = findByProduct(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return compraList;
    }

    public List<Compra> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compra = new Compra();

                compra.setId(result.getInt("id"));
                compra.setIdCliente(result.getInt("id_cliente"));
                compra.setIdProducto(result.getInt("id_producto"));
                compra.setFechaCompra(result.getTimestamp("fecha_compra"));
                compra.setCantidad(result.getInt("cantidad"));


                compraList.add(compra);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, compraList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return compraList;

    }

    public List<Compra> findByProduct(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compra = new Compra();

                compra.setId(result.getInt("id"));
                compra.setIdCliente(result.getInt("id_cliente"));
                compra.setIdProducto(result.getInt("id_producto"));
                compra.setFechaCompra(result.getTimestamp("fecha_compra"));
                compra.setCantidad(result.getInt("cantidad"));


                compraList.add(compra);
            }

            prepareCall.close();

            JOptionPane.showMessageDialog(null, compraList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return compraList;

    }
    @Override
    public void update(Compra compra) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE compras SET id_producto = ?, cantidad = ? WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setInt(1, compra.getIdProducto());
            prepareCall.setInt(2, compra.getCantidad());
            prepareCall.setInt(3, compra.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Compra actualizada correctamente.\n" + compra);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM compras WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();

            JOptionPane.showMessageDialog(null, "Compra eliminado correctamente.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando \n" + e.getMessage());
        }

        ConfigDB.closeConnection();
    }





}
