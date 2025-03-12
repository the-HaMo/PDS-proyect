
package Vistas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Cursos {

    private JFrame frame;

    /**
     * Create the application.
     */
    public Cursos() {
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 480, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
