-- Esquema de incidentes
create schema if not exists "incidentes";

--- Tabla de la entidad 'Usuario'
create table incidentes.usuario(
id_usuario serial primary key,
username VARCHAR(100) unique,
email VARCHAR(100) unique,
contrasenia VARCHAR(100),
"token" TEXT unique,
rol varchar(5) check (rol in ('admin', 'user'))
);

-- Comentarios en la tabla y columnas de Usuario
comment on table incidentes.usuario is 'Esta tabla guarda información sobre los usuarios del sistema';
comment on column incidentes.usuario.username is 'El nombre del usuario';
comment on column incidentes.usuario.id_usuario is 'La ID única de cada usuario';
comment on column incidentes.usuario.email is 'El correo electrónico del usuario';
comment on column incidentes.usuario.token is 'El token que se genera cuando el usuario inica sesión';
comment on column incidentes.usuario.rol is 'El rol que tiene cada usuario: administrador o usuario común';

-- Tabla de la entidad 'Incidente'
create table incidentes.incidente(
id_incidente SERIAL primary key,
titulo varchar(100),
descripcion varchar(1000),
estado varchar(20) check (estado in ('no solucionado', 'solucionado')), 
ubicacion varchar(400),
estado_ubicacion varchar(100),
municipio varchar(100),
colonia varchar(100),
calle varchar(100),
categoria varchar(100)
);

-- Comentarios en la tabla y columnas de Incidente
comment on table incidentes.incidente is 'Esta tabla guarda información sobre los incidentes registrados en el sistema';
comment on column incidentes.incidente.id_incidente is 'La ID única de cada incidente';
comment on column incidentes.incidente.titulo is 'El título que llevará el incidente';
comment on column incidentes.incidente.descripcion is 'La descripción que llevará el incidente registrado';
comment on column incidentes.incidente.estado is 'El estado en el que se encuentra algún incidente: solucionado o no solucionado';
comment on column incidentes.incidente.ubicacion is 'La ubicación completa del incidente registrado';
comment on column incidentes.incidente.estado_ubicacion is 'El estado de la república donde ocurrió el incidente';
comment on column incidentes.incidente.municipio is 'El municipio donde ocurrió el incidnete';
comment on column incidentes.incidente.colonia is 'La colonia donde ocurrió el incidente';
comment on column incidentes.incidente.calle is 'La calle donde ocurrió el incidente';
comment on column incidentes.incidente.categoria is 'La categoría a la que pertenece el incidente';

-- Tabla de la relación 'Actualizar'
create table incidentes.actualizar(
id_incidente int,
foreign key (id_incidente) references incidentes.incidente (id_incidente) on delete cascade,
id_usuario int,
foreign key (id_usuario) references incidentes.usuario (id_usuario) on delete cascade
);

-- Comentarios en la tabla y columnas de Actualizar
comment on table incidentes.actualizar is 'Esta tabla guarda información sobre las actualizaciones que algún usuario haga sobre un incidente';
comment on column incidentes.actualizar.id_incidente is 'La ID del incidente actualizado';
comment on column incidentes.actualizar.id_usuario is 'La ID del usuario que actualizó el incidente';


-- Tabla de la relación 'Registrar'
create table incidentes.registrar(
id_incidente int,
foreign key (id_incidente) references incidentes.incidente (id_incidente) on delete cascade,
id_usuario int,
foreign key (id_usuario) references incidentes.usuario (id_usuario) on delete cascade
);

-- Comentarios en la tabla y columnas de Incidente
comment on table incidentes.registrar is 'Esta tabla guarda información sobre los usuarios y los incidentes registrados por ellos';
comment on column incidentes.registrar.id_incidente is 'La ID del incidente que fue registrado por un usuario';
comment on column incidentes.registrar.id_usuario is 'La ID del usuario que registró el incidente';