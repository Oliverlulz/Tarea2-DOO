import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Se ocupa para probar Reunion con ReunionVirtual ya que no se
 * puede crear un abstract
 */
class ReunionTest {
    private ArrayList Lista = new ArrayList<Invitacion>();
    private Invitacion inv1;
    private Invitacion inv2;
    private Invitacion inv3;
    private Invitacion invS;
    private Reunion reunion;

    @BeforeEach
    void setup() throws DatoInvalidoException {
        Departamento dep = new Departamento("Dep1");
        Empleado emp1 = new Empleado("01", "ortega", "kris", "@correo1", dep);
        Empleado emp2 = new Empleado("02", "palacios", "susan", "@correo2", dep);
        Empleado emp3 = new Empleado("03", "fuentes", "natalia", "@correo3", dep);
        InvitadoExterno vis = new InvitadoExterno("pedro", "agustin", "@correo4");
        inv1 = new Invitacion(emp1, Instant.now());
        inv2 = new Invitacion(emp2, Instant.now());
        inv3 = new Invitacion(emp3, Instant.now());
        invS = new Invitacion(vis, Instant.now());
        Lista.add(inv1);
        Lista.add(inv2);
        Lista.add(inv3);
        Lista.add(invS);
        reunion = new ReunionVirtual(LocalTime.now(),Duration.ofHours(22),Lista,"501");
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Iniciar y finalizar reunion")
    void IniFin (){
        assertDoesNotThrow(() ->
                reunion.Iniciar());
                reunion.Finalizar();
    }
    @Test
    @DisplayName("Total Asistencia exitosa")
    void ObtenerTotal () throws DatoInvalidoException {
        reunion.marcarAsistencia(inv1);
        reunion.Iniciar();
        reunion.marcarAsistencia(inv2);
        reunion.marcarAsistencia(invS);
        reunion.Finalizar();
        assertEquals(reunion.obtenerTotalAsistencia(), 3);
    }
    @Test
    @DisplayName("Porcentaje Asistencia exitosa")
    void ObtenerPorcentaje () throws DatoInvalidoException {
        reunion.marcarAsistencia(inv1);
        reunion.Iniciar();
        reunion.marcarAsistencia(inv2);
        reunion.marcarAsistencia(invS);
        reunion.Finalizar();
        assertEquals(reunion.obtenerPorcentajeAsistencia(), 75.0);
    }
    @Test
    @DisplayName("Tiempo Real exitosa")
    void TiempoReal() throws DatoInvalidoException {
        assertDoesNotThrow(() -> {
            reunion.marcarAsistencia(inv1);
            reunion.Iniciar();
            reunion.marcarAsistencia(inv2);
            reunion.marcarAsistencia(invS);
            reunion.Finalizar();
            System.out.println(reunion.calcularTiempoReal());
        });
    }
    }