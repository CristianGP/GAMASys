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
id_empleado int(10) auto_increment primary key,
numero_cuenta int(20) not null,
numero_seguro varchar(20) not null,
banco char(20) not null,
curp varchar(20) not null,
telefono int(15) not null,
calle char(30) not null,
colonia char(15) not null,
estado char(15) not null,
ciudad char(15) not null,
tipo_empleado char(25) not null,
usuario varchar(30) not null,
password varchar(30) not null,
id_sucursal int(11) not null,
foreign key (id_sucursal) references sucursales(id_sucursal));

create table provedores(
rfc  int(10) auto_increment primary key,
nombre_provedor char(15) not null,
telefono int(10) not null,
calle char(30) not null,
colonia char(15) not null,
estado char(15) not null,
ciudad char(15) not null);

create table productos(
id_producto int(10) auto_increment primary key,
nombre_producto char(50) not null,
tipo_producto char(25) not null,
marca char(25) not null,
precio_venta int(20) not null,
sku int(20) not null,
lote int(20) not null,
fecha_entrada datetime not null);


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










