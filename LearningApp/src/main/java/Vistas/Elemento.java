package Vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Modelo.Curso;

public class Elemento extends JPanel {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;
    private String autor;
    private ImageIcon icono;
    private Curso curso;

    public Elemento(Curso curso) {
    	this.nombre=curso.getNombre();
    	this.descripcion=curso.getDescripcion();
    	if (curso.getIdioma().equals("Francés")) {
    	    this.icono = new ImageIcon(getClass().getResource("/france.png"));
    	} else if (curso.getIdioma().equals("Inglés")) {
    	    this.icono = new ImageIcon(getClass().getResource("/uk.png"));
    	} else if (curso.getIdioma().equals("Español")) {
    	    this.icono = new ImageIcon(getClass().getResource("/spain.png"));
    	}
    	
    	if(curso.getAutor()!=null) {
    		this.autor=curso.getAutor().getNombre();
    	}else {
    		this.autor="";
    	}
    	this.curso=curso;
        initializeComponent();
    }

    private void initializeComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        fixSize(this, 230, 100);
        this.setBackground(Color.WHITE);
        this.setBorder(new TitledBorder("Curso"));
        
        JLabel lblimagen = new JLabel();
        Image imagen = icono.getImage();
        lblimagen.setIcon(new ImageIcon(imagenCircular(imagen)));
        fixSize(lblimagen, 60,60);
        
        InfoModelo nomb = new InfoModelo(nombre, 10, Color.BLACK);
        InfoModelo desc = new InfoModelo(descripcion, 10, Color.BLACK);
        InfoModelo creador = new InfoModelo(autor, 10, Color.BLACK);
       
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        fixSize(info, 200, 75);
        info.setOpaque(false);
        info.add(nomb);
        info.add(desc);
        info.add(creador);
        

        this.add(lblimagen);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(info);
    }

    private void fixSize(JComponent c, int x, int y) {
        c.setMinimumSize(new Dimension(x, y));
        c.setMaximumSize(new Dimension(x, y));
        c.setPreferredSize(new Dimension(x, y));
    }
    
    public Image imagenCircular(Image img) {
        BufferedImage imgCicrculo = new BufferedImage(72, 72, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = imgCicrculo.createGraphics();
        Ellipse2D.Double forma = new Ellipse2D.Double(0, 0, 72, 72);
        graphics.setClip(forma);
        graphics.drawImage(img, 0, 0, 60, 60, null);
        graphics.dispose();
        return imgCicrculo;
    }
    
	public Curso getCurso() {
		return curso;
	}
    
}

