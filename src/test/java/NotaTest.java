import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la Clase Nota")
public class NotaTest {

    @Test
    @DisplayName("Creación exitosa de una nota con texto valido y verificación de fecha")
    public void testConstructorNotaExitoso() {

        String mensaje ="Acuerdo: Juan hace la clase bebida";
        Date ahora = new Date();
        Nota nota = new Nota(mensaje);

        System.out.println("--> RESULTADO REAL DEL TOSTRING: " + nota);

        String formatoEsperado = "[" + ahora.getHours() + ":" + ahora.getMinutes() + "] Nota: " + mensaje;
        assertEquals(formatoEsperado, nota.toString(), "El toString de la nota no coincide con el formato, la hora o el mensaje esperado.");
    }

    @Test
    @DisplayName("Falla al intentar crear una nota con contenido nulo (null)")
    public void testConstructorNotaNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Nota(null);
        }, "Debería lanzar IllegalArgumentException si el contenido es null.");
    }

    @Test
    @DisplayName("Falla al intentar crear una nota con texto vacío o puros espacios")
    public void testConstructorNotaVaciaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Nota("   ");
        }, "Debería lanzar IllegalArgumentException si el contenido solo tiene espacios en blanco.");
    }
}
