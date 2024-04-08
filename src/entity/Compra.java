package entity;

import java.time.LocalDate;

public class Compra {

    private Integer id;

    private Integer idCliente;

    private Integer idProducto;

    private LocalDate fechaCompra;

    private Integer cantidad;

    public Compra() { }

    public Compra(Integer id, Integer idCliente, Integer idProducto, LocalDate fechaCompra, Integer cantidad) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Compra\n" +
                "id: " + id +
                ", id Cliente: " + idCliente +
                ", idProducto: " + idProducto +
                ", fecha Compra: " + fechaCompra +
                ", cantidad: " + cantidad +
                '}';
    }
}
