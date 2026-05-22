import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {
    Departamento dpto;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        dpto = new Departamento("Depto. de Inf.");
    }

    @Test
    @DisplayName("Test todo correcto")
    void testEmpleadoCorrecto() throws DatoInvalidoException {
        Empleado empl = new Empleado("1", "Mart. Carr.",
                                    "Pepe", "pepe123@gmail.com", dpto);

        assertEquals("1", empl.getId());
        assertEquals("Mart. Carr.", empl.getApellidos());
        assertEquals("Pepe", empl.getNombre());
        assertEquals("pepe123@gmail.com", empl.getCorreo());
        assertEquals("Depto. de Inf.", empl.getDepartamento());
    }

    @Test
    @DisplayName("Test id inválida")
    void testEmpleadoIdNuloVacio() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new Empleado(null, "Mart.", "Pepe",
                        "pepe123@gmail.com", dpto));

        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("", "Mart.", "Pepe",
                        "pepe123@gmail.com", dpto));
    }

    @Test
    @DisplayName("Test apellidos inválidos")
    void testEmpleadoApellidosNuloVacio() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", null, "Pepe",
                        "pepe123@gmail.com", dpto));

        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "", "Pepe",
                        "pepe123@gmail.com", dpto));
    }

    @Test
    @DisplayName("Test nombre inválido")
    void testEmpleadoNombreNuloVacio() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "Mart.", null,
                        "pepe123@gmail.com", dpto));

        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "Mart.", "",
                        "pepe123@gmail.com", dpto));
    }

    @Test
    @DisplayName("Test correo inválido")
    void testEmpleadoCorreoNuloVacio() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "Mart.", "Pepe",
                        null, dpto));

        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "Mart.", "Pepe",
                        "", dpto));
    }

    @Test
    @DisplayName("Test departamento inválido")
    void testEmpleadoDepartamentoNulo() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new Empleado("123", "Mart.", "Pepe",
                        "pepe123@gmail.com", null));
    }
}