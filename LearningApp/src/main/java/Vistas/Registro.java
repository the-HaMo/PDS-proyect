package Vistas;
import javax.swing.*;

import Controlador.Controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JPasswordField txtContrasena2;
    private JComboBox<String> comboRol;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public Registro() {
        setTitle("LearningApp - Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
        // Panel principal, con un color de fondo similar al de la ventana Login
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(210, 222, 239));
        
        
        // ----- Encabezado con título -----
        JLabel lblTitulo = new JLabel("Registrarse", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(50, 80, 100));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        // ----- Panel central para los campos -----
        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(new Color(210, 222, 239));
        panelCampos.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Label Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblUsuario, gbc);

        // Campo Usuario
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtUsuario = new JTextField(15);
        panelCampos.add(txtUsuario, gbc);

        // Label Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblContrasena, gbc);

        // Campo Contraseña
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtContrasena = new JPasswordField(15);
        panelCampos.add(txtContrasena, gbc);
        
     // Label Contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblContrasena2 = new JLabel("Repetir Contraseña:");
        lblContrasena.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblContrasena2, gbc);

        // Campo Contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtContrasena2 = new JPasswordField(15);
        panelCampos.add(txtContrasena2, gbc);

        // Label Rol
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblRol = new JLabel("Rol:");
        lblRol.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblRol, gbc);

        // ComboBox Rol
        gbc.gridx = 1;
        gbc.gridy = 3;
        comboRol = new JComboBox<>(new String[] {"Estudiante", "Colaborador"});
        panelCampos.add(comboRol, gbc);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        // ----- Panel inferior con botones -----
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(210, 222, 239));
        panelBotones.setLayout(new GridBagLayout()); // Cambiamos el layout
        
        GridBagConstraints gbcBoton = new GridBagConstraints();
        gbcBoton.insets = new Insets(0, 10, 0, 10);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(70, 130, 180));
        btnCancelar.setForeground(Color.WHITE);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(70, 130, 180));
        btnAceptar.setForeground(Color.WHITE);
        
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());
                String rolSeleccionado = (String) comboRol.getSelectedItem();
                if (registrarUsuario(usuario, contrasena, new String(txtContrasena2.getPassword()))) {
					JOptionPane.showMessageDialog(Registro.this, "Usuario registrado exitosamente", "Registro",
							JOptionPane.INFORMATION_MESSAGE);
					Controlador.INSTANCE.crearUsuario(usuario, contrasena, rolSeleccionado);
					new Login();
					dispose();
				} else {
					JOptionPane.showMessageDialog(Registro.this, "Las contraseñas no coinciden", "Registro",
							JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ejemplo de acción al hacer clic en “Iniciar Sesión”
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        gbcBoton.gridx = 0;
        panelBotones.add(btnCancelar, gbcBoton);
        gbcBoton.gridx = 1;
        panelBotones.add(btnAceptar, gbcBoton);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Agregamos el panel principal al JFrame
        getContentPane().add(panelPrincipal);
    }
 
	public boolean registrarUsuario(String nombre, String contrasena, String contrasena2) {
		//USUARIO EXISTE
		return(contrasena.equals(contrasena2));
		
	}
}