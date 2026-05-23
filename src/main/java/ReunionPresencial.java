import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

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

