package org.digijava.module.contentrepository.dbentity.template;

public class TextAreaField extends TemplateField {
	
	public String getType(){
		return "taf"; //text area field
	}
	
	//whether this field is allowed to have multiple pre-defined values
	public boolean getCanHaveMultipleValues() {
		return false;
	}
	
	//whether this field is allowd not to have pre-defined possible values
	public boolean getHasEmptyPossibleValsRights() {
		return true;
	}

	@Override
	public String getRendered() {
		String retVal="<textarea name=\"doc_textArea_"+getOrdinalNumber().intValue()+"\" class=\"inp-text\" rows=\"4\" cols=\"40\">";
		retVal+="</textarea>";
		return retVal;
	}

}
