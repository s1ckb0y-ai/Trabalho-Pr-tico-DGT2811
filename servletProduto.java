package cadastroee.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import java.util.List;

public class servletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtos = facade.findAll();
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html><html><head><meta charset='utf-8'><title>Produtos</title>");
            // incluir Bootstrap via CDN se quiser
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'/>");
            out.println("</head><body><div class='container'><h1>Produtos</h1><ul class='list-group'>");
            for (Produto p : produtos) {
                out.printf("<li class='list-group-item'>%d - %s - %s</li>", p.getId(), p.getNome(), p.getPrecoVenda());
            }
            out.println("</ul></div></body></html>");
        }
    }
}
