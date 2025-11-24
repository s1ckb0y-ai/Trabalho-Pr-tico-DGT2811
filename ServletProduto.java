package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtos = facade.findAll();
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html><html><head><meta charset='utf-8'><title>Produtos</title>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'/>");
            // Bootstrap CDN (v5) — apenas link, sem controle de versão local
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'/>");
            out.println("</head><body><div class='container py-4'>");
            out.println("<h1 class='mb-4'>Produtos</h1>");
            out.println("<table class='table table-striped'><thead><tr><th>ID</th><th>Nome</th><th>Preço</th></tr></thead><tbody>");
            if (produtos != null && !produtos.isEmpty()) {
                for (Produto p : produtos) {
                    out.printf("<tr><td>%d</td><td>%s</td><td>%.2f</td></tr>",
                               p.getId(), p.getNome(), p.getPrecoVenda() != null ? p.getPrecoVenda() : 0.0f);
                }
            } else {
                out.println("<tr><td colspan='3'>Nenhum produto encontrado</td></tr>");
            }
            out.println("</tbody></table></div></body></html>");
        }
    }
}
