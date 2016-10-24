package validator;

import finiteAutomata.NFA;
import messageService.MessageGenerator;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.NfaInputParser;
import tuples.Alphabet;

public class NfaValidator extends Validator {

    public NfaValidator(JSONObject stateMachineInfo) {
        super(stateMachineInfo);
    }

    @Override
    public void validate() {
        NfaInputParser nfaInputParser = new NfaInputParser();
        NFA nfa = nfaInputParser.generateNfa(stateMachineInfo);

        System.out.println(MessageGenerator.generateValidationMessage(stateMachineInfo.getString("type"), stateMachineInfo.getString("name"), "pass"));
        validate(stateMachineInfo.getJSONArray("pass-cases"), nfa, nfaInputParser);

        System.out.println(MessageGenerator.generateValidationMessage(stateMachineInfo.getString("type"), stateMachineInfo.getString("name"), "fail"));
        validate(stateMachineInfo.getJSONArray("fail-cases"), nfa, nfaInputParser);
    }

    private boolean validate(JSONArray jsonArray, NFA nfa, NfaInputParser nfaInputParser) {
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = nfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            String message = MessageGenerator.generateIntermediateMessage(MessageGenerator.inputAsString(inputs), " is ");
            System.out.println(generatePrintableMessage(nfa.isRecognize(inputs), message));
        }
        return false;
    }
}
