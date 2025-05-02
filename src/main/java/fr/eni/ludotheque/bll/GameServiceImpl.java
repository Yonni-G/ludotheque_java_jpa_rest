package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Game;
import fr.eni.ludotheque.dal.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }
}
