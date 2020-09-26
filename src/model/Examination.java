package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.SQLException;

public class Examination extends AbstractPersistentJDBCObject {
    private String nameExamination;
    private long idExaminationSup;

    protected Examination(BasicDBService service, long idExamination) {
        super(service, idExamination);
    }

    public String getNameExamination() {
        return nameExamination;
    }

    public void setNameExamination(String nameExamination) {
        this.nameExamination = nameExamination;
    }

    public long getIdExaminationSup() {
        return idExaminationSup;
    }

    public void setIdExaminationSup(long idExaminationSup) {
        this.idExaminationSup = idExaminationSup;
    }

    @Override
    public String toString() {
        return "Examination{" +
                ", nameExamination='" + nameExamination + '\'' +
                ", idExaminationSup=" + idExaminationSup +
                '}';
    }

    @Override
    public long store(Connection connection) throws SQLException {
        return 0;
    }
}
