package de.hshn.mi.pdbg.enhanced;

import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.basicservice.BasicDBService;
import de.hshn.mi.pdbg.exception.FetchException;
import de.hshn.mi.pdbg.exception.StoreException;

import java.util.Date;
import java.util.List;

public interface EnhancedDBService extends BasicDBService {

    /**
     * @param date
     *             must not be {@code null}.
     * @return A {@link Finding} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    Finding createFinding(Date date);

    /**
     *
     * @param icdCode
     *           must not be {@code null} and must not be <code>""</code>.
     * @return A {@link Diagnosis} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    Diagnosis createDiagnosis(String icdCode);

    /**
     * Returns a list of {@link Diagnosis} objects that match a set of search
     * criteria. When searching via icdCode, one may use the
     * SQL wildcards ("*", "?") in the search string.
     * @param icdCode
     *            must not be {@code null} and must not be <code>""</code>.
     * @return A list of matching {@link Diagnosis} objects is returned. It may be
     *         empty.
     * @throws AssertionError
     *              Thrown if a given parameter value is invalid.
     * @throws FetchException
     *             Thrown if an error occurred while fetching objects.
     */
    List<Diagnosis> getDiagnosis(String icdCode);

    /**
     * @param resultSummary
     *              must not be {@code null} and must not be <code>""</code>.
     * @param requirmentDate
     *              must not be {@code null}.
     * @return A {@link ExaminationResult} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    ExaminationResult createExaminationResult(String resultSummary, Date requirmentDate);

    /**
     * Removes an examination result from the database.
     * @param examinationResultID
     *            the object id of the examination result to be removed. It must be greater
     *            than zero ({@code 0}). It must not be
     *            {@link PersistentObject#INVALID_OBJECT_ID}
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     * @throws StoreException
     *             Thrown if an error occurred while removing the object.
     */
    void removeExaminationResult(long examinationResultID);

    /**
     * @param patientID
     *            The identifier (objectID) of the patient for which the stays
     *            will be fetched. It must be greater than zero ( {@code 0}
     *            ). It must not be {@link PersistentObject#INVALID_OBJECT_ID}
     * @return A list of matching {@link ExaminationResult} objects is returned. It
     *         may be empty.
     * @throws FetchException
     *             Thrown if an error occurred while fetching objects.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    List<ExaminationResult> getExaminationResult(long patientID);

    /**
     * @param nameExamination
     *            must not be {@code null} and must not be <code>""</code>
     * @return A {@link Examination} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    Examination createExamination(String nameExamination);

    /**
     * @return A {@link TehnicalExamination} object.
     */
    TehnicalExamination createTehnicalExamination();

    /**
     * @param sampleType
     *            must not be {@code null} and must not be <code>""</code>
     * @param standardValue
     *            must not be {@code null} and must not be <code>""</code>
     * @return A {@link LaboratoryTest} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    LaboratoryTest createLaboratoryTest(String sampleType, String standardValue);

    /**
     * @param bodyRegion
     *              must not be {@code null} and must not be <code>""</code>
     * @return A {@link ClinicalExamination} object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    ClinicalExamination createClinicalExamination(String bodyRegion);

    /**
     * Returns a list of {@link Examination} objects that match a set of search
     * criteria. When searching via nameExamination, one may use the
     * SQL wildcards ("%", "*") in the search string.
     * A {@code null} value for one of these parameters indicates that the
     * related search criterion will be ignored.
     *
     * @param nameExamination
     *            May be {@code null}. If so, the value will be ignored as
     *            a search criterion.
     * @return A list of matching {@link Examination} objects is returned. It may be
     *         empty.
     * @throws FetchException
     *             Thrown if an error occurred while fetching objects.
     */
    List<Examination> getExaminations(String nameExamination);

    /**
     * Persists a given {@link PersistentObject} (within a database).
     * Implementations must distinguish between INSERT/UPDATE operations.
     *
     * @param object
     *            The object to be stored. It must not be {@code null}. Has
     *            to be persistent (objectID set) in case of a successful store operation.
     *
     * @return The primary key as given by the database system.
     * @throws StoreException
     *             Thrown if errors occurred while persisting an object.
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    long store(PersistentObject object);

    /**
     * Closes the service and releases all dependent resources. Afterwards the
     * service is unusable.
     */
    void close();


}
