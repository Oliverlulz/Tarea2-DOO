import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;

public class RetrasoTest {
    Invitable participante;
    Instant hora;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        participante = new InvitadoExterno("Pepe", "Mart.",
                "pepe123@gmail.com");
        hora = Instant.now();
    }

    @Test
    @DisplayName("Test todo correcto")
    void testRetrasoCorrecto() throws DatoInvalidoException {
        Retraso ret = new Retraso(participante, hora);

        assertEquals(participante, ret.getParticipante());
        assertEquals(hora, ret.getHora());
    }

    @Test
    @DisplayName("Test participante nulo")
    void testRetrasoParticipanteNulo() {
        assertThrows(DatoInvalidoException.class, () ->
                new Retraso(null, hora));
    }

    @Test
    @DisplayName("Test hora nula")
    void testRetrasoHoraNula() {
        assertThrows(DatoInvalidoException.class, () ->
                new Retraso(participante, null));
    }
}