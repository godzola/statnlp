

package driver;

import chart.Candidate;
import interpreter.IBuilder;
import scorer.Scorer;
import utterance.StatNLPUtterance;

/**
 *
 * @author scot zola
 */
public class run_me {
    
    // the NLP algorithm is this:
    //   1.  tokenize an utterance into all possible ngrams
    //       note, we don't limit the length of the ngrams but
    //       we can, and maybe should. We'll know the length of
    //       the longest meaningful substring and this will speed
    //       things up a bit.
    //
    //       IN:  an utterance: a rod homers
    //       OUT: a list: [a, rod, homers, a rod, rod homers, a rod homers]
    //
    //   2.  assign a word/phrase level interpretation to the ngrams
    //       via our "looker uppers." This essentially mutates our
    //       list of ngrams into a list of ngrams plus meaningful tokens
    //
    //       IN: a list: [a, rod, homers, a rod, rod homers, a rod homers]
    //       OUT: a smarter list: [a, rod, homers, STAT:homers, a rod, PLAYER:a rod, rod homers, a rod homers]
    //
    //   3.  here we build all possible interpretations of the smarter list
    //       we returned in step 2. In this case we want to entertain 4 interpretations:
    //
    //       interpretation 0:    a, rod, homers
    //       interpretation 1: PLAYER:a rod, homers
    //       interpretation 2: a, rod, STAT:homers
    //       interpretation 3:  PLAYER:a rod, STAT:homers
    //
    //  Interpretation 0 is actually no interpretation at all. Interpretations 1 and 2 are ok, but
    //  not as good as interpretation 3. That's for the scorer to determine
    //
    // Also, notice that we pass in the query to the utterance constructor, then we make a interpretation
    // builder and a scorer. If we make this a web service, we would want to build the scorer and the looker uppers
    // and the interpretation builder once, when we start the service. Just a little re-organization.
    //

    StatNLPUtterance ut;
    IBuilder ib;
    Scorer sc;


    public run_me(String u){
        ut = new StatNLPUtterance(u);
//        ut = new StatNLPUtterance("bah blah blah");
        ib = new IBuilder();
        sc = new Scorer();
    }    
    
    public static void main(String[] args) {
        
        // this makes a new StatNLPUtterance which parses the query
        // into the usable and important pieces
        run_me rm = new run_me("How many homers did A-Rod have in 2009?");

        /*
         * you can uncomment all these methods to examine the guts of the process
         *
        // this method just gives us back the orig query
        System.out.println("RUN_ME: Original Query As a list");
        for(StatEntity se : rm.ut.getOrigQryList())
            System.out.println(se.toString());
        
        
        // this provides access to all the possible tokens in case we need them
        //  for some reason I haven't thought of yet
        System.out.println("RUN_ME: NG LIST");
        for(StatEntity se : rm.ut.getToks()){
            System.out.println("RUN_ME: " + se.toString());
        }
            
        //
        // this shows us the meaningfully interpreted words/phrases
        // within whatever domain we're working
        System.out.println("RUN_ME: WDS LIST");
        for(StatEntity se : rm.ut.getMeaningfulWds()){
            System.out.println("RUN_ME: " + se.toString());
        }
        */
        
        // this slightly complicated method generates all our interpretations
        System.out.println("INTERPRETATIONS");
        rm.ib.buildAllInterpretations(rm.ut.getOrigQryList(), rm.ut.getMeaningfulWds());
        for(Candidate c : rm.ib.candidates){
            System.out.println("RUN_ME: CANDIDATE INTERPRETATION\n" + c.toString() + "\n\n");
        }

        // there is obvious room for optimization here, even just by reordering
        System.out.println("\n\nSCORING\n");
        for(Candidate c : rm.ib.candidates){
            c.Score(rm.sc.scoreCandidate(c));
        }


        for(Candidate c : rm.ib.candidates) {
            System.out.println("RUN_ME: SCORED INTERPRETATION\n" + c.toString() + "\n\n");
        }

        // here's the original utterance, do whatever you want with it
        System.out.println("RUN_ME: original query [" + rm.ut.getOrigQry() + "]");

    }
    
}
