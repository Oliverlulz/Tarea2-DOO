import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Hereda de Reunion y tiene un atributo extra llamado enlace
 */
public class ReunionPresencial extends Reunion {
    private String Enlace;
    public ReunionPresencial(LocalTime HoraPrevista, Duration DuracionPrevista, ArrayList Invitados, String link) {
        super(HoraPrevista,DuracionPrevista,Invitados);
        this.Enlace = link;
    }
    public String getEnlace() {
        return Enlace;
    }
}

