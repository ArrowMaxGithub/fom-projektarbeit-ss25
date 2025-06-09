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
//Aufbau der grafischen Oberfläche
public final class TodoListView implements ListDataListener{                                                            //Reaktion auf Änderungen im Modell durch den implementierung des DataListeners
    private final TodoList controller;                                                                                  //Referenz auf die Hauptklasse TodoList
    private final TodoListModel model;                                                                                  //Referenz auf Klasse TodoListModel
    private final JPanel masterPanel;                                                                                   //Top Level Komponente
    private final JPanel buttonsPanel;                                                                                  //Panel für Atomare Komponenten
    private final JScrollPane todoScrollPane;                                                                           //Initialisierung des scrollbaren Bereiches
    private final JPanel todoPanel;                                                                                     //Panel, dass alle Todo Elemente enthält

    public TodoListView(TodoList controller, TodoListModel model){
        this.controller = controller;
        this.model = model;

        JFrame frame = new JFrame("TodoList");                                                                          //Erstellung der Top Level Komponente - Hauptfenster der Todo Liste
        frame.setSize(500, 500);

        this.masterPanel = new JPanel();                                                                                //Erstellung des Hauptpanels und Border Layouts der Todo Liste
        this.masterPanel.setLayout(new BorderLayout());
        frame.add(this.masterPanel);
//Erstellung aller Eingabefelder und einer Schaltfläche innerhalb der Todo Liste
        JTextField inputHeader = new JTextField("Titel");                                                               //Erstellung des Textfeldes 'Titel'
        JTextField inputMessage = new JTextField("Aufgabe");                                                            //Erstellung des Textfeldes 'Aufgabe'
        JButton inputSubmit = new JButton("Hinzufügen");                                                                //Erstellung des Buttons 'Hinzufügen'
        inputSubmit.addActionListener((ActionEvent _) -> {                                                              //Erstellung des Textfeldes 'Titel'
            controller.addTodoElement(new TodoElement(inputHeader.getText(), inputMessage.getText()));                  //Erstellung des Textfeldes 'Titel'
            inputHeader.setText("Titel");                                                                               //Erstellung des Textfeldes 'Titel'
            inputMessage.setText("Aufgabe");                                                                            //Erstellung des Textfeldes 'Titel'
        });
//Initialisierung des Panels zur Eingabe eines neuen Todo Elementes
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new BoxLayout(this.buttonsPanel, BoxLayout.LINE_AXIS));                             //Horizontale Anordnung des gesamten Eingabebereiches
        this.buttonsPanel.add(inputHeader);                                                                             //Eingabefeld für die Überschrift des Todo Elementes
        this.buttonsPanel.add(inputMessage);                                                                            //Eingabefeld für den Inhalt des Todo Elementes
        this.buttonsPanel.add(inputSubmit);                                                                             //Schaltfläche zum hinzufpgen des Todo Elementes
        masterPanel.add(this.buttonsPanel, BorderLayout.PAGE_START);                                                    //Einfügen des Eiganbebereiches in den oberen Bereich der Todo Liste
//Initilaisierung eines Panels zur Anzeige der Todo Elemente
        this.todoPanel = new JPanel();
        this.todoPanel.setLayout(new BoxLayout(this.todoPanel, BoxLayout.PAGE_AXIS));                                   //Todo Elemente werden vertikal angeordnet
        this.todoScrollPane = new JScrollPane(todoPanel);                                                               //Hinzufügen eines vertikalen Scrollbalkens in die Ansicht der Todo Elemente
        this.todoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);                          //Einstellung damit der vertikale Scrollbalken dauerhaft sichtbar bleibt
        this.todoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                       //Kein horizontaler Scrollblaken soll erstellt werden
        masterPanel.add(this.todoScrollPane, BorderLayout.CENTER);                                                      //Positionieren des Ansichtsfeldes für Todo Elemente im Zentrum der Todo Liste

        frame.setVisible(true);                                                                                         //Einstellung die die Todo Liste sichtbar macht
    }
    //Aufräumen der Todo Liste und neu hinzfügen aller aktuellen Todo Elemente
    public void update() {
        this.todoPanel.removeAll();
//Iterator zum feststellen der aktuellen Todo Elemente, damit diese wieder hinzugefügt werden können
        model.iterator().forEachRemaining(element -> {
            this.addElement(element);
        });

        this.todoPanel.revalidate();
        this.todoPanel.repaint();
    }
    //Methode die aufgerufen wird, wenn Elemente zu der Todo Liste hinzugefügt werden
    @Override
    public void intervalAdded(ListDataEvent e) {
        System.out.println("intervalAdded: " + e.getIndex0() + " - "  + e.getIndex1());
        update();                                                                                                       //Aktualisierung der gesamten Liste
    }
    //Methode die aufgerufen wird, wenn Elemente aus der Todo Liste entfernt  werden
    @Override
    public void intervalRemoved(ListDataEvent e) {
        System.out.println("intervalRemoved: " + e.getIndex0() + " - "  + e.getIndex1());
        update();
    }
    //Methode die aufgerufen wird, wenn Inhalte innerhalb der Todo Liste geändert werden
    @Override
    public void contentsChanged(ListDataEvent e) {
        System.out.println("contentsChanged: " + e.getIndex0() + " - "  + e.getIndex1());
        update();
    }
    //Metzhode die aufgerufen wird wenn ein Todo Element zu der Todo Liste hinzugefügt werden soll
    void addElement(TodoElement element){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), element.getHeader()));
        panel.setAlignmentY(0.0f);
        panel.setAlignmentX(0.0f);
//Textbereich für den Inhalt des Todo Elementes wird Initialisiert
        JTextArea message = new JTextArea(element.getMessage());
        message.setEditable(false);
        message.setFocusable(false);
        message.setLineWrap(true);
//Initialisierung des Scrollbalkens innerhalb des Todo Elementes
        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setAlignmentY(0.0f);
        scrollPane.setAlignmentX(0.0f);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(400, 100));
        panel.add(scrollPane);
//Initiliserung der Schaltflächen: 'erledgit', 'hoch', 'runter'
        JButton done = new JButton("erledigt");
        JButton up = new JButton("🠉");
        JButton down = new JButton("🠋");
//Initialisiern von Aktionen für die Schaltflächen innerhalb der Todo Liste
        done.addActionListener((ActionEvent _) -> {
            this.controller.removeTodoElement(element);                                                                 //Hinzufügen der Aktion 'Entferne das Todo Element aus der Liste'
        });

        up.addActionListener((ActionEvent _) -> {
            this.controller.moveElementUp(element);                                                                     //Hinzufügen der Aktion 'Bewege das Todo Element in der Liste nach oben'
        });

        down.addActionListener((ActionEvent _) -> {
            this.controller.moveElementDown(element);                                                                   //Hinzufügen der Aktion 'Bewege das Todo Element in der Liste nach unten'
        });
//Erstellen eines Panels für die Schaltflächen
        JPanel buttons = new JPanel();
        buttons.setAlignmentY(0.0f);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));                                                 //Vertikale Anordnung der Schaltflächen im Panel
        buttons.add(done);                                                                                              //Hinzufügen der Schaltfläche 'erledigt'
        buttons.add(up);                                                                                                //Hinzufügen der Schaltfläche 'hoch'
        buttons.add(down);                                                                                              //Hinzufügen der Schaltfläche 'runter'

        panel.add(buttons);                                                                                             //Hinzufügen des Schaltflächen Panels in ein Todo Element Panel

        this.todoPanel.add(panel);                                                                                      //Vollständige Einbindung des gesamten Todo Elemente Panels in das Haupt Panel
    }
}