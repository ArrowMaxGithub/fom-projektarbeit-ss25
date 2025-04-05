public final class TodoList{
    private final TodoListView view;
    private final TodoListModel model;

    public static void main(String[] args) {
        TodoElement[] todoElements = {
            new TodoElement("Nacharbeitung Mathe", "Übungen 1 bis 10 nacharbeiten"),
            new TodoElement("Einkaufen gehen", "Brot, Wasser, Obst, Gemüse"),
            new TodoElement("Kinobesuch", "Samstag 20:00 Kinosaal 5 Reihe 2 Platz 34"),
            new TodoElement("Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."),
        };
        TodoList _ = new TodoList(todoElements);
    }

    public TodoList(TodoElement[] elements){
        this.model = new TodoListModel();
        this.view = new TodoListView(this, this.model);
        this.model.addListDataListener(this.view);
        for (TodoElement element : elements) {
            addTodoElement(element);
        }
    }
    
    public void addTodoElement(TodoElement element) {
        this.model.add(element);
    }

    public void removeTodoElement(TodoElement element) {
        this.model.remove(element);
    }

    public void moveElementUp(TodoElement element) {
        this.model.moveElementUp(element);
    }

    public void moveElementDown(TodoElement element) {
        this.model.moveElementDown(element);
    }
}