import java.awt.Color;

import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class Util {
	
	public static XSLFTextParagraph createParagraph(XSLFTextShape shape,
			                                        Color color,
			                                        double size,
			                                        boolean bold,
			                                        boolean italic,
			                                        String fontFamily,
			                                        String text) {
		XSLFTextParagraph paragraph = shape.addNewTextParagraph();

	    XSLFTextRun textRun = paragraph.addNewTextRun();

	    textRun.setFontColor(color);
	    textRun.setFontSize(size);
	    textRun.setBold(bold);
	    textRun.setItalic(italic);
	    textRun.setFontFamily(fontFamily);

	    textRun.setText(text);
	    
	    return(paragraph);
	}

}
