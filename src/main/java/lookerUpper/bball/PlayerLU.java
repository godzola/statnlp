

package lookerUpper.bball;

import lookerUpper.LookerUpper;

import java.util.TreeMap;

/**
 *
 * @author szola
 *
 */
public class PlayerLU implements LookerUpper {

    private final TreeMap<String, String> pm;

    public PlayerLU() {
        this.pm = new TreeMap<>();
        pm.put("alex rodriguez", "12345");
        pm.put("a rod", "12345");
    }

    @Override
    public String getIdentifier(String token) {
        return  pm.get(token);
    }
    
}
