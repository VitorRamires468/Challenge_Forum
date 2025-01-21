 create table topicos(
    id bigint primary key auto_increment,
    titulo varchar(100) not null unique,
    mensagem varchar(255) not null unique,
    data datetime not null,
    status varchar(20) not null,
    curso varchar(20) not null,
    usuario_id bigint not null,

    constraint fk_topico_usuario_id foreign key(usuario_id) references usuarios(id)
 );