package tuples;

import java.util.HashMap;

public class Transition {

    private final HashMap<Alphabet, State> transition;

    public Transition() {
        transition = new HashMap<>();
    }

    public void put(Alphabet alphabet, State state) {
        transition.put(alphabet, state);
        return;
    }

    public State get(Alphabet input) {
        return transition.get(input);
    }

    public boolean isInvalidDFATransition(AlphabetSet alphabetSet, States states) {
        if (alphabetSet.size() != transition.keySet().size()) return true;
        for (Alphabet alphabet : transition.keySet()) {
            if (!alphabetSet.contains(alphabet) || !states.contains(transition.get(alphabet))) {
                return true;
            }
        }
        return false;
    }
}
