package viewlayer;

/**
 * Student Name: Mohammadhassan Yeganeshenas Student Number: 041086643 Course &
 * Section #: 22S_CST8288_031 Declaration: Assignment 01 This is my own original
 * work and is free from Plagiarism.
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslayer.PeerTutorBusinessLogic;
import transferobject.PeerTutor;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for handling PeerTutor-related requests.
 */
public class PeerTutorServlet extends HttpServlet {

    private PeerTutor peertutor = new PeerTutor();
    private String courseCode;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PeerTutorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>PeerTutorServlet at " + request.getContextPath() + "</h1>");

            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            courseCode = request.getParameter("coursecode");

            peertutor.setFirstName(firstName);
            peertutor.setLastName(lastName);

            PeerTutorBusinessLogic businessLogic = new PeerTutorBusinessLogic(peertutor);

            try {
                if (!businessLogic.isPeerTutorRegistered(peertutor)) {
                    out.println("<p>Error: Case #1 - The person is not registered as a peer tutor.</p>");
                } else if (!businessLogic.isCourseValid(courseCode)) {
                    out.println("<p>Error: Case #2 - The course is not valid.</p>");
                } else if (!businessLogic.hasPeerTutorTakenCourse(peertutor, courseCode)) {
                    out.println("<p>Error: Case #3 - The peer tutor has not taken the course.</p>");
                } else {
                    String letterGrade = businessLogic.getPeerTutorLetterGradeForCourse(peertutor, courseCode);
                    if (letterGrade == null || !isValidLetterGrade(letterGrade)) {
                        out.println("<p>Error: Case #4 - The letter grade obtained by the peer tutor for the course is not sufficient.</p>");
                    } else if (businessLogic.isCourseAlreadyAssignedToPeerTutor(peertutor, courseCode)) {
                        out.println("<p>Error: Case #5 - The peer tutor is already assigned to the course.</p>");
                    } else {
                        businessLogic.assignCourseToPeerTutor(peertutor, courseCode);

                        List<PeerTutor> assignedTutors = businessLogic.getAllPeerTutorsForCourse(courseCode);
                        if (assignedTutors != null && !assignedTutors.isEmpty()) {
                            out.println("<p>Success: Case #6 - The course is assigned to the peer tutor.</p>");
                            out.println("<table border='1'>");
                            out.println("<tr><th>Peer Tutor ID</th><th>First Name</th><th>Last Name</th></tr>");
                            for (PeerTutor assignedTutor : assignedTutors) {
                                out.println("<tr>");
                                out.println("<td>" + assignedTutor.getPeerTutorID() + "</td>");
                                out.println("<td>" + assignedTutor.getFirstName() + "</td>");
                                out.println("<td>" + assignedTutor.getLastName() + "</td>");
                                out.println("</tr>");
                            }
                            out.println("</table>");
                        } else {
                            out.println("<p>Error: Unable to retrieve assigned peer tutors.</p>");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeerTutorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Checks if a letter grade is valid.
     *
     * @param letterGrade The letter grade to validate.
     * @return True if the letter grade is valid; otherwise, false.
     */
    private boolean isValidLetterGrade(String letterGrade) {
        // Add your logic to validate the letter grade (e.g., A, B, C, etc.)
        // For now, assume any non-null letter grade is valid
        return letterGrade != null && (letterGrade.equals("A+") || letterGrade.equals("A") || letterGrade.equals("A-"));
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
