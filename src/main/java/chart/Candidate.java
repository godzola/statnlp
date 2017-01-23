
package chart;

import entity.StatEntity;
import types.bball.BballType;

import java.util.*;

import static types.bball.BballType.*;

/**
 *
 * @author szola
 */
public class Candidate {
    Set<StatEntity> dbInfo;
    ArrayList<StatEntity> matchList;
    int next;
    Integer score;
    boolean isActive;
    String entityPattern;
    SortedSet<String> queryWords;
    
    public Candidate(){
        this.dbInfo = new HashSet<>();
        this.matchList = new ArrayList<>();
        this.queryWords = new TreeSet<>();
        this.next = 0;
        this.score = 0;
        this.isActive = true;
        this.entityPattern = "";
    }
    
    public Candidate(ArrayList<StatEntity> interpretationList, Set<StatEntity> dbInfo){
        this.dbInfo = new HashSet<>();
        this.matchList = new ArrayList<>();
        this.queryWords = new TreeSet<>();
        this.matchList.addAll(interpretationList);
        this.dbInfo.addAll(dbInfo);
        this.next = -1;
        this.score = 0;
        this.isActive = true;
        // set entity string here
        this.entityPattern = getEntityPatternAsString(dbInfo);
        for(StatEntity se : interpretationList){
            if(se.Type() == BballType.UG)
                this.queryWords.add(se.TokenText());
        }
    }
    
    public Candidate(Candidate c){
        this.dbInfo = new HashSet<>();
        this.matchList = new ArrayList<>();
        this.queryWords = new TreeSet<>();
        this.dbInfo.addAll(c.dbInfo);
        this.matchList.addAll(c.matchList);
        this.queryWords.addAll(c.getQueryWords());
        this.next = c.next;
        this.score = c.score;
        this.isActive = c.isActive;
        this.entityPattern = c.getEntityPattern();
    }

    public void Score(Integer sc){
        this.score += sc;
    }

    public Integer score(){
        return this.score;
    }
    
    public void updateNext(StatEntity se){
        //System.out.println("UPDATING CANDIDATE, SETTING NEXT == " + se.getEndPos());
        this.next = se.EndPos();
    }
    
    public void updateMatchList(StatEntity se){
        ArrayList<StatEntity> temp = new ArrayList<StatEntity>(this.matchList);
        //System.out.println("UPDATING MATCH LIST: " + se.toString());
        temp.add(se);  
        this.matchList.clear();
        this.matchList.addAll(temp);        
    }
    
    public Set<String> getQueryWords(){
        return this.queryWords;
    }
    
    public int getNext(){
        return this.next;
    }
    
    public boolean isActive(){
        return this.isActive;
    }
    
    public String getEntityPattern(){
        return getEntityPatternAsString();
    }
    
    public void setIsActive(boolean val){
        this.isActive = val;
    }
    
    public String getEntityPatternAsString(){
        ArrayList<String> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder(100);
        for(StatEntity se : this.dbInfo){
            tmp.add(se.Type().name());
        }
        Collections.sort(tmp);
        for(String s : tmp){
            sb.append(s).append(",");
        }
        return sb.toString().replaceAll(",$", "");
    }

    public String getEntityPatternAsString(Set<StatEntity> in){
        ArrayList<String> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder(100);
        for(StatEntity se : in){
            tmp.add(se.Type().name());
        }
        Collections.sort(tmp);
        for(String s : tmp){
            sb.append(s).append(",");
        }
        return sb.toString().replaceAll(",$", "");
    }

    
    public String getDBInfoAsString(){
        StringBuilder sb = new StringBuilder(100);
        for(StatEntity se : this.dbInfo){
            sb.append(se.Type().name()).append("=").append(se.DbIdentifier()).append(",");
        }
        return sb.toString().replaceAll(",$", "");
    }
    
    public String getMatchListAsString(){
        StringBuilder sb = new StringBuilder(100);
        for(StatEntity se : this.matchList){
            if(se.Type() == UG){
                sb.append(se.TokenText()).append(",");
            }
            else{
                sb.append(se.Type().name()).append(",");
            }           
        }       
        return sb.toString().replaceAll(",$", "");
    }

    public String getQueryWordsAsString(){
        StringBuilder sb = new StringBuilder(100);
        for(String w : this.queryWords)    
            sb.append(w).append(",");     
        return sb.toString().replaceAll(",$", "");
    }

    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append("score=[").append(this.score).append("] ")
          .append("match_string=[").append(getMatchListAsString())
          .append("]  ")
          .append("db_info=[").append(getDBInfoAsString()).append("]  ")
          .append("entity_pattern=[").append(getEntityPatternAsString()).append("]  ")
          .append("query_words=[").append(getQueryWordsAsString()).append("]");
        return sb.toString();
    }
    
}
