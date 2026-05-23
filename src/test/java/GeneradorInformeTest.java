import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

@DisplayName("Pruebas Unitarias para la Generacon de Informes")
public class GeneradorInformeTest {
    private Reunion reunionValida;
    private Empleado empleado1;
    private Empleado empleado2;
    private final String RUTA_TEST = "informe_test.txt";

    @BeforeEach
    @DisplayName("Preparar reunión con datos reales para el informe")
    public void setUp() throws Exception {
        Departamento depto = new Departamento("Ventas");
        empleado1 = new Empleado("1", "Soto", "Sofia", "sofia@gmail.com", depto);
        empleado2 = new Empleado("2", "Perez", "Juan", "juan@gmail.com", depto);

        ArrayList<Invitable> lista = new ArrayList<>();
        lista.add(empleado1);
        lista.add(empleado2);

        reunionValida = new ReunionPresencial("Sala 302", new Date(), Instant.now(), Duration.ofMinutes(60), tipoReunion.MARKETING, lista);
        reunionValida.iniciar();
        reunionValida.pasarAsistencia(empleado1, Instant.now().minus(Duration.ofMinutes(5)));
        reunionValida.pasarAsistencia(empleado2, Instant.now().minus(Duration.ofMinutes(5)));
        reunionValida.crearNota("Acuerdo 1: Hacer anuncio de CocaCola.");
        reunionValida.finalizar();
    }

    @AfterEach
    @DisplayName("Limpieza: Borrar el archivo de prueba generado")
    public void tearDown() {
        File archivo = new File(RUTA_TEST);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    // VALIDACIONES

    @Test
    @DisplayName("1. Excepcion si la reunion entregada es nula")
    public void testInformeFallaConReunionNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            GeneradorInforme.generarInforme(null, RUTA_TEST);
        }, "Deberia rechazar una reunion nula");
    }

    @Test
    @DisplayName("2. Excepcion si la dirección del archivo es nula")
    public void testInformeFallaConRutaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            GeneradorInforme.generarInforme(reunionValida, null);
        }, "Deberia rechazar una ruta nul.");
    }

    @Test
    @DisplayName("3. Excepcion si la direccion del archivo está vacia")
    public void testInformeFallaConRutaVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            GeneradorInforme.generarInforme(reunionValida, "    ");
        }, "Deberia rechazar rutas que contengan solo espacios en blanco");
    }

    // VERIFICACION DE CONTENIDO

    @Test
    @DisplayName("4. Creacion Exitosa: Verifica que el archivo fisico realmente aparezca")
    public void testGenerarInformeExitoso() throws Exception {
        GeneradorInforme.generarInforme(reunionValida, RUTA_TEST);

        File archivoGenerado = new File(RUTA_TEST);
        assertTrue(archivoGenerado.exists(), "El archivo informe_test.txt deberia haberse creado");
        assertTrue(archivoGenerado.length() > 0, "El archivo de informe no deberia estar vacio");
    }

    @Test
    @DisplayName("5. Validación de Contenido: El archivo debe tener los datos que escribe tu código")
    public void testContenidoDelInforme() throws Exception {
        GeneradorInforme.generarInforme(reunionValida, RUTA_TEST);

        String contenido = Files.readString(Path.of(RUTA_TEST));

        assertTrue(contenido.contains("Sofia"), "El informe debe contener la asistencia registrada.");
        assertTrue(contenido.contains("CocaCola"), "El informe debe contener las notas de la reunión.");
        assertTrue(contenido.contains("Sala 302"), "El informe debe mostrar la ubicación correcta.");
    }

    @Test
    @DisplayName("6. Caso Borde: Funciona correctamente si nadie asistio a la reunión")
    public void testInformeReunionSinAsistencia() throws Exception {
        ArrayList<Invitable> lista = new ArrayList<>();
        lista.add(empleado1);

        Reunion reunionVacia = new ReunionPresencial("Sala 302", new Date(), Instant.now(), Duration.ofMinutes(60), tipoReunion.MARKETING, lista);

        assertDoesNotThrow(() -> {
            GeneradorInforme.generarInforme(reunionVacia, RUTA_TEST);
        });

        File archivoGenerado = new File(RUTA_TEST);
        assertTrue(archivoGenerado.exists());
    }
}

