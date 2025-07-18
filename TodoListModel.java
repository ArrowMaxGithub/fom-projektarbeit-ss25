import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;

// Klasse zur Verwaltung der Daten der To-do Liste (Modell in MVC)
public final class TodoListModel extends AbstractListModel<TodoElement>{
    final ArrayList<TodoElement> list; // To-do Elemente werden in einer privaten ArrayList gespeichert

    // Konstruktor zur Initialisierung der Daten
    public TodoListModel() {
        this.list = new ArrayList<>(); 
    }

    @Override
    public int getSize() {
        return this.list.size(); // Gibt die Anzahl der Elemente in der To-do Liste zurück
    }

    @Override
    public TodoElement getElementAt(int index) {
        return this.list.get(index); // Rückgabe des To-do Elements an Stelle index
    }

    // Iterator zum Durchlaufen aller To-do Elemente der Liste
    public Iterator<TodoElement> iterator(){
        return this.list.iterator();
    }

    // Methode zum Hinzufügen eines neuen Elementes in die To-do Liste
    public void add(TodoElement element){
        int index = this.list.size();
        this.list.add(element);
        this.fireIntervalAdded(this, index, index);
    }

    // Methode zum Entfernen eines Elementes aus der To-do Liste
    public void remove(TodoElement element){
        int index = this.list.indexOf(element);
        this.list.remove(element);
        this.fireIntervalRemoved(this, index, index);
    }

    // Verschieben des Elementes um eine Position nach oben
    public void moveElementUp(TodoElement element){
        int index = this.list.indexOf(element); // Abfrage des Index des Elementes
        if (index == 0){ // Überprüfung, ob das Element bereits an oberster Stelle steht
            return;
        }
        // Austausch des Elements mit dem darüber liegenden mithilfe einer Hilfsvariable
        TodoElement swapped = this.list.set(index - 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index - 1, index); // Benachrichtigung aller Listener über den Platztausch
    }

    // Verschieben des Elementes um eine Position nach unten
    public void moveElementDown(TodoElement element){
        int index = this.list.indexOf(element); // Abfrage des Index des Elementes
        if (index == this.list.size() - 1){ // Überprüfung, ob das Element bereits an unterster Stelle steht
            return;
        }
        // Austausch des Elementes mit dem darunter liegenden mithilfe einer Hilfsvariable
        TodoElement swapped = this.list.set(index + 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index, index + 1); // Benachrichtigung aller Listener über den Platztausch
    }
}