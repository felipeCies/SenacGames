package com.senac.games.controller;

import com.senac.games.entities.Game;
import com.senac.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        // Lógica para criar o jogo, por exemplo, salvar no banco de dados
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }
    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        if (gameRepository.existsById(id)) {
            game.setId(id);
            return gameRepository.save(game);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }
}

