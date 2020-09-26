package model;

import de.hshn.mi.pdbg.basicservice.BasicDBService;

public class ClinicalExamination extends Examination {
    private String bodyRegion;

    public ClinicalExamination(BasicDBService service, long idExamination, String bodyRegion) {
        super(service, idExamination);
        this.bodyRegion = bodyRegion;
    }

    public String getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    @Override
    public String toString() {
        return "ClinicalExamination{" +
                "bodyRegion='" + bodyRegion + '\'' +
                '}';
    }
}

