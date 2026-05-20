import java.util.ArrayList;

public class Departamento implements Invitable{
    private String nombre;
    private ArrayList<Empleado> empleados;

    public Departamento(String Nombre) {
        this.nombre = Nombre;
        empleados = new ArrayList();
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }

    public String getNombreDepartamento() {
        return nombre;
    }

    public void setNombreDepartamento(String Nombre) {
        this.nombre = Nombre;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public void Invitar() {
        System.out.println("Invitación enviada al " + nombre +
                            " (" + obtenerCantidadEmpleados() + " empleados)");
    }

    @Override
    public String toString() {
        return nombre + ", " + "Cantidad de empleados: " + empleados.size();
    }
}
