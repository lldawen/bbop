package ph.gov.bbop.common.util;

import com.aspose.words.Document;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${app.templates.dir}")
	private static String TEMPLATE_DIR;

	@Value("${app.documents.dir}")
	private static String DOCUMENT_DIR;

	@Value("${app.temp.dir}")
	private static String TEMP_DIR;

    public void generate(String clearanceType, Map<String, String> fieldValues) {
		try (HWPFDocument templateDocument = new HWPFDocument(new POIFSFileSystem(new File(TEMPLATE_DIR + File.separator + getTemplate(clearanceType))))) {
			for (Map.Entry<String, String> value : fieldValues.entrySet()) {
				replaceText(templateDocument, value.getKey(), value.getValue());
			}
			saveDocument(templateDocument, getOutputFilename(clearanceType));
		} catch (IOException e) {
			log.error("DocumentGenerator | generate | error: {}", e.getMessage(), e);
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

	private void saveDocument(HWPFDocument doc, String filename) {
		String filePath = TEMP_DIR + File.separator + filename + ".docx";
		try (FileOutputStream out = new FileOutputStream(filePath)) {
			doc.write(out);
			convertToPdf(filename, filePath);
		} catch (IOException e) {
			log.error("DocumentGenerator | saveDocument | error: {}", e.getMessage(), e);
		}
	}

	private void convertToPdf(String filename, String docFilePath) {
		try {
			Document doc = new Document(docFilePath);
			doc.save(DOCUMENT_DIR + File.separator + filename + ".pdf");
		} catch (Exception e) {
			log.error("DocumentGenerator | convertToPdf | error: {}", e.getMessage(), e);
		}
	}
	private String getTemplate(String clearanceType) {
		if (CommonConstants.APPL_TYPE_CLEARANCE.equals(clearanceType)) {
			return "barangay-clearance.doc";
		}
		return null;
	}

	private String getOutputFilename(String clearanceType) {
		StringJoiner filenameJoiner = new StringJoiner("-");
		filenameJoiner.add(DateTimeUtil.formatForFilename(LocalDate.now()));
		if (CommonConstants.APPL_TYPE_CLEARANCE.equals(clearanceType)) {
			filenameJoiner.add("Barangay-Clearance.pdf");
		}
		return filenameJoiner.toString();
	}
}
