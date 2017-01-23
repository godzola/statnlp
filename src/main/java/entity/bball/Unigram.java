/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.bball;

import entity.StatEntity;
import types.bball.BballType;

/**
 *
 * @author szola
 *
 */
public class Unigram extends StatEntity {
    public Unigram() {
        this.Type(BballType.UG);
    }
    
    public Unigram(StatEntity se){
        this.Type(BballType.UG);
        this.TokenText(se.TokenText());
        this.StartPos(se.StartPos());
        this.EndPos(se.EndPos());
        this.DbIdentifier(se.DbIdentifier());
    }
        
    public Unigram(String t, int st, int e, String dbIdenfifier){
        this.Type(BballType.UG);
        this.StartPos(st);
        this.EndPos(e);
        this.TokenText(t);
        this.DbIdentifier(dbIdenfifier);
    }
}
