package entity;

public class Producto {

    private Integer id;

    private String nombre;

    private Double precio;

    private Integer stock;

    private Integer idTienda;

    public Producto() {}

    public Producto(Integer id, String nombre, Double precio, Integer stock, Integer idTienda) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idTienda = idTienda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }


    @Override
    public String toString() {
        return "Producto\n" +
                "id: " + id +
                ", nombre: " + nombre +
                ", precio: " + precio +
                ", stock: " + stock +
                ", id tienda: " + idTienda +
                "\n--------------------------------\n";
    }
}