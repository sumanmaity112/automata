package finiteAutomata;

import exceptions.InvalidInput;
import messageService.MessageGenerator;
import tuples.*;

public abstract class FiniteAutomata {
    protected final AlphabetSet alphabetSet;
    protected final States states;
    protected final State initialState;
    protected final FinalStates finalStates;

    public FiniteAutomata(AlphabetSet alphabetSet, State initialState, States states, FinalStates finalStates) {
        this.alphabetSet = alphabetSet;
        this.initialState = initialState;
        this.states = states;
        this.finalStates = finalStates;
    }

    protected boolean generateErrorMessage(Alphabet[] inputs) {
        throw new InvalidInput("Please check input " + MessageGenerator.inputAsString(inputs));
    }

    protected boolean isValidInput(Alphabet[] inputs) {
        for (Alphabet input : inputs) {
            if (!input.equals(new Alphabet("")) && !(alphabetSet.contains(input))) {
                return false;
            }
        }
        return true;
    }

    protected boolean isOnFinalState(State state) {
        return finalStates.contains(state);
    }

    protected boolean isEmptyInput(Alphabet[] inputs) {
        return inputs.length == 1 && inputs[0].equals(new Alphabet(""));
    }
}
