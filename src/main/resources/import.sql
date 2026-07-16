--===================================================================================================================================================================
--=                                                                        QUADRINISTA!                                                                           =
--=================================================================================================================================================================
INSERT INTO tb_quadrinista (data_nascimento, data_atualizacao, data_cadastro, nacionalidade, nome)
VALUES (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Uruguaio', 'Layo'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'Mauricio'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'Caio'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'Jonas'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'Matheus'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'Romulo'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Brasileiro', 'João'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Estadunidense', 'John'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Estadunidense', 'Burg'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Uruguaio', 'Layo'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Inglês', 'Alan'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Inglês', 'Mathew'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Inglês', 'Harry'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Inglês', 'Harry Potter'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Espanhol', 'Lamine'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Espanhol', 'Vasco'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Espanhol', 'Leon'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Norueguês', 'Haaland'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Norueguês', 'Odergaard'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Norueguês', 'Bergman'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Australiano', 'Pierre'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Australiano', 'Surf'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Francês', 'Moebius'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Chileno', 'Jodorovysk'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Francês', 'Pumik'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Francês', 'Ruk'),
       (CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Francês', 'Çoan');

--===================================================================================================================================================================
--=                                                                        QUADRINHO!                                                                                 =
--===================================================================================================================================================================
INSERT INTO tb_quadrinho (codigo_genero, data_atualizacao, data_cadastro, sinopse, titulo)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de heróis', 'Guerra Civil'),
       (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de dinossauros', 'Era esquecida'),
       (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de robos', 'Combustivel'),
       (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de robôs gigantes', 'Cenozoico'),
       (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de samurais', 'Tetano'),
       (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de pistoleiros', 'Gripe'),
       (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Briga de samurais e pistoleiros', 'Corte inflamado');


--===================================================================================================================================================================
--=                                                                        USUÁRIO!                                                                                 =
--===================================================================================================================================================================

INSERT INTO tb_usuario (data_cadastro, data_atualizacao, ativo, cpf, telefone, keycloak_id, email, nome)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '111.222.333-01', '(11) 98888-0001', 'kc-usr-0001',
        'joao.silva@email.com', 'João Silva'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '111.222.333-02', '(11) 98888-0002', 'kc-usr-0002',
        'maria.santos@email.com', 'Maria Santos'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '111.222.333-03', '(21) 98888-0003', 'kc-usr-0003',
        'pedro.oliveira@email.com', 'Pedro Oliveira'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '111.222.333-04', '(21) 98888-0004', 'kc-usr-0004',
        'ana.souza@email.com', 'Ana Souza'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, '111.222.333-05', '(31) 98888-0005', 'kc-usr-0005',
        'lucas.pereira@email.com', 'Lucas Pereira');



--===================================================================================================================================================================
--=                                                                        EDITORA!                                                                                 =
--===================================================================================================================================================================
INSERT INTO tb_editora (data_atualizacao, data_cadastro, cnpj, nome)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '32432', 'Fantasia'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '32232', '50%'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '12432', 'Tinta amarela');


--===================================================================================================================================================================
--=                                                                        CREDITO!                                                                                 =
--===================================================================================================================================================================
INSERT INTO tb_credito (data_atualizacao, data_cadastro, quadrinho_id, quadrinista_id, funcao)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 'Roteirista'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 'Colorista'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2, 'Desenhista');

--===================================================================================================================================================================
--=                                                                        USUÁRIO!                                                                                 =
--===================================================================================================================================================================


--===================================================================================================================================================================
--=                                                                         COLEÇÂO!                                                                                =
--===================================================================================================================================================================
-- TODO EU PRECISO DE EDIÇÔES! (AH edições se tornam produtos!)
INSERT INTO tb_colecao (data_fim_publicacao, data_inicio_publicacao, data_atualizacao, data_cadastro, editora_id,
                        descricao, nome)
VALUES (CURRENT_DATE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'Hqs do batima', 'Batman colection'),
       (CURRENT_DATE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'Hqs do doido de ferro', 'Robo colection'),
       (CURRENT_DATE, CURRENT_DATE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'Hqs do doido de pedra', 'Porradaria');
--===================================================================================================================================================================
--=                                                                        PRODUTO!                                                                                 =
--===================================================================================================================================================================
-- TODO EU PRECISO DE COLEÇÕES!
INSERT INTO tb_produto (data_publicacao, numero, preco, tiragem, codigo_tipo_capa, colecao_id, data_atualizacao,
                        data_cadastro, editora_id, quadrinho_id, isbn, tipo_produto, dimensoes, descricao, nome,
                        estoque)
VALUES (CURRENT_DATE, 1, '143.85', 15, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, '32412', 'EDICAO', '123x23',
        'aff', 'Porradaria', 100),
       (CURRENT_DATE, 1, '143.85', 15, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, '8412', 'EDICAO', '123x23',
        'Luta', 'Porradaria', 100),
       (CURRENT_DATE, 1, '143.85', 15, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, '39412', 'EDICAO', '123x23',
        'Descrição', 'Porradaria', 100);