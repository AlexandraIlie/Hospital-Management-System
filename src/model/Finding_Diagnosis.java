package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.jdbc.AbstractPersistentJDBCObject;

import java.sql.Connection;
import java.sql.SQLException;

public class Finding_Diagnosis {
    private long idFinding;
    private long idDiagnosis;

    public Finding_Diagnosis(long idFinding, long idDiagnosis) {
        this.idFinding = idFinding;
        this.idDiagnosis = idDiagnosis;
    }

    public long getIdFinding() {
        return idFinding;
    }

    public void setIdFinding(long idFinding) {
        this.idFinding = idFinding;
    }

    public long getIdDiagnosis() {
        return idDiagnosis;
    }

    public void setIdDiagnosis(long idDiagnosis) {
        this.idDiagnosis = idDiagnosis;
    }

    @Override
    public String toString() {
        return "Finding_Diagnosis{" +
                "idFinding=" + idFinding +
                ", idDiagnosis=" + idDiagnosis +
                '}';
    }

}
