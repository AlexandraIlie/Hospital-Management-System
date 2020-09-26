package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.SQLException;

public class Diagnosis extends AbstractPersistentJDBCObject {
    private String icdCode;
    private String diagnosisText;

    public Diagnosis(BasicDBService service, long idDiagnosis, String icdCode, String diagnosisText) {
        super(service, idDiagnosis);
        this.icdCode = icdCode;
        this.diagnosisText = diagnosisText;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getDiagnosisText() {
        return diagnosisText;
    }

    public void setDiagnosisText(String diagnosisText) {
        this.diagnosisText = diagnosisText;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "icdCode='" + icdCode + '\'' +
                ", diagnosisText='" + diagnosisText + '\'' +
                '}';
    }

    @Override
    public long store(Connection connection) throws SQLException {
        return 0;
    }
}
