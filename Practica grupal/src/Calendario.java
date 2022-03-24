public class Calendario {

	private Jornada[] jornadas;

	public Calendario() {}

	public Calendario(Equipo[] listaEquipos, Arbitro[] arbitros) { // RAFA
		
		int numeroEquipos = listaEquipos.length;
		
		Equipo[][] emparejamientos = new Equipo[2][numeroEquipos/2];
		
		//RELLENAR MITAD IZQUIERDA
		for(int i = 0; i<numeroEquipos/2; i++) {
			emparejamientos[0][i] = listaEquipos[i];
		}
		//RELLENAR MITAD DERECHA
		for(int j=((numeroEquipos/2)-1) ; j>=0 ; j--) {
			emparejamientos[1][j] = listaEquipos[numeroEquipos-1-j];
		}
		
		//Asignamos la longitud a la jornada de Calendario
		this.jornadas = new Jornada[(numeroEquipos-1)*2];
		
		//Como vamos a asignar en la misma vuelta los partidos de ida y de vuelta,
		//el for solo será de largo como la mitad de las jornadas.
		for(int i=0; i<jornadas.length/2; i++) {
			
			//Creamos dos arrays en los cuales vamos a meter los partidos de cada mitad de la liga
			Partido[] partidosIda = new Partido[numeroEquipos/2];
			Partido[] partidosVuelta = new Partido[numeroEquipos/2];
			
			//Una vez que tenemos creados los arrays de Partidos, hacemos otro for que 
			//da numeroEquipos/2 vueltas para crear los enfrentamientos.
			for(int j = 0; j<numeroEquipos/2; j++) {
				//Jornada de Ida
				partidosIda[j] = new Partido();
				partidosIda[j].setEquipoLocal(emparejamientos[0][j]);
				partidosIda[j].setEquipoVisitante(emparejamientos[1][j]);
				
				//Jornada de vuelta
				partidosVuelta[j] = new Partido();
				partidosVuelta[j].setEquipoLocal(emparejamientos[1][j]);
				partidosVuelta[j].setEquipoVisitante(emparejamientos[0][j]);
			}
			
			
			//Asignamos los partidos a las jornadas de ida
			jornadas[i] = new Jornada();
			jornadas[i].setPartidos(partidosIda);
			
			//Asignamos los partidos a las jornadas de vuelta
			jornadas[jornadas.length-1-i] = new Jornada();
			jornadas[jornadas.length-1-i].setPartidos(partidosVuelta);
			
			Equipo[][] copiaEnfrentamientos = new Equipo[2][numeroEquipos/2];
			
			//Reajustar la tabla de emparejamientos
			
			//PARTE IZQUIERDA
			for (int k=0 ; k<numeroEquipos/2 ; k++) {
				
				//Si k=0 nos encontramos en la posición 0 0, por lo que el valor se queda igual
				if (k == 0) {
					copiaEnfrentamientos[0][k] = emparejamientos[0][k];
				}
				//Si k está entre 1 y el índice de la penúltima posición, copiaremos en 
				//el índice k+1 lo que antes estaba en la posición k
				else if(k>0 && k<(numeroEquipos/2)-1) {
					copiaEnfrentamientos[0][k+1] = emparejamientos[0][k];
				}
				//si k es igual al índice de la última posicion 
				else {
					copiaEnfrentamientos[1][k] = emparejamientos[0][k];
				}
				
			}
			
			//PARTE DERECHA
			for (int k = 0; k<numeroEquipos/2;k++) {
				if (k==0) {
					copiaEnfrentamientos[0][1]=emparejamientos[1][0];
				}
				else {
					copiaEnfrentamientos[1][k-1]=emparejamientos[1][k];
				}
			}
			
			emparejamientos = copiaEnfrentamientos;
			
		}
		
	}
	
	public Jornada[] getJornadas() {
		return jornadas;
	}

	public void setJornadas(Jornada[] jornadas) {
		this.jornadas = jornadas;
	}

}