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
}
