package de.hshn.mi.pdbg.enhanced;

import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.exception.FetchException;

import java.util.Set;

/**
 * {@link Examination} describes a patient's examination.
 */

public interface Examination extends PersistentObject{

    String getNameExamination();

    /**
     * @param nameExamination must not be {@code null} and must not be <code>""</code>
     * @throws AssertionError
     *             Thrown if a given parameter value is invalid.
     */
    void setNameExamination(String nameExamination);

}
