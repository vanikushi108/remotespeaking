package com.ca.rs.steps;

import com.ca.rs.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class StepFields {

    /**
     * A literal that starts and ends with a single quote, and may not contain single quotes.
     */
    public static final String SINGLE_QUOTED = "'[^']*'";
    /**
     * A literal that starts and ends with a double quote, and may not contain double quotes.
     */
    public static final String DOUBLE_QUOTED = " \"[^\"]*\"";

    /**
     * The common Literal used in step definitions, the contents inside a set of double quotes.
     */
    public static final String LITERAL = "\"([^\"]*)\"";

    private static final Logger LOG = LoggerFactory.getLogger(StepFields.class);

    /**
     * the result should be passed through {@link #unquote} to remove any quotes.
     */
    public static final String Q_LITERAL = "(" + SINGLE_QUOTED + "|" + DOUBLE_QUOTED + ")";
    /**
     * A capturing literal that can will match an Integer.
     */
    public static final String INTEGER = "(\\d+)";

    /**
     * String capturing the suffix for numbers to be used
     *
     * @param value
     * @return
     */

    public static final String ORDINAL = "(st|nd|rd|th)";

    /**
     * remove quotes (if present) from around a LITERAL, SINGLE_QUOTED or DOUBLE_QUOTED field.
     */
    public static String unquote(String possiblyQuoted) {
        if (checkNotNull(possiblyQuoted).isEmpty() || "'\"".indexOf(possiblyQuoted.charAt(0)) == -1) {
            return possiblyQuoted;
        }
        // either should be of the form '<s>' or "<s>"
        int lastCharIdx = possiblyQuoted.length() - 1;
        checkArgument(possiblyQuoted.charAt(0) == possiblyQuoted.charAt(lastCharIdx), "Quote mismatch !");
        return possiblyQuoted.substring(1, lastCharIdx);
    }

    public static String generateRandomString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(Constants.RANDOM_CASEID_LENGTH_OF_STRING);
        LOG.debug("***Inside generateRandomString Method Generated String is***" + generatedString + "Generated String ends");
        return generatedString;
    }

    public static String generateRandomUuidString() {
        String randomUuidString = UUID.randomUUID().toString();
        String returnRandomUuidString = null;
        if (randomUuidString.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            returnRandomUuidString = randomUuidString;
        }

        return returnRandomUuidString;
    }


}
