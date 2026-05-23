import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

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
