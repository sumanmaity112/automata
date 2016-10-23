import exceptions.InvalidInput;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.InputParser;
import parsers.InputReader;
import validator.DfaValidator;
import validator.NfaValidator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new InvalidInput("Please give a input file name");
        }

        String jsonString = InputParser.parseJSONString(InputReader.readFile(args[0]));
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int index = 0; index < jsonArray.length(); index++) {

            JSONObject stateMachineInfo = (JSONObject) jsonArray.get(index);
            if ("DFA".equals(stateMachineInfo.getString("type").toUpperCase())) {
                DfaValidator dfaValidator = new DfaValidator(stateMachineInfo);
                dfaValidator.validate();
            } else if ("NFA".equals(stateMachineInfo.getString("type").toUpperCase())) {
                NfaValidator nfaValidator = new NfaValidator(stateMachineInfo);
                nfaValidator.validate();
            }
        }
    }
}
