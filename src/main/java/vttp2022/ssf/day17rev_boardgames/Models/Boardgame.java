package vttp2022.ssf.day17rev_boardgames.Models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Boardgame {

    //POJO extra functions
    //1. json string -> json Object / POJO (StringReader + JsonReader)
    /*
     * Function name: create, jStrToJObj
     * Type: static
     * params: String
     * #1 returns: Json Object
     * #2 returns: POJO (object) but call jObjToPojo
     */

    //2. json Object -> Object ( new POJO, POJO.setWtv(jsonObject.getType("<field>") )
    /*
     * Function name: create, jObjToPojo
     * Type: static
     * params: json Object
     * returns: POJO (object)
     */

    //3. Object -> jsonObject ( Json.createObjectBuilder().add(<name>, <value>).buidl() )
    /*
     * Function name: toJson, pojoToJObj
     * params:
     * returns: json Object
     */

    /*
     * Notes
     * whenever got createXXX() is just Json.createXXX(), NOT new Json.createXXX()
     * toJson doesnt take any params
     */

    //Instance Fields - 1. list out the data structure
    private Integer id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;

    //Constructors

    //Getters and Setters - 2.

    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public Integer getYear() {return this.year;}
    public void setYear(Integer year) {this.year = year;}

    public Integer getRanking() {return this.ranking;}
    public void setRanking(Integer ranking) {this.ranking = ranking;}

    public Integer getUsers_rated() {return this.users_rated;}
    public void setUsers_rated(Integer users_rated) {this.users_rated = users_rated;}

    public String getUrl() {return this.url;}
    public void setUrl(String url) {this.url = url;}

    public String getImage() {return this.image;}
    public void setImage(String image) {this.image = image;}

       
    
    public static Boardgame create (String jsonStr) {

        StringReader strReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(strReader);
        return create(reader.readObject());
    }

    public static Boardgame create (JsonObject jo) {

        //create a new object
        Boardgame boardgame = new Boardgame();
        
        //set fields
        boardgame.setId(jo.getInt("id"));
        boardgame.setName(jo.getString("name"));
        boardgame.setYear(jo.getInt("year"));
        boardgame.setRanking(jo.getInt("ranking"));
        boardgame.setUsers_rated(jo.getInt("users_rated"));
        boardgame.setUrl(jo.getString("url"));
        boardgame.setImage(jo.getString("image"));

        return boardgame;
        
    }

    public JsonObject toJson () {

        JsonObject jo = Json.createObjectBuilder()
        .add("id", id)
        .add("name", name)
        .add("year", year)
        .add("ranking", ranking)
        .add("users_rated", users_rated)
        .add("url", url)
        .add("image", image)
        .build();

        return jo;
    }
    

    

}
