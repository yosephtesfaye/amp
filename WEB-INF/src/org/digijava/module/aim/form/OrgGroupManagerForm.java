package org.digijava.module.aim.form;

import org.apache.struts.action.*;
import java.util.Collection;

public class OrgGroupManagerForm extends ActionForm {

	private Collection organisation;
	private Collection pages;
	private Integer currentPage=new Integer(1);
	
	public Collection getOrganisation() {
		 return organisation;
	}

	public void setOrganisation(Collection organisation) {
		 this.organisation = organisation;
	}

	public Collection getPages() {
		 return pages;
	}

	public void setPages(Collection pages) {
		 this.pages = pages;
	}

	public Integer getCurrentPage() {
		if(currentPage==null) return new Integer(1);
		return currentPage;
	}

	public void setCurrentPage(Integer currentpage) {
		this.currentPage = currentpage;
	}
	
}
