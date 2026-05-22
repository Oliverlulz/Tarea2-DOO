import java.time.Instant;

/**
 * Representa a un registro sobre un participante que asistió a la reunión, pero llegó tarde,
 * guarda el momento en el cual el participante llegó
 * a la reunión, extiende el funcionamiento de {@link Asistencia}
 */
public class Retraso extends Asistencia {
    private Instant hora;

    /**
     * Crea el registro de una persona que asistió a la reunión con la hora a la que llegó
     * y la persona en concreto
     * @param participante La persona que asistió a la reunión
     * @param hora La hora a la cual la persona llegó a la reunión
     */
    public Retraso(Invitable participante, Instant hora) throws DatoInvalidoException {
        super(participante);

        if (hora == null)
            throw new DatoInvalidoException("La hora no puede ser nula");
        this.hora = hora;
    }

    /**
     * Obtiene la hora a la cual llegó el participante
     * @return La hora exacta de la asistencia de una persona a la reunión
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Modifica la hora en la cual llegó un participante a la reunión
     * @param hora La hora a la cual se desea cambiar
     */
    public void setHora(Instant hora) throws DatoInvalidoException {
        if (hora == null)
            throw new DatoInvalidoException("La hora no puede ser nula");
        this.hora = hora;
    }

    /**
     * Entrega una descripción detallada sobre la asistencia de un participante
     * el cual llegó tarde a la reunión, incluye quién, el estado de asistencia
     * y la hora a la cual llegó
     * @return La descripción sobre la asistencia de un participante
     */
    @Override
    public String toString() {
        return super.getParticipante().toString() + ", Presente, con retraso\n"
                + "Llegó a las: " + this.hora.toString();
    }
}
