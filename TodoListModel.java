import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
//Klasse zur Verwaltung der Todo Liste und umordnen der Todo-Elemente
public final class TodoListModel extends AbstractListModel<TodoElement>{
    final ArrayList<TodoElement> list;                                                                                  //Speichern der Todo-Elemente in einer internen Liste

    public TodoListModel() {
        this.list = new ArrayList<>();																					//Konstruktor zur Initialisierung der internen Liste
    }

    @Override
    public int getSize() {
        return this.list.size();																						//Gibt die Anzahl der Elemente in der Todo Liste zurück
    }

    @Override
    public TodoElement getElementAt(int index) {
        return this.list.get(index);																					//Rückgabe des Index eines Todo Listen Elementes
    }

    public Iterator<TodoElement> iterator(){																			//Iterator der ermöglicht die Elemente der Todo Liste zu durchlaufen
        return this.list.iterator();
    }
    // Methode zum hinzufügen eines neuen Elementes in die Todo Liste
    public void add(TodoElement element){
        int index = this.list.size();
        this.list.add(element);
        this.fireIntervalAdded(this, index, index);
    }
    //Methode zum entfernen eines Elementes aus der Todo Liste
    public void remove(TodoElement element){
        int index = this.list.indexOf(element);
        this.list.remove(element);
        this.fireIntervalRemoved(this, index, index);
    }
    //Verschieben des Elementes nach oben, wenn es nicht an oberster Stelle der Todo Liste steht
    public void moveElementUp(TodoElement element){
        int index = this.list.indexOf(element);                                                                         //Abfrage des Index des Elementes
        if (index == 0){                                                                                                //Überprüfung, ob das Element an oberster Stelle steht
            return;
        }
        //Austausch des Elements mit dem darüber liegenden
        TodoElement swapped = this.list.set(index - 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index - 1, index);                                                               //Benachrichtigung der GUI über den Platztausch
    }
    //Verschieben eines Elementes nach unten, wenn es nicht an unterster Stelle der Todo Liste steht
    public void moveElementDown(TodoElement element){
        int index = this.list.indexOf(element);                                                                         //Abfrage des Index des Elementes
        if (index == this.list.size() - 1){                                                                             //Überprüfung, ob das Element an letzter Stelle steht
            return;
        }
        //Austausch des Elementes mit dem darunter liegenden
        TodoElement swapped = this.list.set(index + 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index, index + 1);                                                               //Benachrichtigung der GUI über den Platztausch
    }
}