/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juros.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz Henrique VM1
 */
@WebServlet(name = "JurosCompostoServlet", urlPatterns = {"/juros-composto"})
public class JurosCompostoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Juros-Compostos</title>");
            out.println("<meta charset='utf-8'>");
            out.println("<style type='text/css'>");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            //Aqui é a inicialização das variáveis locais, onde receberão os valores dos campos como parâmetros.
            double capital = 0;
            double taxaJuros = 0.00;
            double montante = 0;
            int meses = 0;

            //Java
            //Iniciando com o Try-Catch, para tratar o erro de digitação e/ou caso o form seja submitado sem nada.
            try {
                /*Usando o método request.getParameter para passar 
                o valor do campo c para a variável capital. Como todos os valores em html são textos, usando o metodo Float.parseFloat() para
                converter de string para float(decimal)
                Também usei o BigDecimal que corrigiu o problema de precisão, e de números muito longos.*/

                if (request.getParameter("c") != null) {
                    capital = Float.parseFloat(request.getParameter("c"));
                    capital = BigDecimal.valueOf(capital).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    //Explicação do método
                    //variavel = BigDecimal.valueOf(variavel).setScale(quantidade de casas decimais, modo de arredondamento).definirTipo();

                }

                if (request.getParameter("i") != null) {
                    taxaJuros = Float.parseFloat(request.getParameter("i")); //Taxa de Juros
                    taxaJuros = taxaJuros / 100;
                    taxaJuros = BigDecimal.valueOf(taxaJuros).setScale(6, RoundingMode.HALF_UP).doubleValue();
                }
                if (request.getParameter("n") != null) {
                    meses = Integer.parseInt(request.getParameter("n"));
                }
            } catch (NumberFormatException e) {
                //Caso a excesão seja na falha da conversão
                out.println("<span style='color:red'>Valores incorretos. Tente novamente!</span>");
            }

            out.println("<form><!--Coloquei o nome dos campos com o nome das variaveis que se ultiliza nas formulas -->");
            out.println("<h1>Calculo do Juros Composto:</h1><br>");
            out.println("Capital: <input type='text' name='c' value='" + capital + "'><br>");
            out.println("Meses: <input type='number' name='n' value='" + meses + "'><br>");
            out.println("Taxa de Juros: <input type='text' name='i' value='" + taxaJuros + "'><br>");
            out.println("<input type='submit' value='Calcular'>	");
            out.println("</form>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>Meses</th><th>Montante Acumulado</th>");
            out.println("</tr>");

            //Java
            //For para popular dinamicamente as linhas da tabela já com os resultados.
            for (int i = 1; i <= meses; i++) {
                
                //Formula = M = C(1+ i)^n
                montante = (capital * Math.pow((1 + taxaJuros), i));
                //Método Math.Pow > realiza potenciação - Uso: Math.pow(número,expoente)
                //Nesse caso coloquei o número como (1 + taxaJuros), e o expoente como o valor de i do contador, que representa os meses
                montante = BigDecimal.valueOf(montante).setScale(2, RoundingMode.HALF_UP).doubleValue();
                out.println("<tr><td>" + i + "</td><td>" + montante + "</td></tr>"); //Na saida, concatena o valor de i e o montante calculado
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
