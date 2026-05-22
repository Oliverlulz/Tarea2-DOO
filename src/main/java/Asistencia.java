/**
 * Representa un registro de la asistencia de una persona que asistió a cierta reunión
 */
public class Asistencia {
    private Invitable participante;

    /**
     * Registra que un participante asistió a la reunión
     * @param participante El participante que asistió
     */
    public Asistencia(Invitable participante) throws DatoInvalidoException {
        if (participante == null)
            throw new DatoInvalidoException("El participante no puede ser nulo");
        this.participante = participante;
    }

    /**
     * Obtiene al participante que asistió a la reunión
     * @return El participante en concreto
     */
    public Invitable getParticipante() {
        return participante;
    }

    /**
     * Modifica al participante asociado al registro
     * @param participante El participante modificado
     */
    public void setParticipante(Invitable participante) throws DatoInvalidoException {
        if (participante == null)
            throw new DatoInvalidoException("El participante no puede ser nulo");
        this.participante = participante;
    }

    /**
     * Entrega una descripción del participante y su estado en la reunión
     * @return El participante y su estado de asistencia
     */
    @Override
    public String toString() {
        return participante.toString() + ", Presente a tiempo";
    }
}
