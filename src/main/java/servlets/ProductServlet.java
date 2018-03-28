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

public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String save = request.getParameter("save");
        categoryDAO categoryDAO = new categoryDAO();
        goodsDAO gdao = new goodsDAO();
        Goods goods;
        if (save != null) { // Is the save button pressed? (note: if empty then no product ID was supplied, which means that it "add product".
            if(save.length()==0||save.equals("")){
                Category cat = categoryDAO.selectById(Integer.valueOf(request.getParameter("idCategory")));
                goods=new Goods(Integer.valueOf(request.getParameter("code")),request.getParameter("name"),Integer.valueOf(request.getParameter("weight")),
                        Integer.valueOf(request.getParameter("price")),Integer.valueOf(request.getParameter("quantity")),cat,
                        request.getParameter("img"));
                gdao.addElement(goods);
                int id=gdao.getIdByName(request.getParameter("name"));
                goods.setIdgoods(id);
            }
            else {
                goods = gdao.selectById(Integer.valueOf(save));
                goods.setName(request.getParameter("name"));
                goods.setCode(Integer.valueOf(request.getParameter("code")));
                goods.setWeight(Integer.valueOf(request.getParameter("weight")));
                goods.setPrice(Integer.valueOf(request.getParameter("price")));
                goods.setQuantity(Integer.valueOf(request.getParameter("quantity")));
                Category cat = categoryDAO.selectCatByName(request.getParameter("idCategory"));
                goods.setCategory(cat);
                goods.setImg(request.getParameter("img"));
                gdao.updateElementById(goods,Integer.valueOf(save));
            }
        }

        response.sendRedirect(request.getContextPath() + "/products"); // Go to page with table.
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String edit = request.getParameter("edit");
        goodsDAO gdao = new goodsDAO();
        if (edit != null) { // Is the edit link clicked?
            Goods goods = gdao.selectById(Integer.valueOf(edit));
            request.setAttribute("product", goods); // Will be available as ${product} in JSP.
        }

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
}
