import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ReunionVirtual extends Reunion {
    private String enlace;

    public ReunionVirtual(String enlace,Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipo, ArrayList<Invitable> invitados)throws DatoInvalidoException{
        super(fecha, horaPrevista, duracionPrevista, tipo, invitados);
        this.enlace = enlace;
    }
    @Override
    public String getUbicacion() {
        return enlace;
    }
}
