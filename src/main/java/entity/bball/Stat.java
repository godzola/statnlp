package entity.bball;

import entity.StatEntity;
import types.bball.BballType;

/**
 *
 * @author scot zola
 */
public class Stat extends StatEntity {
    
    

    public Stat() {
        this.Type(BballType.STAT);
    }
    
    public Stat(StatEntity se){
        this.Type(BballType.STAT);
        this.TokenText(se.TokenText());
        this.StartPos(se.StartPos());
        this.EndPos(se.EndPos());
        this.DbIdentifier(se.DbIdentifier());
    }
    
    public Stat(String t, int st, int e, String dbIdenfifier){
        this.Type(BballType.STAT);
        this.StartPos(st);
        this.EndPos(e);
        this.TokenText(t);
        this.DbIdentifier(dbIdenfifier);
    }
}

