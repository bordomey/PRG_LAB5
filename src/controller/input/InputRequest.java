package controller.input;

public class InputRequest {
    private String message;
    private String respond;

    public InputRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getRespond() {
        return respond;
    }

    public void respond(String respond) {
        this.respond = respond;
    }
}
