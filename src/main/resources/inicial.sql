create schema SenacGames;
use SenacGames;
CREATE TABLE game (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      genre VARCHAR(255) NOT NULL
);
INSERT into game(id, title, genre) values(1, "Teste", "Teste");
SELECT * FROM game;