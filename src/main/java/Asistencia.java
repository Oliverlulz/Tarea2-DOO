public class Asistencia {
    private Invitable participante;

    public Asistencia(Invitable participante) {
        this.participante = participante;
    }

    public Invitable getParticipante() {
        return participante;
    }

    public void setParticipante(Invitable participante) {
        this.participante = participante;
    }

    @Override
    public String toString() {
        return participante.toString() + ", Presente a tiempo";
    }
}
