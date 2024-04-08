package database;

import entity.Cliente;
import entity.Tienda;

import java.util.List;

public interface TiendaCRUD {

    List<Tienda> findAll();

}
