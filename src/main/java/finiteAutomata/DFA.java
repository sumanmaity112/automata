package finiteAutomata;

import exceptions.InvalidTransition;
import tuples.*;

public class DFA extends FiniteAutomata {
    private Transitions transitions;

    private DFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, Transitions transitions) {
        super(alphabetSet, initialState, states, finalStates);
        this.transitions = transitions;
    }

    public static DFA generateDFA(States states, State initialState, AlphabetSet alphabetSet, FinalStates finalStates, Transitions transitions) {
        if (!transitions.isValidDFATransitions(alphabetSet, states)) {
            throw new InvalidTransition("Please check your transitions table");
        }
        return new DFA(states, initialState, alphabetSet, finalStates, transitions);
    }

    public boolean isRecognize(Alphabet[] inputs) {
        boolean validInput = isValidInput(inputs);
        return validInput ? isValid(inputs) : generateErrorMessage(inputs);
    }

    private boolean isValid(Alphabet[] inputs) {
        if (isEmptyInput(inputs)) {
            return isOnFinalState(initialState);
        }
        State currentState = initialState;
        for (Alphabet input : inputs) {
            currentState = findNextState(currentState, input);
        }
        return isOnFinalState(currentState);
    }

    private State findNextState(State currentState, Alphabet input) {
        return transitions.get(currentState, input);
    }

}
