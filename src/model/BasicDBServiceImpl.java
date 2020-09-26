package model;

import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.HospitalStay;
import de.hshn.mi.pdbg.basicservice.Patient;
import de.hshn.mi.pdbg.basicservice.Ward;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;
import de.hshn.mi.pdbg.exception.FetchException;
import de.hshn.mi.pdbg.exception.StoreException;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicDBServiceImpl implements BasicDBService {

    private Connection connection;

    public BasicDBServiceImpl() throws SQLException {
        connection = getConnection();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/HOSPITAL", "postgres", "secret");
    }

    public static void releaseConnection(Connection conn) throws SQLException {
        conn.close();
    }

    @Override
    public Patient createPatient(String lastname, String firstname) {
        Patient patient;
        assert (lastname!=null && lastname!="");
        assert(firstname!=null && firstname!="");
        patient = new PatientImpl(this, PatientImpl.INVALID_OBJECT_ID, firstname, lastname,null,null,null);
        return patient;
    }

    /**
     * @param name         must not be {@code null} and must not be <code>""</code>
     * @param numberOfBeds must not be negative
     * @return A {@link Ward} object.
     * @throws AssertionError Thrown if a given parameter value is invalid.
     */
    @Override
    public Ward createWard(String name, int numberOfBeds) {
        Ward ward = null;
        assert (name != null && name != "");
        assert (numberOfBeds >= 0);
        ward = new WardImpl(this, WardImpl.INVALID_OBJECT_ID, name, numberOfBeds);
        return ward;
    }

    /**
     * @param p             must not be {@code null}.
     * @param s             must not be {@code null}.
     * @param admissionDate must must not be {@code null}.
     * @return A {@link HospitalStay} object.
     * @throws AssertionError Thrown if a given parameter value is invalid.
     */
    @Override
    public HospitalStay createHospitalStay(Patient p, Ward s, Date admissionDate) {
        HospitalStay hospitalStay=null;
        assert(p!=null && s!=null && admissionDate!=null);
        hospitalStay = new HospitalStayImpl(this,HospitalStayImpl.INVALID_OBJECT_ID,admissionDate,null);
        return hospitalStay;
    }

    /**
     * Removes a hospital stay from the database.
     *
     * @param hospitalStayID
     *            the object id of the stay to be removed. It must be greater
     *            than zero ({@code 0}). It must not be
     *            {@link PersistentObject#INVALID_OBJECT_ID}
     *
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     * @throws StoreException
     *             Thrown if an error occurred while removing the object.
     */
    @Override
    public void removeHospitalStay(long hospitalStayID) {
        assert(hospitalStayID>0 && hospitalStayID!=WardImpl.INVALID_OBJECT_ID);
        try
        {
            PreparedStatement ps = connection.prepareStatement("delete from hospitalstay where idhospitalstay = ?");
            ps.setLong(1, hospitalStayID);
            ps.executeUpdate();
            releaseConnection(connection);

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(StoreException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Returns a list of {@link Patient} objects that match a set of search
     * criteria. When searching via first name and last name, one may use the
     * SQL wildcards ("%", "*", "_") in the search string.
     * <p>
     * <code>startDate</code> and <code>endDate</code> define a period in which
     * the patient was born.
     * </p>
     * A {@code null} value for one of these parameters indicates that the
     * related search criterion will be ignored.
     *
     * @param lastname  May be {@code null}. If so, the value will be ignored as
     *                  a search criterion.
     * @param firstname May be {@code null}. If so, the value will be ignored as
     *                  a search criterion.
     * @param startDate The start date of the time period considered for valid patient
     *                  birth dates during search. Every patient born after or at this
     *                  date is considered for the search result. The value may be
     *                  {@code null}. If so, there is no lower bound for
     *                  valid birth dates during search.
     * @param endDate   The end date of the time period considered for valid patient
     *                  birth dates during search. Every patient born before or at
     *                  this date is considered for the search result. The value may
     *                  be {@code null}. If so, there is no upper bound for valid
     *                  birth dates during search.
     * @return A list of matching {@link Patient} objects is returned. It may be
     * empty.
     * @throws FetchException Thrown if an error occurred while fetching objects.
     */
    @Override
    public List<Patient> getPatients(String lastname, String firstname, Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            System.out.print("ignore search");

        try {
            Connection conn = getConnection();
            if (lastname != null) {
                PreparedStatement ps1 = conn.prepareStatement("select * from patient where lastname=?");
                ps1.setString(1, lastname);
                ResultSet rs = ps1.executeQuery();
                List<Patient> patientList = new ArrayList<>();
                while (rs.next()) {
                    patientList.add(new PatientImpl(
                            this,
                            rs.getLong("idPerson"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getDate("dateOfBirth"),
                            rs.getString("healthInsurance"),
                            rs.getString("incuranceNumber")
                    ));
                }
                releaseConnection(conn);
                return patientList;
            }
            if (firstname != null) {
                PreparedStatement ps1 = conn.prepareStatement("select * from patient where firstname=?");
                ps1.setString(1, firstname);
                ResultSet rs = ps1.executeQuery();
                List<Patient> patientList = new ArrayList<>();
                while (rs.next()) {
                    patientList.add(new PatientImpl(
                            this,
                            rs.getLong("idPerson"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getDate("dateOfBirth"),
                            rs.getString("healthInsurance"),
                            rs.getString("incuranceNumber")
                    ));
                }
                releaseConnection(conn);
                return patientList;
            }
        } catch (SQLException e) {
            return null;
        }
        catch(StoreException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @param patientID
     *            The identifier (objectID) of the object to be fetched. It must
     *            be greater than zero ({@code 0}). It must not be
     *            {@link PersistentObject#INVALID_OBJECT_ID}
     * @return The {@link Patient} object or {@code null}, if none was
     *         found with the given <code>patientID</code>.
     * @throws FetchException
     *             Thrown if an error occurred while fetching an object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    @Override
    public Patient getPatient(long patientID) {
        assert(patientID>0 && patientID!=PatientImpl.INVALID_OBJECT_ID);
        Patient patient=null;
        try
        {
            PreparedStatement ps = connection.prepareStatement("select * from patient where idperson = ?");
            ps.setLong(1,patientID);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                patient = new PatientImpl(
                        this,
                        rs.getLong("idperson"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("dateofbirth"),
                        rs.getString("healthinsurance"),
                        rs.getString("insurancenumber")
                );
            }
            releaseConnection(connection);
            return patient;
        }
        catch(SQLException e)
        {
            return null;
        }
        catch(FetchException e)
        {
            return null;
        }

    }

    @Override
    public List<Ward> getWards() {
        try
        {
            PreparedStatement ps = connection.prepareStatement("select * from ward");
            ResultSet rs = ps.executeQuery();
            List<Ward> wardList = new ArrayList<>();

            while(rs.next())
            {
                wardList.add(new WardImpl(this,
                        rs.getInt("idward"),
                        rs.getString("name"),
                        rs.getInt("numberofbeds")));
            }
            releaseConnection(connection);
            return wardList;
        }
        catch(SQLException e)
        {
            return null;
        }
        catch(FetchException e)
        {
            return null;
        }
    }
    /**
     * @param wardID
     *            The identifier (objectID) of the object to be returned. It
     *            must be greater than zero ({@code 0}). It must not be
     *            {@link PersistentObject#INVALID_OBJECT_ID}
     * @return The {@link Ward} object or {@code null}, if none was found
     *         with the given <code>wardID</code>.
     * @throws FetchException
     *             Thrown if an error occurred while fetching an object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    @Override
    public Ward getWard(long wardID) {
        assert (wardID>0 && wardID!=WardImpl.INVALID_OBJECT_ID);
        Ward ward = null;
        try
        {
            PreparedStatement ps = connection.prepareStatement("select * from ward where idward = ?");
            ps.setLong(1, wardID);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                ward = new WardImpl(
                        this,
                        wardID,
                        rs.getString("wardname"),
                        rs.getInt("bednr")
                );

            }
            releaseConnection(connection);
            return ward;
        }
        catch(SQLException e)
        {
            return null;
        }
        catch(FetchException e)
        {
            System.out.println( "Error occurred while fetching an object." );
        }
        return null;
    }

    @Override
    public List<HospitalStay> getHospitalStays(long patientID) {
        assert(patientID>0 && patientID!= WardImpl.INVALID_OBJECT_ID);
        try
        {
            PreparedStatement ps = connection.prepareStatement("select * from hospitalStay");
            ResultSet rs = ps.executeQuery();
            List<HospitalStay> hospitalStayList = new ArrayList<>();

            while(rs.next())
            {
                hospitalStayList.add(new HospitalStayImpl(this,
                        rs.getLong("idHospitalstay"),
                        rs.getDate("admissiondate"),
                        rs.getDate("dischargedate")));
            }
            releaseConnection(connection);
            return hospitalStayList;
        }
        catch(SQLException e)
        {
            return null;
        }
        catch(FetchException e)
        {
            return null;
        }
    }

    @Override
    public List<HospitalStay> getHospitalStays(long patientID, Date startDate, Date endDate) {
        return null;
    }

    /**
     * Persists a given {@link PersistentObject} (within a database).
     * Implementations must distinguish between INSERT/UPDATE operations.
     *
     * @param object
     *            The object to be stored. It must not be {@code null}. Has
     *            to be persistent (objectID set) in case of a successful store operation.
     *
     * @return The primary key as given by the database system.
     * @throws StoreException
     *             Thrown if errors occurred while persisting an object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    @Override
    public long store(PersistentObject object) {
        //check if istance of the classes than cast
        //if abstract persistentjdbc object->invoke store
        //distinguisch between insert or update

        //if it is not persistent then I try to store it (insert query), if after store is still not persistent then exception
        assert(object != null);
        try {
            if(object instanceof AbstractPersistentJDBCObject)
                return ((AbstractPersistentJDBCObject) object).store(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new StoreException();
    }

    @Override
    public void close() {

    }
}
