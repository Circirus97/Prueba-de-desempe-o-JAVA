package database;


import entity.Compra;

import java.util.List;

public interface CompraCRUD {

    Compra create (Compra compra);

    List<Compra> findAll();

    List<Compra> findByFilter(String filter, String value);
    //compras por producto

    void update(Compra compra);

    void delete(Integer id);
}
