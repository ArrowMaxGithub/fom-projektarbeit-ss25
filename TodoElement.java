//Initialisierung des Inhaltes eines Todo Elementes
public final class TodoElement{
    private final String header;                                                                                        //Überschrift des Todo Elementes
    private final String message;                                                                                       //Inhalt des Todo Elementes
    // Konstruktor zum Intialisierung des Todo Elementes mit Überschrift und Inahlt
    public TodoElement(String header, String message) {
        this.header = header;
        this.message = message;
    }

    public String getHeader() {
        return header;																									//Rückgabe der Überschrift des Todo Elementes, wodurch diese zur Verfügung gestellt wird
    }

    public String getMessage() {
        return message;																									//Rückgabe des Inhaltes des Todo Elementes, wodurch dieser zur Verfügung gestellt wird
    }
}