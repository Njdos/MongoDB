package company.Mediator;

public interface Speaker {
    void sendMessage(String message);
    void getMessage(String message, Speaker speaker );
}
