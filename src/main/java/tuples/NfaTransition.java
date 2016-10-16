package tuples;

import java.util.HashMap;

public class NfaTransition {

    private final HashMap<Alphabet, States> transition;

    public NfaTransition() {
        transition = new HashMap<>();
    }

    public void put(Alphabet alphabet, States states) {
        transition.put(alphabet, states);
    }

    public States get(Alphabet alphabet) {
        return transition.get(alphabet);
    }
}
