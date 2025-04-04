ALTER TABLE pedido ADD codigo varchar(36) NOT NULL after id;

UPDATE pedido set codigo = uuid();

ALTER TABLE pedido add constraint uk_pedido_codigo unique (codigo);