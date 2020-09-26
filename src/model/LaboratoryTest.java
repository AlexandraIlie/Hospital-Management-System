package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;

public class LaboratoryTest extends Examination {
    private String sampleType;
    private String standardValue;

    public LaboratoryTest(BasicDBService service, long idExamination, String sampleType, String standardValue) {
        super(service, idExamination);
        this.sampleType = sampleType;
        this.standardValue = standardValue;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    @Override
    public String toString() {
        return "LaboratoryTest{" +
                "sampleType='" + sampleType + '\'' +
                ", standardValue='" + standardValue + '\'' +
                '}';
    }
}
