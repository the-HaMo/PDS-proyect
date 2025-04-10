package Modelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

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
    	System.out.println("Creando elemento....");
    	this.autor = c.getAutor().getNombre();
    	this.Nombre = c.getNombre();
    	this.descripcion = c.getDescripcion();
    	this.idioma = c.getIdioma();
    	switch (idioma) {
    		case "español":
    			ImageIcon spain = new ImageIcon(getClass().getResource("/spain.png"));
    		    Image spainScalar = imagenCircular(spain.getImage());
    		    this.fto = new ImageIcon(spainScalar);
    			break;
    		case "francés":
    			ImageIcon france = new ImageIcon(getClass().getResource("/france.png"));
    		    Image franceScalar = imagenCircular(france.getImage());
    		    this.fto = new ImageIcon(franceScalar);
    			break;
    		case "ingles":
    			ImageIcon uk = new ImageIcon(getClass().getResource("/United-Kingdom.png"));
    		    Image ukScalar = imagenCircular(uk.getImage());
    		    this.fto = new ImageIcon(ukScalar);
    			break;
    		default:
    			ImageIcon uks = new ImageIcon(getClass().getResource("/United-Kingdom.png"));
    			Image ukScalars = imagenCircular(uks.getImage());
    			this.fto = new ImageIcon(ukScalars);
    			break;
    	}
    	
    	initializeComponent(Color.BLACK);
    	
    }
    
    
    private void initializeComponent(Color color) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        fixSize(this, 300, 100);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(new TitledBorder(""));

        JLabel lblimagen = new JLabel();
        Image Imagen = fto.getImage();
        lblimagen.setIcon(new ImageIcon(imagenCircular(Imagen)));
        fixSize(lblimagen, 75, 84);

        InfoModelo nomb = new InfoModelo(Nombre, 15, color);
        InfoModelo aut = new InfoModelo(autor, 15, Color.BLACK);
        InfoModelo descrip = new InfoModelo(descripcion, 11, Color.BLACK);
        
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        fixSize(info, 200, 100);
        info.setOpaque(false);
        info.add(nomb);
        info.add(aut);
        info.add(descrip);
        this.add(lblimagen);
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
