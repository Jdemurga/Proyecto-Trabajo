package intento2;

public class Test {
	public static void main(String[] args) {
		VistaRegistro miVista = new VistaRegistro();
		VistaBuscar miVistaBuscar=new VistaBuscar();
		VistaEquipo miVistaEquipo=new VistaEquipo();
		Modelo miModelo= new Modelo();
		VistaLogin miVistaLogin =new VistaLogin();
		Controlador miControlador= new Controlador(miVista, miModelo,miVistaBuscar,miVistaLogin,miVistaEquipo);
		miModelo.setControlador(miControlador);
		miVista.setControlador(miControlador);
		miVistaBuscar.setControlador(miControlador);
		miVistaLogin.setControlador(miControlador);
		miVistaLogin.setVisible(true);
	}

}
