package de.hshn.mi.pdbg.enhanced;

/**
 * {@link LaboratoryTest} describes an examination
 */
public interface LaboratoryTest extends Examination {

    String getSampleType();

    /**
     * @param sampleType must not be {@code null} and must not be <code>""</code>
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setSampleType(String sampleType);

    String getStandardValue();

    /**
     * @param standardValue must not be {@code null} and must not be <code>""</code>
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setStandardValue(String standardValue);




}
