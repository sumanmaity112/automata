package tuples;

import java.util.HashMap;

public class Transitions {

    private HashMap<State, Transition> transitions;

    public Transitions() {
        transitions = new HashMap<>();
    }

    public void put(State state, Transition transition) {
        transitions.put(state, transition);
        return;
    }

    public State get(State currentState, Alphabet input) {
        return transitions.get(currentState).get(input);
    }

    public boolean isValidDFATransitions(AlphabetSet alphabetSet, States states) {
        if (transitions.size() != states.size()) return false;
        for (State state : transitions.keySet()) {
            if (!states.contains(state)) return false;
        }

        for (Transition transition : transitions.values()) {
            if (transition.isInvalidDFATransition(alphabetSet, states)) return false;
        }
        return true;
    }
}
