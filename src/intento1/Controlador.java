package intento1;

import java.sql.ResultSet;

public class Controlador {
	private VistaRegistro vista;
	private Modelo modelo;
	private VistaBuscar vistaBuscar;
	private VistaLogin vistaLogin;
	private VistaEquipo vistaEquipo;
	
	public Controlador(VistaRegistro vista,Modelo modelo, VistaBuscar vistaBuscar,VistaLogin vistaLogin, VistaEquipo vistaEquipo) {
		this.modelo=modelo;
		this.vista=vista;
		this.vistaBuscar=vistaBuscar;
		this.vistaLogin=vistaLogin;
		this.vistaEquipo=vistaEquipo;
	}
	public void registroLogin(){
		vista.setVisible(false);
		vistaLogin.setVisible(true);
	}
	public void abrirEquipo(){
		vistaEquipo.setVisible(true);
	}
	public void LoginRegistro(){
		if(modelo.verificacion(vistaLogin.obtenerUSr(), vistaLogin.obtenerClave())){
			vistaLogin.setVisible(false);
			vista.setVisible(true);
		}
	}
	
	public void registarBuscar(){
		vista.setVisible(false);
		vistaBuscar.setVisible(true);
	}
	public void buscarRegistrar(){
		vista.setVisible(true);
		vistaBuscar.setVisible(false);
	}
	
	public void registrar(){
		String nombre=vista.Nombre();
		String corpub=vista.correopub();
		String contrCorPub=vista.contropub();
		String corpriv=vista.correopriv();
		String contrCorPriv=vista.contrapriv();
		String licencia =vista.licencia();
		String servidor=vista.servidor();
		String contraServ=vista.contraserv();
		String ip=vista.ip();
		String dominio=vista.dominio();
		String contraDominio=vista.contradominio();
		String local=vista.local();
		String contraLocal=vista.contralocal();
		String owncloud=vista.owncloud();
		String contraOwncloud=vista.contraown();
		String sistope=vista.sope();
		modelo.insert(corpub, contrCorPub,  nombre, corpriv, contrCorPriv, licencia, servidor, contraServ, ip, dominio, contraDominio, local, contraLocal, owncloud, contraOwncloud, sistope);
	}
	

}
