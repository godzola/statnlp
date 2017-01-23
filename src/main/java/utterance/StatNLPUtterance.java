

package utterance;

import entity.StatEntity;
import lookerUpper.LookerUpper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author scot zola
 *
 */
public class StatNLPUtterance {

    String origQry;
    ArrayList<StatEntity> toks;
    ArrayList<StatEntity> origQryList;
    Set<StatEntity> meaningfulWds;
    
    LookerUpper plu;
    LookerUpper ulu;
    LookerUpper slu;
    LookerUpper tlu;
    LookerUpper ylu;
    
    public StatNLPUtterance(String origQ){
        // the normalized original query
        this.origQry = Normalize(origQ);
       
        this.plu = new lookerUpper.bball.PlayerLU();
        this.ulu = new lookerUpper.bball.UnigramLU();
        this.tlu = new lookerUpper.bball.TeamLU();
        this.slu = new lookerUpper.bball.StatLU();
        this.ylu = new lookerUpper.bball.YearLU();

        this.origQryList = new ArrayList<>();
        
        // all possible tokens in the original query
        this.toks = new ArrayList<>();
        Tokenize(this.origQry);
       
        // wd/phraseclevel interpretations of the meaningful tokens
        this.meaningfulWds= new HashSet<>();
        this.wordLevelInterpret();             
    }

    public String getOrigQry() {
        return origQry;
    }

    public ArrayList<StatEntity> getToks() {
        return toks;
    }

    public ArrayList<StatEntity> getOrigQryList() {
        return origQryList;
    }

    public Set<StatEntity> getMeaningfulWds() {
        return meaningfulWds;
    }
    
    public static String Normalize(String in){
        String out = in.toLowerCase();
        out = out.replaceAll("\\W", " ");
        out = out.replace("\\s+", " ");
        return out;
    }
    
    // here, we only care about the words/phrases we have menaings for
    // Also, if we add a new type, we need to look it up here
    private void wordLevelInterpret(){
        for(StatEntity se : this.toks){
            String dbid = null;
            if((dbid = plu.getIdentifier(se.TokenText())) != null ){
                this.meaningfulWds.add(new entity.bball.Player(se.TokenText(), se.StartPos(), se.EndPos(), dbid));
                dbid = null;
            }
            
            if((dbid = tlu.getIdentifier(se.TokenText())) != null ){
                this.meaningfulWds.add(new entity.bball.Team(se.TokenText(), se.StartPos(), se.EndPos(), dbid));
                dbid = null;
            }
            
            if((dbid = slu.getIdentifier(se.TokenText())) != null ){
                this.meaningfulWds.add(new entity.bball.Stat(se.TokenText(), se.StartPos(), se.EndPos(), dbid));
                dbid = null;
            }
            
            if((dbid = ylu.getIdentifier(se.TokenText())) != null ){
                this.meaningfulWds.add(new entity.bball.Year(se.TokenText(), se.StartPos(), se.EndPos(), dbid));
                dbid = null;
            }
        }
    }
    
    public static String makeString(String[] words, int start, int end) {
        StringBuilder tmp= new StringBuilder(100);
        for (int i = start; i < end; i++) {
            tmp.append(words[i]).append(" ");
        }
        return tmp.toString().trim();
    }
    
    public final ArrayList<StatEntity> Tokenize(String in){
        ArrayList<StatEntity> toks = new ArrayList<StatEntity>();
        String[] a = in.split("\\s+");
        for(int st = 0; st < a.length; st++){
            for(int end = st + 1; end <= a.length; end++ ){
               
               String ss = makeString(a,st,end);
               entity.bball.Unigram ug = new entity.bball.Unigram(ss,st,end,ss);
               // toks are all the possibly meaningful tokens
               this.toks.add(ug);
               // add the unigrams to a separate list that
               // we want to go over at interpretation time
               if(ug.Length() == 1){
                   this.origQryList.add(ug);
               }
            }
        }
        return toks;
    }
    
}
