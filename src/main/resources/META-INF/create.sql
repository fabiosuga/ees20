

CREATE SEQUENCE public.seq_cliente
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
CREATE SEQUENCE public.seq_item_pedido
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
CREATE SEQUENCE public.seq_pedido
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
CREATE SEQUENCE public.seq_produto
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

 create table tb_cliente (
    id int4 not null primary key,
    cpf varchar(14),
    nome varchar(50) not null,
    sobrenome varchar(50) not null,
    unique (cpf)
 );

 create table tb_item_do_pedido (
    id int4 not null primary key,
     quantidade int4 check (quantidade>=1),
     id_pedido int4 not null,
     id_produto int4 not null
 );

 create table tb_pedido (
    id int4 not null primary key,
     data date,
     id_cliente int4
 );


 create table tb_produto (
    id int4 not null primary key,
     descricao varchar(255) not null
 );


 alter table if exists tb_item_do_pedido
    add constraint FKrvajalgy8jvpbmta6bnqd3ins
    foreign key (id_pedido)
    references tb_pedido;

 alter table if exists tb_item_do_pedido
    add constraint FK8liowq3lyx0evile2fus7pelw
    foreign key (id_produto)
    references tb_produto;

 alter table if exists tb_pedido
    add constraint FKmjqm65wiaj65gia070a45vt9w
    foreign key (id_cliente)
    references tb_cliente;


   ALTER TABLE tb_cliente ALTER COLUMN id SET DEFAULT nextval('seq_cliente');

ALTER TABLE tb_item_do_pedido ALTER COLUMN id SET DEFAULT nextval('seq_item_pedido');


ALTER TABLE tb_pedido ALTER COLUMN id SET DEFAULT nextval('seq_pedido');

 ALTER TABLE tb_produto ALTER COLUMN id SET DEFAULT nextval('seq_produto');
   