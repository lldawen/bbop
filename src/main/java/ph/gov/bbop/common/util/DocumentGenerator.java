package ph.gov.bbop.common.util;

import com.aspose.words.Document;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;
import ph.gov.bbop.common.CommonConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.StringJoiner;

@Component
@Slf4j
public class DocumentGenerator {

	private static final String TEMPLATE_DIR = "D:/BBOP/server-files/templates";

	private static final String DOCUMENT_DIR = "D:/BBOP/server-files/documents";

	private static final String TEMP_DIR = "D:/BBOP/server-files/temp";

    public String generate(String clearanceType, Map<String, String> fieldValues) {
		String applicationId = fieldValues.get("APPL_ID");
		try (HWPFDocument templateDocument = new HWPFDocument(new POIFSFileSystem(new File(TEMPLATE_DIR + File.separator + getTemplate(clearanceType))))) {
			for (Map.Entry<String, String> value : fieldValues.entrySet()) {
				replaceText(templateDocument, value.getKey(), value.getValue());
			}
			String outputFilename = getOutputFilename(clearanceType, applicationId);
			return saveDocument(templateDocument, outputFilename, applicationId);
		} catch (IOException e) {
			log.error("DocumentGenerator | generate | error: {}", e.getMessage(), e);
			return null;
		}
	}

	private void replaceText(HWPFDocument doc, String findText, String replaceText) {
		Range range = doc.getRange();
		for (int numSec = 0; numSec < range.numSections(); ++numSec) {
			Section sec = range.getSection(numSec);
			for (int numPara = 0; numPara < sec.numParagraphs(); numPara++) {
				Paragraph para = sec.getParagraph(numPara);
				for (int numCharRun = 0; numCharRun < para.numCharacterRuns(); numCharRun++) {
					CharacterRun charRun = para.getCharacterRun(numCharRun);
					String text = charRun.text();
					if (text.contains(findText)) {
						charRun.replaceText(findText, replaceText);
					}
				}
			}
		}
	}

	private String saveDocument(HWPFDocument doc, String filename, String applicationId) throws IOException {
		File tempDir = new File(TEMP_DIR + File.separator + applicationId);
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		File tempFile = new File(tempDir.getAbsolutePath() + File.separator + filename + ".doc");
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			doc.write(out);
			return convertToPdf(filename, tempFile.getAbsolutePath(), applicationId);
		} catch (IOException e) {
			log.error("DocumentGenerator | saveDocument | error: {}", e.getMessage(), e);
			return null;
		}
	}

	private String convertToPdf(String filename, String docFilePath, String applicationId) {
		String pdfOutputFilePath = DOCUMENT_DIR + File.separator + applicationId + File.separator + filename + ".pdf";
		try {
			Document doc = new Document(docFilePath);
			doc.save(pdfOutputFilePath);
		} catch (Exception e) {
			log.error("DocumentGenerator | convertToPdf | error: {}", e.getMessage(), e);
		}
		return pdfOutputFilePath;
	}
	private String getTemplate(String clearanceType) {
		if (CommonConstants.APPL_TYPE_CLEARANCE.equals(clearanceType)) {
			return "barangay-clearance.doc";
		}
		if (CommonConstants.APPL_TYPE_INDIGENCY.equals(clearanceType)) {
			return "certificate-of-indigency.doc";
		}
		if (CommonConstants.APPL_TYPE_RESIDENCY.equals(clearanceType)) {
			return "certificate-of-residency.doc";
		}
		return null;
	}

	private String getOutputFilename(String clearanceType, String applicationId) {
		StringJoiner filenameJoiner = new StringJoiner("-");
		filenameJoiner.add(DateTimeUtil.formatForFilename(LocalDate.now()));
		filenameJoiner.add(applicationId);
		if (CommonConstants.APPL_TYPE_CLEARANCE.equals(clearanceType)) {
			filenameJoiner.add("Barangay-Clearance");
		}
		if (CommonConstants.APPL_TYPE_INDIGENCY.equals(clearanceType)) {
			filenameJoiner.add("Certificate-of-Indigency");
		}
		if (CommonConstants.APPL_TYPE_RESIDENCY.equals(clearanceType)) {
			filenameJoiner.add("Certificate-of-Residency");
		}
		return filenameJoiner.toString();
	}
}
