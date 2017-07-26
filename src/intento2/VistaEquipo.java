package intento2;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaEquipo extends JFrame{
	private String nombre;
	public JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1 ;
	private String url = "jdbc:mysql://localhost/ariasdatabase";
	private String pwd = "Arias2017";
	private String usr = "AriasDataBase";
	private Connection conexion;
	private Statement stmt;	
	private Controlador controlador;
	public VistaEquipo() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblTipo = new JLabel("Tipo");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblProcesador = new JLabel("Procesador");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblRam = new JLabel("RAM");
		
		JLabel lblNewLabel = new JLabel("Sistema");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblSistemaOperativo = new JLabel("Sistema Operativo");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha_comprado");
		
		comboBox= new JComboBox();
		
		comboBox_1= new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"------------", "Sobremesa", "Portatil", "Movil"}));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		
		JButton btnNewButton = new JButton("MODIFICAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				textField_3.setEnabled(true);
				textField_5.setEnabled(true);
				textField_6.setEnabled(true);
				comboBox.setEnabled(true);
				comboBox_1.setEnabled(true);
			}
		});
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipo)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProcesador))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRam))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSistemaOperativo)
								.addComponent(btnNewButton, Alignment.TRAILING))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrecio)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(103)
									.addComponent(btnGuardar)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(lblProcesador)
						.addComponent(lblRam)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSistemaOperativo)
						.addComponent(lblNewLabel_1)
						.addComponent(lblPrecio))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnGuardar))
					.addGap(57))
		);
		getContentPane().setLayout(groupLayout);
		setTitle("Arias Equipo");
		setSize(555, 429);
		setLocationRelativeTo(null);
		
	}

	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			System.err.println("ok");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("nop");
		}
	}
	
	 public void ponerTexto(String a)
	   {
	       textField.setText(a);
	  }
	
}
