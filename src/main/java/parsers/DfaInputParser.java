package parsers;

import finiteAutomata.DFA;
import org.json.JSONException;
import org.json.JSONObject;
import tuples.*;

import java.util.Iterator;
import java.util.Set;

public class DfaInputParser extends InputParser {
    public DFA generateDfa(JSONObject jsonObject) throws JSONException {
        assignTuple(jsonObject);
        Transitions transitions = createTransitionTable(delta);

        return DFA.generateDFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    private Transitions createTransitionTable(JSONObject delta) {
        Transitions transitions = new Transitions();
        Set keySet = delta.keySet();
        for (Object next : keySet) {
            Transition transition = createTransition(delta.getJSONObject(next.toString()));
            transitions.put(new State(next.toString()), transition);
        }

        return transitions;
    }

    private Transition createTransition(JSONObject jsonObject) {
        Iterator keys = jsonObject.keys();
        Transition transition = new Transition();
        while (keys.hasNext()) {
            Object next = keys.next();
            transition.put(new Alphabet(next.toString()), new State(jsonObject.getString(next.toString())));
        }
        return transition;
    }
}
