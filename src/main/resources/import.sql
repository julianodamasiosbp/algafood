INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');
INSERT INTO cozinha (id, nome) VALUES (3, 'Brasileira');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Camarões', 10, 3);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Zumbi', 15, 3);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tuk Tuk Comida Indiana', 5, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Delivery', 8, 1);

INSERT INTO estado (id, nome) VALUES (1, 'Rio Grande do Norte');
INSERT INTO estado (id, nome) VALUES (2, 'Ceara');

INSERT INTO cidade (nome, estado_id) VALUES ('Natal', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Fortaleza', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Mossoro', 1);

INSERT INTO forma_pagamento (descricao) VALUES ('Credito');
INSERT INTO forma_pagamento (descricao) VALUES ('Debito');
INSERT INTO forma_pagamento (descricao) VALUES ('Boleto');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');

INSERT INTO permissao (nome, descricao) VALUES ('consultar_cozinhas', 'Permite consultar cozinhas');
INSERT INTO permissao (nome, descricao) VALUES ('editar_cozinhas', 'Permite editar cozinhas');