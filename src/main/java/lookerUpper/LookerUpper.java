
package lookerUpper;

/**
 *
 * @author scot zola
 *
 */
public interface LookerUpper {

    //
    // These are preloaded maps of ngrams -> db identifiers
    // we'll use to look up domain objects. It doesn't specifically
    // need to be an interface, but I thought maybe validating
    // things like years or words might be different, I can change
    // this if I need to later
    //
    //public Boolean isType(BballType type, StatEntity e);
    String getIdentifier(String token);
    
}
