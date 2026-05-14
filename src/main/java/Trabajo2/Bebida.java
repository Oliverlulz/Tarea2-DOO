package Trabajo1;
/**
 * Representa un artículo líquido de la máquina expendedora. Extiende la función de
 * {@link Producto} para modelar un producto bebida.
 */
public abstract class Bebida extends Producto {
    /**
     * Constructor de las bebidas con su respectivo número de serie
     * @param serie El número identificador de la bebida.
     */
    public Bebida(int serie) {
        super(serie);
    }
}
