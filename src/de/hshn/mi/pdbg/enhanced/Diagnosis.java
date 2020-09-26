package de.hshn.mi.pdbg.enhanced;


import de.hshn.mi.pdbg.PersistentObject;
import de.hshn.mi.pdbg.exception.FetchException;

import java.util.Set;

/**
 * {@link Diagnosis} describes a diagnosis of a finding.
 */
public interface Diagnosis extends PersistentObject {

        String getIcdCode();

        /**
         * @param icdCode
         *            A icdCode must not be
         *                  {@code null}.
         * @throws AssertionError
         *             Thrown if a given parameter value is invalid.
         */
        void setIcdCode(String icdCode);


        String getDiagnosisText();

        /**
         * @param diagnosisText No restrictions on this parameter.
         */
        void setDiagnosisText(String diagnosisText);

}

