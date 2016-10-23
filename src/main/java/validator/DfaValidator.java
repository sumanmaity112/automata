package validator;

import finiteAutomata.DFA;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.DfaInputParser;
import tuples.Alphabet;

public class DfaValidator implements Validator {
    private JSONObject stateMachineInfo;

    public DfaValidator(JSONObject stateMachineInfo) {
        this.stateMachineInfo = stateMachineInfo;
    }

    @Override
    public void validate() {
        DfaInputParser dfaInputParser = new DfaInputParser();
        DFA dfa = dfaInputParser.generateDfa(stateMachineInfo);

        System.out.println(ValidatorMessageService.validateMessage(stateMachineInfo.getString("type"),stateMachineInfo.getString("name"), "pass"));
        validate(stateMachineInfo, "pass-cases", dfa, dfaInputParser);

        System.out.println(ValidatorMessageService.validateMessage(stateMachineInfo.getString("type"),stateMachineInfo.getString("name"), "fail"));
        validate(stateMachineInfo, "fail-cases", dfa, dfaInputParser);
    }

    private boolean validate(JSONObject stateMachineInfo, String fieldName, DFA dfa, DfaInputParser dfaInputParser) {
        JSONArray jsonArray = stateMachineInfo.getJSONArray(fieldName);
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = dfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            String message = ValidatorMessageService.generateIntermediateMessage("       ", ValidatorMessageService.generateMessage(inputs), " is ");

            message += (dfa.isRecognize(inputs) ? "" : "not ") + "recognized";
            System.out.println(message);
        }
        return false;
    }
}
