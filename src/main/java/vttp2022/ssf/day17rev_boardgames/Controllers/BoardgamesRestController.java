package vttp2022.ssf.day17rev_boardgames.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.Json;
import vttp2022.ssf.day17rev_boardgames.Services.BoardgameService;

@RestController
@RequestMapping(path="boardgames")
public class BoardgamesRestController {

    @Autowired
    private BoardgameService boardgameSvc;
    
    @GetMapping
    public ResponseEntity<String> getBoardgames
    (@RequestParam (name="offset", defaultValue="0") Integer offset,
    @RequestParam (name="limit", defaultValue="5") Integer limit) {

        //Create a "box" to hold the data
        List<String> allKeys = boardgameSvc.keys().subList(offset, offset + limit);
        //specify keys using subList, [ offset(index) [start index]] 
        //and [ offset + limit(amount) [end index]]

        //create new list to display "/boardgame/%s"
        List<String> keyRange = allKeys.stream()
            .map(k -> "/boardgame/%s".formatted(k))
            .toList();

        //create new json array to send to response body
        JsonArray arr = Json.createArrayBuilder(keyRange).build();

        return ResponseEntity.ok(arr.toString());
    }

    @GetMapping (path="count")
    public ResponseEntity<String> getBoardgamesCount() {

        Integer count = boardgameSvc.count();

        JsonObject payload = Json.createObjectBuilder()
            .add("count", count)
            .build();

        return ResponseEntity.ok(payload.toString());
    }
}
