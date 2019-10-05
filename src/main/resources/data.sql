INSERT INTO user (email, password, name, created_at) VALUES ('admin@admin.com', '$2a$10$lOAE44uqqKC6ECy3TvVD4.7/VvrivGeMKOIYCukeM/Yu1jdKkNIyO', 'admin', now());
INSERT INTO user (email, password, name, created_at) VALUES ('user@user.com', '654321', 'user', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('teste detalhes', 'test', 0, 'warning', 'localhost', 1, 'Teste title', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'test', 0, 'error', 'localhost', 2, 'Titulo do erro', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'test', 0, 'error', 'localhost', 1, 'Titulo do erro', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'producao', 0, 'error', 'localhost', 1, 'Erro db', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'producao', 0, 'error', 'localhost', 2, 'Titulo do erro', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'test', 0, 'error', 'localhost', 2, 'Acesso negado', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'dev', 0, 'error', 'localhost', 2, 'Titulo do erro', now());