package controller.input.exception;

import java.io.IOException;
public class InputFlushedException extends InputException {
    public InputFlushedException() {
        super("System.in closed");
    }
}