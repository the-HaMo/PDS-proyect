package Vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoModelo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public InfoModelo(String obj, int i, Color color) {
		setMinimumSize(new Dimension(200, 20));
		setMaximumSize(new Dimension(200, 20));
		setPreferredSize(new Dimension(200, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setOpaque(false);

		label = new JLabel(obj, JLabel.LEFT); 
		label.setFont(new Font("Tahoma", Font.BOLD, i));
		label.setForeground(color);
		label.setMinimumSize(new Dimension(250, 25));
		label.setMaximumSize(new Dimension(250, 25));
		label.setPreferredSize(new Dimension(250, 25));

		this.add(label);
	}

	public void setText(String text) {
		label.setText(text);
	}
	
	public String getText() {
		return label.getText();
	}
}
