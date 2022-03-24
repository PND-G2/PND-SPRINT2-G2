import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FutbolUtils {

	public void guardarEquipo (String nombreArchivo, Equipo equipo) {

		//Este método recibe un equipo y un nombre de archivo y guarda el equipo
		//en un archivo con ese nombre

		String nombreEquipo = equipo.getNombre();
		FileWriter writer = null;
		PrintWriter pw = null;

		try {

			writer = new FileWriter(nombreArchivo+".txt",false);
			pw = new PrintWriter(writer);
			pw.println(nombreEquipo);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				// Cerramos el fichero
				if (writer != null)
					writer.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public Equipo cargarEquipo (String nombreArchivo) {

		//Este método recibe un nombre de Archivo y devuelve un equipo

		Equipo equipo = new Equipo();
		File archivo = null;
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {

			archivo = new File (nombreArchivo+".txt");
			reader = new FileReader (archivo);
			bufferedReader = new BufferedReader(reader);
			// Lectura del fichero
			String linea;
			while((linea = bufferedReader.readLine()) != null)
				System.out.println(linea);
			equipo.setNombre(linea);

		}catch(Exception e){

			e.printStackTrace();

		}finally{
			try{                    
				if( null != reader ){   
					reader.close();
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		return equipo;
	}


	public Equipo ordenarEquipo (Equipo equipo) {
		//Este método recibe un Equipo y ordena sus jugadores por apellidos y en
		//caso de igualdad de apellidos por nombre

		Jugador[] arrayJugadores = equipo.getJugadores();
		
		int n = arrayJugadores.length;
		for (int i = 0; i < n-1; i++) {
			
			for (int j = 0; j < n-i-1; j++) { 
				
				if (arrayJugadores[j].getApellidos().compareTo(arrayJugadores[j+1].getApellidos()) > 0){
					// swap arr[j+1] and arr[j]
					Jugador temp = arrayJugadores[j];
					arrayJugadores[j] = arrayJugadores[j+1];
					arrayJugadores[j+1] = temp;
					equipo.setJugadores(arrayJugadores);
					//Caso de igualdad de apellidos por nombre
				}else if(arrayJugadores[j].getApellidos().compareTo(arrayJugadores[j+1].getApellidos()) == 0){
					if(arrayJugadores[j].getNombre().compareTo(arrayJugadores[j+1].getNombre()) > 0) {
						// swap arr[j+1] and arr[j]
						Jugador temp = arrayJugadores[j];
						arrayJugadores[j] = arrayJugadores[j+1];
						arrayJugadores[j+1] = temp;
						equipo.setJugadores(arrayJugadores);
					}
				}
				
			}
			
		}
		return equipo; //Return Equipo ordenado
	}



}
