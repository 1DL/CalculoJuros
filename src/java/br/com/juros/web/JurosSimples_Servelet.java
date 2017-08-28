/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juros.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/juros-simples"})
public class JurosSimples_Servelet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Juros Simples</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            out.println("<table border = '0'>");
            out.println("<tr>");
            out.println("<th>Capital:</th>");
            out.println("<td><input type ='number'step = '0.01' name = 'capital' value = '' placeholder = 'R$'> </td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Taxa de juros: </th>");
            out.println("<td><input type ='number'step = '0.01' name = 'txjuros' value = '' placeholder = '0.00'></td> ");   
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Tempo:</th> ");
            out.println("<td><input type ='number' name = 'tempo' value = '' placeholder = 'Meses'></td> ");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type = 'submit' value = 'Calcular'></td>");
            out.println("<td><input type = 'reset' value = 'Limpar'></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("<br>");
            //Inicialização das variáveis
            int tempo = 0; 
            double txjuros = 0;
            double capital = 0;
            //Tratamento de excessão caso o usuário nao digite nada e envie o formulário
            try{
                if(request.getParameter("tempo") != null || request.getParameter("txjuros")!= null ||request.getParameter("capital") != null ){
                 tempo = Integer.parseInt(request.getParameter("tempo")); 
                 txjuros = Double.parseDouble(request.getParameter("txjuros"));
                 capital = Double.parseDouble(request.getParameter("capital"));
                }
             }catch(Exception e){
             out.println("<span style = 'color: Red;'>Erro, insira valores.</span>");
             }
           
            //if para verificação de numeros negativos
            if(tempo != 0 && txjuros != 0 && capital != 0){
                if(tempo > 0 && txjuros > 0 && capital > 0){
            //cslculo da montante
            txjuros = txjuros / 100;
            double montante = capital * (1 + (txjuros * tempo));
            DecimalFormat df = new DecimalFormat("###,###.00");
            out.println("<table border = 1>");
            out.println("<tr>");
            out.println("<th> Meses: </th>");
            out.println("<td><span style = 'color: blue;'>"+tempo+"</span></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th> Taxa %: </th>");
            out.println("<td><span style = 'color: blue;'>"+txjuros+"</span></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th> Valor inicial: </th>");
            out.println("<td> <span style = 'color: blue;'>"+capital+"</span></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th> Montante: </th>");
            out.println("<td><span style = 'color:green;'>"+df.format(montante)+"</span></td>");
            out.println("</tr>");
            }else
                    out.println("<span style = 'color: Red;'>Erro, insira valores validos</span>");
            }
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