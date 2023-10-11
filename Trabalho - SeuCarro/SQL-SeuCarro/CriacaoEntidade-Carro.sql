set schema 'public';
CREATE TABLE carro (
    idCarro SERIAL PRIMARY KEY,
    marca VARCHAR(150),
    modelo VARCHAR(150),
    configuracao VARCHAR(150),
    anoFabricacao INTEGER,
    tipoCombustivel VARCHAR(150),
    preco DOUBLE PRECISION
);