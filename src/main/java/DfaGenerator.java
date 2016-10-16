import finiteAutomata.DFA;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.DfaInputParser;
import parsers.InputReader;
import tuples.Alphabet;

import java.io.IOException;

public class DfaGenerator {
    public static void main(String[] args) throws IOException {
        DfaInputParser dfaInputParser = new DfaInputParser();
        String jsonString = dfaInputParser.parseJSONString(InputReader.readFile(args[0]));
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject stateMachineInfo = (JSONObject) jsonArray.get(index);
            DFA dfa = dfaInputParser.generateDfa(stateMachineInfo);

            System.out.println("This DFA \"" + stateMachineInfo.getString("name") + "\" should pass for following inputs");
            validate(stateMachineInfo, "pass-cases", dfa, dfaInputParser);
            System.out.println("This DFA \"" + stateMachineInfo.getString("name") + "\" should fail for following inputs");
            validate(stateMachineInfo, "fail-cases", dfa, dfaInputParser);
        }
    }

    private static boolean validate(JSONObject stateMachineInfo, String fieldName, DFA dfa, DfaInputParser dfaInputParser) {
        JSONArray jsonArray = stateMachineInfo.getJSONArray(fieldName);
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = dfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            StringBuilder builder = new StringBuilder();
            for (Alphabet input : inputs) {
                builder.append(input.toString());
            }
            String inputsAsString = builder.toString();
            if (inputsAsString.equals("")) {
                inputsAsString = "\"\"";
            }
            String message = inputsAsString + " is ";

            message += (dfa.isRecognize(inputs) ? "" : "not ") + "recognized";
            System.out.println(message);
        }
        return false;
    }
}
