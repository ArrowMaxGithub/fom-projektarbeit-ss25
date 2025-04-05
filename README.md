# fom-projektarbeit-ss25
Projektarbeit Konzepte der Programmierung: Event Handling in grafischen Oberflächen in Java

## Beispielanwendung: Todo-Liste
Eine einfache Anwendung, um Todos einzugeben, nach Priorität zu sortieren und abzuhaken.
Aufbau nach MVC-Prinzip:
- TodoList: **Controller** - main() und zuständig für Delegation an Model.
- TodoListModel: **Model** - Erhält neue Todos von Controller und delegiert die grafische Darstellung an View via DataListener.
- TodoListView: **View** - Layout und Darstellung von TodoElements. Kommuniziert mit Controller via ActionListener für Userinteraktion.
- TodoListElement: **Element** - Ein einzelnes Element im Model.