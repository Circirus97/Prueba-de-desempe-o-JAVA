CREATE DATABASE deModaOutlet;

USE deModaOutlet;

CREATE TABLE tiendas(
id INT PRIMARY KEY,
nombre varchar(255),
ubicacion varchar(255)
);

CREATE TABLE productos(
id INT PRIMARY KEY,
nombre varchar(255),
precio DECIMAL(10,2),
id_tienda INT,
CONSTRAINT fk_id_tienda FOREIGN KEY(id_tienda) REFERENCES tiendas(id) ON DELETE CASCADE
);

ALTER TABLE `deModaOutlet`.`productos` 
ADD COLUMN `stock` INT NOT NULL AFTER `id_tienda`;

CREATE TABLE clientes(
id INT PRIMARY KEY,
nombre varchar(255),
apellido varchar(255),
email varchar(255)
);

CREATE TABLE compras(
id INT PRIMARY KEY,
id_cliente INT,
CONSTRAINT fk_id_cliente FOREIGN KEY(id_cliente) REFERENCES clientes(id) ON DELETE CASCADE,
id_producto INT,
CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES productos(id) ON DELETE CASCADE,
fecha_compra DATE,
cantidad INT
);

INSERT INTO `deModaOutlet`.`tiendas` (`id`, `nombre`, `ubicacion`) VALUES 
('1', 'Movies', 'Local 325'),
('2', 'Mercado', 'Local 328');
SELECT * FROM tiendas;






