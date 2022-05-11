package controller.commands.exceptions;

public class ScriptOverloadException extends ScriptException {
    public ScriptOverloadException() {
        super("Depth of script stack more than maximum or recursion detected");
    }
}
