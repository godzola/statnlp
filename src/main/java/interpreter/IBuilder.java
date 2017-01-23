/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import chart.Candidate;
import com.google.common.collect.Sets;
import entity.StatEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author szola
 *
 */
public class IBuilder {
 
    public Set<Candidate> candidates;
    public Set<Set<StatEntity>> allAdditions; 
    
    
    public IBuilder(){
        this.candidates = new HashSet<>();
        this.allAdditions = new HashSet<>();
    }
    
    public void buildAllInterpretations(ArrayList<StatEntity> wdLevel, Set<StatEntity> meaningfulWords){
        addAllInterpretations(wdLevel, meaningfulWords);
    }
   
    
    public ArrayList<StatEntity> generateInterpretation(ArrayList<StatEntity> origQ, Set<StatEntity> addMe){
        ArrayList<StatEntity> newInterp = new ArrayList<>();

        // debug - recursive, so it prints in a weird order,
        //   here's what we got, then here's how we got there
        // Also, I use sets to get rid of duplicates - need to check that out
//        System.out.println("\n\nBEGIN - Starting new interp");
//        System.out.println("Trying to add: ");
//        for(StatEntity se : addMe){
//            System.out.println("ADDITION: " + se.toString());
//        }

        for(int curPos = 0; curPos < origQ.size();){
//            System.out.println("looking at additions");
//            System.out.println("Position in original string: " + curPos);
//            System.out.println("Orig Token at this position: " + origQ.get(curPos));
            boolean replaced = false;
            for(StatEntity se : addMe){
//                System.out.println("First Addition");
//                System.out.println("Token Text: " + se.TokenText());
//                System.out.println("Start Pos: " + se.StartPos());
//                System.out.println("End Pos: " + se.EndPos());
                if(se.StartPos() == curPos){
//                    System.out.println("Found Addition! " + se.toString());
//                    System.out.println("Should add this token here");
                    newInterp.add(se);
                    curPos = se.EndPos();
                    replaced = true;
                }
            } // for all additions
            if(!replaced){
//                System.out.println("No Addition Adding Original Token: " + origQ.get(curPos));
                newInterp.add(origQ.get(curPos));
                curPos++;
            }
        }// for all original tokens
        
//        System.out.println("FINALLY - Generated new interpretation:");
//        for(StatEntity se : newInterp){
//            System.out.println(se.toString());
//        }
//        System.out.println("END\n\n");
        return newInterp;
    }
    
    public void addAllInterpretations(ArrayList<StatEntity> origQ, Set<StatEntity> meaningfulWords){
   
        // this is the key call to google library code I didn't want to write
        this.allAdditions = Sets.powerSet(meaningfulWords);
        
//        // debug
//        for(Set<StatEntity> s : this.allAdditions){
//            System.out.println("\nAddition");
//            for(StatEntity se : s)
//                System.out.println(se);
//        }
        
        // here, s represents the db info we need to keep
        // to make a query
        for(Set s : this.allAdditions){
            //need this check or the empty subset will kill the generation process
            if(!s.isEmpty())
                this.candidates.add(new Candidate(generateInterpretation(origQ, s), s));
        }       
    }
    
    public void printCandidateList(){
        
        for(Candidate c : this.candidates){
                System.out.println(c.toString()); 
        }
    }
    
}
