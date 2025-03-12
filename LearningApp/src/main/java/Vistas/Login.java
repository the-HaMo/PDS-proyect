package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login {
	  private 	JFrame frame;
	    private JTextField usernameField;
	    private JPasswordField passwordField;
	    private JLabel logoLabel;
	    private ImageIcon originalIcon;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                Login window = new Login();
	                window.frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }

	    public Login() {
	        initialize();
	    }

	    private void initialize() {
	        frame = new JFrame("LearningApp");
	        frame.setBounds(100, 100, 450, 500);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(new BorderLayout());

	        // 🔹 Panel principal con BorderLayout
	        JPanel mainPanel = new JPanel(new BorderLayout());
	        mainPanel.setBackground(SystemColor.inactiveCaption);
	        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
	        frame.getContentPane().add(mainPanel);

	        // 🔹 Zona Norte (Título)
	        JLabel titleLabel = new JLabel("LearningApp", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 26));
	        titleLabel.setForeground(new Color(70, 130, 180));
	        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
	        mainPanel.add(titleLabel, BorderLayout.NORTH);

	        // 🔹 Zona Central (Logo y campos)
	        JPanel centerPanel = new JPanel(new BorderLayout());
	        centerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
	        mainPanel.add(centerPanel, BorderLayout.CENTER);

	        // 🔹 Logo en la parte norte del centro
	        JPanel logoPanel = new JPanel(new BorderLayout());
	        logoPanel.setPreferredSize(new Dimension(0, 200));
	        centerPanel.add(logoPanel, BorderLayout.NORTH);

	        originalIcon = new ImageIcon(getClass().getResource("/logo.png"));
	        logoLabel = new JLabel();
	        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        logoLabel.setVerticalAlignment(SwingConstants.CENTER);
	        logoPanel.add(logoLabel, BorderLayout.CENTER);
	        
	        Component verticalStrut = Box.createVerticalStrut(20);
	        logoPanel.add(verticalStrut, BorderLayout.SOUTH);

	        // 🔹 Redimensionar la imagen dinámicamente cuando cambie el tamaño del panel
	        logoPanel.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                setScaledImage(logoLabel, originalIcon, logoPanel.getWidth(), logoPanel.getHeight());
	            }
	        });

	     // 🔹 Panel para los campos (con padding en la zona sur)
	        JPanel fieldsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
	        fieldsPanel.setBorder(new EmptyBorder(30, 50, 20, 50)); // Padding
	        centerPanel.add(fieldsPanel, BorderLayout.SOUTH);
	        
	        // 🔹 Panel para el campo de Username
	        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado
	        JLabel usernameLabel = new JLabel("Username:");
	        usernameLabel.setForeground(new Color(0, 0, 128));
	        usernameLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	        usernamePanel.add(usernameLabel);

	        usernameField = new JTextField(15);
	        usernameField.setHorizontalAlignment(SwingConstants.LEFT);
	        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
	        usernamePanel.add(usernameField);

	        // 🔹 Panel para el campo de Password
	        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado
	        JLabel passwordLabel = new JLabel("Password:");
	        passwordLabel.setForeground(new Color(0, 0, 128));
	        passwordLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	        passwordPanel.add(passwordLabel);

	        passwordField = new JPasswordField(15);
	        passwordField.setHorizontalAlignment(SwingConstants.LEFT);
	        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
	        passwordPanel.add(passwordField);

	        // 🔹 Agregar los paneles al fieldsPanel
	        fieldsPanel.add(usernamePanel);
	        fieldsPanel.add(passwordPanel);


	        // 🔹 Zona Sur (Botones)
	        JPanel buttonsPanel = new JPanel();
	        buttonsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
	        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

	        JButton loginButton = new JButton("Login");
	        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
	        loginButton.setBackground(new Color(70, 130, 180));
	        loginButton.setForeground(Color.WHITE);
	        loginButton.setPreferredSize(new Dimension(120, 40));
	        buttonsPanel.add(loginButton);

	        JButton registerButton = new JButton("Register");
	        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
	        registerButton.setBackground(new Color(70, 130, 180));
	        registerButton.setForeground(Color.WHITE);
	        registerButton.setPreferredSize(new Dimension(120, 40));
	        buttonsPanel.add(registerButton);
	        
	        Component horizontalStrut = Box.createHorizontalStrut(20);
	        buttonsPanel.add(horizontalStrut);

	        // 🔹 Eventos de botones
	        loginButton.addActionListener(e -> {
	            System.out.println("Username: " + usernameField.getText());
	            System.out.println("Password: " + new String(passwordField.getPassword()));
	        });

	        registerButton.addActionListener(e -> {
	            System.out.println("Register button clicked");
	        });
	    }

	    // 🔹 Método para escalar la imagen al tamaño completo del logoPanel
	    private void setScaledImage(JLabel label, ImageIcon icon, int width, int height) {
	        if (width > 0 && height > 0) { 
	            Image img = icon.getImage();
	            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	            label.setIcon(new ImageIcon(scaledImg));
	        }
	    }
}
