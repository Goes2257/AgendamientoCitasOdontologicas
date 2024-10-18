import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AgendarCitaServlet")
public class AgendarCitaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");

        // Lógica de conexión a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Conexión sin contraseña
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/odontologia_db", "root", "");
            String sql = "INSERT INTO citas (nombre_paciente, fecha_cita, hora_cita, motivo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, fecha);
            stmt.setString(3, hora);
            stmt.setString(4, motivo);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            // Redirigir a la página de éxito si todo sale bien
            response.sendRedirect("citaExitosa.jsp");
        } catch (Exception e) {
            // Muestra un error detallado en la consola
            e.printStackTrace();
            // Muestra un mensaje de error en la página para depuración
            response.getWriter().println("Error al guardar los datos: " + e.getMessage());
        }
    }
}




