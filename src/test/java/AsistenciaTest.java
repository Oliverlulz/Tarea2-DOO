import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AsistenciaTest {
    Invitable participante;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        participante = new InvitadoExterno("Pepe", "Mart.",
                "pepe123@gmail.com");
    }

    @Test
    @DisplayName("Test todo correcto")
    void testAsistenciaCorrecto() throws DatoInvalidoException {
        Asistencia asis = new Asistencia(participante);

        assertEquals(participante, asis.getParticipante());
    }

    @Test
    @DisplayName("Test participante nulo")
    void testAsistenciaParticipanteNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                new Asistencia(null));
    }
}
