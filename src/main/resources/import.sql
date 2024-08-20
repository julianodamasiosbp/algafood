
#Cozinha

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');
insert into cozinha (id, nome) values (4, 'Chinesa');
insert into cozinha (id, nome) values (5, 'Turca');

#Restaurante

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Camaroes', 10, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Coco Bambu', 15, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Comeu Morreu', 5, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Fogo de Chão', 0, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Food', 22.87, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('China in Box', 13, 4);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk', 7, 5);

#FormaPagamento

insert into forma_pagamento (descricao) values ('Credito');
insert into forma_pagamento (descricao) values ('Debito');
insert into forma_pagamento (descricao) values ('Dinheiro');

#Permissao

insert into permissao (nome, descricao) values ('administrador', 'Consegue acessar todas as funcionalidades');
insert into permissao (nome, descricao) values ('suporte', 'Consegue acessar todas as funcionalidades, exceto financeiro');

#Estado

insert into estado (nome) values ('Rio Grande do Norte');
insert into estado (nome) values ('Paraiba');
insert into estado (nome) values ('Ceara');

#Cidade

insert into cidade (nome, estado_id) values ('Natal', 1);
insert into cidade (nome, estado_id) values ('Joao Pessoa', 2);
insert into cidade (nome, estado_id) values ('Fortaleza', 3);

# Restaurante Forma de Pagamento
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 1), (5, 2), (6, 3), (7, 3), (4, 2), (5, 3);