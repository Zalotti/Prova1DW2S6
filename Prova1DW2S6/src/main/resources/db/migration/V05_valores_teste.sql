--Valores Testes para Usuários--

INSERT INTO user (name, email, password, active)
VALUES ('Biana', 'bibi@email.com', '222222', true);

INSERT INTO user (name, email, password, active)
VALUES ('Babi', 'babi@email.com', '321321', true);

--Valores Testes para Tag--


INSERT INTO tag (name)
VALUES ('PC');

INSERT INTO tag (name)
VALUES ('Celular')

--Valores Testes para Tarefa--

INSERT INTO task (description, observation, task_date, user_id, tag_id)
VALUES ('Devo chamar o encanador', 'A casa está tendo problemas com encanamento', '2023-09-01', 2, 1);

INSERT INTO task (description, observation, task_date, user_id, tag_id)
VALUES ('Devo comprar um computador', 'Preciso de um computador para estudar', '2023-09-01', 2, 2);


