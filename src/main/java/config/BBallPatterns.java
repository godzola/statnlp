package config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author szola
 *
 */
public class BBallPatterns {
    private final ArrayList<String> patterns;
//    private final ArrayList<String> entityList;
    private final ArrayList<Set<String>> qryWords; 
   
    public BBallPatterns(){
            
        this.patterns = new ArrayList<>();
//        this.entityList = new ArrayList<>();
        this.qryWords = new ArrayList<>();
        this.patterns.add("how,many,STAT,did,PLAYER,have,in,YEAR");
        this.patterns.add("how,many,STAT,did,PLAYER,have");
        this.patterns.add("how,many,STAT,does,PLAYER,have");        
        this.patterns.add("how,many,STAT,did,PLAYER,have,all,time");
        this.patterns.add("PLAYER,STAT,in,YEAR");
        this.patterns.add("PLAYER,STAT");
        this.patterns.add("who,has,the,most,STAT,in,YEAR");        
        this.patterns.add("what,were,PLAYER,s,stats,in,YEAR"); 
        
        for(String s : this.patterns){
            loadPatterns(s);
            loadQueryWords(s);
//            loadEntities(s);
        }
    }

    private void loadPatterns(String s){
        // dont need this now but we will when we load these from a config file or select them from the DB
    }

    public ArrayList<String> getPatterns() {
        return patterns;
    }
    
    // creates a list of sets of the query words in the patterns
    //  to be matched at score time
    private void loadQueryWords(String s){
        Set<String> tmp = new HashSet<>();
        String[] sa = s.split(","); 
        for(String ts : sa){
            if(ts.matches("[a-z]"))
               tmp.add(ts);
        }
        this.qryWords.add(tmp);
        
    }

    public ArrayList<Set<String>> getQryWords() {
        return qryWords;
    }

    // move this to its own class!!
    //
    // creates a list of things like "PLAYER,STAT"
    //  to be matched against at scoring time
//    private void loadEntities(String s){
//        ArrayList<String> tmp = new ArrayList<>();
//        String[] sa = s.split(",");
//        StringBuilder sb = new StringBuilder(100);
//        for(String ts : sa){
//            if(ts.matches("[A-Z]"))
//                tmp.add(ts);
//        }
//        Collections.sort(tmp);
//        for(String ts : tmp){
//            sb.append(ts).append(",");
//        }
//        this.entityList.add(sb.toString().replaceAll(",$", ""));
//    }
//
//    public ArrayList<String> getEntityList() {
//        return entityList;
//    }



}
