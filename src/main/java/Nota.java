import java.util.Date;
/**
 * Representa una nota o apunte tomado durante el desarrollo de una reunion.
 * Almacena el contenido textual del comentario y registra automaticamente
 * el momento exacto de su creacion para permitir su ordenamiento cronologico
 */
public class Nota {

    /**
     * El texto o mensaje que contiene la nota
     */
    private String contenido;

    /**
     * La fecha y hora exacta en la que se registro la nota
     */
    private Date fechaCreacion;

    /**
     * Constructor que inicializa una nueva nota con su contenido
     * y captura el instante actual como fecha de creación
     *
     * @param contenido El texto que se desea guardar en la nota
     */
    public Nota(String contenido) {
        this.contenido = contenido;
        this.fechaCreacion = new Date(); // Registra el momento actual automáticamente
    }

    /**
     * Obtiene el contenido de la nota
     *
     * @return El texto de la nota
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Permite modificar el contenido textual de la nota
     *
     * @param contenido El nuevo texto que reemplazará al anterior
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el momento exacto en que fue creada la nota
     *
     * @return Objeto Date con la fecha y hora de creación
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Devuelve una representacion en texto de la nota
     *
     * @return Una cadena de texto con la hora y el contenido
     */
    @Override
    public String toString() {
        return "[" + this.fechaCreacion.getHours() + ":" + this.fechaCreacion.getMinutes() + "] Nota: " + this.contenido;
    }
}