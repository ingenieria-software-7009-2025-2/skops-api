CREATE SCHEMA IF NOT EXISTS "incidentes";

CREATE TABLE incidentes.usuario (
    id_usuario SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE,
    contrasenia VARCHAR(100),
    token TEXT UNIQUE
);

create table incidentes.incidente(
id_incidente SERIAL primary key,
titulo varchar(100),
descripcion varchar(1000),
estado varchar(50) check (estado in ('no solucionado', 'solucionado')), 
ubicacion varchar(400),
estado_ubicacion varchar (100),
municipio varchar(100),
colonia varchar(100),
calle varchar(100)
);

ALTER TABLE incidentes.incidente
ADD categoria varchar(100);

ALTER TABLE incidentes.usuario
ADD rol varchar(100) check (rol in ('admin', 'user'));


create table incidentes.actualizar(
fk_incidente int,
foreign key (fk_incidente) references incidentes.incidente (id_incidente) on delete cascade,
fk_usuario int,
foreign key (fk_usuario) references incidentes.usuario (id_usuario) on delete cascade
);

create table incidentes.registrar(
fk_incidente int,
foreign key (fk_incidente) references incidentes.incidente (id_incidente) on delete cascade,
fk_usuario int,
foreign key (fk_usuario) references incidentes.usuario (id_usuario) on delete cascade
);

