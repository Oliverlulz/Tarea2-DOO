import java.time.Instant;

public class Retraso extends Asistencia {
    private Instant hora;

    public Retraso(Invitable participante, Instant hora) {
        this.hora = hora;
        super(participante);
    }
}
