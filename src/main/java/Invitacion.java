import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    public Invitacion(Invitable invitado, Instant hora) {
        this.hora = hora;
        this.invitado = invitado;
    }

    public void enviar() {
        this.invitado.Invitar();
    }

    public Instant getHora() {
        return hora;
    }

    public void setHora(Instant hora) {
        this.hora = hora;
    }

    public Invitable getInvitado() {
        return invitado;
    }

    public void setInvitado(Invitable invitado) {
        this.invitado = invitado;
    }

    @Override
    public String toString() {
        return "Invitación enviada a:\n" + invitado.toString() + "\nHora: " + hora;
    }
}