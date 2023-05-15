package ph.gov.bbop.common.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

@Slf4j
public final class FileUtil {

    private FileUtil() {}

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getDecoder();

    public static String encodeDocumentPath(String documentPath) {
		return documentPath; //base64Encoder.encodeToString(documentPath.getBytes()); TODO investigate the error java.lang.IllegalArgumentException: Illegal base64 character 3a, due to dashes
    }

    public static String decodeDocumentPath(String documentPath) {
        return documentPath; //new String(base64Decoder.decode(documentPath)); TODO investigate the error java.lang.IllegalArgumentException: Illegal base64 character 3a, due to dashes
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
		try {
			String errorStr = null;
			String fullPath = file.getAbsolutePath();

		    int startIndex = fullPath.lastIndexOf("\\");
		    if (startIndex == -1) {
		    	startIndex = fullPath.lastIndexOf("/");
		    }

		    int endIndex = fullPath.length();

		    String fileName = fullPath.substring(startIndex + 1, endIndex);

		    String userAgent = request.getHeader("User-Agent");
		    if (userAgent != null && userAgent.contains("MSIE 7.0")) {
		    	fileName = fileName.replace(" ", "%20");
		    }
		    fileName = fileName.replaceAll(";", ""); // to avoid setHeader parameter mess out

		    response.setContentType("application/octet-stream");
		    // DM Packaging - set the content length and encoding
		    response.setContentLength((int)new File(fullPath).length());
		    response.setHeader("Content-Transfer-Encoding", "binary");

		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		    try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);
                 ServletOutputStream outstr = response.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(outstr))  {

				byte[] buff = new byte[8096];
				int bytesRead;

				while (-1 != (bytesRead = bis.read(buff))) {
				    bos.write(buff, 0, bytesRead);
				}
		    } catch (Exception e) {
				errorStr = "Error Streaming the Data";
                log.error("streamFileDownload | error: {}", errorStr);
		    }
		} catch (Exception e) {
		    log.error("streamFileDownload | error", e);
		}
    }
}
