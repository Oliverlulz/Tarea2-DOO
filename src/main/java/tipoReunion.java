

/**
 * Representa los tipos de reuniones
 */
public enum tipoReunion {
    TECNICA("Técnica"),
    MARKETING("Marketing"),
    OTRO("Otro");

    /**
     * El tipo de reunion
     */
    private final String reunion;

    /**
     * Constructor de Constantes para asignarle el nombre a los tipode de reunion
     * @param reunion El nombre que tendra el tipo de reunion
     */
    tipoReunion(String reunion) {
        this.reunion = reunion;
    }

    /**
     * Devuelve el nombre del tipo de reunión
     * @return Cadena de texto formateada.
     */
    @Override
    public String toString() {
        return this.reunion;
    }
}
