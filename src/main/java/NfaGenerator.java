import finiteAutomata.NFA;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.InputReader;
import parsers.NfaInputParser;
import tuples.Alphabet;

import java.io.IOException;

public class NfaGenerator {
    public static void main(String[] args) throws IOException {
        NfaInputParser nfaInputParser = new NfaInputParser();
        String jsonString = nfaInputParser.parseJSONString(InputReader.readFile(args[0]));
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int index = 0; index < jsonArray.length(); index++) {

            JSONObject stateMachineInfo = (JSONObject) jsonArray.get(index);
            NFA nfa = nfaInputParser.generateNfa(stateMachineInfo);

            System.out.println("This " + stateMachineInfo.getString("type").toUpperCase() + " \"" + stateMachineInfo.getString("name") + "\" should pass for following inputs");
            validate(stateMachineInfo, "pass-cases", nfa, nfaInputParser);


            System.out.println("This " + stateMachineInfo.getString("type").toUpperCase() + " \"" + stateMachineInfo.getString("name") + "\" should fail for following inputs");
            validate(stateMachineInfo, "fail-cases", nfa, nfaInputParser);
        }
    }

    private static boolean validate(JSONObject stateMachineInfo, String fieldName, NFA nfa, NfaInputParser nfaInputParser) {
        JSONArray jsonArray = stateMachineInfo.getJSONArray(fieldName);
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = nfaInputParser.parseInputString(jsonArray.get(index).toString(), "");
            String message = "       " + InputReader.generateMessage(inputs);

            message += (nfa.isRecognize(inputs) ? "" : "not ") + "recognized";
            System.out.println(message);
        }
        return false;
    }


}
