package transferobject;

/**
 * Student Name: Mohammadhassan Yeganeshenas
 * Student Number: 041086643
 * Course & Section #: 22S_CST8288_031
 * Declaration: Assignment 01
 * This is my own original work and is free from Plagiarism.
 */

/**
 * Transfer Object (TO) representing a Peer Tutor.
 */
public class PeerTutor {

    // Fields for a peer tutor.
    private int peerTutorID;
    private String lastName;
    private String firstName;

    /**
     * Gets the Peer Tutor ID.
     *
     * @return The Peer Tutor ID.
     */
    public int getPeerTutorID() {
        return peerTutorID;
    }

    /**
     * Sets the Peer Tutor ID.
     *
     * @param peerTutorID The Peer Tutor ID to set.
     */
    public void setPeerTutorID(int peerTutorID) {
        this.peerTutorID = peerTutorID;
    }

    /**
     * Gets the last name of the Peer Tutor.
     *
     * @return The last name of the Peer Tutor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the Peer Tutor.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of the Peer Tutor.
     *
     * @return The first name of the Peer Tutor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the Peer Tutor.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
