package servlets;

import classes.Category;
import classes.Goods;
import classes.categoryDAO;
import classes.goodsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String delete = request.getParameter("delete");
        goodsDAO gdao = new goodsDAO();

        if (delete != null) { // Is the delete button pressed?
            gdao.delElementId(Integer.valueOf(delete));
        }

        response.sendRedirect(request.getContextPath() + "/products");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Category> categories;
        categoryDAO categoryDAO = new categoryDAO();
        categories = categoryDAO.select();
        request.getSession().setAttribute("categories",categories); // Will be available as ${products} in JSP.
        String catId = request.getParameter("category");
        if(catId!=null){
            int cId=Integer.parseInt(catId);
            goodsDAO gdao = new goodsDAO();
            List<Goods> goods ;
            goods = gdao.selectByCategory(cId);
            request.setAttribute("products",goods);
        }
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}
