package lookerUpper.bball;

import lookerUpper.LookerUpper;

import java.util.ArrayList;

/**
 *
 * @author szola
 *
 */
public class YearLU implements LookerUpper {

    public ArrayList<String> years;
    
    public YearLU(){
        this.years = new ArrayList<>();
        
    years.add("2000");
    years.add("2001");
    years.add("2002");
    years.add("2003");
    years.add("2004");
    years.add("2005");
    years.add("2006");
    years.add("2007");
    years.add("2008");
    years.add("2009");
    years.add("2010");
    years.add("2011");
    years.add("2012");
    years.add("2013");
    }
    
    @Override
    public String getIdentifier(String token) {
        // the thing is what it is
        if(years.contains(token))
            return token;
        return null;
    }
    
}

