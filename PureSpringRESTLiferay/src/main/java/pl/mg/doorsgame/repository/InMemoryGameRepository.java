package pl.mg.doorsgame.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.mg.doorsgame.exception.GameNotFoundException;
import pl.mg.doorsgame.exception.InvalidGameIdFormatException;
import pl.mg.doorsgame.model.Game;
import pl.mg.doorsgame.model.GameStatus;

/**
 * 
 * @author Maciej Gzik
 *
 */
@Repository
public class InMemoryGameRepository implements GameRepository {

    private static Map<Integer, Game> games = new HashMap<>();

    @Autowired
    DoorRepository doorRepository;

    @Override
    public Game create() {
        int nextGameId = getNextGameId();
        Game createdGame = new Game.GameBuilder(nextGameId).gameStatus(GameStatus.NOT_STARTED)
                .doors(doorRepository.generateDoors()).build();
        games.put(nextGameId, createdGame);
        return createdGame;
    }

    @Override
    public Game retrieve(int gameId) throws InvalidGameIdFormatException, GameNotFoundException {
        if (gameId < 0) {
            throw new InvalidGameIdFormatException();
        }
        for (Integer searchedGameId : games.keySet()) {
            if (searchedGameId == gameId) {
                return games.get(searchedGameId);
            }
        }
        throw new GameNotFoundException();
    }

    @Override
    public void remove(int gameId) throws InvalidGameIdFormatException, GameNotFoundException {
        if (gameId <0){
            throw new InvalidGameIdFormatException();
        }
        
        for (Integer searchedGameId : games.keySet()) {
            if (searchedGameId == gameId) {
                games.remove(searchedGameId);
                return;
            }
        }
        
        throw new GameNotFoundException();
    }

    private int getNextGameId() {
        if (games != null) {
            if (games.size() == 0) {
                return 0;
            }
            int maxValue = 0;
            Set<Integer> gameIds = games.keySet();
            for (Integer gameId : gameIds) {
                maxValue = Math.max(maxValue, gameId);
            }
            maxValue = maxValue + 1;
            return maxValue++;
        } else {
            games = new HashMap<>();
            return 0;
        }
    }

}
