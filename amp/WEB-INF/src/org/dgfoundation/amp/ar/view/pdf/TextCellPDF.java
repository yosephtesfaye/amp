/**
 * TextCellPDF.java
 * (c) 2006 Development Gateway Foundation
 * @author Mihai Postelnicu - mpostelnicu@dgfoundation.org
 * 
 */
package org.dgfoundation.amp.ar.view.pdf;

import org.dgfoundation.amp.ar.Exporter;
import org.dgfoundation.amp.ar.ReportData;
import org.dgfoundation.amp.ar.Viewable;
import org.dgfoundation.amp.ar.cell.TextCell;
import org.digijava.kernel.persistence.WorkerException;
import org.digijava.kernel.translator.TranslatorWorker;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * 
 * @author Mihai Postelnicu - mpostelnicu@dgfoundation.org
 * @since Aug 28, 2006
 *
 */
public class TextCellPDF extends PDFExporter {

	public TextCellPDF(Exporter parent,Viewable item) {
		super(parent,item);
	}
	
	/**
	 * @param table
	 * @param item
	 */
	public TextCellPDF(PdfPTable table, Viewable item,Long ownerId) {
		super(table, item,ownerId);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.dgfoundation.amp.ar.view.pdf.PDFExporter#generate()
	 */
	public void generate() {
		TextCell c=(TextCell) item;
		PdfPCell pdfc=null;
		if(c.getColumn().getName().compareTo("Status")==0)
		{
			String actualStatus=c.toString();
			//multiple statuses can be (separated by " /")
			String[] statuses=actualStatus.split(" /");

			ReportData parent=(ReportData)c.getColumn().getParent();
			while (parent.getReportMetadata()==null)
			{
				parent=parent.getParent();
			}
			//when we get to the top of the hierarchy we have access to AmpReports
			
			//requirements for translation purposes
			TranslatorWorker translator=TranslatorWorker.getInstance();
			String siteId=parent.getReportMetadata().getSiteId();
			String locale=parent.getReportMetadata().getLocale();
			
			String finalStatus=new String();//the actual text to be added to the column
			
			for(int i=0;i<statuses.length;i++)
			{
				if(i>0)finalStatus+=" /";
				String translatedStatus=null;
				String prefix="aim:";
				try{
					translatedStatus=TranslatorWorker.translate(prefix+statuses[i],locale,siteId);
				}catch (WorkerException e)
					{System.out.println(e);}
				if (translatedStatus.compareTo("")==0)
					translatedStatus=statuses[i];
				finalStatus+=translatedStatus;
			}
			pdfc = new PdfPCell(new Paragraph(finalStatus));
		}
		else 
			pdfc = new PdfPCell(new Paragraph(c.toString()));
		table.addCell(pdfc);
	}

}
