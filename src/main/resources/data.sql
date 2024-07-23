ALTER TABLE autor ALTER COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO autor (nome) VALUES
            ('Espada de Assis'),
            ('Monteiro Roubado'),
            ('Clarice Espectro'),
            ('Jorge Odiado'),
            ('José de Alavanca');

INSERT INTO biografia (autor_id, biografia_id, detalhes) VALUES
            (1, 1, 'Foi um escritor brasileiro, amplamente reconhecido por críticos, estudiosos, escritores e leitores como o maior expoente da literatura.'),
            (2, 2, 'Foi um advogado, promotor, escritor, editor, ativista e tradutor.'),
            (3, 3, 'Foi uma escritora e jornalista brasileira nascida na Ucrânia. Autora de romances, contos e ensaios.'),
            (4, 4, 'Um dos mais famosos e traduzidos escritores brasileiros de todos os tempos, sendo o autor mais adaptado para o cinema, teatro e televisão.'),
            (5, 5, 'Foi um jornalista, advogado, político e escritor romântico brasileiro.');

ALTER TABLE categoria ALTER COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO categoria (nome) VALUES
            ('Romance'),
            ('Terror'),
            ('Drama'),
            ('Comédia'),
            ('Policial');

ALTER TABLE emprestimo ALTER COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO emprestimo (data_emprestimo, data_retorno) VALUES
            ('2022-03-01 10:00:00', '2022-03-10 10:00:00'),
            ('2023-05-04 15:00:00', '2023-05-14 15:00:00'),
            ('2024-09-05 17:00:00', '2024-09-15 17:00:00'),
            ('2023-07-08 20:00:00', '2023-07-18 20:00:00');

ALTER TABLE livro ALTER COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO livro (titulo, preco, data_publicacao, situacao, autor_id, emprestimo_id) VALUES
            ('Dom no Murro', 29.99, '1899-01-01', 'I', 1, 1),
            ('Boquinha', 19.99, '1920-02-01', 'D', 2, NULL),
            ('A Hora da Lua', 29.99, '1977-01-02', 'I', 3, 3),
            ('Sitio Vermelho', 39.99, '1920-03-10', 'D', 2, NULL);

INSERT INTO livro_categoria (livro_id, categoria_id) VALUES
            (1, 1),
            (2, 2),
            (3, 3),
            (4, 4);



