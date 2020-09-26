package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.Ward;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;
import de.hshn.mi.pdbg.exception.StoreException;


import java.sql.*;

public class WardImpl extends AbstractPersistentJDBCObject implements Ward {

    private String name;
    private int numberOfBeds;

    public WardImpl(BasicDBService service, long idWard, String name, int numberOfBeds) {
        super(service, idWard);
        this.name = name;
        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    @Override
    public void setNumberOfBeds(int numberOfBeds) {
        assert(numberOfBeds>0);
        this.numberOfBeds=numberOfBeds;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        assert(name!=null && name!="");
        this.name=name;
    }

    /**
     * Override this method in order to store an instance of this class using
     * JDBC. <p/> This method should be invoked via your implementation of
     * {//@link BasicDBService#store(PersistentObject)}.
     *
     * @param connection
     *            The JDBC connection to be used for storing (should be open an
     *            usable).
     * @return The persistent object ID of this (stored) object.
     * @throws SQLException
     *             In case of any SQL/JDBC problems.
     */
    @Override
    public long store(Connection connection) {
        try {
            //PreparedStatement ps2 = connection.prepareStatement("insert into ward(wardname,bednr) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            //check if it persisitent and distinguish between insert and update
            PreparedStatement ps = connection.prepareStatement("insert into Ward (idward,wardname,bednr) values (nextval('ward_seq'), ?,?)");

            ps.setString(1, this.getName());
            ps.setInt(2, this.getNumberOfBeds());

            //fetch the obj that was stored and get the id from it
            /*extract nextval from seq si sa o punem intr-un long si sa pun intr-un statement
                    pt sequence call
                    daca e bagat in mysql trebuie salvat in java
                    this.object id is the id from the data base
             */


            // local variable to hold auto generated student id
            long wardId = 0;
            ps.executeUpdate();

                // getGeneratedKeys() returns result set of keys that were auto generated
                // in our case idward column
            ResultSet generatedKeys = ps.getGeneratedKeys();

                // if resultset has data, get the primary key value
                // of last inserted record
            if (null != generatedKeys && generatedKeys.next()) {
                    // voila! we got student id which was generated from sequence
                    wardId = generatedKeys.getLong(1);

                }

            return wardId;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return 0;
    }

        @Override
    public String toString() {
        return "WardImpl{" +
                ", name='" + name + '\'' +
                ", NumberOfBeds=" + numberOfBeds +
                '}';
    }
}
