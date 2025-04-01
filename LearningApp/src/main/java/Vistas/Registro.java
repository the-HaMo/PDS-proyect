package Vistas;
import javax.swing.*;
import Controlador.Controlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame {

    private static final long serialVersionUID = 1L;
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
        setLocationRelativeTo(null);

        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(210, 222, 239));
        panelPrincipal.setLayout(new BorderLayout());

        
        JLabel lblTitulo = new JLabel("Registrarse", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(50, 80, 100));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.setBackground(new Color(210, 222, 239));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblUsuario);
        txtUsuario = new JTextField(15);
        panelCampos.add(txtUsuario);

        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblContrasena);
        txtContrasena = new JPasswordField(15);
        panelCampos.add(txtContrasena);

        
        JLabel lblContrasena2 = new JLabel("Repetir Contraseña:");
        lblContrasena2.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblContrasena2);
        txtContrasena2 = new JPasswordField(15);
        panelCampos.add(txtContrasena2);

        
        JLabel lblRol = new JLabel("Rol:");
        lblRol.setForeground(new Color(25, 40, 60));
        panelCampos.add(lblRol);
        comboRol = new JComboBox<>(new String[] {"Estudiante", "Colaborador"});
        panelCampos.add(comboRol);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

       
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(210, 222, 239));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(70, 130, 180));
        btnCancelar.setForeground(Color.WHITE);
        
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
        panelBotones.add(btnCancelar);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(new Color(70, 130, 180));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());
                String repetirContrasena = new String(txtContrasena2.getPassword());
                String rolSeleccionado = (String) comboRol.getSelectedItem();

                if (registrarUsuario(contrasena, repetirContrasena)) {
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
        panelBotones.add(btnAceptar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
    }

    public boolean registrarUsuario(String contrasena, String contrasena2) {
        return contrasena.equals(contrasena2);
    }
}
