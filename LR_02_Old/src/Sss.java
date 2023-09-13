import java.io.IOException; // исключения ввода/вывода 
import javax.servlet.*; // интерфейсы и классы общего типа 
import javax.servlet.http.*; // расширение javax.servlet для http
public class Sss extends HttpServlet implements Servlet { 
    public Sss() { 
        super(); 
        System.out.println("Sss:constructor"); 
    } 
    public void init(ServletConfig sc) throws ServletException { 
        // инициализация сервлета
        super.init(); 
        System.out.println("Sss:init"); 
    } 
    public void destroy() { 
        // перед уничтожением сервлета
        super.destroy(); 
        System.out.println("Sss:destroy"); 
    } 
    protected void service(HttpServletRequest rq, HttpServletResponse rs) 
    throws ServletException, IOException { 
        // обработка http-запроса 
        System.out.println("Sss:service:"+rq.getMethod()); 
    } 
}