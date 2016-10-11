import finiteAutomata.DFA;
import tuples.*;

import java.util.ArrayList;

public class DfaGenerator {
    public static void main(String[] args) {
        ArrayList<Alphabet> alphabets = new ArrayList<>();
        Alphabet alphabet0 = new Alphabet("0");
        Alphabet alphabet1 = new Alphabet("1");
        Alphabet alphabet2 = new Alphabet("2");
        alphabets.add(alphabet0);
        alphabets.add(alphabet1);
        alphabets.add(alphabet2);
        AlphabetSet alphabetSet = new AlphabetSet(alphabets);
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");

        ArrayList<State> states = new ArrayList<>();
        states.add(q0);
        states.add(q1);
        states.add(q2);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(q0);

        Transitions transitions = new Transitions();
        Transition q0Transition = new Transition();
        Transition q1Transition = new Transition();
        Transition q2Transition = new Transition();

        q0Transition.put(alphabet0, q0);
        q0Transition.put(alphabet1, q1);
        q0Transition.put(alphabet2, q2);

        q1Transition.put(alphabet0, q1);
        q1Transition.put(alphabet1, q2);
        q1Transition.put(alphabet2, q0);

        q2Transition.put(alphabet0, q2);
        q2Transition.put(alphabet1, q0);
        q2Transition.put(alphabet2, q1);

        transitions.put(q0, q0Transition);
        transitions.put(q1, q1Transition);
        transitions.put(q2, q2Transition);

        Alphabet inputs[] = {alphabet1, alphabet1, alphabet1, alphabet1, alphabet0, alphabet2};
        DFA dfa = DFA.generateDFA(new States(states), q0, alphabetSet, new FinalStates(finalStates), transitions);

        StringBuilder builder = new StringBuilder();
        for (Alphabet input : inputs) {
            builder.append(input.toString());
        }

        String message = "The input string " + builder.toString() + " is ";

        message += (dfa.isRecognize(inputs) ? "" : "not ") + "recognize by the given DFA" ;
        System.out.println(message);
    }
}
