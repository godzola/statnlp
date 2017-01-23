

package config;

import java.util.HashMap;

/**
 *
 * @author scot zola
 *
 */
public class EntityPatterns {
    HashMap<String, Integer> patterns;

    // the pattern of domain entities in an utterance maps to its length
    public EntityPatterns(){
        this.patterns = new HashMap<>();
        this.patterns.put("PLAYER,STAT,YEAR", 3);
        this.patterns.put("PLAYER,STAT", 2);
        this.patterns.put("STAT,YEAR", 2);
        this.patterns.put("PLAYER,YEAR", 2);
        this.patterns.put("PLAYER", 1);
        this.patterns.put("STAT", 1);
        this.patterns.put("YEAR", 1);
    }

    public Integer hasMatchingPattern(String s){
        return patterns.get(s);
    }
    public HashMap<String, Integer> getPatterns() {
        return patterns;
    }     
    
}
