# fom-projektarbeit-ss25
Projektarbeit Konzepte der Programmierung: Eventhandling in grafischen Oberflächen in Java

## Beispielanwendung: Todo-Liste
Eine einfache Anwendung, um Todos einzugeben, nach Priorität zu sortieren und abzuhaken.
Aufbau nach MVC-Prinzip:
- TodoList.java: **Controller** - main() und zuständig für Delegation aller Interaktionen an Model.
- TodoListModel.java: **Model** - Erhält neue To-do Elemente von Controller und delegiert die grafische Darstellung an View.
- TodoListView.java: **View** - Layout und Darstellung von To-do Elemente.
- TodoListElement.java: **Element** - Ein einzelnes To-do Element im Model.

## Codebeispiele
Die Dateien ExampleAdapter.java und MiniGUI.java sind lediglich Code-Beispiele, welche in der Projektarbeit verwendet werden:
- ExampleAdapter.java: Implementierung eines KeyListener über Adapterklasse KeyAdapter
- ExampleMiniGUI.java: Minimale Beispielanwendung im Eventhandling