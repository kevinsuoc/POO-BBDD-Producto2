package dataroast.controlador;

public class InstanceNotFoundException extends IllegalArgumentException{
    public InstanceNotFoundException(String message) {
        super(message);
    }
}
