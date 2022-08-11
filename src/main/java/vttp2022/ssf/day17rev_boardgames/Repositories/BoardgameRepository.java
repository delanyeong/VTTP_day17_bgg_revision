package vttp2022.ssf.day17rev_boardgames.Repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardgameRepository {

    @Autowired
    @Qualifier ("redislab")
    private RedisTemplate<String, String> redisTemplate;

    public String get (String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);
    }
    
    public List<String> keys() {

        //box according to documentation
        Set<String> keys = redisTemplate.keys("[0-9]*");

        List<String> result = new LinkedList<>(keys);
        return result.stream()
            .map(v -> Integer.parseInt(v)) //all keys from String to Int
            .sorted()                      //Int to sort by numbering
            .map(v -> v.toString())        //change back to String
            .toList();                     //from type Stream back to List
    }

    public Integer count() {

        //box according to documentation
        Set<String> keys = redisTemplate.keys("[0-9]*");
        return keys.size();
    }
}
