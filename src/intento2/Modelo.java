package intento2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
	private String url = "jdbc:mysql://localhost/ariasdatabase";
	private String pwd = "Arias2017";
	private String usr = "AriasDataBase";
	private Controlador controlador;
	private Connection conexion;
	private Statement stmt;
	private ResultSet rs;

	public Modelo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			System.out.println(" - Conexión con BBDD establecida -");
		} catch (Exception e) {
			System.out.println(" – Error de Conexión con BBDD -");
			e.printStackTrace();
		}
	}

	public boolean verificacion(String user, String clave) {
		try {
			String query = "SELECT * FROM administrador  WHERE user=? AND clave=?";
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, clave);
			ResultSet rset = pstmt.executeQuery();
			rset.next();
			
			if (user.equals(rset.getString("user")) && clave.equals(rset.getString("clave"))) {
				System.out.println("LOGIN CORRECTO");
				return true;
			}
			rset.close();
			pstmt.close();
		} catch (SQLException s) {
			System.out.println("FALLO DEL LOGIN");
		}
		return false;
	}

	public void insert(String correoCuenta, String contraCuenta, String nombre, String correoPrivado,
			String contraPrivado, String licencia, String servidor, String contraServidor, String ip, String dominio,
			String contraDominio, String local, String contraLocal, String owncloud, String contraOwncloud,
			String sistOper) {
		String query = "INSERT INTO usuario(CORREO_CUENTA, CONTRA_CUENTA, NOMBRE, CORREO_PRIVADO, CONTRA_PRIVADO, LICENCIA, SERVIDOR, CONTRA_SERVIDOR, IP, DOMINIO, CONTRA_DOMINIO, LOCAL, CONTRA_LOCAL, OWNCLOUD, CONTRA_OWNCLOUD, SIST_OPER) VALUES ('"
				+ correoCuenta + "','" + contraCuenta + "','" + nombre + "','" + correoPrivado + "','" + contraPrivado
				+ "','" + licencia + "','" + servidor + "','" + contraServidor + "','" + ip + "','" + dominio + "','"
				+ contraDominio + "','" + local + "','" + contraLocal + "','" + owncloud + "','" + contraOwncloud
				+ "','" + sistOper + "')";
		try {
			stmt = conexion.createStatement();
			int r = stmt.executeUpdate(query);
			System.out.println("insert correcto : " + r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
