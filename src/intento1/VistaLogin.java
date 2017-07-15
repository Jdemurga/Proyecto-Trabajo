package intento1;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class VistaLogin extends JFrame {
	private Controlador controlador;
	private JTextField textField;
	private JPasswordField passwordField;
	private FondoEscritorio contenedor = new FondoEscritorio();

	public VistaLogin() {
		contenedor.alto(90);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/icono_flojo - copia.png")));
		setContentPane(contenedor);
		contenedor.setImagen("/img/logo_flojo.jpg");
		contenedor.setBackground(Color.BLUE);

		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblContrasea = new JLabel("Contrase\u00F1a : ");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));

		textField = new JTextField();

		textField.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.LoginRegistro();
				textField.setText("");
				passwordField.setText("");

			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char teclapresion = e.getKeyChar();
				if (teclapresion == KeyEvent.VK_ENTER) {
					controlador.LoginRegistro();
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char teclapresion = e.getKeyChar();
				if (teclapresion == KeyEvent.VK_ENTER) {
					controlador.LoginRegistro();
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblContrasea)
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 99,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 341,
												GroupLayout.PREFERRED_SIZE)
										.addGap(110))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 337,
												GroupLayout.PREFERRED_SIZE)
										.addGap(114))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(246).addComponent(btnLogin)
						.addContainerGap(269, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(87)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
				.addGap(45)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(62).addComponent(btnLogin).addContainerGap(91, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Arias Login");
		setSize(614, 429);
		setLocationRelativeTo(null);

	}

	public String obtenerUSr() {
		return textField.getText();
	}

	public String obtenerClave() {
		return passwordField.getText();
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
