import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		final int EDAD = (int) Math.floor(Math.random() * 15) + 4;
		
		//Pedimos al usuario que introduzca un numero par de equipos ;)
		System.out.println("Introduzca un número par de equipos:");
		Scanner sc = new Scanner(System.in);
		final int NUMEROEQUIPOS = sc.nextInt();
		System.out.println("Participan "+NUMEROEQUIPOS+" equipos en la liga");

		//La cantidad de jornadas de la liga dependerá del número de equipos que metamos.
		final int CANTIDADJORNADAS = (NUMEROEQUIPOS-1)*2;

		//Generamos de manera random la jornada actual.
		int jornadaActual = (int) Math.floor(Math.random()*CANTIDADJORNADAS);
		System.out.println("Jornada actual: "+(jornadaActual+1)+"\n");

		//Array de equipos que participan en la liga
		Equipo[] listaEquipos = crearEquipos(NUMEROEQUIPOS, EDAD);
		Arbitro[] listaArbitros =new Arbitro[NUMEROEQUIPOS/2];
		for(int i = 0 ; i<listaArbitros.length ; i++) {
			listaArbitros[i]=crearArbitro();
		}

		//Creamos nuestra liga
		Liga miLiga = new Liga("LIGA CON EMPAQUE" ,listaEquipos, listaArbitros);
		Calendario miCalendario = miLiga.getCalendario();

		generarPartidos(miCalendario,jornadaActual);

		Clasificacion clasificacion = new Clasificacion(listaEquipos, miCalendario);
		System.out.println("\t\t\t\t\t\t"+miLiga.getNombre());
		System.out.println();
		System.out.println(clasificacion);
		
		Menu.esperaPulsacion();
		Menu.opciones(miCalendario, jornadaActual);
		
		Clasificacion nuevaClasificacion = new Clasificacion(listaEquipos, miCalendario);
		System.out.println(nuevaClasificacion);
	}

	private static void generarPartidos(Calendario calendario, int jornadaActual) {
		Jornada[] jornadas = calendario.getJornadas();
		int totalJornadas = jornadas.length;
		final int MAXIMOGOLES=7;

		for (int i = 0 ; i<=jornadaActual && i<totalJornadas ; i++) {

			Partido[] partidos = jornadas[i].getPartidos();

			for (Partido par : partidos) {

				int golesLocales = (int) Math.floor(Math.random()*MAXIMOGOLES);
				int golesVisitantes = (int) Math.floor(Math.random()*MAXIMOGOLES);

				par.setGolesLocal(golesLocales);
				par.setGolesVisitante(golesVisitantes);
			}

			jornadas[i].terminar();
		}

	}

	private static Jugador[] crearJugadores(int numeroJugadores, int EDAD, Equipo equipo) {

		// Listado de Nombres, Apellidos, Posiciones para generador random
		String[] nombres = { "Antonio", "Pepito", "Alejandra", "Ismael", "Hugo", "Oliver", "Kalesi", "Ingrid", "Astrid",
				"Indira", "Jenny", "Jessi", "Vane", "Joel", "Bruno", "Sasha", "Billie", "Masha", "Pingu" };
		String[] apellidos = { "Messi", "Vinicius", "Cristiano", "Ronaldo", "Piqué", "Bale (lesionado)", "Amunike",
				"N'kono", "Butragueño", "Sanchís", "Neymar", "Batistuta", "Maradona", "Pelé", "Beckenbauer" };
		String[] posiciones = { "Portero/a", "Defensa", "Centrocampista", "Delantero/a" };

		// Estructura de Array de Jugadores
		Jugador[] jugadores = new Jugador[numeroJugadores];

		for (int i = 0; i < numeroJugadores; i++) {
			// Crear un Jugador
			Jugador jug = new Jugador();
			// Nombre
			int numero = (int) Math.floor(Math.random() * nombres.length);
			String nombre = nombres[numero];
			jug.setNombre(nombre);

			// Apellidos
			numero = (int) Math.floor(Math.random() * apellidos.length);
			String apellido1 = apellidos[numero];
			numero = (int) Math.floor(Math.random() * apellidos.length);
			String apellido2 = apellidos[numero];
			jug.setApellidos(apellido1 + " " + apellido2);

			// Posición
			numero = (int) Math.floor(Math.random() * posiciones.length);
			String posicion = posiciones[numero];
			jug.setPosicion(posicion);

			// Edad
			jug.setEdad(EDAD);

			// Dorsal
			jug.setDorsal(i + 1);

			// Equipo
			jug.setEquipo(equipo);

			jugadores[i] = jug;

		}

		return jugadores;
	}

	private static Equipo[] crearEquipos(int NUMEROEQUIPOS, int EDAD) {

		String[] nombreBarrios = { "El Candado", "Huelin", "Tiro Pichón", "Rincón de la Victoria", "La Rosaleda",
				"Torremolinos", "Velez Málaga", "Cerrado de Calderon", "El Puerto de la Torre", "Bresca", "Mezquitilla",
				"Teatinos", "Motril", "El Centro", "Santa Paula", "El Palo", "Los Corazones", "Las Delicias", "Recogidas",
				"Nueva Málaga", "Casas Blancas", "La Palmilla", "Los Asperones", "Campanillas", "La Corta" };
		String[] mascotas = { "Los Pollos", "Los Araclanes", "Los Limones", "Los Delfines", "Los Chanquetes",
				"Los Gatitos", "Los Boquerones", "Los Toros", "Los Perritos", "Los Halcones", "Los Ornitorrincos",
				"Los Caracoles", "Los Palomos Cojos", "Los Heterosaurios", "Las Tortugas Ninjas", "Los Pintarrojas" };

		Equipo[] listaEquipos = new Equipo[NUMEROEQUIPOS];

		for (int i = 0; i < NUMEROEQUIPOS; i++) {
			// Creamos Equipo
			Equipo equipo = new Equipo();

			// Elegimos random un nombre y una mascota de las listas respectivas.
			int numero = (int) Math.floor(Math.random() * nombreBarrios.length);
			String barrio = nombreBarrios[numero];
			numero = (int) Math.floor(Math.random() * mascotas.length);
			String mascota = mascotas[numero];

			// Definimos el club en base al nombre del barrio
			equipo.setClub(barrio + " F.C.");

			// Las pegamos con un "de" en medio
			String nombre;
			if (barrio.startsWith("El ")) {
				barrio = barrio.substring(3);
				nombre = mascota + " del " + barrio;
			} else {
				nombre = mascota + " de " + barrio;
			}

			equipo.setNombre(nombre);
			// Continuamos con entrenador
			Entrenador entrenador = crearEntrenador(equipo);
			equipo.setEntrenador(entrenador);

			// Meter los jugadores
			int numeroJugadores = (int) Math.floor(Math.random() * 7) + 15;
			Jugador[] jugadores = crearJugadores(numeroJugadores, EDAD, equipo);
			equipo.setJugadores(jugadores);

			// Meter el equipo en el array de equipos

			listaEquipos[i] = equipo;

		}
		return listaEquipos;

	}

	private static Entrenador crearEntrenador(Equipo equipo) {
		// Listado de Nombres, Apellidos, Posiciones para generador random
		String[] nombres = { "Antonio", "Pepito", "Alejandra", "Ismael", "Hugo", "Oliver", "Kalesi", "Ingrid", "Astrid",
				"Indira", "Jenny", "Jessi", "Vane", "Joel", "Bruno", "Sasha", "Billie", "Masha", "Pingu" };
		String[] apellidos = { "Messi", "Vinicius", "Cristiano", "Ronaldo", "Piqué", "Bale (lesionado)", "Amunike",
				"N'kono", "Butragueño", "Sanchís", "Neymar", "Batistuta", "Maradona", "Pelé", "Beckenbauer" };
		Entrenador entrenador = new Entrenador();

		// Nombre
		int numero = (int) Math.floor(Math.random() * nombres.length);
		String nombre = nombres[numero];
		entrenador.setNombre(nombre);

		// Apellidos
		numero = (int) Math.floor(Math.random() * apellidos.length);
		String apellido1 = apellidos[numero];
		numero = (int) Math.floor(Math.random() * apellidos.length);
		String apellido2 = apellidos[numero];
		entrenador.setApellidos(apellido1 + " " + apellido2);

		// Equipo
		entrenador.setEquipo(equipo);

		// Edad
		int edad = (int) Math.floor(Math.random() * 47) + 18;
		entrenador.setEdad(edad);
		// Licencia
		int licencia = (int) Math.floor(Math.random() * 100000);
		entrenador.setNumeroLicencia(licencia);

		return entrenador;
	}

	private static Arbitro crearArbitro() {
		// Listado de Nombres, Apellidos
		String[] nombres = { "Antonio", "Paco", "Natalia", "Natasha", "Marcos", "Miguel", "Kalesi", "Jose", "Víctor",
				"Esteban", "Loren", "Adolfo", "Arturo", "Carlos", "Alejandro", "Juan", "Aina", "Claudia", "Guillermo" };
		String[] apellidos = { "García", "Sánchez", "Ortiz", "González", "de la Torre", "Castellano", "Martín",
				"Santana", "Lynch", "Belver", "Dommarco", "Domínguez", "Guzmán", "Lara", "Savilov" };
		Arbitro arbitro = new Arbitro();

		// Nombre
		int numero = (int) Math.floor(Math.random() * nombres.length);
		String nombre = nombres[numero];
		arbitro.setNombre(nombre);

		// Apellidos
		numero = (int) Math.floor(Math.random() * apellidos.length);
		String apellido1 = apellidos[numero];
		numero = (int) Math.floor(Math.random() * apellidos.length);
		String apellido2 = apellidos[numero];
		arbitro.setApellidos(apellido1 + " " + apellido2);

		// Edad
		int edad = (int) Math.floor(Math.random() * 47) + 18;
		arbitro.setEdad(edad);

		// Licencia
		int licencia = (int) Math.floor(Math.random() * 100000);
		arbitro.setLicencia(licencia);

		return arbitro;
	}

}