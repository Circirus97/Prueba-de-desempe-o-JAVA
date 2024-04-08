package model;

import database.ConfigDB;
import database.TiendaCRUD;
import entity.Cliente;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel implements TiendaCRUD {


    @Override
    public List<Tienda> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Tienda> tiendaList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tiendas;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Tienda tienda = new Tienda();

                tienda.setId(result.getInt("id"));
                tienda.setNombre(result.getString("nombre"));
                tienda.setUbicacion(result.getString("ubicacion"));


                tiendaList.add(tienda);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return tiendaList;
    }
}
