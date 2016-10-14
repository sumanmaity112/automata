import finiteAutomata.DFA;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.DfaInput;
import tuples.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DfaGenerator {
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
//        String jsonString = "[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]},{\"name\":\"even number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q1\"]},\"fail-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"pass-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]},{\"name\":\"at least one zero\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q2\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"10\",\"100\",\"1100\",\"01\",\"010\"],\"fail-cases\":[\"\",\"1\",\"11\",\"111\"]},{\"name\":\"at least one one\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"1\":\"q2\",\"0\":\"q1\"},\"q2\":{\"0\":\"q2\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"1\",\"10\",\"100\",\"1100\",\"01\",\"010\"],\"fail-cases\":[\"\",\"0\",\"00\",\"000\"]},{\"name\":\"string length multiple of three\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q2\"},\"q2\":{\"0\":\"q3\",\"1\":\"q3\"},\"q3\":{\"0\":\"q1\",\"1\":\"q1\"}},\"start-state\":\"q1\",\"final-states\":[\"q1\"]},\"pass-cases\":[\"000\",\"111\",\"010\",\"101\",\"111111\",\"000000\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"11\",\"10\",\"01\",\"11111\",\"00000\",\"01010\",\"10101\"]},{\"name\":\"alternate ones and zeroes beginning with zero\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q2\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q4\"},\"q2\":{\"0\":\"q4\",\"1\":\"q3\"},\"q3\":{\"0\":\"q2\",\"1\":\"q4\"},\"q4\":{\"0\":\"q4\",\"1\":\"q4\"}},\"start-state\":\"q1\",\"final-states\":[\"q3\",\"q2\"]},\"pass-cases\":[\"0\",\"01\",\"010\",\"0101\",\"01010\",\"010101\"],\"fail-cases\":[\"\",\"1\",\"10\",\"101\",\"11\",\"00\",\"0100\",\"011\"]}]";
        String jsonString = parseJSONString(readFile(args[0]));
        System.out.println(jsonString);
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject stateMachineInfo = (JSONObject) jsonArray.get(index);
            DFA dfa = DfaInput.generateDfa(stateMachineInfo);

            System.out.println("This DFA \"" + stateMachineInfo.getString("name") + "\" should pass for following inputs");
            validate(stateMachineInfo, "pass-cases", dfa);
            System.out.println("This DFA \"" + stateMachineInfo.getString("name") + "\" should fail for following inputs");
            validate(stateMachineInfo, "fail-cases", dfa);
        }
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");
    }

    private static String parseJSONString(String jsonString) {
        if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            jsonString = jsonString.replaceAll("\\\\\"", "\"");
        }
        return jsonString;
    }

    private static boolean validate(JSONObject stateMachineInfo, String fieldName, DFA dfa) {
        JSONArray jsonArray = stateMachineInfo.getJSONArray(fieldName);
        for (int index = 0; index < jsonArray.length(); index++) {
            Alphabet[] inputs = DfaInput.parseInputString(jsonArray.get(index).toString(), "");
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
