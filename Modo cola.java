/**
 * Nodo utilizado por la ColaPrioridad.
 * Guarda un ticket y una referencia al siguiente nodo de la cola.
 */
public class NodoCola {

    private Ticket ticket;
    private NodoCola siguiente;

    public NodoCola(Ticket ticket) {
        this.ticket = ticket;
        this.siguiente = null;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public NodoCola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}
