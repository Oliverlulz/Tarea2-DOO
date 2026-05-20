import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;

    private tipoReunion tipo;
    private ArrayList<Invitacion> invitaciones;
    private ArrayList<Asistencia> asistencias;
    private ArrayList<Nota> notas;

    /**
     * Devuelve una copia de la lista con todos los presentes en la reunion.
     * Incluye tanto a los participantes que llegaron a tiempo como a los que
     * llegaron atrasados
     *
     * @return Una lista de objetos Asistencia con todos los presentes
     */
    public List obtenerAsistencia(){
        ArrayList<Asistencia> listaAsistencias = new ArrayList<>();
        for(int i = 0; i < this.asistencias.size(); i++ ){
            listaAsistencias.add(this.asistencias.get(i));
        }
        return listaAsistencias;
    }

    public List obtenerAusencias(){}

    /**
     * Filtra y devuelve una lista con todos los asistentes que llegaron tarde.
     * Este metodo recorre los registros de asistencia e identifica cuales corresponden
     * específicamente a instancias de retraso
     * @return Una lista de objetos Asistencia que son instancias de Retraso.
     */
    public List obtenerRetrasos(){
        ArrayList<Asistencia> listaRetrasos = new ArrayList<>();
        for(int i = 0; i < this.asistencias.size(); i++ ){
            if(this.asistencias.get(i) instanceof Retraso){
                listaRetrasos.add(this.asistencias.get(i));
            }
        }
        return listaRetrasos;

    }

    /**
     * Obtiene la cantidad total de personas que asistieron a la reunión.
     * Cuenta a todos los participantes registrados, sin importar si llegaron
     * a la hora o tarde.
     * @return El número total de asistentes presentes
     */
    public int obtenerTotalAsistencia(){
        return this.asistencias.size();
    }

    /**
     * Calcula el porcentaje de asistencia a la reunión en base al total de invitados.
     * Realiza una regla de tres relacionando los participantes presentes contra
     * el total de de invitados
     * @return El porcentaje de asistencia como un valor decimal entre 0.0 y 100.0
     * */
    public float obtenerPorcentajeAsistencia(){
        return ( (float)this.asistencias.size() / (float)this.invitaciones.size() ) * 100;
    }

    public float calcularTiempoReal(){}
    public void iniciar(){}
    public void finalizar(){}
}
