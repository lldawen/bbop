package ph.gov.bbop.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.sql.rowset.serial.SerialClob;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.SQLException;

@Slf4j
public final class ClobConverter {

    private ClobConverter() {}

    public static Clob fromString(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return new SerialClob(value.toCharArray());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String toString(Clob clob) {
        if (clob == null) {
            return StringUtils.EMPTY;
        }
        StringWriter stringWriter = new StringWriter();
        try {
            IOUtils.copyLarge(clob.getCharacterStream(), stringWriter);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return stringWriter.toString();
    }
}
