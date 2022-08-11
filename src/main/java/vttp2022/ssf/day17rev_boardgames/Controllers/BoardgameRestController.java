package vttp2022.ssf.day17rev_boardgames.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.ssf.day17rev_boardgames.Models.Boardgame;
import vttp2022.ssf.day17rev_boardgames.Services.BoardgameService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping (path="/boardgame", produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardgameRestController {

    @Autowired
    private BoardgameService boardgameSvc;
    
    @GetMapping (path="/{id}")
    public ResponseEntity<String> getBoardgame
    (@PathVariable String id) {

        //1. Create a box to hold the object
        Optional<Boardgame> opt = boardgameSvc.getBoardgameById(id);
        //1b. Create the boardgameSvc method

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("error", "Id %s not found".formatted(id))
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.toString());
        }

        Boardgame boardgame = opt.get();
        //opt.get() bc using just boardgame will only get the reference and not the actual value
        return ResponseEntity.ok(boardgame.toJson().toString()); 
        //this is jsonObject.toString(), NOT object.toString();
    }

}
