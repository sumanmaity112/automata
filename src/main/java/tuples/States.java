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

    public int size() {
        return states.size();
    }

    public State getByIndex(int index) {
        return states.get(index);
    }
}
