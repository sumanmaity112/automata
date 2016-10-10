package tuples;

import java.util.ArrayList;

public class States {
    private ArrayList<State> states;

    public States(ArrayList<State> states) {
        this.states = states;
    }

    public boolean contains(State state) {
        return states.contains(state);
    }
}
