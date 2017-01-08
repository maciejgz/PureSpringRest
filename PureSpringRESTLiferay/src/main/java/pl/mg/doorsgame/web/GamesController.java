package pl.mg.doorsgame.web;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.mg.doorsgame.exception.GameNotFoundException;
import pl.mg.doorsgame.exception.InvalidGameIdFormatException;
import pl.mg.doorsgame.model.Game;
import pl.mg.doorsgame.repository.GameRepository;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    GameRepository gameRepository;

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getGame(@PathVariable("gameId") int gameId)
            throws InvalidGameIdFormatException, GameNotFoundException {
        Game retrievedGame = gameRepository.retrieve(gameId);
        return new ResponseEntity<Object>(retrievedGame, HttpStatus.OK);
    }

    /**
     * Creating the game with usage location of the created user
     * @return
     * @throws URISyntaxException 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createGame() throws URISyntaxException {
        Game createdGame = gameRepository.create();
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(createdGame, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeGame(@PathVariable("gameId") int gameId)
            throws InvalidGameIdFormatException, GameNotFoundException {
        gameRepository.remove(gameId);
        return new ResponseEntity<Object>("Game: " + gameId + " removed", HttpStatus.OK);
    }

    ////// Exception handling
    @ExceptionHandler(value = { InvalidGameIdFormatException.class })
    public ResponseEntity<String> handleInvalidGameId(InvalidGameIdFormatException e) {
        return new ResponseEntity<String>("Invalid game id format", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { GameNotFoundException.class })
    public ResponseEntity<String> handleGameNotFound(GameNotFoundException e) {
        return new ResponseEntity<String>("Game for that id is not found", HttpStatus.BAD_REQUEST);
    }

}
