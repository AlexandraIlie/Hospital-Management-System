

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.BasicDBServiceFactory;
import de.hshn.mi.pdbg.basicservice.Patient;
import de.hshn.mi.pdbg.basicservice.Ward;
import model.PatientImpl;
import model.WardImpl;

import java.sql.*;

public class DBCreator {
    protected static final String[] SQL_DDL_STATEMENTS = {
            "DROP TABLE IF EXISTS person, patient, nurse, physician, hospitalstay, ward, department, finding, diagnosis, finding_diagnosis, examinationresult, "+
                    "examination, clinicalexamination, tehnicalexamination, laboratorytest;",
            "DROP SEQUENCE person_seq, ward_seq, hospital_seq;",
            "CREATE SEQUENCE PERSON_SEQ" +
                    "   START WITH 1" +
                    "   MINVALUE 1;",
            "CREATE SEQUENCE WARD_SEQ" +
                    "   START WITH 1" +
                    "   MINVALUE 1;",

            "CREATE TABLE Person( idPerson bigint, firstname varchar(255) NOT NULL, lastname varchar(255) NOT NULL, dateOfBirth date NOT NULL, " +
                    "PRIMARY KEY(idPerson));",

            "CREATE TABLE Patient (idPerson bigint, healthInsurance varchar(255), insuranceNumber varchar(255), " +
                    "PRIMARY KEY(idPerson), " +
                    "FOREIGN KEY (idPerson)  REFERENCES Person(idPerson) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE Department( idDepartment bigint, departmentName varchar(255) NOT NULL, " +
                    "PRIMARY KEY (idDepartment));",

            "CREATE TABLE Ward( idWard bigint, wardName varchar(255) NOT NULL, bedNr smallint, idDepartment bigint, " +
                    "PRIMARY KEY (idWard), " +
                    "FOREIGN KEY (idDepartment) REFERENCES Department(idDepartment) ON UPDATE CASCADE ON DELETE SET NULL);",

            "CREATE TABLE HospitalStay( idHospitalStay bigint, admissionDate date NOT NULL, dischargeDate date, patientIdPerson bigint NOT NULL, idWard bigint NOT NULL, " +
                    "PRIMARY KEY (idHospitalStay), FOREIGN KEY (patientIdPerson) REFERENCES Patient (idPerson) ON UPDATE CASCADE ON DELETE  CASCADE, " +
                    "FOREIGN KEY (idWard) REFERENCES Ward (idWard) ON UPDATE CASCADE ON DELETE SET NULL);",

            "CREATE TABLE Nurse( idPerson bigint, idNurseSupv bigint, idWard bigint NOT NULL, PRIMARY KEY (idPerson)," +
                    "FOREIGN KEY (idNurseSupv) REFERENCES Nurse(idPerson) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (idWard) REFERENCES Ward(idWard) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE Physician ( idPerson bigint, degree varchar(255) , idPhysicianSupv bigint, idWard bigint,idDepartment bigint NOT NULL, " +
                    "PRIMARY KEY(idPerson), " +
                    "FOREIGN KEY (idPerson) REFERENCES Person(idPerson) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (idPhysicianSupv) REFERENCES Physician(idPerson) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (idWard) REFERENCES Ward(idWard) ON UPDATE CASCADE ON DELETE SET NULL, " +
                    "FOREIGN KEY (idDepartment) REFERENCES Department(idDepartment) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE Finding(idFinding bigint,date date NOT NULL,summary varchar(255) ,physicianIdPerson bigint NOT NULL,patientIdPerson bigint NOT NULL," +
                    "PRIMARY KEY (idFinding)," +
                    "FOREIGN KEY (physicianIdPerson) REFERENCES Physician(idPerson) ON UPDATE CASCADE ON DELETE RESTRICT," +
                    "FOREIGN KEY (patientIdPerson) REFERENCES Patient(idPerson) ON UPDATE CASCADE ON DELETE CASCADE );",

            "CREATE TABLE Diagnosis(idDiagnosis bigint, icdCode varchar(255) NOT NULL, diagnosisText varchar(255)," +
                    "PRIMARY KEY (idDiagnosis) );",

            "CREATE TABLE Finding_Diagnosis(idFinding bigint,idDiagnosis bigint," +
                    "PRIMARY KEY( idFinding, idDiagnosis)," +
                    "FOREIGN KEY (idFinding) REFERENCES Finding (idFinding) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (idDiagnosis) REFERENCES Diagnosis (idDiagnosis) ON UPDATE CASCADE ON DELETE CASCADE );",

            "CREATE TABLE Examination( idExamination bigint, nameExamination varchar(255) NOT NULL, idExaminationSup bigint, " +
                    "PRIMARY KEY (idExamination), " +
                    "FOREIGN KEY (idExaminationSup) REFERENCES Examination(idExamination) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE ExaminationResult( idExaminationResult bigint, resultSummary varchar(255) NOT NULL, requirmentDate date NOT NULL, resultDate date, idFinding bigint, idExamination bigint NOT NULL, " +
                    "PRIMARY KEY (idExaminationResult), " +
                    "FOREIGN KEY (idFinding) REFERENCES Finding (idFinding)  ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (idExamination) REFERENCES Examination(idExamination) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE ClinicalExamination ( idExamination bigint, bodyRegion varchar(255) NOT NULL, " +
                    "PRIMARY KEY(idExamination), " +
                    "FOREIGN KEY (idExamination) REFERENCES Examination (idExamination) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE TehnicalExamination( idExamination bigint, " +
                    "PRIMARY KEY(idExamination), " +
                    "FOREIGN KEY (idExamination) REFERENCES Examination (idExamination) ON UPDATE CASCADE ON DELETE CASCADE);",

            "CREATE TABLE LaboratoryTest ( idExamination bigint, sampleType varchar(255) NOT NULL, standardValue varchar(255) NOT NULL, " +
                    "PRIMARY KEY(idExamination), " +
                    "FOREIGN KEY (idExamination) REFERENCES Examination (idExamination) ON UPDATE CASCADE ON DELETE CASCADE);",
    };

    public static void main ( String [ ] args ) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/HOSPITAL";
        new DBCreator().createDB(url, "postgres", "secret");

       BasicDBServiceFactory patDAOFactory = new BasicDBServiceFactory();
        BasicDBService patDAO = patDAOFactory.createBasicDBService();

       // System.out.println(patDAO.getWard());



//
		/*Employee foundEmp = eDAO.findById(1);
	foundEmp.setName("Cristina");
	eDAO.update(foundEmp);*/


    }






    //get connection and execute DDL statements
    public void createDB ( String jdbcURL , String user , String password )
            throws ClassNotFoundException , SQLException {
        Connection connection = getConnection(jdbcURL, user, password);
        PreparedStatement preparedStatement=null;

        for(int i=0;i<SQL_DDL_STATEMENTS .length;i++) {
            preparedStatement = connection.prepareStatement(SQL_DDL_STATEMENTS[i]);
            preparedStatement.executeUpdate();
        }
    }

    // createa connection to DB
    public static Connection getConnection (String jdbcURL , String user, String password )
            throws ClassNotFoundException , SQLException {
        Connection conPSQL=null;
        Class.forName("org.postgresql.Driver");
        conPSQL= DriverManager.getConnection(jdbcURL,user,password);
        return conPSQL;
    }


}
