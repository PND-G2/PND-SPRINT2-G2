public class EquipoClasificacion {

	private Equipo equipo;
	private int partidosJugados=0;
	private int ganados=0;
	private int empate=0;
	private int perdidos=0;
	private int gFavor=0;
	private int gContra=0;
	private int difGoles=0;
	private int puntos=0;
	
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public int getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	public int getGanados() {
		return ganados;
	}
	public void setGanados(int ganados) {
		this.ganados = ganados;
	}
	public int getEmpate() {
		return empate;
	}
	public void setEmpate(int empate) {
		this.empate = empate;
	}
	public int getPerdidos() {
		return perdidos;
	}
	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}
	public int getgFavor() {
		return gFavor;
	}
	public void setgFavor(int gFavor) {
		this.gFavor = gFavor;
	}
	public int getgContra() {
		return gContra;
	}
	public void setgContra(int gContra) {
		this.gContra = gContra;
	}
	public int getDifGoles() {
		return difGoles;
	}
	public void setDifGoles(int difGoles) {
		this.difGoles = difGoles;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public void añadirGolesFavor (int goles) {
		this.gFavor += goles;
		this.difGoles += goles;
	}
	public void añadirGolesContra (int goles) {
		this.gContra += goles;
		this.difGoles -= goles;
	}
	public void addPartidoGanado() {
		this.partidosJugados++;
		this.puntos +=3;
		this.ganados++;
	}
	public void addPartidoPerdido() {
		this.partidosJugados++;
		this.perdidos++;
	}
	public void addPartidoEmpatado() {
		this.partidosJugados++;
		this.puntos +=1;
		this.empate++;
	}
	@Override
	public String toString() {
		String nombreTab = equipo.getNombre();
		int longitud = nombreTab.length();
		int tabuladores = 6;
		int tabEquipo = longitud/8;
		for (int i=0; i<tabuladores-tabEquipo;i++) {
			nombreTab+="\t";
		}
		
		return nombreTab +"\t"+partidosJugados+"\t"+ganados+"\t"+empate+"\t"+perdidos+"\t"+gFavor+
				"\t"+gContra+"\t"+difGoles+"\t"+puntos +"\n";
	}
	

}