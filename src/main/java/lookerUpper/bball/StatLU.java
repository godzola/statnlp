

package lookerUpper.bball;

import lookerUpper.LookerUpper;

import java.util.TreeMap;

/**
 *
 * @author szola
 */
public class StatLU implements LookerUpper {

    private final TreeMap<String, String> sm;

    public StatLU() {
        this.sm = new TreeMap<>();
        sm.put("homers", "HR");
        sm.put("homer", "HR");
        sm.put("home run", "HR");
        sm.put("homerun", "HR");
        sm.put("hrs", "HR");
        sm.put("hr", "HR");
    }

    @Override
    public String getIdentifier(String token) {
        return  sm.get(token);
    }
    
}

