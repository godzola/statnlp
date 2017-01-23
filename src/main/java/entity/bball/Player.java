
package entity.bball;

import entity.StatEntity;
import types.bball.BballType;

/**
 *
 * @author scot zola
 *
 */
public class Player extends StatEntity {
    
    

    public Player() {
        this.Type(BballType.PLAYER);
    }
    
    public Player(StatEntity se){
        this.Type(BballType.PLAYER);
        if(se.TokenText() != null)
            this.TokenText(se.TokenText());
        this.StartPos(se.StartPos());
        this.EndPos(se.EndPos());
        if(se.DbIdentifier() != null)
            this.DbIdentifier(se.DbIdentifier());
    }
    
    public Player(String t, int st, int e, String dbIdenfifier){
        this.Type(BballType.PLAYER);
        this.StartPos(st);
        this.EndPos(e);
        this.TokenText(t);
        this.DbIdentifier(dbIdenfifier);
    }
}
