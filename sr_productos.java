package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modelo.Producto;

public class sr_productos extends HttpServlet {
    Producto producto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_productos</title>");
            out.println("</head>");
            out.println("<body>");

            // Intentamos parsear la fecha
            try {
                producto = new Producto(
                    Integer.valueOf(request.getParameter("txt_id_producto")), 
                    request.getParameter("txt_producto"), 
                    Integer.valueOf(request.getParameter("drop_marca")), 
                    request.getParameter("txt_descripcion"),
                    request.getParameter("txt_imagen"),
                    Double.parseDouble(request.getParameter("txt_precio_costo")), 
                    Double.parseDouble(request.getParameter("txt_precio_venta")), 
                    Integer.valueOf(request.getParameter("txt_existencia")),
                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txt_fecha_ingreso"))
                );

                // Si el botón presionado es "crear"
                if ("crear".equals(request.getParameter("btn_crear"))) {
                    if (producto.crear()>0) {
                        System.out.println(producto.getfecha_ingreso());
                        out.println("<p>Ingreso Exitoso</p>");
                    } else {
                        out.println("<p>xxx Error no ingreso xxx</p>");
                    }
                }
                
                // Manejo de eliminación
/*if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
    try {
        String idProductoStr = request.getParameter("txt_id_producto");
        System.out.println("txt_id_producto: " + idProductoStr);
        
        if (idProductoStr == null || idProductoStr.isEmpty()) {
            System.out.println("ID del producto es nulo o vacío.");
            response.sendRedirect("productos.jsp?error=missingId");
            return;
        }
        
        int idProducto = Integer.parseInt(idProductoStr);
        producto = new Producto();
        producto.eliminar(idProducto);
        response.sendRedirect("productos.jsp?success=true");
        
    } catch (NumberFormatException e) {
        System.out.println("Error al convertir el ID del producto: " + e.getMessage());
        response.sendRedirect("productos.jsp?error=invalidId");
    } catch (Exception e) {
        System.out.println("Error al eliminar el producto: " + e.getMessage());
        response.sendRedirect("productos.jsp?error=deleteFailed");
    }
    return;
}*/



                // Manejo de actualización
              /* if ("modificar".equals(request.getParameter("btn_modificar"))) {
                    int idProducto = Integer.parseInt(request.getParameter("txt_id_producto"));
                    // Aquí iría tu lógica para cargar el producto y mostrar el formulario de actualización
                    response.sendRedirect("pagina_actualizar.jsp?id=" + idProducto);
                    return; // Asegúrate de salir después de la redirección
                }*/
               

            } catch (ParseException e) {
                // Captura de la excepción ParseException
                out.println("<p>Error al parsear la fecha. Verifique el formato.</p>");
                e.printStackTrace(out); // Opcional, imprime el error en el log
            }
            

            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
