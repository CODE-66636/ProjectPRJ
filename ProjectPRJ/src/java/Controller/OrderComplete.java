/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.RentalOrder;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="OrderComplete", urlPatterns={"/OrderComplete"})
public class OrderComplete extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        RentalOrder ro = (RentalOrder) session.getAttribute("lou");
        int vehicleID=Integer.parseInt(request.getParameter("vehicleID"));
        LocalDate pickupDate = LocalDate.parse(request.getParameter("pickup_date"));
        LocalDate returnDate = LocalDate.parse(request.getParameter("return_date"));
        String totalAmount =  request.getParameter("total_amount");
        totalAmount=totalAmount.substring(0, totalAmount.length()-2);
        dao.updateRentalOrder(ro.getOrderId(), pickupDate, returnDate, totalAmount, "Pending", Boolean.FALSE, vehicleID);
        request.setAttribute("vehicleID", vehicleID);
        request.setAttribute("pickup_date", pickupDate);
        request.setAttribute("vehicle", dao.getVehicleById(vehicleID));
        request.setAttribute("return_date", returnDate);
        request.setAttribute("total_amount",totalAmount);
        request.getRequestDispatcher("order_complete.jsp").forward(request, response);
    } 
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
