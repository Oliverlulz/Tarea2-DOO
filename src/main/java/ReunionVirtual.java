import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Hereda de Reunion y tiene un atributo extra llamado enlace
 */
public class ReunionVirtual extends Reunion {
    private String Sala;
    public ReunionVirtual(LocalTime HoraPrevista, Duration DuracionPrevista, ArrayList Invitados, String Sala) {
        super(HoraPrevista,DuracionPrevista,Invitados);
        this.Sala = Sala;
    }
    public String getSala() {
        return Sala;
    }
}
