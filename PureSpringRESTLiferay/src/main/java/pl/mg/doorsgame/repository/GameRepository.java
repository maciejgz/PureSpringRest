package pl.mg.doorsgame.repository;

import pl.mg.doorsgame.model.Game;

public interface GameRepository {
    
    public Game create();
    
    public Game retrieve(int gameId);
    
    public void remove(int gameId);
    
}
