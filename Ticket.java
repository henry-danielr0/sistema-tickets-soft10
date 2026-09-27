import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un ticket del sistema de soporte.
 * Cada ticket tiene un id unico (autoincremental), la informacion de quien
 * lo creo, y las fechas de creacion y resolucion (esta ultima null hasta
 * que un administrador lo resuelva).
 */
public class Ticket {

    // Contador estatico: genera el id unico e irrepetible de cada ticket.
    private static int cantidad = 0;

    private final int id;
    private String descripcion;
    private String nombreCompleto;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Ticket(String descripcion, String nombreCompleto) {
        this.id = ++cantidad; // cada ticket nuevo obtiene el siguiente numero
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null; // por defecto, no resuelto
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public boolean estaResuelto() {
        return fechaResolucion != null;
    }

    @Override
    public String toString() {
        String resolucion = (fechaResolucion == null)
                ? "PENDIENTE"
                : fechaResolucion.format(FORMATO);

        return "Ticket #" + id
                + "\n  Descripcion: " + descripcion
                + "\n  Creado por: " + nombreCompleto
                + "\n  Fecha creacion: " + fechaCreacion.format(FORMATO)
                + "\n  Fecha resolucion: " + resolucion;
    }
}
