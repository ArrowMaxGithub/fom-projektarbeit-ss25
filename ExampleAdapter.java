import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class ExampleAdapter extends KeyAdapter {
    public static void main(String[] args) {
        ExampleAdapter example = new ExampleAdapter();

        JFrame frame = new JFrame();
        frame.addKeyListener(example);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){ 
            System.out.println("Leertaste gedrückt");
        }
    }
}
