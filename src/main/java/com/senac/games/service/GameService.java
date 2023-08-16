package com.senac.games.service;

import com.senac.games.entities.Game;
import com.senac.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isPresent()) {
            Game game = existingGame.get();
            game.setTitle(updatedGame.getTitle());
            game.setGenre(updatedGame.getGenre());
            return gameRepository.save(game);
        }
        return null;
    }

    public boolean deleteGame(Long id) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if (existingGame.isPresent()) {
            gameRepository.delete(existingGame.get());
            return true;
        }
        return false;
    }
}
