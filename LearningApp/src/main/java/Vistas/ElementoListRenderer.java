package Vistas;

//Para completar
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ElementoListRenderer extends DefaultListCellRenderer{

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, 
						Object value, int index, boolean isSelected, 
						boolean cellHasFocus) {
		if (value!=null && value instanceof Elemento) {
			Elemento ele=(Elemento) value;
			if (isSelected) {
				ele.setBackground(new Color(182, 255, 149));
			} else ele.setBackground(list.getBackground());
			
			return ele;
		} 
		else return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 

	}
}
