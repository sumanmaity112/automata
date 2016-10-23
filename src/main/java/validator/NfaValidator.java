package validator;

import finiteAutomata.NFA;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.NfaInputParser;
import tuples.Alphabet;

public class NfaValidator implements Validator {
    private JSONObject stateMachineInfo;

    public NfaValidator(JSONObject stateMachineInfo) {

        this.stateMachineInfo = stateMachineInfo;
    }

    @Override
    public void validate() {
        NfaInputParser nfaInputParser = new NfaInputParser();
        NFA nfa = nfaInputParser.generateNfa(stateMachineInfo);

        System.out.println(ValidatorMessageService.validateMessage(stateMachineInfo.getString("type"),stateMachineInfo.getString("name"), "pass"));
        validate(stateMachineInfo, "pass-cases", nfa, nfaInputParser);

        System.out.println(ValidatorMessageService.validateMessage(stateMachineInfo.getString("type"),stateMachineInfo.getString("name"), "fail"));
        validate(stateMachineInfo, "fail-cases", nfa, nfaInputParser);
    }

    private static boolean validate(JSONObject stateMachineInfo, String fieldName, NFA nfa, NfaInputParser nfaInputParser) {
        JSONArray jsonArray = stateMachineInfo.getJSONArray(fieldName);
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = nfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            String message = ValidatorMessageService.generateIntermediateMessage("       ", ValidatorMessageService.generateMessage(inputs), " is ");

            message += (nfa.isRecognize(inputs) ? "" : "not ") + "recognized";
            System.out.println(message);
        }
        return false;
    }
}
