package messageService;

import tuples.Alphabet;

public class MessageGenerator {
    public static String inputAsString(Alphabet[] inputs) {
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

    public static String generateValidationMessage(String type, String name, String flag) {
        return "This " + type.toUpperCase() + " \"" + name + "\" should " + flag + " for following inputs";
    }

    public static String generateIntermediateMessage(String inputs, String suffix) {
        return "     " + " " + inputs + " " + suffix;
    }
}
