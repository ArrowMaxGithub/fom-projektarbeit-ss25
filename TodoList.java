//Hauptklasse zum erstellen und Verwalten der Todo Liste (C in MVC Modell)
public final class TodoList{
    private final TodoListView view;                                                                                    //Referenz auf die Klasse "TodoListView" - Ansicht der Todo Liste (V in MVC - Modell)
    private final TodoListModel model;                                                                                  //Referenz auf die Klasse "TodoListModel" - Datenmodell der Todo Liste (M in MVC - Modell)

    // Erstellung  eine Beispielhaften Todo Liste mit vordefinierten Todo Elementen
    public static void main(String[] args) {
        TodoElement[] todoElements = {                                                                                  //Erstellung eines Arrays mit den vordefinierten Todo Elementen
                new TodoElement("Nacharbeitung Mathe", "Übungen 1 bis 10 nacharbeiten"),
                new TodoElement("Einkaufen gehen", "Brot, Wasser, Obst, Gemüse"),
                new TodoElement("Kinobesuch", "Samstag 20:00 Kinosaal 5 Reihe 2 Platz 34"),
                new TodoElement("Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
        };
        TodoList _ = new TodoList(todoElements);                                                                        //Initialisierung der Todo Liste inklusive der Beispiel Todo Elemente
    }
    //Konstruktor zum Initialisieren von Modell und Ansicht mit Einbinden der Elemente
    public TodoList(TodoElement[] elements){
        this.model = new TodoListModel();                                                                               //Initialisierung des Datenmodells
        this.view = new TodoListView(this, this.model);                                                                 //Erstellung der Ansicht mit Verweis auf Controller und Modell
        this.model.addListDataListener(this.view);                                                                      //Ansicht als Listener für Modelländerungen registrieren
        for (TodoElement element : elements) {
            addTodoElement(element);                                                                                    //Elemente zum Modell hinzufügen
        }
    }
    //Erstellung von neuem Todo Element
    public void addTodoElement(TodoElement element) {
        this.model.add(element);
    }
    //Entfernen eines Todo Elementes
    public void removeTodoElement(TodoElement element) {
        this.model.remove(element);
    }
    //Ein Element in der Liste nach oben verschieben
    public void moveElementUp(TodoElement element) {
        this.model.moveElementUp(element);
    }
    //Ein Element in der Liste nach unten verschieben
    public void moveElementDown(TodoElement element) {
        this.model.moveElementDown(element);
    }
}