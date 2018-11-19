Drop database ferreteria;
create database ferreteria;
use ferreteria;

create table clientes(
id_cliente int not null auto_increment primary key,
nombre_cliente char(50) not null,
apellido_paterno char(50) not null,
apellido_materno char(50) not null,
telefono int(15) not null,
rfc varchar(15) not null,
calle varchar(50) not null,
colonia varchar(50) not null,
no_interrior int(5) not null,
no_exterrior int(5) not null,
cp int(10) not null,
email varchar(50) not null,
ciudad varchar(50) not null,
pais varchar(50) not null);

create table sucursales(
id_sucursal int(10) auto_increment primary key,
calle varchar(50) not null,
colonia varchar(50) not null,
no_interrior int(5) not null,
no_exterrior int(5) not null,
cp int(10) not null,
telefono int(15) not null,
email varchar(50)not null);

create table empleados(
id_emp int(10) auto_increment primary key,
nombre_emp char(15) not null,
ap_paterno_emp char (15)not null,
ap_materno_emp char (15)not null,
numero_cuenta_emp int(20) not null,
numero_seguro_emp varchar(20) not null,
banco_emp char(20) not null,
curp_emp varchar(20) not null,
telefono_emp int(15) not null,
calle_emp char(30) not null,
colonia_emp char(15) not null,
estado_emp char(15) not null,
ciudad_emp char(15) not null,
cp_emp int(5)not null,
tipo_empleado char(25) not null,
usuario varchar(30) not null,
password varchar(30) not null,
id_sucursal int(11) not null,
foreign key (id_sucursal) references sucursales(id_sucursal));

create table proveedores(
id_prov  int auto_increment primary key,
nombre_prov char(15) not null,
telefono_prov int(10) not null,
calle_prov char(30) not null,
colonia_prov char(15) not null,
ciudad_prov char(15) not null,
estado_prov char(15) not null);

create table productos(
id_producto int(10) auto_increment primary key,
nombre_producto char(50) not null,
tipo_producto char(25) not null,
marca char(25) not null,
precio_venta int(20) not null,
sku int(20) not null,
lote int(20) not null,
fecha_entrada timestamp not null);



create table compras(
id_compra int not null auto_increment primary key,
fecha datetime not null,
subtotal int(10) not null,
iva int(10) not null,
total int(10) not null,
id_empleado int(11) not null,
rfc_provedor int(11) not null,
foreign key (id_empleado) references empleados(id_empleado),
foreign key (rfc_provedor) references provedores(rfc));

create table detalle_compras_productos(
id_detalle_compras_productos int(11) not null auto_increment primary key,
cantidad_producto int(10) not null,
total_producto int(10) not null,
id_compra int(11) not null,
id_producto int(11) not null,
foreign key (id_compra) references compras(id_compra),
foreign key (id_producto) references productos(id_producto));



create table detalle_sucursal_productos(
d_detalle_sucursal_productos int(11) not null auto_increment primary key,
existencias int(100) not null,
id_producto int(11) not null,
id_sucursal int(11) not null,
foreign key (id_producto) references productos(id_producto),
foreign key (id_sucursal) references sucursales(id_sucursal));


create table ventas(
id_venta int(10) auto_increment primary key,
fecha datetime not null,
subtotal int(10) not null,
iva int(10) not null,
total int(10) not null,
id_cliente int(11) not null,
id_empleado int(11) not null,
foreign key (id_cliente) references clientes(id_cliente),
foreign key (id_empleado) references empleados(id_empleado));


create table detalle_ventas_productos(
id_detalle_ventas_productos int(11) not null auto_increment primary key,
cantidad int(10) not null,
descuento int(10) not null,
total int(10) not null,
id_producto int(11) not null,
id_venta int(11) not null,
foreign key (id_producto) references productos(id_producto),
foreign key (id_venta) references ventas(id_venta));










