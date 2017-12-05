package lib.itext.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PRAcroForm.FieldInformation;
import com.itextpdf.text.pdf.PdfAcroForm;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.TextField;

public class ItextPdf {
	
	
	public static void main(String[] args) {
		
		String origen = "/home/carlos/Documentos/IBM/Bugs/simulacion.pdf";
		String destino = "/home/carlos/Documentos/IBM/Bugs/simulacionModificado.pdf";

		ItextPdf itext = new ItextPdf();
		try {
			
			
			itext.leer01(origen, destino);
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void leer01(String origen, String destino) throws DocumentException, IOException {
		
		PdfReader reader = new PdfReader( origen );

		
		
//		 PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destino));
//		 AcroFields fields01 = stamper.getAcroFields();
//		 Map<String, Item> mapItem = fields01.getFields();
//		 Set<String> sets = mapItem.keySet();
//		for (String string : sets) {
//			System.out.println(string);
//		}
//		 stamper.close();
	        
	        
//		AcroFields fields01 =  reader.getAcroFields();
//		Map<String, Item> mapItem = fields01.getFields();
//		Set<String> sets = mapItem.keySet();
//		for (String string : sets) {
//			System.out.println(string);
//		}
		
//		PRAcroForm form = reader.getAcroForm();
//		ArrayList<FieldInformation> fields02 = form.getFields();
//		for (FieldInformation info : fields02) {
//		  System.out.println( info.getName());
//		}

		reader.close();   
		 
	}

	
	private void lectura02 () throws FileNotFoundException, IOException, DocumentException {
		
		PdfReader reader = new PdfReader(new FileInputStream(new File("C:\\Temp\\577979.pdf")));
        PdfStamper stamper = new PdfStamper(reader, (new FileOutputStream(new File("C:\\Temp\\577979-out.pdf"))));
        AcroFields form = stamper.getAcroFields();

        // Let's add value to an existing field
        form.setField("idCustomer", "My customer!!!");

        // Let's add a new field
        TextField idDocTrackTypeField = new TextField(stamper.getWriter(), new Rectangle(150, 740, 180, 790), "idDocTrackType");
        PdfFormField field1 = idDocTrackTypeField.getTextField();

        // First attempt to set the value (before adding it)
        field1.setValueAsString("idDocTrackTypeValue1");

        // Let's add it
        stamper.addAnnotation(field1, 1);

        // Second attempt to set the value (after adding it)
        field1.setValueAsString("idDocTrackTypeValue2");

        // Third attempt to set the value
        form.setField("idDocTrackType", "idDocTrackTypeValue3");		
	}
	
	
	
}

