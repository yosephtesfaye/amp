package org.digijava.module.aim.form;

import org.apache.struts.action.*;
import java.util.*;
public class ListAppliedPatchesForm extends ActionForm {
	List patch = null;
	String content = null;

	public List getPatch() {
		return patch;
	}

	public void setPatch(List patch) {
		this.patch = patch;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}