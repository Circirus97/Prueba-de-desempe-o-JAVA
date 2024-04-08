package controller;

import entity.Cliente;
import model.ClienteModel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ClienteController{

    ClienteModel clienteModel;

    public ClienteController(ClienteModel clienteModel) {
        this.clienteModel = new ClienteModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de cliente: \n" + clienteModel.findAll() + "\nIngrese el ID del cliente a eliminar"));

        this.clienteModel.delete(id);

    }

    public void update(){

        List<Cliente> listaClientes = clienteModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de clientes:\n" + clienteModel.findAll() + "\nIngrese el ID del cliente a actualizar"));
        Cliente clienteFiltro = listaClientes.stream().filter(cliente1 -> cliente1.getId().equals(id)).findFirst().get();


        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del cliente: ", clienteFiltro.getNombre());
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido del cliente: ", clienteFiltro.getApellido());
        String email = JOptionPane.showInputDialog(null, "Ingrese el nuevo email del cliente: ", clienteFiltro.getEmail());

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setId(id);

        this.clienteModel.update(cliente);
    }

    public void findByFilters(){

        String[] opciones = {"ID" ,"Nombre"};

        String seleccionarFiltro = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        String valorFiltro = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID del cliente, nombre del cliente)");

        List<Cliente> listaClientes = this.clienteModel.findByFilter(seleccionarFiltro, valorFiltro);
    }

    public void findAll(){

        List<Cliente> listaClientes = this.clienteModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de clientes:\n" + listaClientes);
    }

    public void create(){

        Cliente cliente = new Cliente();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del cliente: ");
        String email = JOptionPane.showInputDialog(null, "Ingrese el correo electronico: )");

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);

        this.clienteModel.create(cliente);

    }
}
