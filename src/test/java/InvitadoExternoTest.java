import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvitadoExternoTest {
    @Test
    @DisplayName("Test todo correcto")
    void testInvExtTodoCorrecto() throws DatoInvalidoException {
        InvitadoExterno invExt = new InvitadoExterno("Pepe", "Mart.",
                                            "pepe123@gmail.com");

        assertEquals("Pepe", invExt.getNombre());
        assertEquals("Mart.", invExt.getNombre());
        assertEquals("pepe123@gmail.com", invExt.getCorreo());
    }

    @Test
    @DisplayName("Test nombre inválido")
    void testInvExtNombreNuloVacio() throws DatoInvalidoException {
        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno(null, "Mart.", "pepe123@gmail.com"));

        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno("", "Mart.", "pepe123@gmail.com"));
    }

    @Test
    @DisplayName("Test apellidos inválidos")
    void testInvExtApellidosNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno("Pepe", null, "pepe123@gmail.com"));

        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno("Pepe", "", "pepe123@gmail.com"));
    }

    @Test
    @DisplayName("Test correo inválido")
    void testInvExtCorreoNuloVacio() {
        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno("Pepe", "Mart.", null));

        assertThrows(DatoInvalidoException.class, () ->
                new InvitadoExterno("Pepe", "Mart.", ""));
    }
}