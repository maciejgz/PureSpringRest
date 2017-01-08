package pl.mg.doorsgame.model;

import java.util.List;

public class Game {

    private final int gameId;
    private List<Door> doors;
    private GameStatus gameStatus;

    public Game(GameBuilder gameBuilder) {
        this.gameId = gameBuilder.gameId;
        this.gameStatus = gameBuilder.gameStatus;
        this.doors = gameBuilder.doors;
        
    }

    public static class GameBuilder {
        private final int gameId;
        private GameStatus gameStatus;
        private List<Door> doors;

        public GameBuilder(int gameId) {
            this.gameId = gameId;
        }

        public GameBuilder gameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public GameBuilder doors(List<Door> doors) {
            this.doors = doors;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

    public int getGameId() {
        return gameId;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Door> getDoors() {
        return doors;
    }

}
