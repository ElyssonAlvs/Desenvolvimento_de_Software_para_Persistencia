SET SCHEMA 'public';
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    codigo INT,
    descricao VARCHAR(200),
    preco NUMERIC(10, 2),
    quantidade INT,
    ultima_entrada DATE
);
