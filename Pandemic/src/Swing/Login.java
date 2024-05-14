package Swing;
import Controlador.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controlador.ControlDatos;

public class Login extends JPanel implements ActionListener {

	JLabel usuarioLabel;
	JLabel contrasenaLabel;
	JTextField usuarioField;
	JPasswordField contrasenaField;
	JButton botonIniciar;
	JButton botonRegistrar;
	public static boolean loginExitoso=false;
	public static boolean registroExitoso=false;



	Login() {
		setLayout(new GridLayout(3, 2));

		usuarioLabel = new JLabel("Usuario:");
		contrasenaLabel = new JLabel("Contraseña:");
		usuarioField = new JTextField();
		contrasenaField = new JPasswordField();
		botonIniciar = new JButton("Iniciar");
		botonRegistrar = new JButton("Registrar");

		botonIniciar.addActionListener(this);
		botonRegistrar.addActionListener(this);

		add(usuarioLabel);
		add(usuarioField);
		add(contrasenaLabel);
		add(contrasenaField);
		add(botonIniciar);
		add(botonRegistrar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonIniciar) {
			
			ControlDatos.user_partida = usuarioField.getText();
			String contrasena = String.valueOf(contrasenaField.getPassword());

			// iniciar sesión
			loginExitoso = ControlDatos.iniciarSesion(ControlDatos.user_partida, contrasena);

			if (loginExitoso) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
				marco.dispose(); // Cerrar la ventana de inicio de sesión
				marco.remove(this);
				
	            Marco Juego = new Marco();
						

			} else {
				JOptionPane.showMessageDialog(this, "Inicio de sesión fallido. Verifique sus credenciales.",
						"Inicio de Sesión Fallido", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == botonRegistrar) {
			ControlDatos.user_partida = usuarioField.getText();
			String contrasena = String.valueOf(contrasenaField.getPassword());

			// registro
			 registroExitoso = ControlDatos.registrarUsuario(ControlDatos.user_partida , contrasena);

			if (registroExitoso) {
				JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente: " + ControlDatos.user_partida , "Registro Exitoso",
						JOptionPane.INFORMATION_MESSAGE);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
				marco.dispose();
				marco.remove(this);
				
				Marco Juego = new Marco();
				

			} else {
				JOptionPane.showMessageDialog(this, "Error al registrar usuario. Inténtelo nuevamente.",
						"Registro Fallido", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
