import java.time.Instant;

/**
 * Representa la invitación enviada a un empleado o a un departamento, contiene la hora
 * en la cual se envió la invitación y el invitado al cual se le envió
 */
public class Invitacion {
    private Instant hora;
    private Invitable invitado;

    /**
     * Crea el registro de la invitación que contiene a quien recibió la invitación
     * y la hora a la cual se envió
     * @param invitado La persona a la cual se envió la invitación a la reunión
     * @param hora La hora en la cual se envió la invitación
     */
    public Invitacion(Invitable invitado, Instant hora) {
        this.hora = hora;
        this.invitado = invitado;
    }

    /**
     * Obtiene el momento en el que se envió la invitación
     * @return El instante en el cual se envió la invitación
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Modifica la hora en la cual se envió la invitación
     * @param hora La hora a la cual se quiere cambiar
     */
    public void setHora(Instant hora) {
        this.hora = hora;
    }

    /**
     * Otorga la persona a la cual se le envió la invitación
     * @return El invitado en concreto
     */
    public Invitable getInvitado() {
        return invitado;
    }

    /**
     * Modifica a la persona que se tiene registro sobre el envío de la invitación
     * @param invitado La nueva persona que se quiere cambiar
     */
    public void setInvitado(Invitable invitado) {
        this.invitado = invitado;
    }

    /**
     * Imprime en pantalla una descripción detallada sobre la invitación que se envió
     * incluye la persona a la cual se le envió y el momento exacto cuando se envió
     * @return La descripción que contiene al receptor y el instante cuando se envió
     */
    @Override
    public String toString() {
        return "Invitación enviada a:\n" + invitado.toString() + "\nHora: " + hora;
    }
}