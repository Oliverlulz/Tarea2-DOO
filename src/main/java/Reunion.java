import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

public abstract class Reunion {
    private LocalDate fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio = null;
    private Instant horaFin = null;

    private ArrayList<Empleado> listInvitados;
    private ArrayList<Asistencia> listAsistencia;
    private ArrayList<Retraso> listRestraso;
    private ArrayList<Empleado> listAusencia;

    public Reunion(Instant HoraPrevista,Duration DuracionPrevista,ArrayList Invitados){
        this.fecha = LocalDate.now();
        this.horaPrevista = HoraPrevista;
        this.duracionPrevista = DuracionPrevista;
        this.listAusencia = Invitados;
    }

    public void marcarAsistencia(Invitacion Invitado) throws DatoInvalidoException{
        if (horaFin != null)
            throw new DatoInvalidoException("El invitado fuera de tiempo");
        if (Invitado == null)
            throw new DatoInvalidoException("No hay Invitado");

        if (horaInicio != null){
            listAsistencia.add(new Asistencia(Invitado.getInvitado()));
        } else {
            listRestraso.add(new Retraso(Invitado.getInvitado(), Instant.now()));
        }
    }

    public List obtenerAsistencias() throws DatoInvalidoException{}
    public List obtenerAusencias(){}
    public List obtenerRetrasos(){}

    public int obtenerTotalAsistencia(){
        return listRestraso.size()+listAsistencia.size();
    }
    public float obtenerPorcentajeAsistencia(){
        return (obtenerTotalAsistencia()*100)/listInvitados.size();
    }
    public Instant calcularTiempoReal(){
        return Instant.now();
    }
    public void Iniciar() {
        this.horaInicio = Instant.now();
    }
    public void Finalizar() {
        this.horaInicio = Instant.now();

    }
}
