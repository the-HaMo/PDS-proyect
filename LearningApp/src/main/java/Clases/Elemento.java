package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Elemento extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private String autor;
    private String Nombre;
    private String descripcion;
    private String idioma;
    private ImageIcon fto;
    
    
    public Elemento(Curso c) {
        System.out.println("Creando elemento para curso: " + c.getNombre());
        
        this.autor = c.getAutor().getNombre();
        this.Nombre = c.getNombre();
        this.descripcion = c.getDescripcion();
        this.idioma = c.getIdioma();

        // Debug: Imprime la ruta del idioma
        System.out.println("Idioma del curso: " + idioma);
        
        // Carga la imagen con depuración
        this.fto = cargarImagenIdioma(idioma);
        System.out.println("Imagen cargada: " + (fto != null ? "Éxito" : "Falló"));
        
        initializeComponent(Color.BLACK);
    }

    private ImageIcon cargarImagenIdioma(String idioma) {
        String ruta = "";
        switch (idioma.toLowerCase()) {
            case "español":
                ruta = "/spain.png";
                break;
            case "francés":
                ruta = "/france.png";
                break;
            case "ingles":
                ruta = "/United-Kingdom.png";
                break;
            default:
                System.err.println("Idioma no reconocido: " + idioma);
                return crearIconoTexto(idioma); // Fallback
        }

        try {
            // Debug: Imprime la ruta completa
            System.out.println("Buscando imagen en: " + getClass().getResource(ruta));
            ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
            if (icono.getImage() == null) throw new Exception("Imagen no encontrada");
            return new ImageIcon(imagenCircular(icono.getImage()));
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            return crearIconoTexto(idioma); // Fallback
        }
    }
    
    private ImageIcon crearIconoTexto(String texto) {
        BufferedImage img = new BufferedImage(72, 72, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(0, 0, 72, 72);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(texto.substring(0, 2).toUpperCase(), 20, 40);
        g.dispose();
        return new ImageIcon(img);
    }
    
    
    private void initializeComponent(Color color) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Márgenes

        // Panel para la imagen (izquierda)
        JLabel lblimagen = new JLabel();
        if (fto != null) {
            lblimagen.setIcon(fto); // Usa el ImageIcon ya procesado
        } else {
            lblimagen.setText("No image"); // Fallback visual
        }
        lblimagen.setPreferredSize(new Dimension(75, 84));
        this.add(lblimagen);

        // Panel para la info (derecha)
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new InfoModelo(Nombre, 15, color));
        info.add(new InfoModelo(autor, 12, Color.BLACK));
        info.add(new InfoModelo(descripcion, 11, Color.GRAY));
        this.add(info);
    }
    
       
	public String getAutor() {
		return autor;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getIdioma() {
		return idioma;
	}

	public ImageIcon getFto() {
		return fto;
	}

	private Image imagenCircular(Image img) {
        BufferedImage imgCicrculo = new BufferedImage(72, 72, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = imgCicrculo.createGraphics();
        Ellipse2D.Double forma = new Ellipse2D.Double(0, 0, 72, 72);
        graphics.setClip(forma);
        graphics.drawImage(img, 0, 0, 72, 72, null);
        graphics.dispose();
        return imgCicrculo;
    }
	
	 private void fixSize(JComponent c, int x, int y) {
	        c.setMinimumSize(new Dimension(x, y));
	        c.setMaximumSize(new Dimension(x, y));
	        c.setPreferredSize(new Dimension(x, y));
	    }

    

}
