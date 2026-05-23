import org.junit.jupiter.api.Test;

import javax.xml.datatype.Duration;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReunionTest {
    @Test
    void ObtenerAsistenciaRegula() throws DatoInvalidoException {
        var delta = new Departamento("Deltawarriors");
        var kris = new Empleado("01","dremurr","kris","@blub",delta);
        var susie = new Empleado("02","gaster","susie","@blub",delta);
        var noelle = new Empleado("03","holiday","noelle","@blub",delta);


        var Lista = new ArrayList<Invitacion>();
        var invN = new Invitacion(Departamento,Instant.now());
        Lista.add(invK);Lista.add(invS);Lista.add(invK);

        var reunion = new Reunion(Instant.now(), new Duration, Lista);
        reunion.Iniciar();
        reunion.marcarAsistencia();
        reunion.Finalizar();

    }

}