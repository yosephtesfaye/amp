/**
 * 
 */
package org.dgfoundation.amp.onepager.components.fields;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.dgfoundation.amp.onepager.validators.AmpSemanticValidator;

/**
 * @author mihai
 * This is an {@link AmpHiddenFieldPanel} that encapsulates an {@link AmpSemanticValidator}
 * This field is added to the form to provide non-critical validation, that can be turned off if necessary (non-syntatic).
 * @see AmpSemanticValidator
 */
public abstract class AmpSemanticValidatorField <H> extends AmpHiddenFieldPanel <H> implements IAjaxIndicatorAware {

	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();
	protected AmpSemanticValidator<H> semanticValidator;

	public AmpSemanticValidator getSemanticValidator() {
		return semanticValidator;
	}

	public AmpSemanticValidatorField(String id, String fmName, AmpSemanticValidator<H> semanticValidator) {
		super(id, fmName);
		this.semanticValidator=semanticValidator;
		hiddenContainer.add(semanticValidator);
		add(indicatorAppender);
	}

	@Override
	public String getAjaxIndicatorMarkupId() {
		return indicatorAppender.getMarkupId();
	}

	
}
