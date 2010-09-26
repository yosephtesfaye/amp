/**
 * AmountCellPDF.java
 * (c) 2006 Development Gateway Foundation
 * @author Mihai Postelnicu - mpostelnicu@dgfoundation.org
 * 
 */
package org.dgfoundation.amp.ar.view.pdf;

import org.dgfoundation.amp.ar.Exporter;
import org.dgfoundation.amp.ar.Viewable;
import org.dgfoundation.amp.ar.cell.AmountCell;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * 
 * @author Mihai Postelnicu - mpostelnicu@dgfoundation.org
 * @since Aug 28, 2006
 *
 */
public class AmountCellPDF extends PDFExporter {

	
	public AmountCellPDF(Exporter parent,Viewable item) {
		super(parent,item);
	}
	
	/**
	 * @param table
	 * @param item
	 */
	public AmountCellPDF(PdfPTable table, Viewable item,Long ownerId) {
		super(table, item,ownerId);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.dgfoundation.amp.ar.view.pdf.PDFExporter#generate()
 	 */
	public void generate() {

		
		
		AmountCell ac=(AmountCell) item;
		PdfPCell pdfc = new PdfPCell(new Paragraph(ac.toString(),new Font(Font.FontFamily.COURIER, 9, Font.NORMAL)));
		pdfc.setVerticalAlignment(Element.ALIGN_CENTER);
		pdfc.setHorizontalAlignment(Element.ALIGN_RIGHT);
		if (currentBackColor!=null){
		    pdfc.setBackgroundColor(currentBackColor);
		}
		table.addCell(pdfc);
	}
 
}
