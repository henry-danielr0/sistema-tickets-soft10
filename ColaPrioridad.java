/**
 * Cola de prioridad implementada con nodos enlazados.
 * Almacena los tickets PENDIENTES de resolucion.
 * La prioridad se determina por el id del ticket: entre menor sea el id,
 * mas antiguo (y por lo tanto mas prioritario) es el ticket. Esto asegura
 * que los tickets se resuelvan en el mismo orden en que fueron creados.
 */
public class ColaPrioridad {

    private NodoCola frente;

    public ColaPrioridad() {
        this.frente = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Inserta un ticket respetando el orden de prioridad (por id ascendente).
     */
    public void encolar(Ticket ticket) {
        NodoCola nuevoNodo = new NodoCola(ticket);

        if (estaVacia() || ticket.getId() < frente.getTicket().getId()) {
            nuevoNodo.setSiguiente(frente);
            frente = nuevoNodo;
            return;
        }

        NodoCola actual = frente;
        while (actual.getSiguiente() != null
                && actual.getSiguiente().getTicket().getId() <= ticket.getId()) {
            actual = actual.getSiguiente();
        }
        nuevoNodo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevoNodo);
    }

    /**
     * Devuelve el ticket al frente de la cola sin eliminarlo.
     */
    public Ticket verFrente() {
        return estaVacia() ? null : frente.getTicket();
    }

    /**
     * Elimina y devuelve el ticket al frente de la cola.
     */
    public Ticket desencolar() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticket = frente.getTicket();
        frente = frente.getSiguiente();
        return ticket;
    }
}
