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

public final class TodoListView implements ListDataListener{
    private final TodoList controller;
    private final TodoListModel model;
    private final JPanel masterPanel;
    private final JPanel buttonsPanel;
    private final JScrollPane todoScrollPane;
    private final JPanel todoPanel;

    public TodoListView(TodoList controller, TodoListModel model){
        this.controller = controller;
        this.model = model;
        
        JFrame frame = new JFrame("TodoList");
        frame.setSize(500, 500);

        this.masterPanel = new JPanel();
        this.masterPanel.setLayout(new BorderLayout());
        frame.add(this.masterPanel);

        JTextField inputHeader = new JTextField("Titel");
        JTextField inputMessage = new JTextField("Aufgabe");
        JButton inputSubmit = new JButton("Hinzufügen");
        inputSubmit.addActionListener((ActionEvent _) -> {
            controller.addTodoElement(new TodoElement(inputHeader.getText(), inputMessage.getText()));
            inputHeader.setText("Titel");
            inputMessage.setText("Aufgabe");
        });

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new BoxLayout(this.buttonsPanel, BoxLayout.LINE_AXIS));
        this.buttonsPanel.add(inputHeader);
        this.buttonsPanel.add(inputMessage);
        this.buttonsPanel.add(inputSubmit);
        masterPanel.add(this.buttonsPanel, BorderLayout.PAGE_START);

        this.todoPanel = new JPanel();
        this.todoPanel.setLayout(new BoxLayout(this.todoPanel, BoxLayout.PAGE_AXIS));
        this.todoScrollPane = new JScrollPane(todoPanel);
        this.todoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.todoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        masterPanel.add(this.todoScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void update() {
        this.todoPanel.removeAll();

        model.iterator().forEachRemaining(element -> {
            this.addElement(element);
        });

        this.todoPanel.revalidate();
        this.todoPanel.repaint();
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        System.out.println("intervalAdded: " + e.getIndex0() + " - "  + e.getIndex1());
        update();
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        System.out.println("intervalRemoved: " + e.getIndex0() + " - "  + e.getIndex1());
        update();
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        System.out.println("contentsChanged: " + e.getIndex0() + " - "  + e.getIndex1());
        update();
    }

    void addElement(TodoElement element){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), element.getHeader()));
        panel.setAlignmentY(0.0f);
        panel.setAlignmentX(0.0f);
        
        JTextArea message = new JTextArea(element.getMessage());
        message.setEditable(false);
        message.setFocusable(false);
        message.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(message);
        scrollPane.setAlignmentY(0.0f);
        scrollPane.setAlignmentX(0.0f);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(400, 100));
        panel.add(scrollPane);

        JButton done = new JButton("erledigt");
        JButton up = new JButton("🠉");
        JButton down = new JButton("🠋");

        done.addActionListener((ActionEvent _) -> {
            this.controller.removeTodoElement(element);
        });
        
        up.addActionListener((ActionEvent _) -> {
            this.controller.moveElementUp(element);
        });
        
        down.addActionListener((ActionEvent _) -> {
            this.controller.moveElementDown(element);
        });

        JPanel buttons = new JPanel();
        buttons.setAlignmentY(0.0f);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.add(done);
        buttons.add(up);
        buttons.add(down);

        panel.add(buttons);

        this.todoPanel.add(panel);
    }
}