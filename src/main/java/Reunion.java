import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

public class Reunion { // por ahora no es abstract
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

    public int obtenerTotalAsistencia(){
        return listAsistencia.size()+listRestraso.size();
    }
    public float obtenerPorcentajeAsistencia(){
        return (obtenerTotalAsistencia()*100)/listInvitados.size();
    }
    public Duration calcularTiempoReal() throws DatoInvalidoException{
        if (horaFin != null)
            throw new DatoInvalidoException("La reunion todavia no inicia");
        return Duration.between(horaInicio,LocalTime.now());
    }
    public void Iniciar() {
        this.horaInicio = LocalTime.now();
    }
    public void Finalizar() {
        this.horaInicio = LocalTime.now();
    }

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
