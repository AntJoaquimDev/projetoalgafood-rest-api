insert into cozinha(cozinha) values ('Tailandesa');
insert into cozinha(cozinha) values ('Indiana');
insert into cozinha(cozinha) values ('Brasileira');
insert into cozinha(cozinha) values ('Buritiense');


insert into restaurante(nome, taxa_frete,cozinha_id) values ('DGuster',1, 1);
insert into restaurante(nome, taxa_frete,cozinha_id) values ('Mangata',2.5, 2);
insert into restaurante(nome, taxa_frete,cozinha_id) values ('Nonata',0,3);
insert into restaurante(nome, taxa_frete,cozinha_id) values ('Balça',0,4);


insert into estados(nome) values ('São Paulo');
insert into estados(nome) values ('Bahia');
insert into estados(nome) values ('Pará');
insert into estados(nome) values ('Piaui');


insert into cidade(nome ,estado_id) values ('São Paulo', 1);
INSERT INTO cidade (nome, estado_id) VALUES('salvador', 2);
insert into cidade(nome ,estado_id) values ('Belem', 3);
INSERT INTO cidade (nome, estado_id) VALUES('Buriiti dos Lopes', 4);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (4, 'PIX');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3),(4, 4);