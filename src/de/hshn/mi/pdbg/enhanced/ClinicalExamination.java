package de.hshn.mi.pdbg.enhanced;

/**
 * {@link ClinicalExamination} describes an examination
 */
public interface ClinicalExamination extends Examination{

    String getBodyRegion();

    /**
     * @param bodyRegion must not be {@code null} and must not be <code>""</code>
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setBodyRegion(String bodyRegion);



}
