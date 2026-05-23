import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;

public class ReunionPresencial extends Reunion {
    private String sala;
    public ReunionPresencial(String sala, Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipo, ArrayList<Invitable> invitados)throws DatoInvalidoException{
        super(fecha, horaPrevista, duracionPrevista, tipo, invitados);
        this.sala = sala;
    }
    @Override
    public String getUbicacion() {
        return sala;
    }
}
