

package entity;

import types.bball.BballType;

/**
 *
 * @author scot zola
 *
 */

//
// TODO: change this to use the builder pattern.
//
public abstract class StatEntity {
    private String tokenText;
    private String dbIdentifier;
    
    private int startPos;
    private int endPos;
    
    private BballType type;
    
    

    public BballType Type() {
        return type;
    }

    public StatEntity Type(BballType type) {
        this.type = type;
        return this;
    }

    public String TokenText() {
        return tokenText;
    }

    public StatEntity TokenText(String tokenText) {
        this.tokenText = tokenText;
        return this;
    }

    public String DbIdentifier() {
        return dbIdentifier;
    }

    public StatEntity DbIdentifier(String dbIdentifier) {
        this.dbIdentifier = dbIdentifier;
        return this;
    }

    public int StartPos() {
        return startPos;
    }

    public StatEntity StartPos(int startPos) {
        this.startPos = startPos;
        return this;
    }

    public int EndPos() {
        return endPos;
    }

    public StatEntity EndPos(int endPos) {
        this.endPos = endPos;
        return this;
    }
    
    public int Length() {
        return this.EndPos() - this.StartPos();
    }

//    public StatEntity copyMe(StatEntity in){
//        StatEntity se = new StatEntity;
//        se.setType(in.getType());
//        se.setStartPos(in.getStartPos());
//        se.setEndPos(in.getEndPos());
//        se.setTokenText(in.getTokenText());
//        se.setDbIdentifier(in.getDbIdentifier());
//    }
    
    @Override
    public String toString() {
        return "StatEntity:" +  
               " tokenText [" + tokenText + "]  " +
               " dbIdentifier [" + dbIdentifier + "]  " + 
               " startPos [" + startPos + "]  " + 
               " endPos [" + endPos + "]  " +
               " type [" + type + "]  ";
    }
    
    
}
