package businesslayer;
/**
 * Student Name: Mohammadhassan Yeganeshenas
 * Student Number: 041086643
 * Course & Section #: 22S_CST8288_031
 * Declaration: Assignment 01
 * This is my own original work and is free from Plagiarism.
 */

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;
import transferobject.PeerTutor;

/**
 * Business logic class for managing PeerTutor operations.
 */
public class PeerTutorBusinessLogic {
    
    private PeerTutorDAO peerTutorDAO = null;

    /**
     * Constructor to instantiate PeerTutorBusinessLogic with a specific PeerTutor.
     *
     * @param peerTutor The PeerTutor object to associate with the business logic.
     * @throws ClassNotFoundException If there's an issue with class loading.
     */
    public PeerTutorBusinessLogic(PeerTutor peerTutor) throws ClassNotFoundException  {
        peerTutorDAO = new PeerTutorDAOImpl();
    }

    /**
     * Default constructor. Not supported.
     *
     * @throws UnsupportedOperationException If the default constructor is called.
     */
    public PeerTutorBusinessLogic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if a PeerTutor is registered.
     *
     * @param peerTutor The PeerTutor object to check for registration.
     * @return True if the PeerTutor is registered; otherwise, false.
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }

    /**
     * Checks if a course is valid.
     *
     * @param courseCode The code of the course to check.
     * @return True if the course is valid; otherwise, false.
     */
    public boolean isCourseValid(String courseCode) {
        return peerTutorDAO.isCourseValid(courseCode);
    }

    /**
     * Checks if a PeerTutor has taken a specific course.
     *
     * @param peerTutor The PeerTutor object to check for course history.
     * @param courseCode The code of the course to check.
     * @return True if the PeerTutor has taken the course; otherwise, false.
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }

    /**
     * Gets the letter grade of a PeerTutor for a specific course.
     *
     * @param peerTutor The PeerTutor object to get the grade for.
     * @param courseCode The code of the course to get the grade for.
     * @return The letter grade of the PeerTutor for the specified course.
     */
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }

    /**
     * Checks if a course is already assigned to a PeerTutor.
     *
     * @param peerTutor The PeerTutor object to check for course assignment.
     * @param courseCode The code of the course to check.
     * @return True if the course is already assigned; otherwise, false.
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }

    /**
     * Assigns a course to a PeerTutor if not already assigned.
     *
     * @param peerTutor The PeerTutor object to assign the course to.
     * @param courseCode The code of the course to assign.
     * @return True if the course is successfully assigned; otherwise, false.
     */
    public boolean assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        if (!isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode)) {
            peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
            return true;
        }
        return false;
    }

    /**
     * Gets a list of all PeerTutors for a specific course.
     *
     * @param courseCode The code of the course to get PeerTutors for.
     * @return List of PeerTutor objects associated with the specified course.
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
}
