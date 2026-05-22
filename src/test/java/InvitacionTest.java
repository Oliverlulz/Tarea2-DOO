import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

class InvitacionTest {
    Invitable invitado;
    Instant hora;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        Invitable invitado = new InvitadoExterno("Pepe", "Mart.",
                "pepe123@gmail.com");
        Instant hora = Instant.now();
    }
    @Test
    @DisplayName("Test todo correcto")
    void testInvitacionCorrecto() throws DatoInvalidoException {
        Invitacion inv = new Invitacion(invitado, hora);

        assertEquals(invitado, inv.getInvitado());
        assertEquals(hora, inv.getHora());
    }

    @Test
    @DisplayName("Test invitado inválido")
    void testInvitacionInvitadoNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                new Invitacion(null, hora));
    }

    @Test
    @DisplayName("Test hora inválida")
    void testInvitacionHoraNula() {
        assertThrows(DatoInvalidoException.class, () ->
                new Invitacion(invitado, null));
    }
}