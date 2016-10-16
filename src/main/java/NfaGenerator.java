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
        }
    }



}
