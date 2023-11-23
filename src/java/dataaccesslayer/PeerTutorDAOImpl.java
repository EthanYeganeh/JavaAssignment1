package dataaccesslayer;

/**
 * Student Name: Mohammadhassan Yeganeshenas Student Number: 041086643 Course &
 * Section #: 22S_CST8288_031 Declaration: Assignment 01 This is my own original
 * work and is free from Plagiarism.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobject.PeerTutor;

/**
 * Implementation of the PeerTutorDAO interface for database operations.
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {

    private Connection connection = null;

    /**
     * Constructor to initialize a connection to the database.
     *
     * @throws ClassNotFoundException If there's an issue with class loading.
     */
    public PeerTutorDAOImpl() throws ClassNotFoundException {
        DataSource dataSource = new DataSource();
        connection = dataSource.createConnection();
    }

    private int getStudentId(PeerTutor peerTutor) {
        try {
            String query = "SELECT studentid FROM student WHERE lastname = ? AND firstname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(2, peerTutor.getFirstName());
                preparedStatement.setString(1, peerTutor.getLastName());
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next() ? resultSet.getInt("studentid") : -1;
                } finally {
                    resultSet.close();
                }
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    private int getPeerTutorId(PeerTutor peerTutor) {
        try {
            String query = "SELECT peertutorid FROM peertutor WHERE lastname = ? AND firstname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(2, peerTutor.getFirstName());
                preparedStatement.setString(1, peerTutor.getLastName());
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next() ? resultSet.getInt("peertutorid") : -1;
                } finally {
                    resultSet.close();
                }
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        try {
            String query = "SELECT * FROM PeerTutor WHERE FirstName = ? AND LastName = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, peerTutor.getFirstName());
                preparedStatement.setString(2, peerTutor.getLastName());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If a row is returned, the peer tutor is registered
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isCourseValid(String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        try {
            String query = "SELECT * FROM Course WHERE CourseCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, courseCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next(); // If a row is returned, the course is valid
                } finally {
                    resultSet.close();
                }
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

//    SELECT FROM peertutor.peertutor where lastname = "Mouse" and Firstname = "Minnie;"
//    SSELECT StudentID from peertutoe.student where lastname = "Mouse" and Firstname = "Minnie;"
//    SELECT Course_CourseCode From StudentCourse where Student_StudentID = 901601;
    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        try {
            // Assuming 'connection' is an instance variable representing your database connection
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM StudentCourse WHERE Student_StudentID = ? AND Course_CourseCode = ?");
            try {
                int studentID = getStudentId(peerTutor);

                preparedStatement.setInt(1, studentID);
                preparedStatement.setString(2, courseCode);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        try {
            String query = "SELECT GradeCode FROM Grade WHERE Student_StudentID = ? AND Course_CourseCode = ?";
            try (PreparedStatement prep = connection.prepareStatement(query)) {
                int studentid = getStudentId(peerTutor);
                prep.setInt(1, studentid);
                prep.setString(2, courseCode);

                try (ResultSet resultSet = prep.executeQuery()) {
                    return resultSet.next() ? resultSet.getString("GradeCode") : null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        try {
            String query = "SELECT * FROM PeerTutorCourse WHERE PeerTutor_PeerTutorID = ? AND Course_CourseCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                int peerTutorId = getPeerTutorId(peerTutor);
                preparedStatement.setInt(1, peerTutorId);
                preparedStatement.setString(2, courseCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next(); // If a row is returned, the course is already assigned
                } finally {
                    resultSet.close();
                }
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        try {
            String query = "INSERT INTO PeerTutorCourse(PeerTutor_PeerTutorID, course_CourseCode) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                int peerTutorId = getPeerTutorId(peerTutor);
                preparedStatement.setInt(1, peerTutorId);
                preparedStatement.setString(2, courseCode);
                preparedStatement.executeUpdate();
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

      @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //        Do not forget to close the resources used inside this method.
        List<PeerTutor> peerTutors = new ArrayList<>();
        try {
            String query ="SELECT pt.PeerTutorID, pt.LastName, pt.FirstName FROM PeerTutor pt "
                    + "INNER JOIN PeerTutorCourse ptc ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID WHERE ptc.Course_CourseCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, courseCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        PeerTutor peerTutor = new PeerTutor();
                        peerTutor.setPeerTutorID(resultSet.getInt("PeerTutorID"));
                        peerTutor.setFirstName(resultSet.getString("FirstName"));
                        peerTutor.setLastName(resultSet.getString("LastName"));
                        // Set other attributes as needed
                        peerTutors.add(peerTutor);
                    }
                } finally {
                    resultSet.close();
                }
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return peerTutors;
    }}
