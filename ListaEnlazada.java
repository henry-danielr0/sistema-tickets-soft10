/**
 * Lista enlazada simple que almacena los tickets ya RESUELTOS.
 * Permite agregar tickets al final y buscarlos por su id unico.
 */
public class ListaEnlazada {

    private NodoLista cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    /**
     * Agrega un ticket resuelto al final de la lista.
     */
    public void agregar(Ticket ticket) {
        NodoLista nuevoNodo = new NodoLista(ticket);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }

        NodoLista actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevoNodo);
    }

    /**
     * Busca un ticket resuelto por su id.
     * @return el ticket si se encuentra, o null si no esta en la lista.
     */
    public Ticket buscarPorId(int id) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.getTicket().getId() == id) {
                return actual.getTicket();
            }
            actual = actual.getSiguiente();
        }
        return null; // no se encontro: sigue pendiente
    }
}
