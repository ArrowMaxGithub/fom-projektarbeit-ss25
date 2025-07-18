// Ein einzelnes To-do Element in der Liste offener Aufgaben
public final class TodoElement{
    private final String header; // Überschrift des To-do Elementes
    private final String message; // Aufgabenbeschreibung des To-do Elementes

    // Konstruktor zum Initialisierung des To-do Elementes mit Überschrift und Aufgabenbeschreibung
    public TodoElement(String header, String message) {
        this.header = header;
        this.message = message;
    }

    public String getHeader() {
        return this.header; // Rückgabe der Überschrift des To-do Elementes
    }

    public String getMessage() {
        return this.message; // Rückgabe der Aufgabenbeschreibung des To-do Elementes
    }
}