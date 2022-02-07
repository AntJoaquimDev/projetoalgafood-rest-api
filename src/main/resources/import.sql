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
