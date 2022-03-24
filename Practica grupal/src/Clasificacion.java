public class Clasificacion {

	private EquipoClasificacion[] tabla;

	public Clasificacion(Equipo[] equipos, Calendario calendario) {

		int numeroEquipos = equipos.length;
		this.tabla = new EquipoClasificacion[numeroEquipos];

		//Crear la tabla de clasificacion
		for(int i=0; i<numeroEquipos; i++) {
			this.tabla[i] = new EquipoClasificacion();
			this.tabla[i].setEquipo(equipos[i]);
		}
		//Tenemos que cambiar el numero de jornadas a jugar
//		Preguntar a Rafa :(
		
		//Rellenamos la tabla de clasificacion
		Jornada [] jornadas = calendario.getJornadas();

		for(Jornada jor: jornadas) {
			if (jor.isTerminada()) {
				Partido[] partidos = jor.getPartidos();
				for(Partido par: partidos) {
					
					//Quien es local
					Equipo local = par.getEquipoLocal();
					
					//Quien es visitante
					Equipo visitante = par.getEquipoVisitante();
					
					//Buscarlos en la tabla clasificacion
					int contador = 0;
					EquipoClasificacion localClas = tabla[contador];
					while (localClas.getEquipo()!= local) {
						contador++;
						localClas = tabla[contador];
					}
					
					contador = 0;
					EquipoClasificacion visitClas = tabla[contador];
					while (visitClas.getEquipo()!= visitante) {
						contador++;
						visitClas = tabla[contador];
					}
					
					//Veamos los goles		
					int golesLocal = par.getGolesLocal();
					int golesVisitante = par.getGolesVisitante();

					//ACTUALIZAMOS TABLA
					localClas.añadirGolesFavor(golesLocal);
					localClas.añadirGolesContra(golesVisitante);
					visitClas.añadirGolesFavor(golesVisitante);	
					visitClas.añadirGolesContra(golesLocal);

					//Resolvemos puntos
					if (golesLocal > golesVisitante) {//Victoria Local
						localClas.addPartidoGanado();
						visitClas.addPartidoPerdido();
					}else if(golesVisitante > golesLocal) {//Victoria Visitante
						localClas.addPartidoPerdido();
						visitClas.addPartidoGanado();
					}else { //Empate
						visitClas.addPartidoEmpatado();
						localClas.addPartidoEmpatado();
					}
				}
			}
		}
		ordenar();

	}

	private void ordenar() {
		int n = tabla.length;  
		EquipoClasificacion temp = null; 
		
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){  
				if(vaDebajo(tabla[j-1],tabla[j])){
					temp = tabla[j-1];  
					tabla[j-1] = tabla[j];  
					tabla[j] = temp;  
				}
			}
		}

	}

	private boolean vaDebajo(EquipoClasificacion equipo1, EquipoClasificacion equipo2) {

		if(equipo1.getPuntos()<equipo2.getPuntos()) {
			return true;
		}else if(equipo1.getPuntos()>equipo2.getPuntos()) {
			return false;
		}else {//A igualdad de puntos, diferencia goles
			if(equipo1.getDifGoles()<equipo2.getDifGoles()) {
				return true;
			}else if(equipo1.getDifGoles()>equipo2.getDifGoles()) {
				return false;
			}else {//A igualdad de puntos y difGoles, partidos ganados
				if(equipo1.getGanados()<equipo2.getGanados()) {
					return true;
				}else {
					return false;
				}
			}
		}

	}

	public EquipoClasificacion[] getTabla() {
		return tabla;
	}

	public void setTabla(EquipoClasificacion[] tabla) {

		this.tabla = tabla;
	}
	
	@Override
	public String toString() {
		
		String cadena = "EQUIPO\t\t\t\t\t\t\tPJ\tPG\tPE\tPP\tGF\tGC\tDG\tPts\n";
		for(EquipoClasificacion equipoC: tabla) {
			cadena += equipoC;
		}
		
		return cadena;
	}

}