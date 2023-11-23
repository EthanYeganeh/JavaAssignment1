package dataaccesslayer;

/**
 * Student Name: Mohammadhassan Yeganeshenas
 * Student Number: 041086643
 * Course & Section #: 22S_CST8288_031
 * Declaration: Assignment 01
 * This is my own original work and is free from Plagiarism.
 */

import java.util.List;
import transferobject.PeerTutor;

/**
 * Interface for PeerTutor Data Access Object (DAO).
 */
public interface PeerTutorDAO {

    /**
     * Checks if a PeerTutor is registered.
     *
     * @param peerTutor The PeerTutor object to check for registration.
     * @return True if the PeerTutor is registered; otherwise, false.
     */
    boolean isPeerTutorRegistered(PeerTutor peerTutor);

    /**
     * Checks if a course is valid.
     *
     * @param courseCode The code of the course to check.
     * @return True if the course is valid; otherwise, false.
     */
    boolean isCourseValid(String courseCode);

    /**
     * Checks if a PeerTutor has taken a specific course.
     *
     * @param peerTutor The PeerTutor object to check for course history.
     * @param courseCode The code of the course to check.
     * @return True if the PeerTutor has taken the course; otherwise, false.
     */
    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);

    /**
     * Gets the letter grade of a PeerTutor for a specific course.
     *
     * @param peerTutor The PeerTutor object to get the grade for.
     * @param courseCode The code of the course to get the grade for.
     * @return The letter grade of the PeerTutor for the specified course.
     */
    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);

    /**
     * Checks if a course is already assigned to a PeerTutor.
     *
     * @param peerTutor The PeerTutor object to check for course assignment.
     * @param courseCode The code of the course to check.
     * @return True if the course is already assigned; otherwise, false.
     */
    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);

    /**
     * Assigns a course to a PeerTutor.
     *
     * @param peerTutor The PeerTutor object to assign the course to.
     * @param courseCode The code of the course to assign.
     */
    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode);

    /**
     * Gets a list of all PeerTutors for a specific course.
     *
     * @param courseCode The code of the course to get PeerTutors for.
     * @return List of PeerTutor objects associated with the specified course.
     */
    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}
