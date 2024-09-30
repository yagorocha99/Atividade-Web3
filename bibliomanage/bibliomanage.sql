CREATE DATABASE IF NOT EXISTS bibliomanage;

USE bibliomanage;

CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    idAuthor INT NOT NULL,
    status ENUM('DISPONIVEL', 'EMPRESTADO', 'INDISPONIVEL') NOT NULL,
    FOREIGN KEY (idAuthor) REFERENCES author(id)
);

INSERT INTO author (name) VALUES ('Autor 1'), ('Autor 2'), ('Autor 3');

INSERT INTO book (name, date, idAuthor, status) 
VALUES ('Livro 1', '2023-01-01', 1, 'DISPONIVEL'),
       ('Livro 2', '2023-02-15', 2, 'EMPRESTADO'),
       ('Livro 3', '2023-03-20', 3, 'INDISPONIVEL');
