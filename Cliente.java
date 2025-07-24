package Ejercicio;
import java.util.List;
import java.util.ArrayList;


public class Cliente {
	int id;
	String nombre, telefono;
	private List<Cuenta> cuentas = new ArrayList();
	private List<Transaccion> transacciones = new ArrayList();
	
	
	public Cliente(int id, String nombre, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		
	}
	
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	
	public void registrarTransaccion(Transaccion transaccion) {
		transacciones.add(transaccion);
	}
	
	public void deposito(Cuenta cuenta, double monto) {
		cuenta.deposito(monto);
		registrarTransaccion(new Transaccion(TipoTransaccion.Deposito, monto, Hora.getInstancia().today()));
	}
	
	public void retiro(Cuenta cuenta, double monto) {
		cuenta.retiro(monto);
		registrarTransaccion(new Transaccion(TipoTransaccion.Retiro, monto, Hora.getInstancia().today()));
		
	}
}
