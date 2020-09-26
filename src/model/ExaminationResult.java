package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ExaminationResult extends AbstractPersistentJDBCObject {
    private String resultSummary;
    private Date requirmentDate;
    private Date resultDate;

    public ExaminationResult(BasicDBService service, long idExaminationResult, String resultSummary, Date requirmentDate, Date resultDate) {
        super(service, idExaminationResult);
        this.resultSummary = resultSummary;
        this.requirmentDate = requirmentDate;
        this.resultDate = resultDate;
    }

    public String getResultSummary() {
        return resultSummary;
    }

    public void setResultSummary(String resultSummary) {
        this.resultSummary = resultSummary;
    }

    public Date getRequirmentDate() {
        return requirmentDate;
    }

    public void setRequirmentDate(Date requirmentDate) {
        this.requirmentDate = requirmentDate;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    @Override
    public String toString() {
        return "ExaminationResult{" +
                ", resultSummary='" + resultSummary + '\'' +
                ", requirmentDate=" + requirmentDate +
                ", resultDate=" + resultDate +
                '}';
    }

    @Override
    public long store(Connection connection) throws SQLException {
        return 0;
    }
}
