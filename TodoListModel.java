import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;

public final class TodoListModel extends AbstractListModel<TodoElement>{
    final ArrayList<TodoElement> list;

    public TodoListModel() {
        this.list = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.list.size();
    }

    @Override
    public TodoElement getElementAt(int index) {
        return this.list.get(index);
    }

    public Iterator<TodoElement> iterator(){
        return this.list.iterator();
    }

    public void add(TodoElement element){
        int index = this.list.size();
        this.list.add(element);
        this.fireIntervalAdded(this, index, index);
    }

    public void remove(TodoElement element){
        int index = this.list.indexOf(element);
        this.list.remove(element);
        this.fireIntervalRemoved(this, index, index);
    }

    public void moveElementUp(TodoElement element){
        int index = this.list.indexOf(element);
        if (index == 0){
            return;
        }

        TodoElement swapped = this.list.set(index - 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index - 1, index);
    }

    public void moveElementDown(TodoElement element){
        int index = this.list.indexOf(element);
        if (index == this.list.size() - 1){
            return;
        }

        TodoElement swapped = this.list.set(index + 1, element);
        this.list.set(index, swapped);
        this.fireContentsChanged(this, index, index + 1);
    }
}