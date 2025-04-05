public final class TodoElement{
    private final String header;
    private final String message;

    public TodoElement(String header, String message) {
        this.header = header;
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }
}