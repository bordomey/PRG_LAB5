package interaction.requests;



public class InterfaceRequest {
    private InterfaceRequestType type;
    private String message;

    public InterfaceRequest(InterfaceRequestType type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public InterfaceRequestType getType() {
        return type;
    }
}