package validator;

import org.json.JSONObject;

abstract class Validator {
    JSONObject stateMachineInfo;

    Validator(JSONObject stateMachineInfo) {
        this.stateMachineInfo = stateMachineInfo;
    }

    public abstract void validate();

    String generatePrintableMessage(boolean isPassed, String prefix) {
        return prefix + (isPassed ? "" : "not ") + "recognized";
    }
}
