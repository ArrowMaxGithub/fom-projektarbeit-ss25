import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

// Aufbau der grafischen Oberfläche (View in MVC)
// Es wird die Schnittstelle ListDataListener implementiert, damit die Klasse TodoListView als Listener im DatenModell registrieren kann
public final class TodoListView implements ListDataListener{
    private final TodoList controller; // Referenz auf die zentrale Steuerklasse TodoList
    private final TodoListModel model; // Referenz auf TodoListModel als Datenmodell
    private final JPanel masterPanel; // Panel über den gesamten Inhalt der Oberfläche
    private final JPanel buttonsPanel; // Panel für Eingabefelder in der Kopfzeile
    private final JScrollPane todoScrollPane; // Scrollbarer Hauptbereich der To-do Liste
    private final JPanel todoPanel; // Panel innherhalb des scrollbaren Hauptbereichs, hält später die einzelnen To-do Elemente

    public TodoListView(TodoList controller, TodoListModel model){
        this.controller = controller;
        this.model = model;

        JFrame frame = new JFrame("TodoList"); // Erstellung der Top-Level-Komponente - Hauptfenster der To-do Liste
        frame.setSize(500, 500);

        this.masterPanel = new JPanel(); // Erstellung des Hauptpanels und BorderLayouts der To-do Liste
        this.masterPanel.setLayout(new BorderLayout());
        frame.add(this.masterPanel);

        // Erstellung der Eingabefelder für neue To-do Elemente
        JTextField inputHeader = new JTextField("Titel"); // Erstellung des Textfeldes 'Titel'
        JTextField inputMessage = new JTextField("Aufgabe"); // Erstellung des Textfeldes 'Aufgabe'
        JButton inputSubmit = new JButton("Hinzufügen"); // Erstellung des Buttons 'Hinzufügen'

        // Registrierung des ActionListeners für den "Hinzufügen" Button
        inputSubmit.addActionListener((ActionEvent _) -> {
            controller.addTodoElement(new TodoElement(inputHeader.getText(), inputMessage.getText())); // Als Reaktion wird ein neues Element mit den eingegeben Daten hinzugefügt
            inputHeader.setText("Titel"); // Eingabefeld 'Titel' wird auf Platzhaltertext zurückgesetzt
            inputMessage.setText("Aufgabe"); // Eingabefeld 'Aufgabe' wird auf Platzhaltertext zurückgesetzt
        });

        // Die Eingabefelder werden in einen separaten Panel organisiert und dem Hauptpanel als Kopfzeile hinzugefügt
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new BoxLayout(this.buttonsPanel, BoxLayout.LINE_AXIS)); // Horizontale Anordnung des gesamten Eingabebereiches
        this.buttonsPanel.add(inputHeader);
        this.buttonsPanel.add(inputMessage);
        this.buttonsPanel.add(inputSubmit);
        masterPanel.add(this.buttonsPanel, BorderLayout.PAGE_START);

        // Initilaisierung des scrollbaren Panels zur Anzeige der To-do Elemente
        this.todoPanel = new JPanel();
        this.todoPanel.setLayout(new BoxLayout(this.todoPanel, BoxLayout.PAGE_AXIS)); // To-do Elemente werden vertikal angeordnet
        this.todoScrollPane = new JScrollPane(todoPanel); // Das Panel wird einer Scrollpane untergeordnet, damit der Bereich bei Überlauf vertikales Scrollen ermöglicht
        this.todoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Einstellung damit der vertikale Scrollbalken dauerhaft sichtbar bleibt
        this.todoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Kein horizontaler Scrollbalken soll erstellt werden
        masterPanel.add(this.todoScrollPane, BorderLayout.CENTER); // Positionieren des Ansichtsfeldes für To-do Elemente im Zentrum der To-do Liste

        frame.setVisible(true); // Hauptfenster wird sichtbar gestellt
    }

    // Alle To-do Elemente werden entfernt und die gesamte To-do Liste neu eingelesen
    public void update() {
        this.todoPanel.removeAll();
        model.iterator().forEachRemaining(element -> {
            this.addElement(element);
        });

        this.todoPanel.revalidate(); // Neuvalidierung und Neuzeichnen der GUI
        this.todoPanel.repaint();
    }

    // Wird als Rückrufmethode aufgerufen, wenn neue Elemente der To-do Liste hinzugefügt wurden
    @Override
    public void intervalAdded(ListDataEvent e) {
        System.out.println("intervalAdded: " + e.getIndex0() + " - "  + e.getIndex1());
        update(); // Neuaufbau der gesamten Liste                                                                                                       
    }

    // Wird als Rückrufmethode aufgerufen, wenn Elemente aus der To-do Liste entfernt wurden
    @Override
    public void intervalRemoved(ListDataEvent e) {
        System.out.println("intervalRemoved: " + e.getIndex0() + " - "  + e.getIndex1());
        update(); // Neuaufbau der gesamten Liste  
    }

    // Wird als Rückrufmethode aufgerufen, wenn sich die To-do Liste in einer anderen Weise verändert hat
    @Override
    public void contentsChanged(ListDataEvent e) {
        System.out.println("contentsChanged: " + e.getIndex0() + " - "  + e.getIndex1());
        update(); // Neuaufbau der gesamten Liste  
    }

    // Methode zum Aufbau und Hinzufügen eines neuen To-do Elements als grafische Einheit
    void addElement(TodoElement element){
        // Panel mit Überschrift des To-do Elements als Randbeschreibung
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), element.getHeader()));
        panel.setAlignmentY(0.0f);
        panel.setAlignmentX(0.0f);

        // Textbereich für die Aufgabenbeschreibung des To-do Elementes
        JTextArea message = new JTextArea(element.getMessage());
        message.setEditable(false);
        message.setFocusable(false);
        message.setLineWrap(true);

        // Initialisierung des Scrollbereichs bei Überlauf innerhalb des To-do Elementes
        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setAlignmentY(0.0f);
        scrollPane.setAlignmentX(0.0f);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(400, 100));
        panel.add(scrollPane);

        // Initialiserung der Schaltflächen: 'erledigt', 'hoch', 'runter' am rechten Rand des Elementes
        JButton done = new JButton("erledigt");
        JButton up = new JButton("🠉");
        JButton down = new JButton("🠋");

        // Initialisieren von Aktionen für die Schaltflächen
        done.addActionListener((ActionEvent _) -> {
            this.controller.removeTodoElement(element); // Hinzufügen der Aktion 'Entferne das To-do Element aus der Liste'
        });

        up.addActionListener((ActionEvent _) -> {
            this.controller.moveElementUp(element); // Hinzufügen der Aktion 'Bewege das To-do Element in der Liste nach oben'
        });

        down.addActionListener((ActionEvent _) -> {
            this.controller.moveElementDown(element); // Hinzufügen der Aktion 'Bewege das To-do Element in der Liste nach unten'
        });

        // Erstellen eines Panels für die Schaltflächen
        JPanel buttons = new JPanel();
        buttons.setAlignmentY(0.0f);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS)); // Vertikale Anordnung der Schaltflächen im Panel
        buttons.add(done); // Hinzufügen der Schaltfläche 'erledigt'
        buttons.add(up); // Hinzufügen der Schaltfläche 'hoch'
        buttons.add(down); // Hinzufügen der Schaltfläche 'runter'

        panel.add(buttons); // Hinzufügen des Schaltflächen-Panels in das übergeordnete Panel

        this.todoPanel.add(panel); // Vollständige Einbindung des gesamten To-do Elemente Panels in das Hauptpanel aller Elemente
    }
}