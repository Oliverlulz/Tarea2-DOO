/**
 * Representa a una persona externa que no es un empleado de un departamento
 * pero que puede ser invitada a una reunión
 */
public class InvitadoExterno implements Invitable {
    private String nombre;
    private String apellidos;
    private String correo;

    /**
     * Crea a una persona externa a un departamento con un nombre, apellidos y un correo
     * electrónico de contacto
     * @param nombre El nombre de esta persona
     * @param apellidos Los apellidos de esta persona
     * @param correo El correo electrónico donde se le puede contactar
     */
    public InvitadoExterno(String nombre, String apellidos,
                           String correo) throws DatoInvalidoException {
        if (nombre == null || nombre.isBlank())
            throw new DatoInvalidoException("El nombre no puede estar vacío");

        if (apellidos == null || apellidos.isBlank())
            throw new DatoInvalidoException("Los apellidos no pueden estar vacíos");

        if (correo == null || correo.isBlank())
            throw new DatoInvalidoException("El correo no puede estar vacío");

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    /**
     * Obtiene el nombre de la persona
     * @return El nombre de esta persona externa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de una persona
     * @param nombre El nombre al cual se quiere cambiar
     */
    public void setNombre(String nombre) throws DatoInvalidoException {
        if (nombre == null || nombre.isBlank())
            throw new DatoInvalidoException("El nombre no puede estar vacío");
        this.nombre = nombre;
    }

    /**
     * Otorga los apellidos de la persona externa
     * @return Los apellidos de esta persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Cambia los apellidos registrados de esta persona
     * @param apellidos Los o el apellido al cual se desea cambiar
     */
    public void setApellidos(String apellidos) throws DatoInvalidoException {
        if (apellidos == null || apellidos.isBlank())
            throw new DatoInvalidoException("Los apellidos no pueden estar vacíos");
        this.apellidos = apellidos;
    }

    /**
     * Entrega el correo de contacto de la persona
     * @return El correo electrónico de esta persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo registrado asociado a esta persona
     * @param correo El correo modificado de la persona
     */
    public void setCorreo(String correo) throws DatoInvalidoException {
        if (correo == null || correo.isBlank())
            throw new DatoInvalidoException("El correo no puede estar vacío");
        this.correo = correo;
    }

    /**
     * Invita a una persona externa e imprime en pantalla a quién se invitó y dónde
     * se envió la invitación a la reunión
     */
    @Override
    public void Invitar() {
        System.out.println("Invitación enviada a " + nombre
                            + " " + apellidos + " al correo " + correo);
    }

    /**
     * Entrega una descripción de la persona en concreto, incluye su nombre, apellidos
     * y el correo electrónico asociado a la persona
     * @return La descripción con el nombre, apellidos y correo electrónico de la persona
     */
    @Override
    public String toString() {
        return nombre + " " + apellidos + "\n" + "Correo: " + correo;
    }
}
