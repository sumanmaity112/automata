package validator;

import finiteAutomata.DFA;
import messageService.MessageGenerator;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.DfaInputParser;
import tuples.Alphabet;

public class DfaValidator extends Validator {

    public DfaValidator(JSONObject stateMachineInfo) {
        super(stateMachineInfo);
    }

    @Override
    public void validate() {
        DfaInputParser dfaInputParser = new DfaInputParser();
        DFA dfa = dfaInputParser.generateDfa(stateMachineInfo);

        System.out.println(MessageGenerator.generateValidationMessage(stateMachineInfo.getString("type"), stateMachineInfo.getString("name"), "pass"));
        validate(stateMachineInfo.getJSONArray("pass-cases"), dfa, dfaInputParser);

        System.out.println(MessageGenerator.generateValidationMessage(stateMachineInfo.getString("type"), stateMachineInfo.getString("name"), "fail"));
        validate(stateMachineInfo.getJSONArray("fail-cases"), dfa, dfaInputParser);
    }

    private boolean validate(JSONArray jsonArray, DFA dfa, DfaInputParser dfaInputParser) {
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = dfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            String message = MessageGenerator.generateIntermediateMessage(MessageGenerator.inputAsString(inputs), " is ");
            System.out.println(generatePrintableMessage(dfa.isRecognize(inputs), message));
        }
        return false;
    }
}
