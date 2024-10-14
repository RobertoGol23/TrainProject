package servlet;

import entity.user.User;
import entity.dao.UserDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import configuration.JpaConfig;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        userDAO = context.getBean(UserDAO.class);
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.findUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home.jsp"); // Reindirizza a home.jsp
        } else {
            request.setAttribute("errorMessage", "Email o password errati.");
            request.getRequestDispatcher("login").forward(request, response);
        }
        context.close(); // Chiude il contesto dopo l'inizializzazione
    }
}