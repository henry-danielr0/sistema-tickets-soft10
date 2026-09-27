import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Sistema de gestion de tickets en linea (CLI).
 *
 * Los tickets pendientes viven en una ColaPrioridad y los tickets resueltos
 * se guardan en una ListaEnlazada. El usuario puede crear tickets y buscar
 * tickets resueltos; el administrador puede ver y resolver el ticket al
 * frente de la cola.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ColaPrioridad pendientes = new ColaPrioridad();
    private static final ListaEnlazada resueltos = new ListaEnlazada();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> menuUsuario();
                case 2 -> menuAdministrador();
                case 3 -> System.out.println("Hasta luego!");
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        } while (opcion != 3);

        sc.close();
    }

    // ---------- MENU PRINCIPAL ----------

    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== SISTEMA DE TICKETS =====");
        System.out.println("1. Menu de Usuario");
        System.out.println("2. Menu de Administrador");
        System.out.println("3. Salir");
        System.out.print("Elige una opcion: ");
    }

    // ---------- MENU USUARIO ----------

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n--- MENU USUARIO ---");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("3. Volver al menu principal");
            System.out.print("Elige una opcion: ");
            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearTicket();
                case 2 -> buscarTicketResuelto();
                case 3 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    private static void crearTicket() {
        System.out.print("Describe el problema: ");
        String descripcion = sc.nextLine();

        System.out.print("Tu nombre completo: ");
        String nombreCompleto = sc.nextLine();

        Ticket ticket = new Ticket(descripcion, nombreCompleto);
        pendientes.encolar(ticket);

        System.out.println("Ticket creado con exito. Tu numero de ticket es: " + ticket.getId());
    }

    private static void buscarTicketResuelto() {
        System.out.print("Ingresa el id del ticket a buscar: ");
        int id = leerOpcion();

        Ticket ticket = resueltos.buscarPorId(id);
        if (ticket == null) {
            System.out.println("El ticket #" + id + " esta PENDIENTE (aun no ha sido resuelto).");
        } else {
            System.out.println("Ticket encontrado:\n" + ticket);
        }
    }

    // ---------- MENU ADMINISTRADOR ----------

    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("3. Volver al menu principal");
            System.out.print("Elige una opcion: ");
            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> verFrenteCola();
                case 2 -> resolverTicket();
                case 3 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opcion invalida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    private static void verFrenteCola() {
        Ticket ticket = pendientes.verFrente();
        if (ticket == null) {
            System.out.println("No hay tickets pendientes.");
        } else {
            System.out.println("Ticket al frente de la cola:\n" + ticket);
        }
    }

    private static void resolverTicket() {
        if (pendientes.estaVacia()) {
            System.out.println("No hay tickets pendientes para resolver.");
            return;
        }
        Ticket ticket = pendientes.desencolar();
        ticket.setFechaResolucion(LocalDateTime.now());
        resueltos.agregar(ticket);

        System.out.println("Ticket #" + ticket.getId() + " resuelto con exito.");
    }

    // ---------- UTILIDAD ----------

    /**
     * Lee una opcion numerica del usuario de forma segura.
     * Si el usuario escribe algo invalido, devuelve -1 para forzar el
     * mensaje de "opcion invalida" en vez de tronar el programa.
     */
    private static int leerOpcion() {
        String linea = sc.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
