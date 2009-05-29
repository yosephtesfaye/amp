package org.digijava.module.message.triggers;

import org.digijava.module.aim.dbentity.AmpActivity;
import org.digijava.module.message.helper.MessageConstants;
import org.digijava.module.message.helper.Event;

public class ActivityDisbursementDateTrigger extends Trigger {
    public static final String PARAM_NAME="name";
    public static final String PARAM_TEAM_ID="teamId";
    public static final String PARAM_TRIGGER_SENDER="sender";
    public static final String PARAM_URL="activity url";
    public static final String PARAM_CREATED_BY="createdBy";

    public static final String [] parameterNames=new String[]{PARAM_NAME,PARAM_TEAM_ID,PARAM_TRIGGER_SENDER,PARAM_URL};

	public ActivityDisbursementDateTrigger(Object source) {
		if(! (source instanceof AmpActivity)) throw new RuntimeException("Incompatible object. Source must be an activity!");
		this.source=source;
		forwardEvent();
	}

	@Override
	protected Event generateEvent() {
		Event e=new Event(ActivityDisbursementDateTrigger.class);
		AmpActivity activity=(AmpActivity) source;
		e.getParameters().put(PARAM_NAME,activity.getName());
        e.getParameters().put(PARAM_TEAM_ID,activity.getTeam().getAmpTeamId());
		e.getParameters().put(PARAM_TRIGGER_SENDER,MessageConstants.SENDER_TYPE_SYSTEM);
		e.getParameters().put(PARAM_URL, "aim/selectActivityTabs.do~ampActivityId="+activity.getAmpActivityId());
		e.getParameters().put(PARAM_CREATED_BY, activity.getActivityCreator());
		return e;
	}

	@Override
	public String[] getParameterNames() {
		return parameterNames;
	}

}
