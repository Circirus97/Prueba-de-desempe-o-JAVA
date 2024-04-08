package database;


import entity.Producto;

import java.util.List;

public interface ProductoCRUD {

    Producto create (Producto producto);

    List<Producto> findAll();

    List<Producto> findByFilter(String filter, String value);
    //por tienda

    void update(Producto producto);

    void delete(Integer id);
}
