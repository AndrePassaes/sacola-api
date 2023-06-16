INSERT INTO restaurante (id, rua, cidade, complemento, cep, estado, nome_fantasia, email, cnpj, telefone)VALUES
(1L, 'Rua Restaurante 1', 'Cidade do Restaurante', 'Complemento 2', '80123000', 'Estado 1', 'Restaurante 1', 'restaurante@restaurante.com', '01203201/0001-01', '65988523156');

INSERT INTO cliente (id, rua, cidade, complemento, cep, estado, nome, email, telefone, data_nascimento)
VALUES (1L, 'Rua 1', 'Cidade 1', 'Complemento 1', '89890000', 'Estado 1', 'Cliente 1', 'cliente@cliente.com', '409988527532', '12.12.2000');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id)
VALUES (1L, true, 'Produto 1', 5.0, 1L),
       (2L, true, 'Produto 2', 6.0, 1L),
       (3L, true, 'Produto 3', 7.0, 1L);
