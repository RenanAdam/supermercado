
    create table prime_ejb_crud_yaw.mercadoria (
        id bigint not null auto_increment,
        descricao varchar(255),
        nome varchar(255),
        preco double precision,
        quantidade integer,
        primary key (id)
    );
