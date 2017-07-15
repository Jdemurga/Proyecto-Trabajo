package intento1;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaRegistro extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JComboBox comboBox ;
	private Controlador controlador;
	private FondoEscritorio contenedor = new FondoEscritorio();

	public VistaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/icono_flojo - copia.png")));
		setContentPane(contenedor);

		contenedor.setImagen("/img/giphy.gif");
		
		contenedor.setBackground(Color.BLUE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Arias Registro");
		setSize(614, 429);
		setLocationRelativeTo(null);

		JLabel lblNombreCompleto = new JLabel("Nombre completo");
		lblNombreCompleto.setForeground(Color.WHITE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCorreoCuenta = new JLabel("Correo cuenta");
		lblCorreoCuenta.setForeground(Color.WHITE);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblCorreoPrivado = new JLabel("Correo privado");
		lblCorreoPrivado.setForeground(Color.WHITE);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblLicencia = new JLabel("Licencia ");
		lblLicencia.setForeground(Color.WHITE);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Office 365 Empresa Premiun", "Office 365 Empresa Essentials", "Office 365 Enterprise E3"}));
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a cuenta");
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblContraseaPrivada = new JLabel("Contrase\u00F1a privada");
		lblContraseaPrivada.setForeground(Color.WHITE);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Servidor");
		lblNewLabel_1.setForeground(Color.WHITE);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblContraseaServidor = new JLabel("Contrase\u00F1a servidor");
		lblContraseaServidor.setForeground(Color.WHITE);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setForeground(Color.WHITE);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblSoperativo = new JLabel("S.Operativo Servidor");
		lblSoperativo.setForeground(Color.WHITE);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setForeground(Color.WHITE);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblContraseaDominio = new JLabel("Contrase\u00F1a dominio");
		lblContraseaDominio.setForeground(Color.WHITE);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		JLabel lblUlocal = new JLabel("U.Local");
		lblUlocal.setForeground(Color.WHITE);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		JLabel lblContraseaLocal = new JLabel("Contrase\u00F1a local");
		lblContraseaLocal.setForeground(Color.WHITE);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		
		JLabel lblOwncloud = new JLabel("Owncloud");
		lblOwncloud.setForeground(Color.WHITE);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		JLabel lblContraseaOwncloud = new JLabel("Contrase\u00F1a owncloud");
		lblContraseaOwncloud.setForeground(Color.WHITE);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.registrar();
			}
		});
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.registarBuscar();
			}
		});
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			controlador.registroLogin();	
			}
		});
	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(8)
											.addComponent(lblNombreCompleto)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(18)
											.addComponent(lblCorreoCuenta)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addGap(73)
											.addComponent(lblLicencia))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(lblCorreoPrivado)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblContraseaPrivada)
											.addGap(47)
											.addComponent(lblNewLabel_1))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblContraseaServidor, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblContraseaDominio, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(69))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(lblIp)
							.addGap(63)
							.addComponent(lblSoperativo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(lblDominio))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(43)
									.addComponent(lblUlocal)
									.addGap(60)
									.addComponent(lblContraseaLocal, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblOwncloud))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnLogOut)
										.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_14)
								.addComponent(lblContraseaOwncloud, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(220)
							.addComponent(btnEnviar)
							.addGap(94)
							.addComponent(btnBuscar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNombreCompleto)
						.addComponent(lblLicencia)
						.addComponent(lblCorreoCuenta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContraseaPrivada)
						.addComponent(lblContraseaServidor)
						.addComponent(lblCorreoPrivado)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIp)
						.addComponent(lblSoperativo)
						.addComponent(lblDominio)
						.addComponent(lblContraseaDominio))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUlocal)
						.addComponent(lblContraseaLocal)
						.addComponent(lblOwncloud)
						.addComponent(lblContraseaOwncloud))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnviar)
						.addComponent(btnBuscar)
						.addComponent(btnLogOut))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public String Nombre(){
		return textField.getText();
	}
	public String correopub(){
		return textField_1.getText();
	}
	public String contropub(){
		return textField_2.getText();
	}
	public String correopriv(){
		return textField_3.getText();
	}
	public String contrapriv(){
		return textField_4.getText();
	}
	public String licencia(){
		return comboBox.getSelectedItem().toString();
	}
	public String servidor(){
		return textField_5.getText();
	}
	public String contraserv(){
		return textField_6.getText();
	}
	public String ip(){
		return textField_7.getText();
	}
	public String sope(){
		return textField_8.getText();
	}
	public String dominio(){
		return textField_9.getText();
	}
	public String contradominio(){
		return textField_10.getText();
	}
	public String local(){
		return textField_11.getText();
	}
	public String contralocal(){
		return textField_12.getText();
	}
	public String owncloud(){
		return textField_13.getText();
	}
	public String contraown(){
		return textField_14.getText();
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
