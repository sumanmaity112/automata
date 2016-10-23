package validator;

import tuples.Alphabet;

public class ValidatorMessageService {
    public static String generateMessage(Alphabet[] inputs) {
        StringBuilder builder = new StringBuilder();
        for (Alphabet input : inputs) {
            builder.append(input.toString());
        }
        String inputsAsString = builder.toString();
        if (inputsAsString.equals("")) {
            inputsAsString = "\"\"";
        }
        return inputsAsString;
    }

    public static String validateMessage(String type, String name, String flag) {
        return "This " + type.toUpperCase() + " \"" + name + "\" should " + flag + " for following inputs";

    }

    public static String generateIntermediateMessage(String prefix, String inputs, String suffix) {
        return " " + prefix + " " + inputs + " " + suffix;
    }
}
