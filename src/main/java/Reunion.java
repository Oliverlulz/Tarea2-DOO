import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

/**
 * Abstract del cual se crean ambas Reuniones
 */
abstract class Reunion { // por ahora no es abstract

    private LocalDate fecha;
    private LocalTime horaPrevista;
    private Duration duracionPrevista;
    private LocalTime horaInicio = null;
    private LocalTime horaFin = null;

    private ArrayList<Invitacion> listInvitados;
    private ArrayList<Asistencia> listAsistencia;
    private ArrayList<Retraso> listRestraso;
    private ArrayList<Invitacion> listAusencia;
    private ArrayList<Nota> listNota;

    /**
     * Resivimos Los valores previstos y una lista
     * de los invitados que vendran a la reunion
     * @param HoraPrevista
     * @param DuracionPrevista
     * @param Invitados
     */
    public Reunion(LocalTime HoraPrevista,Duration DuracionPrevista,ArrayList Invitados){
        this.fecha = LocalDate.now();
        this.horaPrevista = HoraPrevista;
        this.duracionPrevista = DuracionPrevista;
        this.listAsistencia = new ArrayList<>();
        this.listRestraso = new ArrayList<>();
        this.listInvitados = Invitados;
        this.listAusencia = (ArrayList)Invitados.clone();
        this.listNota = new ArrayList<>();
    }
    /**
     * Con este metodo agregado se marca a un invitado
     * y se guarda en la asistencia si llego antes de que la
     * reunion se iniciara, si fue después se guarda en Atraso
     * Cualquiera de las dos posibilidades los remueve de la lista
     * de ausencia si es que llego antes de finalizar la reunion.
     * @param Invitado
     * @throws DatoInvalidoException
     */
    public void marcarAsistencia(Invitacion Invitado) throws DatoInvalidoException{
        if (horaFin != null)
            throw new DatoInvalidoException("El invitado fuera de tiempo");
        if (Invitado == null)
            throw new DatoInvalidoException("No hay Invitado");

        if (horaInicio != null){
            listAsistencia.add(new Retraso(Invitado.getInvitado(), Instant.now()));
            for (int i = 0;i< listAusencia.size();i++) {
                if (Invitado == listAusencia.get(i))
                    listAusencia.remove(i);
            }
        } else {
            listRestraso.add(new Retraso(Invitado.getInvitado(), Instant.now()));
            for (int i = 0;i<listAusencia.size();i++) {
                if (Invitado == listAusencia.get(i))
                    listAusencia.remove(i);
            }
        }
    }
    /**
     * El metodo analiza si existen personas en las listas
     * y calcula el total de su asistencia
     * @return Cantidad de asistencia
     * @throws DatoInvalidoException
     */
    public int obtenerTotalAsistencia() throws DatoInvalidoException {
        if (listAsistencia.size() == 0)
            throw new DatoInvalidoException("Error lista Asistencia");
        if (listRestraso.size() == 0)
            throw new DatoInvalidoException("Error lista Retraso");
        return listAsistencia.size()+listRestraso.size();
    }
    /**
     * Por simplesa se ocupa el metodo anterior para calcular
     * el porcentaje de asistencia
     * @return Porcentaje Asistencia
     * @throws DatoInvalidoException
     */
    public float obtenerPorcentajeAsistencia() throws DatoInvalidoException {
        if (listRestraso.size() == 0)
            throw new DatoInvalidoException("Error lista Invitados");
        if (obtenerTotalAsistencia()>listInvitados.size()){
            return 100;
        } else {
            return (obtenerTotalAsistencia()*100)/listInvitados.size();
        }
    }
    /**
     * Con la libreria LocalTime se calcula la diferencia
     * entre el tiempo de inicio y final
     * @return TiempoReal
     * @throws DatoInvalidoException
     */
    public LocalTime calcularTiempoReal() throws DatoInvalidoException{
        if (horaInicio == null)
            throw new DatoInvalidoException("La reunion todavia no inicia");
        else if (horaFin == null) {
            throw new DatoInvalidoException("La reunion todavia no finaliza");
        }
        return horaInicio.minusHours(horaFin.getHour());
    }
    /**
     * Inicio de la reunion
     */
    public void Iniciar() {
        this.horaInicio = LocalTime.now();
    }

    /**
     * Fin de la reunion
     */
    public void Finalizar() {
        this.horaFin = LocalTime.now();
    }
    /**
     * para terminar
     */
    public void producirInforme(){

    }

    public List obtenerAsistencias(){
        return listAsistencia;
    }
    public List obtenerAusencias(){
        return listAusencia;
    }
    public List obtenerRetrasos(){
        return listRestraso;
    }
}
