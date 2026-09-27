/**
 * Nodo utilizado por la ListaEnlazada.
 * Guarda un ticket y una referencia al siguiente nodo de la lista.
 */
public class NodoLista {

    private Ticket ticket;
    private NodoLista siguiente;

    public NodoLista(Ticket ticket) {
        this.ticket = ticket;
        this.siguiente = null;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
