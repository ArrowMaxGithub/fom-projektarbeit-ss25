import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExampleMiniGUI extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ExampleMiniGUI");                                                                         
        frame.setSize(300, 75);
        frame.add(new ExampleMiniGUI());
        frame.setVisible(true);
    }

    public ExampleMiniGUI() {
        JButton button = new JButton("Würfeln");
        JLabel ergebnis = new JLabel("Ergebnis: ?");
        button.addActionListener((_) -> {
            int wurf = (int)(Math.random() * 6 + 1);
            ergebnis.setText("Ergebnis: " + wurf);
        });
        this.add(button);
        this.add(ergebnis);
    }
}
