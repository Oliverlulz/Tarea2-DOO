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
    private ArrayList<Invitable> invitados;
    private ArrayList<Asistencia> asistencias;
    private ArrayList<Nota> notas;

    /**
     * Constructor de Reunion. Planifica el evento, inicializa las listas
     * vacias para evitar errores y notifica a cada invitado de la lista
     * @param fecha            Día programado para la reunión
     * @param horaPrevista     Momento exacto fijado para iniciar
     * @param duracionPrevista Tiempo estimado que durará la sesion
     * @param invitados        Lista de personas convocadas a la reunion
     */
    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, List<Invitable> invitados){
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.invitados = new ArrayList<>();
        for(int i = 0; i < invitados.size(); i++){
            Invitable invitado = invitados.get(i);
            invitado.Invitar();
            this.invitados.add(invitado);
        }

        this.asistencias = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.horaInicio = null;
        this.horaFin = null;
    }

    /**
     * Devuelve una copia de la lista con todos los presentes en la reunion.
     * Incluye tanto a los participantes que llegaron a tiempo como a los que
     * llegaron atrasados
     * @return Una lista de invitados presentes
     */
    public List<Invitable> obtenerAsistencia(){
        ArrayList<Invitable> listaAsistencias = new ArrayList<>();
        for(int i = 0; i < this.asistencias.size(); i++ ){
            Asistencia asist =  this.asistencias.get(i);
            listaAsistencias.add(asist.getParticipante());
        }
        return listaAsistencias;
    }

    /**
     * Compara la lista de invitados con la de asistencias para filtrar
     * y devolver unicamente a las personas que faltaron a la reunión
     * @return Una lista con los invitados que se ausentaron
     */
    public List<Invitable> obtenerAusencias(){
        ArrayList<Invitable> listaAusencias = new ArrayList<>();
        for(int i = 0; i < this.invitados.size(); i++ ){
            Invitable personaInvitada = this.invitados.get(i);
            boolean asistio = false;

            for(int j = 0; j < this.asistencias.size(); j++ ){
                Asistencia asist = this.asistencias.get(j);
                Invitable partcipanteAsistente = asist.getParticipante();
                if(partcipanteAsistente.equals(personaInvitada)){
                    asistio = true;
                    break;
                }
            }
            if(!asistio){
                listaAusencias.add(personaInvitada);
            }
        }
        return listaAusencias;
    }

    /**
     * Filtra y devuelve una lista con todos los asistentes que llegaron tarde.
     * Este metodo recorre los registros de asistencia e identifica cuales corresponden
     * específicamente a instancias de retraso
     * @return Una lista de participantes que llegaron tarde a la reunion.
     */
    public List<Invitable> obtenerRetrasos(){
        ArrayList<Invitable> listaRetrasos = new ArrayList<>();
        for(int i = 0; i < this.asistencias.size(); i++ ){
            Asistencia asist = this.asistencias.get(i);
            if(asist.getParticipante() instanceof Retraso){
                listaRetrasos.add(asist.getParticipante());
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
        return ( (float)this.asistencias.size() / (float)this.invitados.size() ) * 100;
    }

    /**
     * Crea una nueva nota con el contenido entregado y la añade
     * automaticamente a la coleccion de notas de la reunion
     * @param contenido Texto descriptivo que se guardara en la nota
     */
    public void crearNota(String contenido){
        this.notas.add(new Nota(contenido));
    }

    public float calcularTiempoReal(){}
    public void iniciar(){}
    public void finalizar(){}

    /**
     * Obtiene el tipo de reunion programada (Presencial o Virtual)
     * @return El identificador {@link tipoReunion} de la sesion
     */
    public tipoReunion getTipoReunion(){ return this.tipo; }

    /**
     * Obtiene la fecha calendarizada para la reunion
     * @return Objeto {@link Date} con el dia de la reunion
     */
    public Date getFecha() { return this.fecha; }

    /**
     * Obtiene el bloque horario teorico o planificado para el inicio de la reunion
     * @return Marca de tiempo {@link Instant} en formato UTC de la planificacion
     */
    public Instant getHoraPrevista() { return this.horaPrevista; }

    /**
     * Obtiene el registro exacto de la hora en que se inicio la reunion en vivo
     * Puede retornar null si la reunion aun no se ha ejecutado
     * @return Marca de tiempo {@link Instant} del inicio real, o null
     */
    public Instant getHoraInicio() { return this.horaInicio; }

    /**
     * Obtiene el registro exacto de la hora en que se dio por finalizada la reunion
     * Puede retornar null si la reunion sigue en curso o no se ha iniciado
     * @return Marca de tiempo {@link Instant} del fin real, o null
     */
    public Instant getHoraFin() { return this.horaFin; }

    /**
     * Obtiene la lista completa de registros de asistencia de la reunion
     * @return Un {@link ArrayList} que contiene los objetos {@link Asistencia}.
     */
    public ArrayList<Asistencia> getAsistencias() { return asistencias; }

    /**
     * Obtiene la coleccion de notas, minutas o apuntes tomados
     * durante el desarrollo de la reunion
     * @return Un {@link ArrayList} que contiene los objetos {@link Nota}.
     */
    public ArrayList<Nota> getNotas() { return notas; }

    /**
     * Metodo abstracto que delega a las clases hijas la responsabilidad de
     * resolver la localizacion fisica o el entorno virtual de la reunion
     * @return String con la sala asignada o el enlace de conexion segun corresponda.
     */
    public abstract String getUbicacion();
}
