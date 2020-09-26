package de.hshn.mi.pdbg.enhanced;

import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.exception.FetchException;

import java.util.Date;

/**
 * {@link ExaminationResult} describes a result of an examination.
 */
public interface ExaminationResult extends PersistentObject {

    String getResultSummary();

    /**
     * @param resultSummary
     *          A resultSummary must not be{@code null} and must not be <code>""</code>.
     */
    void setResultSummary(String resultSummary);


    /**
     * @return The requirment date.
     */
    Date getRequirmentDate();

    /**
     * @param date
     *            An requirment date is only valid if it is a date before the
     *            result date. It must not be {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setRequirmentDate(Date date);

    /**
     * @return The result date or {@code null} if it has not been set.
     */
    Date getResultDate();

    /**
     * @param date
     *            A result date is only valid if it is a date after the
     *            requirment date or {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setResultDate(Date date);

    /**
     * @return An associated {@link Finding} object.
     * @exception FetchException
     *                Thrown if an error occurred while fetching a Finding object.
     */
    Finding getFinding();
    /**
     *
     * @param finding No restrictions on this parameter.
     */
    void setFinding(Finding finding);

    /**
     * @return An associated {@link Examination} object.
     * @exception FetchException
     *                Thrown if an error occurred while fetching an Examination object.
     */
    Examination getExamination();

    /**
     * @param examination
     *            must not be {@code null}.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setExamination(Examination examination);

}
