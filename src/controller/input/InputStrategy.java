package controller.input;


import java.io.IOException;

public interface InputStrategy {
    void handleRequest(InputRequest inputAction);
}
