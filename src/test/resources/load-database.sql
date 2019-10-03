INSERT INTO user (email, password, name, created_at) VALUES ('admin@admin.com', '123456', 'admin', now());
INSERT INTO user (email, password, name, created_at) VALUES ('user@user.com', '654321', 'user', now());

INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('teste detalhes', 'test', 0, 'warning', 'localhost', 1, 'teste title', now());
INSERT INTO log (details, environment, filed, level, source, user_id, title, created_at)
VALUES ('Detalhes do erro', 'test', 0, 'error', 'localhost', 2, 'Titulo do erro', now());
