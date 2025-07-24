package Ejercicio;
import java.time.LocalTime;


public class Transaccion {
	TipoTransaccion tipo;
	double monto;
	LocalTime fecha;
	
	
	public Transaccion(TipoTransaccion tipo, double monto, LocalTime fecha) {
		this.tipo = tipo;
		this.monto = monto;
		this.fecha = fecha;
	}
	
	
	
}
