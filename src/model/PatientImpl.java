package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.basicservice.HospitalStay;
import de.hshn.mi.pdbg.basicservice.Patient;
import de.hshn.mi.pdbg.exception.FetchException;

import java.util.Date;
import java.util.Set;

public class PatientImpl extends PersonImpl implements Patient {

    private String healthInsurance;
    private String insuranceNumber;
    private Set<HospitalStay> hospitalStays;

    public PatientImpl(BasicDBService service, long idPerson, String firstname, String lastname, Date dateOfBirth, String healthInsurance, String insuranceNumber) {
        super(service, idPerson, firstname, lastname, dateOfBirth);
        this.healthInsurance = healthInsurance;
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance=healthInsurance;
    }

    @Override
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber=insuranceNumber;
    }

    @Override
    public String getHealthInsurance() {
        return healthInsurance;
    }

    @Override
    public String getInsuranceNumber() {
        return insuranceNumber;
    }


    @Override
    public Set<HospitalStay> getHospitalStays() throws FetchException{

        return hospitalStays;
    }
}

