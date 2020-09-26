package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


public class Finding extends AbstractPersistentJDBCObject {
    private Date date;
    private String summary;

    public Finding(BasicDBService service, long idFinding, Date date, String summary) {
        super(service, idFinding);
        this.date = date;
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Finding{" +
                ", date=" + date +
                ", summary='" + summary + '\'' +
                '}';
    }

    @Override
    public long store(Connection connection) throws SQLException {
        return 0;
    }
}
