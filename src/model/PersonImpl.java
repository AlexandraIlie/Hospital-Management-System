package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.Person;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonImpl extends AbstractPersistentJDBCObject implements Person{

    private String firstname;
    private String lastname;
    private Date dateOfBirth;

    public PersonImpl(BasicDBService service, long idPerson, String firstname, String lastname, Date dateOfBirth) {
        super(service, idPerson);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String getLastname() {return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        assert(lastname!=null && lastname!="");
        this.lastname=lastname;

    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        assert(firstname!=null && firstname!="");
        this.firstname=firstname;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        Date date = new Date();
        assert(dateOfBirth==null || dateOfBirth.before(date));
        this.dateOfBirth=dateOfBirth;
    }

    @Override
    public long store(Connection connection) throws SQLException {
        Person person = null;
        try {
            //check if it persisitent and distinguish between insert and update
            PreparedStatement ps = connection.prepareStatement("insert into Person (idperson,firstname,lastname,dateOfBirth) values (nextval('person_seq'), ?,?,?)");

            ps.setString(1, person.getFirstname());
            ps.setString(2, person.getLastname());
            ps.setDate(3, (java.sql.Date) person.getDateOfBirth());

            // local variable to hold auto generated student id
            long personId = 0;

            // execute the insert statement, if success get the primary key value
            if (ps.executeUpdate() > 0) {

                // getGeneratedKeys() returns result set of keys that were auto generated
                // in our case idward column
                ResultSet generatedKeys = ps.getGeneratedKeys();

                // if resultset has data, get the primary key value
                // of last inserted record
                if (null != generatedKeys && generatedKeys.next()) {

                    // voila! we got student id which was generated from sequence
                    personId = generatedKeys.getLong(1);
                }
            }
            BasicDBServiceImpl.releaseConnection(connection);
            return personId;

        } catch(SQLException e){
            return 0;
        }

    }
}
