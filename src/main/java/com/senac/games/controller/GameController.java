package com.senac.games.controller;

import com.senac.games.entities.Game;
import com.senac.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        Optional<Game> optionalGame = gameRepository.findById(id);

        if (!optionalGame.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Game existingGame = optionalGame.get();
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setGenre(updatedGame.getGenre());

        Game updated = gameRepository.save(existingGame);
        return ResponseEntity.ok(updated);
    }


@DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameRepository.deleteById(id);
    }
}

