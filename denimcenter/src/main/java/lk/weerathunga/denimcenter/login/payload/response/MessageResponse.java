package lk.weerathunga.denimcenter.login.payload.response;

//represents a response message payload that contains a single message
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}