 create table usuarios(
    id bigint primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    ativo int not null default 1
 );