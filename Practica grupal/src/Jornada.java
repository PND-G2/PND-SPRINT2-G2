import java.util.GregorianCalendar;

public class Jornada {

	private Partido[] partidos;
	private boolean terminada = false;
	public GregorianCalendar fechaJornada;
	
	public Partido[] getPartidos() {
		return partidos;
	}

	public void setPartidos(Partido[] partido) { //SETTER
		this.partidos = partido;
	}	
	
	public boolean isTerminada() {
		return terminada;
	}
	
	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}
	
	public void terminar() {
		this.terminada = true;
	}

	public GregorianCalendar getFechaJornada() {
		return fechaJornada;
	}

	public void setFechaJornada(GregorianCalendar fechaJornada) {
		this.fechaJornada = fechaJornada;
	}

	
}