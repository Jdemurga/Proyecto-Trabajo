package intento1;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class VistaBuscar extends JFrame {
	private JTable table;
	private Controlador controlador;
	private String url = "jdbc:mysql://localhost/ariasdatabase";
	private String pwd = "Arias2017";
	private String usr = "AriasDataBase";
	private Connection conexion;
	public DefaultTableModel dftm = new DefaultTableModel();
	private Statement stmt;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnBorrar;
	private JButton btnPc;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblEquipo;
	private JButton btnNewButton;
	private JLabel lblTipo;
	private JLabel lblProcesador;
	private JLabel lblRam;
	private JLabel lblSistema;
	private JLabel lblSistemaOperativo;
	private JLabel lblNewLabel;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JTextField textField_1;
	private JLabel lblPrecio;
	private FondoEscritorio contenedor = new FondoEscritorio();
	private JButton btnExportar;

	public VistaBuscar() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/icono_flojo - copia.png")));
		setContentPane(contenedor);

		contenedor.setImagen("/img/computer-10.jpg");

		contenedor.setBackground(Color.BLUE);
		conectar();
		actualizar();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblEquipo.setVisible(false);
				lblPrecio.setVisible(false);
				btnNewButton.setVisible(false);
				lblTipo.setVisible(false);
				lblProcesador.setVisible(false);
				lblRam.setVisible(false);
				lblSistema.setVisible(false);
				lblSistemaOperativo.setVisible(false);
				lblNewLabel.setVisible(false);
				comboBox_1.setVisible(false);
				comboBox_2.setVisible(false);
				textField_1.setVisible(false);
				textField_2.setVisible(false);
				textField_3.setVisible(false);
				textField_5.setVisible(false);
				textField_6.setVisible(false);
			}
		});

		JButton btnVolver = new JButton("volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.buscarRegistrar();
			}
		});

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (!textField.equals("") && comboBox.getSelectedItem().equals("Nombre")) {
					while (dftm.getRowCount() > 0) {
						dftm.removeRow(0);
					}
					buscarNombre();
				} else {

					if (!textField.equals("") && comboBox.getSelectedItem().equals("Cuenta")) {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
						}
						buscarCuenta();
					} else {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
						}
						rellenarTabla();
					}
				}
			}
		});

		textField.setColumns(10);

		JLabel lblBuscador = new JLabel("Buscador: ");
		lblBuscador.setForeground(Color.WHITE);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "---------", "Nombre", "Cuenta" }));

		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				while (dftm.getRowCount() > 0) {
					dftm.removeRow(0);
				}
				rellenarTabla();
			}
		});

		btnBorrar = new JButton("Borrar");

		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminar();
			}
		});

		btnPc = new JButton("EQUIPO");
		btnPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				darValor();
				lblEquipo.setVisible(true);
				lblPrecio.setVisible(true);
				btnNewButton.setVisible(true);
				lblTipo.setVisible(true);
				lblProcesador.setVisible(true);
				lblRam.setVisible(true);
				lblSistema.setVisible(true);
				lblSistemaOperativo.setVisible(true);
				lblNewLabel.setVisible(true);
				comboBox_1.setVisible(true);
				comboBox_2.setVisible(true);
				textField_1.setVisible(true);
				textField_2.setVisible(true);
				textField_3.setVisible(true);
				textField_5.setVisible(true);
				textField_6.setVisible(true);
			}
		});
		btnPc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setColumns(10);

		lblEquipo = new JLabel("Equipo :");
		lblEquipo.setForeground(Color.WHITE);

		btnNewButton = new JButton("GUARDAR");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizar_tipo();
				actualizar_procesador();
				actualizar_ram();
				actualizar_sistema();
				actualizar_SistOper();
				actualizar_Fecha();
				actualizar_precio();

			}
		});

		lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(Color.WHITE);

		lblProcesador = new JLabel("Procesador");
		lblProcesador.setForeground(Color.WHITE);

		lblRam = new JLabel("RAM");
		lblRam.setForeground(Color.WHITE);

		lblSistema = new JLabel("Sistema");
		lblSistema.setForeground(Color.WHITE);

		lblSistemaOperativo = new JLabel("Sistema Operativo");
		lblSistemaOperativo.setForeground(Color.WHITE);

		lblNewLabel = new JLabel("Fecha Compra");
		lblNewLabel.setForeground(Color.WHITE);

		comboBox_1 = new JComboBox();
		comboBox_1
				.setModel(new DefaultComboBoxModel(new String[] { "------------", "Sobremesa", "Portatil", "Movil" }));

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "-----", "32", "64", "Movil" }));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.WHITE);

		lblEquipo.setVisible(false);
		lblPrecio.setVisible(false);
		btnNewButton.setVisible(false);
		lblTipo.setVisible(false);
		lblProcesador.setVisible(false);
		lblRam.setVisible(false);
		lblSistema.setVisible(false);
		lblSistemaOperativo.setVisible(false);
		lblNewLabel.setVisible(false);
		comboBox_1.setVisible(false);
		comboBox_2.setVisible(false);
		textField_1.setVisible(false);
		textField_2.setVisible(false);
		textField_3.setVisible(false);
		textField_5.setVisible(false);
		textField_6.setVisible(false);

		btnExportar = new JButton("Exportar");
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				fichero();
				
				
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(btnVolver)
										.addComponent(lblBuscador))
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 409,
														GroupLayout.PREFERRED_SIZE)
												.addGap(50).addComponent(btnRefrescar)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBorrar)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPc))
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
										.addComponent(btnExportar).addGap(12)))
								.addContainerGap())
						.addComponent(lblEquipo)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblTipo)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblProcesador)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblRam)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblSistema)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 122,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(16)
												.addComponent(lblSistemaOperativo))
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_5,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addGap(7)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton,
														GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblPrecio))
								.addContainerGap(38, Short.MAX_VALUE)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnVolver).addGap(21)
						.addComponent(lblBuscador).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRefrescar).addComponent(btnBorrar).addComponent(btnPc)
								.addComponent(btnExportar))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEquipo).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTipo)
								.addComponent(lblProcesador).addComponent(lblRam).addComponent(lblSistema)
								.addComponent(lblSistemaOperativo).addComponent(lblNewLabel).addComponent(lblPrecio))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addGap(15)));

		table = new JTable(dftm);
		table.setBackground(Color.WHITE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(table.getSelectedRow());
				lblEquipo.setVisible(false);
				lblPrecio.setVisible(false);
				btnNewButton.setVisible(false);
				lblTipo.setVisible(false);
				lblProcesador.setVisible(false);
				lblRam.setVisible(false);
				lblSistema.setVisible(false);
				lblSistemaOperativo.setVisible(false);
				lblNewLabel.setVisible(false);
				comboBox_1.setVisible(false);
				comboBox_2.setVisible(false);
				textField_1.setVisible(false);
				textField_2.setVisible(false);
				textField_3.setVisible(false);
				textField_5.setVisible(false);
				textField_6.setVisible(false);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		dftm.addColumn("Nombre");
		dftm.addColumn("Cuenta");
		dftm.addColumn("C.Cuenta");
		dftm.addColumn("Email");
		dftm.addColumn("C.Email");
		dftm.addColumn("Licencia");
		dftm.addColumn("Servidor");
		dftm.addColumn("C.Servidor");
		dftm.addColumn("IP");
		dftm.addColumn("Dominio");
		dftm.addColumn("C.Dominio");
		dftm.addColumn("Local");
		dftm.addColumn("C.Local");
		dftm.addColumn("Owncloud");
		dftm.addColumn("C.Owncloud");
		dftm.addColumn("SistemaOpServidor");
		rellenarTabla();

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		contenedor.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { scrollPane, table, textField,
				btnRefrescar, btnBorrar, btnPc, comboBox, btnVolver, lblBuscador, lblEquipo, lblTipo, comboBox_1,
				lblProcesador, textField_2, lblRam, textField_3, lblSistema, comboBox_2, lblSistemaOperativo,
				textField_5, textField_6, lblNewLabel, textField_1, btnNewButton, lblPrecio }));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Arias Buscar");
		setSize(1038, 542);
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

	public ResultSet listas() throws SQLException {
		String seleccion = "SELECT * FROM usuario ";

		stmt = conexion.prepareStatement(seleccion);

		ResultSet rs = stmt.executeQuery(seleccion);

		return rs;
	}

	public ResultSet nombre() throws SQLException {
		String query = "SELECT * FROM usuario  WHERE NOMBRE LIKE '%" + textField.getText() + "%'";
		stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public ResultSet cuenta() throws SQLException {
		String query = "SELECT * FROM usuario  WHERE CORREO_CUENTA LIKE '%" + textField.getText() + "%'";
		stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public void buscarNombre() {

		try {
			ResultSet rs = nombre();
			while (rs.next()) {
				Object[] fila = new Object[16];// Creamos un Objeto con tantos
												// parámetros como datos retorne
												// cada fila
												// de la consulta
				fila[0] = rs.getString("NOMBRE"); // Lo que hay entre comillas
													// son los campos de la base
													// de datos
				fila[1] = rs.getString("CORREO_CUENTA");
				fila[2] = rs.getString("CONTRA_CUENTA");
				fila[3] = rs.getString("CORREO_PRIVADO");
				fila[4] = rs.getString("CONTRA_PRIVADO");
				fila[5] = rs.getString("LICENCIA");
				fila[6] = rs.getString("SERVIDOR");
				fila[7] = rs.getString("CONTRA_SERVIDOR");
				fila[8] = rs.getString("IP");
				fila[9] = rs.getString("DOMINIO");
				fila[10] = rs.getString("CONTRA_DOMINIO");
				fila[11] = rs.getString("LOCAL");
				fila[12] = rs.getString("CONTRA_LOCAL");
				fila[13] = rs.getString("OWNCLOUD");
				fila[14] = rs.getString("CONTRA_OWNCLOUD");
				fila[15] = rs.getString("SIST_OPER");

				dftm.addRow(fila); // Añade una fila al final del modelo de la
									// tabla
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buscarCuenta() {

		try {
			ResultSet rs = cuenta();
			while (rs.next()) {
				Object[] fila = new Object[16];// Creamos un Objeto con tantos
												// parámetros como datos retorne
												// cada fila
												// de la consulta
				fila[0] = rs.getString("NOMBRE"); // Lo que hay entre comillas
													// son los campos de la base
													// de datos
				fila[1] = rs.getString("CORREO_CUENTA");
				fila[2] = rs.getString("CONTRA_CUENTA");
				fila[3] = rs.getString("CORREO_PRIVADO");
				fila[4] = rs.getString("CONTRA_PRIVADO");
				fila[5] = rs.getString("LICENCIA");
				fila[6] = rs.getString("SERVIDOR");
				fila[7] = rs.getString("CONTRA_SERVIDOR");
				fila[8] = rs.getString("IP");
				fila[9] = rs.getString("DOMINIO");
				fila[10] = rs.getString("CONTRA_DOMINIO");
				fila[11] = rs.getString("LOCAL");
				fila[12] = rs.getString("CONTRA_LOCAL");
				fila[13] = rs.getString("OWNCLOUD");
				fila[14] = rs.getString("CONTRA_OWNCLOUD");
				fila[15] = rs.getString("SIST_OPER");

				dftm.addRow(fila); // Añade una fila al final del modelo de la
									// tabla
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rellenarTabla() {

		try {
			ResultSet rs = listas();
			while (rs.next()) {
				Object[] fila = new Object[16];// Creamos un Objeto con tantos
												// parámetros como datos retorne
												// cada fila
												// de la consulta
				fila[0] = rs.getString("NOMBRE"); // Lo que hay entre comillas
													// son los campos de la base
													// de datos
				fila[1] = rs.getString("CORREO_CUENTA");
				fila[2] = rs.getString("CONTRA_CUENTA");
				fila[3] = rs.getString("CORREO_PRIVADO");
				fila[4] = rs.getString("CONTRA_PRIVADO");
				fila[5] = rs.getString("LICENCIA");
				fila[6] = rs.getString("SErVIDOR");
				fila[7] = rs.getString("CONTRA_SERVIDOR");
				fila[8] = rs.getString("IP");
				fila[9] = rs.getString("DOMINIO");
				fila[10] = rs.getString("CONTRA_DOMINIO");
				fila[11] = rs.getString("LOCAL");
				fila[12] = rs.getString("CONTRA_LOCAL");
				fila[13] = rs.getString("OWNCLOUD");
				fila[14] = rs.getString("CONTRA_OWNCLOUD");
				fila[15] = rs.getString("SIST_OPER");

				dftm.addRow(fila); // Añade una fila al final del modelo de la
									// tabla
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualizar() {
		dftm.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (e.getType() == TableModelEvent.UPDATE) {
					int columna = e.getColumn();
					int fila = e.getLastRow();
					String query = "";
					if (columna == 0) {
						query = "UPDATE usuario SET NOMBRE='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 1) {
						query = "UPDATE usuario SET CORREO_CUENTA='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 2) {
						query = "UPDATE usuario SET CONTRA_CUENTA='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 3) {
						query = "UPDATE usuario SET CORREO_PRIVADO='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 4) {
						query = "UPDATE usuario SET CONTRA_PRIVADO='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 5) {
						query = "UPDATE usuario SET LICENCIA='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 6) {
						query = "UPDATE usuario SET SERVIDOR='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 7) {
						query = "UPDATE usuario SET CONTRA_SERVIDOR='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 8) {
						query = "UPDATE usuario SET IP='" + table.getValueAt(fila, columna) + "' WHERE CORREO_CUENTA='"
								+ table.getValueAt(fila, 1) + "'";
					}
					if (columna == 9) {
						query = "UPDATE usuario SET DOMINIO='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 10) {
						query = "UPDATE usuario SET CONTRA_DOMINIO='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 11) {
						query = "UPDATE usuario SET LOCAL='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 12) {
						query = "UPDATE usuario SET CONTRA_LOCAL='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 13) {
						query = "UPDATE usuario SET OWNCLOUD='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 14) {
						query = "UPDATE usuario SET CONTRA_OWNCLOUD='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 15) {
						query = "UPDATE usuario SET SIST_OPER='" + table.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
					}
					try {
						stmt = conexion.createStatement();
						int r = stmt.executeUpdate(query);
						System.out.println("actualizado");

					} catch (SQLException e1) {
						System.out.println("error actu");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
	}

	public void eliminar() {
		int fila = table.getSelectedRow();
		String query = "DELETE FROM usuario WHERE CORREO_CUENTA = '" + table.getValueAt(fila, 1) + "'";
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			System.out.println("eliminado ok");
		} catch (SQLException e) {
			System.out.println("eliminado problema");
		}
	}

	public void actualizar_tipo() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET TIPO='" + comboBox_1.getSelectedItem().toString() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_procesador() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET PROCESADOR='" + textField_2.getText() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_ram() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET RAM='" + textField_3.getText() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_sistema() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET SISTEMA='" + comboBox_2.getSelectedItem().toString()
				+ "' WHERE CORREO_CUENTA='" + table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_SistOper() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET SIST_OPERATIVO='" + textField_5.getText() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_Fecha() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET FECHA_INI='" + textField_6.getText() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizar_precio() {
		int fila = table.getSelectedRow();
		String query = "UPDATE usuario SET PRECIO='" + textField_1.getText() + "' WHERE CORREO_CUENTA='"
				+ table.getValueAt(fila, 1) + "'";
		;
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void darValor() {
		int fila = table.getSelectedRow();
		String query = "SELECT * FROM usuario WHERE CORREO_CUENTA ='" + table.getValueAt(fila, 1) + "'";
		try {
			stmt = conexion.prepareStatement(query);
			ResultSet rset = stmt.executeQuery(query);
			if (rset == null) {
			} else {
				while (rset.next()) {
					String tipo = rset.getString(17);
					String procesador = rset.getString(18);
					String ram = rset.getString(19);
					String sistema = rset.getString(20);
					String sistema_op = rset.getString(21);
					String fecha = rset.getString(22);
					String precio = rset.getString(23);

					comboBox_1.setSelectedItem(tipo);
					textField_2.setText(procesador);
					textField_3.setText(ram);
					comboBox_2.setSelectedItem(sistema);
					textField_5.setText(sistema_op);
					textField_6.setText(fecha);
					textField_1.setText(precio);
				}
			}
		} catch (Exception e) {
		}

	}

//	public void fichero() {
//		Properties propiedades = new Properties();
//		OutputStream salida = null;
//		int filas=table.getRowCount();
//		try {
//			File miFichero = new File("C:/Users/Cesar/Desktop/tabla.txt");
//			if (miFichero.exists()) {
//				salida = new FileOutputStream(miFichero, true);
//				// asignamos los valores a las propiedades
//				for (int i = 0; i < filas; i++) {
//					propiedades.setProperty("Nombre",table.getValueAt(i, 0).toString());
//					propiedades.setProperty("Correo_Cuenta ", table.getValueAt(i, 1).toString());
//					propiedades.setProperty("Correo_Privado ", table.getValueAt(i, 3).toString());
//					propiedades.setProperty("Licencia", table.getValueAt(i, 5).toString());
//					propiedades.setProperty("Servidor", table.getValueAt(i, 6).toString());
//					propiedades.setProperty("IP", table.getValueAt(i, 7).toString());
//					propiedades.setProperty("Dominio",table.getValueAt(i, 9).toString());
//					propiedades.setProperty("U.Local", table.getValueAt(i, 11).toString());
//					propiedades.setProperty("Owncloud", table.getValueAt(i, 13).toString());
//					propiedades.setProperty("S.Operativo_Servidor", table.getValueAt(i, 15).toString());
//					propiedades.store(salida, "-----------------------------------------------------");
//
//				}
//				
//				// guardamos el archivo de propiedades en la carpeta de
//				// aplicación
//				propiedades.store(salida, "-----------------------------------------------------");
//			} else {
//				miFichero.createNewFile();
//				salida = new FileOutputStream(miFichero, true);
//				for (int i = 0; i < filas; i++) {
//					propiedades.setProperty("Nombre",table.getValueAt(i, 0).toString());
//					propiedades.setProperty("Correo_Cuenta ", table.getValueAt(i, 1).toString());
//					propiedades.setProperty("Correo_Privado ", table.getValueAt(i, 3).toString());
//					propiedades.setProperty("Licencia", table.getValueAt(i, 5).toString());
//					propiedades.setProperty("Servidor", table.getValueAt(i, 6).toString());
//					propiedades.setProperty("IP", table.getValueAt(i, 7).toString());
//					propiedades.setProperty("Dominio",table.getValueAt(i, 9).toString());
//					propiedades.setProperty("U.Local", table.getValueAt(i, 11).toString());
//					propiedades.setProperty("Owncloud", table.getValueAt(i, 13).toString());
//					propiedades.setProperty("S.Operativo_Servidor", table.getValueAt(i, 15).toString());
//					propiedades.store(salida, "-----------------------------------------------------");
//				}
//				
//				
//
//				System.err.println("Fichero creado");
//
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
