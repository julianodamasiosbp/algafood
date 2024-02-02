
#Cozinha

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');

#Restaurante

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Camaroes', 10, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Coco Bambu', 15, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Comeu Morreu', 5, 2);

#FormaPagamento

insert into forma_pagamento (nome) values ('Credito');
insert into forma_pagamento (nome) values ('Debito');