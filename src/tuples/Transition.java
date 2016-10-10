package tuples;

import java.util.HashMap;

public class Transition {

    private final HashMap<Alphabet, State> transition;

    public Transition() {
        transition = new HashMap<>();
    }

    public void put(Alphabet alphabet, State state){
        transition.put(alphabet, state);
        return;
    }

    public State get(Alphabet input) {
        return transition.get(input);
    }
}
