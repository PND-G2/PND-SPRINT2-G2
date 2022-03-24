import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {


	}


	public static void imprimirMenu() {

		System.out.println();
		System.out.println("Elija la opción que desea:");
		System.out.println("1.- Ver enfrentamientos de la última jornada.");
		System.out.println("2.- Modificar un partido de la última jornada.");
		System.out.println("3.- Salir");
		System.out.println("Para ver la clasificación actualizada, pulse 3");

	}

	public static int leerNumero() {

		Scanner sc = new Scanner(System.in);
		int numero = sc.nextInt();

		return numero;

	}

	static void opciones(Calendario calendario, int jornadaActual) {
		int opcion = 1;
		while(opcion != 3) {
			limpiaPantalla();
			imprimirMenu();
			opcion = leerNumero();

			switch(opcion) {

			case 1:
				limpiaPantalla();
				Jornada[] jornadas = calendario.getJornadas();
				Partido[] partidos = jornadas[jornadaActual].getPartidos();
				for(Partido par: partidos) {
					System.out.println(par.getEquipoLocal().getNombre()+" "+(par.getGolesLocal()));
					System.out.println(par.getEquipoVisitante().getNombre()+" "+par.getGolesVisitante());
					System.out.println("------------------------------------------------------------------");
				}
				Menu.esperaPulsacion();
				break;

			case 2:
				limpiaPantalla();
				System.out.println("Elija el partido a modificar:");
				int partidoElegido = leerNumero()-1;
				
				Jornada[] jornadas2 = calendario.getJornadas();
				Partido[] partidos2 = jornadas2[jornadaActual].getPartidos();
				Partido partido = partidos2[partidoElegido];
				
				System.out.println("Introduzca los goles de "+partido.getEquipoLocal().getNombre());
				partido.setGolesLocal(cambiarGoles());;
				
				System.out.println("Introduzca los goles de "+partido.getEquipoVisitante().getNombre());
				partido.setGolesVisitante(cambiarGoles());
				
				break;
				
			case 3:
				limpiaPantalla();
				break;
			

			}
		}
	}

	public static void limpiaPantalla() {
		for(int i=0;i<200;i++) {
			System.out.println();
		}
	}

	public static void esperaPulsacion() {
		System.out.println();
		System.out.println("Pulse enter para continuar. ");
		Scanner sc=new Scanner(System.in);
		sc.nextLine();		
	}
	
	public static int cambiarGoles() {
		
		Scanner sc = new Scanner(System.in);
		int goles = sc.nextInt();
		return goles;
	}
}
