package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.HospitalStay;
import de.hshn.mi.pdbg.basicservice.Patient;
import de.hshn.mi.pdbg.basicservice.Ward;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;
import de.hshn.mi.pdbg.exception.FetchException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HospitalStayImpl extends AbstractPersistentJDBCObject implements HospitalStay  {

    private Date admissionDate;
    private Date dischargeDate;
    private Ward ward;
    private Patient patient;

    public HospitalStayImpl(BasicDBService service, long idHospitalStay, Date admissionDate, Date dischargeDate) {
        super(service, idHospitalStay);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }


    @Override
    public Date getAdmissionDate() {
        return admissionDate;
    }
    /**
     * @param admissionDate
     *            An admission date is only valid if it is a date before the
     *            discharge date. It must not be {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    @Override
    public void setAdmissionDate(Date admissionDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);
        assert(admissionDate!=null && admissionDate.before(date));
       // if(admissionDate!=null && admissionDate.before(date))
        this.admissionDate=admissionDate;
    }

    @Override
    public Date getDischargeDate() {
        return dischargeDate;
    }
    /**
     * @param dischargeDate
     *            A discharge date is only valid if it is a date after the
     *            admission date or {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    @Override
    public void setDischargeDate(Date dischargeDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);
        assert(dischargeDate!=null && dischargeDate.before(date));
        this.dischargeDate=dischargeDate;
    }


    @Override
    public Ward getWard() throws FetchException {
        return ward;
    }

    @Override
    public void setWard(Ward ward) {
        assert(ward!=null);
        this.ward=ward;
    }

    @Override
    public Patient getPatient()throws FetchException {
        return patient;
    }

    @Override
    public long store(Connection connection) throws SQLException {
        /*check if it's an abstract jdbc obj

         */

        HospitalStay hospitalStay = null;
        try {

            PreparedStatement ps = connection.prepareStatement("insert into HospitalStay (idHospitalStay,admissionDate,dischargeDate) values (nextval('person_seq'), ?,?)");

            ps.setDate(1, (java.sql.Date) hospitalStay.getAdmissionDate());
            ps.setDate(2, (java.sql.Date) hospitalStay.getDischargeDate());

            long hospitalStayId = 0;


            if (ps.executeUpdate() > 0) {

                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (null != generatedKeys && generatedKeys.next()) {

                    hospitalStayId = generatedKeys.getLong(1);
                }
            }
            BasicDBServiceImpl.releaseConnection(connection);
            return hospitalStayId;

        } catch (SQLException e) {
            return 0;
        }
    }
}

