

package lookerUpper.bball;

import lookerUpper.LookerUpper;

import java.util.TreeMap;

/**
 *
 * @author szola
 */
public class TeamLU implements LookerUpper {

    private final TreeMap<String, String> tm;

    public TeamLU() {
        this.tm = new TreeMap<>();
        tm.put("tigers", "12345");
        tm.put("detroit tigers", "12345");
    }

    @Override
    public String getIdentifier(String token) {
        return  tm.get(token);
    }
    
}
