package entity.bball;

import entity.StatEntity;
import types.bball.BballType;

/**
 *
 * @author scot zola
 */
public class Year extends StatEntity {
    
    

    public Year() {
        this.Type(BballType.YEAR);
    }
    
    public Year(StatEntity se){
        this.Type(BballType.YEAR);
        this.TokenText(se.TokenText());
        this.StartPos(se.StartPos());
        this.EndPos(se.EndPos());
        this.DbIdentifier(se.DbIdentifier());
    }
    
    public Year(String t, int st, int e, String dbIdenfifier){
        this.Type(BballType.YEAR);
        this.StartPos(st);
        this.EndPos(e);
        this.TokenText(t);
        this.DbIdentifier(dbIdenfifier);
    }
}

