package lib.fop.pdf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;

import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;






public class TransformPdf {
	
	
	private TransformerFactory transformFactory = TransformerFactory.newInstance();
	private FopFactory fopFactory = FopFactory.newInstance (); 
	
	public static void main(String[] args) {
		
		String dirXml = new String("/home/carlos/Documentos/IBM/Bugs/xml response.xml");
		String dirXsl = new String("/home/carlos/Documentos/IBM/Bugs/ContratoCredito.xsl");
		String dirOutPdf = new String("/home/carlos/Documentos/IBM/Bugs/simulacion.pdf");
		
		TransformPdf transPdf = new TransformPdf();
		
		try {
			
			String xml = transPdf.readFile( dirXml );
			String xsl = transPdf.readFile( dirXsl );
			byte[] pdf = transPdf.getPDFDocument(xml, xsl);
			transPdf.writeByteToFile(dirOutPdf, pdf);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void writeByteToFile(String path, byte[] data) throws IOException {
		Files.write(new File(path).toPath(), data);

	}
	
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}

	
	public byte[] getPDFDocument(String xmlDatos, String strXsl)
			throws Exception {
				
				if (strXsl == null)
					throw new Exception("getPDFDocument: StreamSource is nulo");

				Templates template = transformFactory.newTemplates(new StreamSource(new StringReader(strXsl)));
					

				Transformer transformer = template.newTransformer();
				ByteArrayOutputStream outStream = null;
				byte[] pdfBytes = null;
				
				try {
					
					outStream = new ByteArrayOutputStream();

					// setup FOP
					FOUserAgent foUserAgent = this.fopFactory.newFOUserAgent();
					foUserAgent.setProducer(this.getClass().getName());
					Fop fop = this.fopFactory.newFop(org.apache.xmlgraphics.util.MimeConstants.MIME_PDF, foUserAgent, outStream);

					// perform transformation (INYECTION)
					Result res = new SAXResult(fop.getDefaultHandler());
					StreamSource xmlSourcePdf = new StreamSource(new ByteArrayInputStream(xmlDatos.toString().getBytes("UTF-8")));
					transformer.transform(xmlSourcePdf, res);
					pdfBytes = outStream.toByteArray();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				} finally {
					if (outStream != null)
						try {
							outStream.close();
						} catch (Throwable t) {
						}
				}
				return pdfBytes;
			}

}
