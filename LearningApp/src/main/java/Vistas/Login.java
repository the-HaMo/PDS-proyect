package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Colaborador;
import Modelo.Estudiante;

public class Login {
	private 	JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel logoLabel;
	private ImageIcon originalIcon;

	public Login() {
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame("LearningApp");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(0, 255, 64));
		mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		frame.getContentPane().add(mainPanel);

		JLabel titleLabel = new JLabel("LearningApp", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 26));
		titleLabel.setForeground(new Color(0, 0, 0));
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(128, 255, 128));
		centerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel logoPanel = new JPanel(new BorderLayout());
		logoPanel.setBackground(new Color(128, 255, 128));
		logoPanel.setPreferredSize(new Dimension(0, 200));
		centerPanel.add(logoPanel, BorderLayout.NORTH);

		originalIcon = new ImageIcon(getClass().getResource("/logo.png"));
		logoLabel = new JLabel();
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setVerticalAlignment(SwingConstants.CENTER);
		logoPanel.add(logoLabel, BorderLayout.CENTER);

		Component verticalStrut = Box.createVerticalStrut(20);
		logoPanel.add(verticalStrut, BorderLayout.SOUTH);

		logoPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setScaledImage(logoLabel, originalIcon, logoPanel.getWidth(), logoPanel.getHeight());
			}
		});

		JPanel fieldsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		fieldsPanel.setBackground(new Color(128, 255, 128));
		fieldsPanel.setBorder(new EmptyBorder(30, 50, 20, 50)); // Padding
		centerPanel.add(fieldsPanel, BorderLayout.SOUTH);

		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado
		usernamePanel.setBackground(new Color(128, 255, 128));
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		usernamePanel.add(usernameLabel);

		usernameField = new JTextField(15);
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
		usernamePanel.add(usernameField);

		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado
		passwordPanel.setBackground(new Color(128, 255, 128));
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(new Color(0, 0, 0));
		passwordLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField(15);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordPanel.add(passwordField);

		fieldsPanel.add(usernamePanel);
		fieldsPanel.add(passwordPanel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(128, 255, 128));
		buttonsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		loginButton.setBackground(new Color(0, 128, 0));
		loginButton.setForeground(Color.WHITE);
		loginButton.setPreferredSize(new Dimension(120, 40));
		buttonsPanel.add(loginButton);
		loginButton.addActionListener(e -> {
			String username = usernameField.getText();
			String passwd = new String(passwordField.getPassword());
			
			if (Controlador.INSTANCE.iniciarSesion(username, passwd)) {
				if (Controlador.INSTANCE.getUsuarioActual() instanceof Estudiante) {
					CursosEstudiante window = new CursosEstudiante();
					window.Mostrar();
					frame.dispose();
				} else if (Controlador.INSTANCE.getUsuarioActual() instanceof Colaborador) {
					CursosColaborador window = new CursosColaborador();
					window.Mostrar();
					frame.dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
			
			

		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Arial", Font.BOLD, 14));
		registerButton.setBackground(new Color(0, 128, 0));
		registerButton.setForeground(Color.WHITE);
		registerButton.setPreferredSize(new Dimension(120, 40));
		buttonsPanel.add(registerButton);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		buttonsPanel.add(horizontalStrut);


		registerButton.addActionListener(e -> {
			Registro window = new Registro();
			window.setVisible(true);
			frame.dispose();
		});
	}

	// Método para escalar la imagen al tamaño completo del logoPanel
	private void setScaledImage(JLabel label, ImageIcon icon, int width, int height) {
		if (width > 0 && height > 0) { 
			Image img = icon.getImage();
			Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(scaledImg));
		}
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
}
