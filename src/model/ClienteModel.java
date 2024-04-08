package model;

import database.ClienteCRUD;
import database.ConfigDB;
import entity.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteModel  implements ClienteCRUD{


    @Override
    public Cliente create(Cliente cliente) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO clientes(nombre, apellido, email) VALUES (?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, cliente.getNombre());
            prepareCall.setString(2, cliente.getApellido());
            prepareCall.setString(3, cliente.getEmail());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();
            while (result.next()) {
                cliente.setId(result.getInt(1));
            }


            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.\n" + cliente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error agregando el cliente\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> listaClientes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM clientes;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Cliente cliente = new Cliente();


                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setEmail(result.getString("email"));


                listaClientes.add(cliente);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaClientes;
    }

    @Override
    public List<Cliente> findByFilter(String filter, String value) {
        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Cliente> listaClientes = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM clientes WHERE id = ?;";

                listaClientes = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM clientes WHERE nombre LIKE ?;";

                listaClientes = findByName(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaClientes;
    }

    public List<Cliente> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setEmail(result.getString("email"));


                listaClientes.add(cliente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes:\n" + listaClientes);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return listaClientes;

    }

    private List<Cliente> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setEmail(result.getString("email"));


                listaClientes.add(cliente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes:\n" + listaClientes);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return listaClientes;

    }

    @Override
    public void update(Cliente cliente) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE clientes SET nombre =?, apellido =?, email =? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, cliente.getNombre());
            prepareCall.setString(2, cliente.getApellido());
            prepareCall.setString(3, cliente.getEmail());
            prepareCall.setInt(4, cliente.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.\n" + cliente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando\n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sql= "DELETE FROM clientes WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando" + e.getMessage());
        }

        ConfigDB.closeConnection();

    }
}
