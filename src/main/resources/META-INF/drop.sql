--      alter table if exists tb_item_do_pedido
--         drop constraint if exists FKrvajalgy8jvpbmta6bnqd3ins ;
--
--      alter table if exists tb_item_do_pedido
--         drop constraint if exists FK8liowq3lyx0evile2fus7pelw;
--
--      alter table if exists tb_pedido
--         drop constraint if exists FKmjqm65wiaj65gia070a45vt9w;
--
--      alter table if exists tb_pedido_tb_item_do_pedido
--         drop constraint if exists FKgom38igm10cpg0uboe0rncvi5;
--
--      alter table if exists tb_pedido_tb_item_do_pedido
--         drop constraint if exists FKpd8q3pcqmsepxmr49jj7u1tcn;

     drop table if exists tb_cliente cascade;

     drop table if exists tb_item_do_pedido cascade;

     drop table if exists tb_pedido cascade;

     -- drop table if exists tb_pedido_tb_item_do_pedido cascade;

     drop table if exists tb_produto cascade;

     drop sequence if exists seq_cliente;

     drop sequence if exists seq_item_pedido;

     drop sequence if exists seq_pedido;

     drop sequence if exists seq_produto;

