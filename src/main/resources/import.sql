-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


-- Inserindo coleções de quadrinhos
INSERT INTO tb_colecao (nome, descricao, data_inicio_publicacao, data_fim_publicacao) 
VALUES ('Batman: Lendas do Cavaleiro das Trevas', 'Série focada em arcos fechados e histórias icônicas do herói.', '1989-11-01', '2007-03-01');

INSERT INTO tb_colecao (nome, descricao, data_inicio_publicacao, data_fim_publicacao) 
VALUES ('Sandman: Edição Definitiva', 'A jornada de Morpheus, o Rei do Sonhar, em formato de luxo.', '2010-05-15', '2013-12-20');

INSERT INTO tb_colecao (nome, descricao, data_inicio_publicacao, data_fim_publicacao) 
VALUES ('The Walking Dead', 'A sobrevivência de Rick Grimes em um mundo pós-apocalíptico.', '2003-10-08', '2019-07-03');

INSERT INTO tb_colecao (nome, descricao, data_inicio_publicacao, data_fim_publicacao) 
VALUES ('Watchmen', 'Edição comemorativa da obra-prima de Alan Moore e Dave Gibbons.', '1986-09-01', '1987-10-01');

INSERT INTO tb_colecao (nome, descricao, data_inicio_publicacao, data_fim_publicacao) 
VALUES ('X-Men: Dinastia M', 'O evento que mudou a realidade dos mutantes da Marvel.', '2005-06-01', '2005-11-01');

-- Edição 01: Homem-Aranha
INSERT INTO tb_edicao 
(nome, descricao, preco, data_cadastro, numero, data_publicacao, isbn, tiragem, codigo_tipo_capa, dimensoes, codigo_genero, colecao_id) 
VALUES 
('Homem-Aranha: Edição Definitiva Vol. 1', 'A origem do herói mais amado da Marvel em capa dura.', 159.90, CURRENT_TIMESTAMP, 1, '2023-05-15', '9786559511235', 5000, 1, '17x26 cm', 1, 1),
-- Edição 02: Batman: A Piada Mortal
('Batman: A Piada Mortal', 'A clássica história de Alan Moore sobre a dualidade entre Batman e Coringa.', 64.00, CURRENT_TIMESTAMP, 1, '2020-03-10', '9788573514831', 3000, 1, '18x27 cm', 1, 1),
-- Edição 03: Akira Vol. 1
('Akira - Vol. 1', 'O clássico cyberpunk de Katsuhiro Otomo em edição de luxo.', 89.00, CURRENT_TIMESTAMP, 1, '2017-06-01', '9788545702870', 4500, 1, '18x25 cm', 2, 1),
-- Edição 04: Watchmen - Edição Definitiva
('Watchmen', 'A graphic novel que mudou a história dos quadrinhos.', 199.00, CURRENT_TIMESTAMP, 12, '2019-11-20', '9788583683933', 2000, 1, '19x28 cm', 1, 1),
-- Edição 05: Turma da Mônica - Laços
('Graphic MSP: Laços', 'Uma releitura emocionante dos personagens de Mauricio de Sousa.', 45.50, CURRENT_TIMESTAMP, 1, '2013-06-01', '9788583680215', 10000, 1, '19x26 cm', 2, 1);

-- Pessoa
INSERT INTO pessoa (nome, nacionalidade, data_nascimento) 
VALUES 
('João Silva', 'Brasileira', '1995-05-20'),
('Marie Curie', 'Polonesa', '1867-11-07'),
('Linus Torvalds', 'Finlandesa', '1969-12-28'),
('Ada Lovelace', 'Britânica', '1815-12-10'),
('Gabriel Garcia Marquez', 'Colombiana', '1927-03-06');