package de.hshn.mi.pdbg.enhanced;

import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.basicservice.Patient;
import de.hshn.mi.pdbg.exception.FetchException;

import java.util.Date;

/**
 * {@link Finding} describes a finding of a patient.
 */
public interface Finding extends PersistentObject {

    Date getDate();

    /**
     * @param date
     *            A date must not be
     *                  {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setDate(Date date);

    /**
     * @return May return {@code null}.
     */
    String getSummary();

    /**
     * @param summary No restrictions on this parameter.
     */
    void setSummary(String summary);


    /**
     * @return An associated Patient object or {@code null}.
     * @exception FetchException
     *                Thrown if an error occurred while fetching a patient
     *                object.
     */
    Patient getPatient();
}
