import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
/**
 * Hereda a Reunion
 */
public class ReunionVirtual extends Reunion {
    private String enlace;

    public ReunionVirtual(String enlace,Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion tipo, ArrayList<Invitable> invitados)throws DatoInvalidoException{
        super(fecha, horaPrevista, duracionPrevista, tipo, invitados);
        this.enlace = enlace;
    }
    /**
     * @return enlace
     */
    @Override
    public String getUbicacion() {
        return enlace;
    }
    /**
     * @return Descripcion de la reunion
     */
    @Override
    public String toString() {
        return "Reunion Virtual " + super.toString();
    }
}
