package intento2;

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
import java.io.FileWriter;
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
import javax.swing.table.TableModel;
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
	public DefaultTableModel dftm2 = new DefaultTableModel();

	private Statement stmt;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnBorrar;
	private JLabel lblEquipo;
	private FondoEscritorio contenedor = new FondoEscritorio();
	private JButton btnExportar;
	private File fichero;
	private JTable table_1;

	public VistaBuscar() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/icono_flojo - copia.png")));
		setContentPane(contenedor);

		contenedor.setImagen("/img/computer-10.jpg");

		contenedor.setBackground(Color.BLUE);
		conectar();
		actualizar();

		JScrollPane scrollPane = new JScrollPane();

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
						dftm2.removeRow(0);
					}
					buscarNombre();
				} else {

					if (!textField.equals("") && comboBox.getSelectedItem().equals("Cuenta")) {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
							dftm2.removeRow(0);

						}
						buscarCuenta();
					} else {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
						}
						rellenarTabla();
						rellenarTabla2();
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
				while (dftm2.getRowCount() > 0) {
					dftm2.removeRow(0);
				}
				rellenarTabla();
				rellenarTabla2();
			}
		});

		btnBorrar = new JButton("Borrar");

		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminar();
			}
		});

		lblEquipo = new JLabel("Equipos :");
		lblEquipo.setForeground(Color.WHITE);

		lblEquipo.setVisible(true);

		btnExportar = new JButton("Exportar");
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// fichero();
				String nombreFichero = JOptionPane.showInputDialog("nombre del archivo");
				fichero = new File(
						"C:/Users/" + System.getProperty("user.name") + "/Desktop/" + nombreFichero + ".xls");
				try {
					exportarjTable(table, table_1, fichero);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblUsuarios = new JLabel("Usuarios : ");
		lblUsuarios.setForeground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout
												.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
																.createSequentialGroup()
																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 116,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(textField, GroupLayout.PREFERRED_SIZE,
																		409, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnRefrescar)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnBorrar).addGap(206)
																.addComponent(btnExportar))
														.addComponent(lblBuscador).addComponent(lblUsuarios))
												.addGap(85))
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblEquipo)
												.addContainerGap(955, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup().addComponent(btnVolver)
												.addContainerGap(941, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 996,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(scrollPane_1, Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE, 996,
																GroupLayout.PREFERRED_SIZE))
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnVolver).addGap(10)
						.addComponent(lblBuscador).addGap(7)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRefrescar).addComponent(btnBorrar).addComponent(btnExportar))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsuarios).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE).addGap(1)
						.addComponent(lblEquipo).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addGap(33)));

		table_1 = new JTable(dftm2);
		scrollPane_1.setViewportView(table_1);

		table = new JTable(dftm);
		table.setBackground(Color.WHITE);

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
		dftm2.addColumn("Nombre");
		dftm2.addColumn("Tipo");
		dftm2.addColumn("Procesador");
		dftm2.addColumn("RAM");
		dftm2.addColumn("Sistema");
		dftm2.addColumn("Sistema Operativo");
		dftm2.addColumn("Fecha Compra");
		dftm2.addColumn("Precio");
		rellenarTabla2();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		contenedor.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { scrollPane, table, textField,
				btnRefrescar, btnBorrar, comboBox, btnVolver, lblBuscador, lblEquipo }));
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
				Object[] fila2 = new Object[8]; // parámetros como datos retorne
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
				fila2[0] = rs.getString("NOMBRE");
				fila2[1] = rs.getString("TIPO");
				fila2[2] = rs.getString("PROCESADOR");
				fila2[3] = rs.getString("RAM");
				fila2[4] = rs.getString("SISTEMA");
				fila2[5] = rs.getString("SIST_OPERATIVO");
				fila2[6] = rs.getString("FECHA_INI");
				fila2[7] = rs.getString("PRECIO");
				dftm.addRow(fila); // Añade una fila al final del modelo de la
				dftm2.addRow(fila2); // tabla
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
				Object[] fila2 = new Object[8]; // cada fila de la consulta

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
				fila2[0] = rs.getString("NOMBRE");
				fila2[1] = rs.getString("TIPO");
				fila2[2] = rs.getString("PROCESADOR");
				fila2[3] = rs.getString("RAM");
				fila2[4] = rs.getString("SISTEMA");
				fila2[5] = rs.getString("SIST_OPERATIVO");
				fila2[6] = rs.getString("FECHA_INI");
				fila2[7] = rs.getString("PRECIO");

				dftm.addRow(fila); // Añade una fila al final del modelo de la
				dftm2.addRow(fila2); // tabla
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

	public void rellenarTabla2() {

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
				fila[1] = rs.getString("TIPO");
				fila[2] = rs.getString("PROCESADOR");
				fila[3] = rs.getString("RAM");
				fila[4] = rs.getString("SISTEMA");
				fila[5] = rs.getString("SIST_OPERATIVO");
				fila[6] = rs.getString("FECHA_INI");
				fila[7] = rs.getString("PRECIO");
				dftm2.addRow(fila); // Añade una fila al final del modelo de la
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

						for (int j = 0; j < table.getRowCount(); j++) {
							if (!table.getValueAt(j, 0).equals(table_1.getValueAt(j, 0))) {
								table_1.setValueAt(table.getValueAt(j, 0), j, 0);
							}
						}

						System.out.println("actualizado");

					} catch (SQLException e1) {
						System.out.println("error actu");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		dftm2.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (e.getType() == TableModelEvent.UPDATE) {
					int columna = e.getColumn();
					int fila = e.getLastRow();
					String query = "";
					if (columna == 0) {
						query = "UPDATE usuario SET NOMBRE='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
						table.setValueAt(table_1.getValueAt(fila, columna), fila, columna);
					}
					if (columna == 1) {
						query = "UPDATE usuario SET TIPO ='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 2) {
						query = "UPDATE usuario SET PROCESADOR='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 3) {
						query = "UPDATE usuario SET RAM='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 4) {
						query = "UPDATE usuario SET SISTEMA='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 5) {
						query = "UPDATE usuario SET SIST_OPERATIVO='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 6) {
						query = "UPDATE usuario SET FECHA_INI='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (columna == 7) {
						query = "UPDATE usuario SET PRECIO='" + table_1.getValueAt(fila, columna)
								+ "' WHERE CORREO_CUENTA='" + table_1.getValueAt(fila, 1) + "'";
					}
					if (!query.equals("")) {
						try {
							stmt = conexion.createStatement();
							int r = stmt.executeUpdate(query);
							System.out.println("actualizado2");

						} catch (SQLException e1) {
							System.out.println("error actu2");
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
			while (dftm.getRowCount() > 0) {
				dftm.removeRow(0);
			}
			while (dftm2.getRowCount() > 0) {
				dftm2.removeRow(0);
			}
			rellenarTabla();
			rellenarTabla2();
		} catch (SQLException e) {
			System.out.println("eliminado problema");
		}
	}

	// public void fichero() {
	// Properties propiedades = new Properties();
	// OutputStream salida = null;
	// int filas=table.getRowCount();
	// try {
	// File miFichero = new File("C:/Users/Cesar/Desktop/tabla.txt");
	// if (miFichero.exists()) {
	// salida = new FileOutputStream(miFichero, true);
	// // asignamos los valores a las propiedades
	// for (int i = 0; i < filas; i++) {
	// propiedades.setProperty("Nombre",table.getValueAt(i, 0).toString());
	// propiedades.setProperty("Correo_Cuenta ", table.getValueAt(i,
	// 1).toString());
	// propiedades.setProperty("Correo_Privado ", table.getValueAt(i,
	// 3).toString());
	// propiedades.setProperty("Licencia", table.getValueAt(i, 5).toString());
	// propiedades.setProperty("Servidor", table.getValueAt(i, 6).toString());
	// propiedades.setProperty("IP", table.getValueAt(i, 7).toString());
	// propiedades.setProperty("Dominio",table.getValueAt(i, 9).toString());
	// propiedades.setProperty("U.Local", table.getValueAt(i, 11).toString());
	// propiedades.setProperty("Owncloud", table.getValueAt(i, 13).toString());
	// propiedades.setProperty("S.Operativo_Servidor", table.getValueAt(i,
	// 15).toString());
	// propiedades.store(salida,
	// "-----------------------------------------------------");
	//
	// }
	//
	// // guardamos el archivo de propiedades en la carpeta de
	// // aplicación
	// propiedades.store(salida,
	// "-----------------------------------------------------");
	// } else {
	// miFichero.createNewFile();
	// salida = new FileOutputStream(miFichero, true);
	// for (int i = 0; i < filas; i++) {
	// propiedades.setProperty("Nombre",table.getValueAt(i, 0).toString());
	// propiedades.setProperty("Correo_Cuenta ", table.getValueAt(i,
	// 1).toString());
	// propiedades.setProperty("Correo_Privado ", table.getValueAt(i,
	// 3).toString());
	// propiedades.setProperty("Licencia", table.getValueAt(i, 5).toString());
	// propiedades.setProperty("Servidor", table.getValueAt(i, 6).toString());
	// propiedades.setProperty("IP", table.getValueAt(i, 7).toString());
	// propiedades.setProperty("Dominio",table.getValueAt(i, 9).toString());
	// propiedades.setProperty("U.Local", table.getValueAt(i, 11).toString());
	// propiedades.setProperty("Owncloud", table.getValueAt(i, 13).toString());
	// propiedades.setProperty("S.Operativo_Servidor", table.getValueAt(i,
	// 15).toString());
	// propiedades.store(salida,
	// "-----------------------------------------------------");
	// }
	//
	//
	//
	// System.err.println("Fichero creado");
	//
	// }
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }
	public void exportarjTable(JTable tabla, JTable tabla2, File ficheroXLS) throws IOException {
		TableModel modelo = tabla.getModel();
		TableModel modelo2 = tabla2.getModel();
		FileWriter fichero = new FileWriter(ficheroXLS);
		for (int i = 0; i < modelo.getColumnCount(); i++) {
			fichero.write(modelo.getColumnName(i) + "\t");
		}
		fichero.write("\t");
		fichero.write("\t");
		for (int i = 0; i < modelo.getColumnCount(); i++) {
			fichero.write(modelo2.getColumnName(i) + "\t");
		}
		fichero.write("\n");

		for (int i = 0; i < modelo.getRowCount(); i++) {
			for (int j = 0; j < modelo.getColumnCount(); j++) {
				fichero.write(modelo.getValueAt(i, j).toString() + "\t");
			}
			fichero.write("\t");
			fichero.write("\t");

			for (int j = 0; j < modelo2.getColumnCount(); j++) {
				fichero.write(modelo2.getValueAt(i, j)+"\t");
			}

			fichero.write("\n");
		}

		fichero.close();
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
