import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.ZoneId;

/**
 * Clase encargada de la creacion y exportacion del informe de una reunion
 * en formato de texto plano (.txt).
 */
public class GeneradorInforme {
    /**
     * Genera el archivo .txt con el reporte detallado de la reunion
     * Convierte los tiempos a la zona horaria local y maneja control de nulls
     * @param reunion Objeto Reunion desde el cual se extraen los datos
     * @param rutaArchivo Nombre o ruta del archivo de texto a crear
     */
    static public void generarInforme(Reunion reunion, String rutaArchivo){
        if (reunion == null) {
            throw new IllegalArgumentException("No se puede generar un informe de una reunion nula.");
        }
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo de informe no puede estar vacia.");
        }
        // El bloque try-with-resources asegura el cierre automatico del archivo al terminar
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("    INFORME DE REUNION ");
            escritor.println();

            // Convierte el Instant UTC programado a la zona horaria local de la maquina
            ZonedDateTime horaReunion = reunion.getHoraPrevista().atZone(ZoneId.systemDefault());
            escritor.println( "Fecha y hora de la reunion: " + reunion.getFecha().getDate() + "/" +
                    (reunion.getFecha().getMonth() + 1) + "/" +
                    (reunion.getFecha().getYear() + 1900) + " " +
                    horaReunion.getHour() + ":" + horaReunion.getMinute());
            escritor.println();

            // Evita un NullPointerException si la reunion no se ha iniciado o terminado
            if (reunion.getHoraInicio() != null && reunion.getHoraFin() != null) {
                ZonedDateTime horaInicioReunion = reunion.getHoraInicio().atZone(ZoneId.systemDefault());
                ZonedDateTime horaFinReunion = reunion.getHoraFin().atZone(ZoneId.systemDefault());

                escritor.print("Hora de inicio: " + horaInicioReunion.getHour() + ":" + horaInicioReunion.getMinute());
                escritor.print(" | Hora de fin: " + horaFinReunion.getHour() + ":" + horaFinReunion.getMinute());
                escritor.println(" | Duracion: " + reunion.calcularTiempoReal().toMinutes() + " minutos.");
            } else {
                escritor.println("Horarios reales: Esta reunion aun no se ha ejecutado o no ha finalizado.");
            }
            escritor.println();

            escritor.println("El tipo de reunion: " + reunion.getTipoReunion());
            escritor.println();

            escritor.println("Enlace / Sala: " + reunion.getUbicacion());
            escritor.println();

            escritor.println("Participantes de la reunion:");
            for(int i = 0; i < reunion.obtenerTotalAsistencia(); i++){
                escritor.println("  - " + reunion.getAsistencias().get(i));
            }
            escritor.print("\n");

            escritor.println("Notas de la reunion:");
            for(int i = 0; i < reunion.getNotas().size(); i++){
                escritor.println("  - " + reunion.getNotas().get(i));
            }
        } catch (IOException e) {
            System.out.println("Error de disco: " + e.getMessage());
        }
    }
}
