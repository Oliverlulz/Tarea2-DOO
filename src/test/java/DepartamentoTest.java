import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DepartamentoTest {
    Departamento dpto;
    Empleado emp;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        dpto = new Departamento("Inf.");
        emp = new Empleado("1", "Mart.", "Pepe",
                "pepe123@gmail.com", dpto);
    }

    @Test
    @DisplayName("Test todo correcto")
    void testDepartamentoCorrecto() throws Exception {
        assertEquals("Inf.", dpto.getNombreDepartamento());
    }

    @Test
    @DisplayName("Test nombre inválido")
    void testDepartamentoNombreNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new Departamento(null));

        assertThrows(DatoInvalidoException.class, () ->
                new Departamento(""));
    }

    @Test
    @DisplayName("Test obtener cantidad empleados")
    void testCantidadEmpleadosInicial() {
        assertEquals(0, dpto.obtenerCantidadEmpleados());
    }

    @Test
    @DisplayName("Test agregar empleado")
    void testDepartamentoAgregarEmpleado() throws DatoInvalidoException {
        dpto.agregarEmpleado(emp);
        assertEquals(1, dpto.obtenerCantidadEmpleados());
    }

    @Test
    @DisplayName("Test agregar empleado nulo")
    void testDepartamentoAgregarEmpleadoNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                dpto.agregarEmpleado(null));
    }
}