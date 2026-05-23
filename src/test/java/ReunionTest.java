import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Pruebas Unitarias para la Clase Reunion")
public class ReunionTest {

    private Empleado empleado1;
    private Empleado empleado2;
    private ArrayList<Invitable> listaInvitadosValida;
    private Instant horaPlanificada;

    @BeforeEach
    @DisplayName("Configuración inicial: Limpia y prepara el escenario antes de cada test")
    public void setUp() throws Exception {
        horaPlanificada = Instant.now().plus(Duration.ofHours(1));

        Departamento depto = new Departamento("Informática");
        empleado1 = new Empleado("1", "Soto", "Sofia", "sofia@gmail.com", depto);
        empleado2 = new Empleado("2", "Perez", "Juan", "juan@gmail.com", depto);

        listaInvitadosValida = new ArrayList<>();
        listaInvitadosValida.add(empleado1);
        listaInvitadosValida.add(empleado2);
    }

    // TESTS DEL CONSTRUCTOR Y PLANIFICACION

    @Test
    @DisplayName("1. Constructor Exitoso: Transforma los Invitables en objetos Invitacion")
    public void testConstructorExitoso() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);

        assertNotNull(reunion.getFecha());
        assertEquals(horaPlanificada, reunion.getHoraPrevista());
        assertEquals(0, reunion.obtenerTotalAsistencia());
        assertEquals(0.0f, reunion.obtenerPorcentajeAsistencia());
    }

    @Test
    @DisplayName("2. Exepciones si los campos obligatorios del constructor son nulos")
    public void testConstructorCamposNulosLanzanExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", null, horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), null, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), null, listaInvitadosValida);
        });
    }

    @Test
    @DisplayName("3. Exepcion si la duración de la reunion es cero o negativa")
    public void testConstructorDuracionInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(0), tipoReunion.MARKETING, listaInvitadosValida);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(-15), tipoReunion.MARKETING, listaInvitadosValida);
        });
    }

    @Test
    @DisplayName("4. Exepcion si la lista de convocados está vacía o es nula")
    public void testConstructorListaInvitadosInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, null);
        });
        ArrayList<Invitable> listaVacia = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaVacia);
        });
    }

    @Test
    @DisplayName("5. Falla si la lista contiene un elemento null")
    public void testConstructorInvitadoNuloDentroDeLista() {
        ArrayList<Invitable> listaConNulo = new ArrayList<>();
        listaConNulo.add(empleado1);
        listaConNulo.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaConNulo);
        });
    }

    @Test
    @DisplayName("6. Exepcion si hay un participante repetido en la lista")
    public void testConstructorInvitadoRepetidoLanzaExcepcion() {
        ArrayList<Invitable> listaRepetidos = new ArrayList<>();
        listaRepetidos.add(empleado1);
        listaRepetidos.add(empleado1);

        assertThrows(IllegalArgumentException.class, () -> {
            new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaRepetidos);
        });
    }

    // PASAR ASISTENCIA Y CICLO DE VIDA EN VIVO

    @Test
    @DisplayName("7. Exception al pasar asistencia si la reunion no ha iniciado")
    public void testPasarAsistenciaAntesDeIniciarLanzaExcepcion() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);

        assertThrows(IllegalStateException.class, () -> {
            reunion.pasarAsistencia(empleado1, Instant.now());
        });
    }

    @Test
    @DisplayName("8. Exception al pasar asistencia si la reunión ya finalizo")
    public void testPasarAsistenciaReunionFinalizadaLanzaExcepcion() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        reunion.iniciar();
        reunion.finalizar();

        assertThrows(IllegalStateException.class, () -> {
            reunion.pasarAsistencia(empleado1, Instant.now());
        });
    }

    @Test
    @DisplayName("9. Excepcion si un intruso sin invitación intenta registrarse")
    public void testPasarAsistenciaIntrusoNoInvitado() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        reunion.iniciar();

        Empleado colado = new Empleado("3", "Silva", "Diego", "diego@gmail.com", new Departamento("Informatica"));

        assertThrows(IllegalArgumentException.class, () -> {
            reunion.pasarAsistencia(colado, Instant.now());
        });
    }

    @Test
    @DisplayName("10. Falla al registrar dos veces al mismo asistente")
    public void testPasarAsistenciaDuplicadaLanzaExcepcion() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        reunion.iniciar();

        reunion.pasarAsistencia(empleado1, horaPlanificada.minus(Duration.ofMinutes(5)));

        assertThrows(IllegalStateException.class, () -> {
            reunion.pasarAsistencia(empleado1, horaPlanificada.minus(Duration.ofMinutes(5)));
        });
    }

    @Test
    @DisplayName("11. Registro Correcto: Clasifica Asistencia (a tiempo) vs Retraso (tarde)")
    public void testAsistenciaVsRetraso() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        reunion.iniciar();

        reunion.pasarAsistencia(empleado1, horaPlanificada.minus(Duration.ofMinutes(5)));

        reunion.pasarAsistencia(empleado2, horaPlanificada.plus(Duration.ofMinutes(10)));

        assertEquals(2, reunion.obtenerTotalAsistencia());
        assertEquals(1, reunion.obtenerRetrasos().size());
        assertTrue(reunion.obtenerRetrasos().contains(empleado2));
    }

    // MÉTODOS DE FILTRADO Y ALGORITMOS MATEMÁTICOS

    @Test
    @DisplayName("12. Filtro de Ausencias: Detecta correctamente quién no vino")
    public void testObtenerAusencias() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);
        reunion.iniciar();

        reunion.pasarAsistencia(empleado1, horaPlanificada.minus(Duration.ofMinutes(5)));
        reunion.finalizar();

        List<Invitable> ausentes = reunion.obtenerAusencias();
        assertEquals(1, ausentes.size());
        assertTrue(ausentes.contains(empleado2), "Juan debería figurar en la lista de ausentes.");
    }

    @Test
    @DisplayName("13. Cálculos Estadísticos: Total y Porcentaje de Asistencia")
    public void testCalculosMatematicosAsistencia() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING , listaInvitadosValida);
        reunion.iniciar();

        reunion.pasarAsistencia(empleado1, horaPlanificada.minus(Duration.ofMinutes(5)));
        reunion.finalizar();

        assertEquals(1, reunion.obtenerTotalAsistencia());
        assertEquals(50.0f, reunion.obtenerPorcentajeAsistencia(), 0.01f);
    }

    // GESTIÓN DE NOTAS DE LA REUNIÓN

    @Test
    @DisplayName("14. Gestion de Notas: Añade notas validas y rechaza textos vacios")
    public void testGestionNotasReunion() throws Exception {
        Reunion reunion = new ReunionPresencial("Sala 302", new Date(), horaPlanificada, Duration.ofMinutes(60), tipoReunion.MARKETING, listaInvitadosValida);

        reunion.crearNota("Acuerdo 1: Hacer anuncio de CocaCola.");
        assertEquals(1, reunion.getNotas().size());

        assertThrows(IllegalArgumentException.class, () -> {
            reunion.crearNota("    ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            reunion.crearNota(null);
        });
    }
}
