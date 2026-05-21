/**
 * Representa a un empleado perteneciente a un departamento,
 * que puede ser invitado a una reunión
 */
public class Empleado implements Invitable{
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;
    private Departamento departamento;

    /**
     * Crea a un empleado con id, nombre, apellidos, correo y
     * un departamento al cual pertenece
     * @param id la ID del empleado
     * @param apellidos los apellidos del empleado
     * @param nombre el nombre del empleado
     * @param correo el correo de contacto
     * @param departamento el departamento al cual pertenece el empleado
     */
    public Empleado(String id, String apellidos, String nombre
                    , String correo, Departamento departamento) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
        this.departamento = departamento;
    }

    /**
     * Obtiene la ID del empleado
     * @return La ID asociada al empleado
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica la ID asociada al empleado
     * @param id La nueva ID que se le quiere asignar al empleado
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene los apellidos del empleado
     * @return Los apellidos del empleado
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Modifica los apellidos de un empleado
     * @param apellidos Los apellidos a los que se quiere cambiar
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo de contacto del empleado
     * @return El correo asociado
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo asociado al empleado
     * @param correo El nuevo correo del empleado
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el nombre del empleado
     * @return El nombre de este
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de un empleado
     * @param nombre El nombre al que se quiere cambiar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el departamento al cual el empleado pertenece
     * @return El departamento en concreto
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Modifica el departamento del cual el empleado pertenece
     * @param departamento El nuevo departamento donde se encuentra el empleado
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Invita a un empleado específico, imprime en pantalla a quien se invitó
     * y a que correo
     */
    @Override
    public void Invitar() {
        System.out.println("Invitación enviada a " + nombre + " " + apellidos
                            + " al correo " + correo);
    }

    /**
     * Entrega una descripción breve de los datos del empleado la cual imprime en pantalla
     * @return La descripción de los datos del empleado
     */
    @Override
    public String toString() {
        return nombre + " " + apellidos + "\nID: " +
                id + "\nCorreo: " + correo + "\n" + departamento.getNombreDepartamento();
    }
}
