package finiteAutomata;

import tuples.*;

public class NFA extends FiniteAutomata {
    private NfaTransitions transitions;

    private NFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, NfaTransitions transitions) {
        super(alphabetSet, initialState, states, finalStates);
        this.transitions = transitions;
    }

    public static NFA generateNFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, NfaTransitions transitions) {
        return new NFA(states, initialState, alphabetSet, finalStates, transitions);
    }


}
