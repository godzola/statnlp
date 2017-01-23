/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interpreter;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author szola
 *
 */
public class setTest {
    
    
    public static void main(String[] args) {
        Set<String> s = new HashSet<>(); 
        s.add("a");
        s.add("b");
        s.add("c");
        
        
        System.out.println("Set\n" + s);
        System.out.println("\nPOWER SET");
        
        
        Set<Set<String>> pow = Sets.powerSet(s);
        for(Set<String> subset : pow ){
            if(subset.isEmpty())
                continue;
            System.out.println(subset);
        }
    }
}
