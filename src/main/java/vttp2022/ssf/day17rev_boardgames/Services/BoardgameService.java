package vttp2022.ssf.day17rev_boardgames.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.ssf.day17rev_boardgames.Models.Boardgame;
import vttp2022.ssf.day17rev_boardgames.Repositories.BoardgameRepository;

@Service
public class BoardgameService {
    
    @Autowired
    private BoardgameRepository boardgameRepo;

    public Optional<Boardgame> getBoardgameById (String id) {
        //a. Get the payload 
        //      - Check for payload, if nothing, return empty box
        //b. return the POJO 
        //      - create using POJO's method

        //1. Create the somewhat "box" to contain the payload
        String result = boardgameRepo.get(id);

        //1. Check for payload, if nothing, return empty box
        if (null == result)
            return Optional.empty();

        //2. Return the POJO using POJO's method to Create it
        return Optional.of(Boardgame.create(result));
    }
    

    public List<String> keys() {
        List<String> keys = boardgameRepo.keys();
        return keys;
    }

    public Integer count () {
        Integer count = boardgameRepo.count();
        return count;
    }
}
