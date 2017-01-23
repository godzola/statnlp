package entity.bball;

import entity.StatEntity;
import types.bball.BballType;

/**
 *
 * @author scot zola
 */
public class Team extends StatEntity {
    
    

    public Team() {
        this.Type(BballType.TEAM);
    }
    
    public Team(StatEntity se){
        this.Type(BballType.TEAM);
        this.TokenText(se.TokenText());
        this.StartPos(se.StartPos());
        this.EndPos(se.EndPos());
        this.DbIdentifier(se.DbIdentifier());
    }
    
    public Team(String t, int st, int e, String dbIdenfifier){
        this.Type(BballType.TEAM);
        this.StartPos(st);
        this.EndPos(e);
        this.TokenText(t);
        this.DbIdentifier(dbIdenfifier);
    }
}
