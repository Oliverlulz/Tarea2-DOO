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
     * Constructor de Reunion. Planifica el evento, inicializa las listas
     * vacias para evitar errores y notifica a cada invitado de la lista
     * @param fecha            Día programado para la reunión
     * @param horaPrevista     Momento exacto fijado para iniciar
     * @param duracionPrevista Tiempo estimado que durará la sesion
     * @param invitados        Lista de personas convocadas a la reunion
     */
    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipo, ArrayList<Invitable> invitados) throws DatoInvalidoException {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la reunion no puede ser nula.");
        }
        if (horaPrevista == null) {
            throw new IllegalArgumentException("La hora prevista no puede ser nula.");
        }
        if (duracionPrevista == null) {
            throw new IllegalArgumentException("La duracion prevista no puede ser nula.");
        }
        if (duracionPrevista.isNegative() || duracionPrevista.isZero()) {
            throw new IllegalArgumentException("La duracion de la reunion debe ser mayor a 0 minutos.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de reunion es obligatorio.");
        }
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipo = tipo;

        this.invitaciones = new ArrayList<>();
        if (invitados == null || invitados.isEmpty()) {
            throw new IllegalArgumentException("La reunion debe tener al menos un invitado asignado.");
        }
        for(int i = 0; i < invitados.size(); i++){
            Invitable invitado = invitados.get(i);
            if (invitado == null) {
                throw new IllegalArgumentException("La lista contiene un invitado que es nulo.");
            }
            for (int j = i + 1; j < invitados.size(); j++) {
                if (invitado.equals(invitados.get(j))) {
                    throw new IllegalArgumentException("El invitado " + invitado + " esta repetido en la lista.");
                }
            }

            invitado.Invitar();
            Invitacion invitacion = new Invitacion(invitado, Instant.now());
            this.invitaciones.add(invitacion);
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
        for(int i = 0; i < this.invitaciones.size(); i++ ){
            Invitable personaInvitada = this.invitaciones.get(i).getInvitado();
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
            if(asist instanceof Retraso){
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
     * el total de invitados
     * @return El porcentaje de asistencia como un valor decimal entre 0.0 y 100.0
     * */
    public float obtenerPorcentajeAsistencia(){
        return ( (float)this.asistencias.size() / (float)this.invitaciones.size() ) * 100;
    }

    /**
     * Crea una nueva nota con el contenido entregado y la añade
     * automaticamente a la coleccion de notas de la reunion
     * @param contenido Texto descriptivo que se guardara en la nota
     */
    public void crearNota(String contenido){
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new IllegalArgumentException("No se puede añadir una nota vacia a la reunion.");
        }
        this.notas.add(new Nota(contenido));
    }

    /**
     * Registra la llegada de un invitado a la reunion en tiempo real
     * Valida que la reunion este en curso y evita registros duplicados
     * @param invitado El {@link Invitable} que va ingresando a la sesion
     * @param horaLlegada La marca de tiempo {@link Instant} de su ingreso
     */
    public void pasarAsistencia(Invitable invitado, Instant horaLlegada) throws DatoInvalidoException {
        if (invitado == null || horaLlegada == null) {
            throw new IllegalArgumentException("El invitado y la hora de llegada no pueden ser nulos.");
        }
        if (this.horaInicio == null) {
            throw new IllegalStateException("No se puede pasar asistencia: la reunion aun no ha iniciado.");
        }
        if (this.horaFin != null) {
            throw new IllegalStateException("No se puede pasar asistencia: la reunion ya finalizo.");
        }

        boolean esInvitadoValido = false;
        for (int i = 0; i < this.invitaciones.size(); i++) {
            if (this.invitaciones.get(i).getInvitado().equals(invitado)) {
                esInvitadoValido = true;
                break;
            }
        }

        if (!esInvitadoValido) {
            throw new IllegalArgumentException("El participante " + invitado + " no pertenece a la lista de invitados de esta reunion.");
        }

        for (int i = 0; i < this.asistencias.size(); i++) {
            if (this.asistencias.get(i).getParticipante().equals(invitado)) {
                throw new IllegalStateException("El participante " + invitado + " ya tiene su asistencia registrada.");
            }
        }

        if (horaLlegada.isAfter(this.horaPrevista)) {
            Retraso nuevaAsistencia = new Retraso(invitado, horaLlegada);
            this.asistencias.add(nuevaAsistencia);
        }else {
            Asistencia nuevaAsistencia = new Asistencia(invitado);
            this.asistencias.add(nuevaAsistencia);
        }
    }

    public Duration calcularTiempoReal(){
        if (this.horaInicio != null && this.horaFin != null) {
            return Duration.between(this.horaInicio, this.horaFin);
        }
        return Duration.ZERO;
    }
    public void iniciar(){
        this.horaInicio = Instant.now();
    }
    public void finalizar(){
        this.horaFin = Instant.now();
    }

    /**y
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
     * Obtiene el bloque horario teorico o planificado para la duracion de la reunion
     * @return Duracion {@link Duration}
     */
    public Duration getDuracionPrevista() { return this.duracionPrevista; }

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
