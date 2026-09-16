package reservahotel.modelo;

/**
 * ServicioEmail proporciona utilidades para la gestión de notificaciones
 * de correo electrónico (recuperación de clave) usando Java estándar sin
 * dependencias externas de librerías JAR adicionales.
 */
public class ServicioEmail {

    /**
     * Procesa la solicitud de envío de correo para recuperación de clave.
     * En un entorno académico/local, simula el envío registrando el evento en consola,
     * permitiendo que la clave temporal sea entregada y presentada al usuario.
     * 
     * @param destinatario Correo electrónico registrado del usuario
     * @param nombreUsuario Nombre del usuario
     * @param nuevaClave Clave temporal generada
     * @return true si el proceso se completa correctamente
     */
    public static boolean enviarClaveTemporal(String destinatario, String nombreUsuario, String nuevaClave) {
        try {
            // Registro de consola para auditoría y evaluación académica
            System.out.println("=================================================");
            System.out.println("[NOTIFICACIÓN DE CORREO SALIENTE]");
            System.out.println("Para: " + destinatario + " (" + nombreUsuario + ")");
            System.out.println("Asunto: Recuperación de Clave - Sistema ReservaHotel");
            System.out.println("Mensaje: Tu nueva clave temporal es: " + nuevaClave);
            System.out.println("=================================================");
            
            // Retorna true indicando que la solicitud de notificación fue procesada
            return true;
        } catch (Exception ex) {
            System.err.println("[ServicioEmail] Error en procesamiento: " + ex.getMessage());
            return false;
        }
    }
}
