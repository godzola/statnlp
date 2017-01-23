
package scorer;

import chart.Candidate;
import config.BBallPatterns;
import config.EntityPatterns;

/**
 *
 * @author szola
 *
 */
public class Scorer {
    private static final Integer exactPatternMatch = 100000;
    private static final Integer exactEntityMatch = 1000;
    private static final Integer playerBias = 800;
    // we could make this a global object
    // that's loaded once and all scorers access
    BBallPatterns patternInfo;
    EntityPatterns entityPatterns;
    

    public Scorer(){
        patternInfo = new BBallPatterns();
        entityPatterns = new EntityPatterns();
    }


    public Integer scoreCandidate(Candidate c){
        Integer score = 0;
        // check the query for exact match
        System.out.println("\n\nScorer: new interpretation [" + c.getMatchListAsString() + "]" );
        System.out.println("Scorer: Check 1 - exact query match");
        for(String s : patternInfo.getPatterns()){
            if(s.equals(c.getMatchListAsString())) {
                System.out.println("Scorer: Checking pattern [" + s + "] against interpretation" );
                System.out.println("Scorer: EXACT PATTERN MATCH");
                score += this.exactPatternMatch;
                System.out.println("Scorer: Cur Score = " + score);
            }
        }

        // check the domain entities
        System.out.println("Scorer: Check 2 - candidates entity pattern [" + c.getEntityPatternAsString() + "]");
        Integer entityPatternLength = entityPatterns.hasMatchingPattern(c.getEntityPatternAsString());
        if(entityPatternLength != null){
            System.out.println("Scorer: ENTITY MATCH!");
            score += entityPatternLength * exactEntityMatch;
            System.out.println("Scorer: Cur Score = " + score);
        }

        //
        // bias answers towards players, for example
        //
        System.out.println("Scorer: Check 3 - bias check");
        if(c.getEntityPatternAsString().matches(".*PLAYER.*")){
            System.out.println("Scorer: HAS A PLAYER NAME MATCH!");
            score += playerBias;
            System.out.println("Scorer: Cur Score = " + score);
        }

        System.out.println("Scorer: returning score " + score);
        return score;
    }
    
}
