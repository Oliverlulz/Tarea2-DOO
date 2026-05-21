import java.util.ArrayList;

/**
 * Representa un departamento específico de alguna materia
 * Contiene una cantidad de empleados que pueden ser invitados a una reunión
 */
public class Departamento implements Invitable{
    private String nombre;
    private ArrayList<Empleado> empleados;

    /**
     * Crea un nuevo departamento con un nombre y sus empleados
     * @param Nombre el nombre que tiene este departamento
     */
    public Departamento(String Nombre) {
        this.nombre = Nombre;
        empleados = new ArrayList();
    }

    /**
     * Agrega un empleado al departamento
     * @param empleado el empleado que se agrega
     */
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    /**
     * Determina cuantos empleados hay en el departamento
     * @return la cantidad total de empleados
     */
    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }

    /**
     * Obtiene el nombre del departamento
     * @return el nombre del departamento
     */
    public String getNombreDepartamento() {
        return nombre;
    }

    /**
     * Modifica el nombre del departamento
     * @param Nombre el nombre al cual se desea cambiar
     */
    public void setNombreDepartamento(String Nombre) {
        this.nombre = Nombre;
    }

    /**
     * Obtiene la lista de los empleados actuales en el departamento
     * @return la lista que contiene a los empleados
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Cambia la lista actual de empleados por una nueva
     * @param empleados la lista nueva de empleados la cual se desea cambiar
     */
    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Invita a todas los empleados dentro del departamento
     */
    @Override
    public void Invitar() {
        for (Empleado empleado : this.empleados) {
            empleado.Invitar();
        }

        System.out.println("Invitación enviada al " + nombre +
                            " (" + obtenerCantidadEmpleados() + " Personas)");
    }

    /**
     * Imprime en pantalla una descripción del departamento y la cantidad de empleados que
     * contiene
     * @return
     */
    @Override
    public String toString() {
        return nombre + ", " + "Cantidad de empleados: " + empleados.size();
    }
}
