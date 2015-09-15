package fr.citesciences.bonita.exceptions;

public class TechnicalException extends Throwable {
    
    private static final long serialVersionUID = 8681052457906087427L;

    public TechnicalException() {
        
    }

    public TechnicalException(String message) {
        super(message);
    }   
}
