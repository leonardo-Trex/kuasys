-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Edição 01: Homem-Aranha
INSERT INTO tb_edicao 
(nome, descricao, preco, datacadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero) 
VALUES 
('Homem-Aranha: Edição Definitiva Vol. 1', 'A origem do herói mais amado da Marvel em capa dura.', 159.90, CURRENT_TIMESTAMP, 1, '2023-05-15', '9786559511235', 5000, 1, '17x26 cm', 1);

-- Edição 02: Batman: A Piada Mortal
INSERT INTO tb_edicao (nome, descricao, preco, datacadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero) 
VALUES ('Batman: A Piada Mortal', 'A clássica história de Alan Moore sobre a dualidade entre Batman e Coringa.', 64.00, CURRENT_TIMESTAMP, 1, '2020-03-10', '9788573514831', 3000, 1, '18x27 cm', 1);

-- Edição 03: Akira Vol. 1
INSERT INTO tb_edicao (nome, descricao, preco, datacadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero) 
VALUES ('Akira - Vol. 1', 'O clássico cyberpunk de Katsuhiro Otomo em edição de luxo.', 89.00, CURRENT_TIMESTAMP, 1, '2017-06-01', '9788545702870', 4500, 1, '18x25 cm', 2);

-- Edição 04: Watchmen - Edição Definitiva
INSERT INTO tb_edicao (nome, descricao, preco, datacadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero) 
VALUES ('Watchmen', 'A graphic novel que mudou a história dos quadrinhos.', 199.00, CURRENT_TIMESTAMP, 12, '2019-11-20', '9788583683933', 2000, 1, '19x28 cm', 1);

-- Edição 05: Turma da Mônica - Laços
INSERT INTO tb_edicao (nome, descricao, preco, datacadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero) 
VALUES ('Graphic MSP: Laços', 'Uma releitura emocionante dos personagens de Mauricio de Sousa.', 45.50, CURRENT_TIMESTAMP, 1, '2013-06-01', '9788583680215', 10000, 1, '19x26 cm', 2);