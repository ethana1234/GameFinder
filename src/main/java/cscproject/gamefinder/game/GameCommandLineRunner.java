package cscproject.gamefinder.game;


import cscproject.gamefinder.user.User;
import cscproject.gamefinder.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(GameCommandLineRunner.class);
    @Autowired
    private GameService gameService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String[] args) {
        List<Game> gameList = new ArrayList<>();
        try{
            gameList = Parse.createGameList();
        }
        catch(Exception e) {
            log.debug("Error parsing json");
        }
        gameService.insertGameList(gameList);
        log.debug(gameService.getAllGames().toString());
        userRepository.save(new User("henlo", "password"));

    }
}