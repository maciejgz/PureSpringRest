package pl.mg.doorsgame.repository;

import pl.mg.doorsgame.exception.GameNotFoundException;
import pl.mg.doorsgame.exception.InvalidGameIdFormatException;
import pl.mg.doorsgame.model.Game;

public interface GameRepository {
    
    public Game create();
    
    public Game retrieve(int gameId) throws InvalidGameIdFormatException, GameNotFoundException;
    
    public void remove(int gameId);
    
}
