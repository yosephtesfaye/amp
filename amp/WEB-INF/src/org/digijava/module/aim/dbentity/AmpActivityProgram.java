package org.digijava.module.aim.dbentity;

import java.io.Serializable;
import java.util.ArrayList;

import org.digijava.module.aim.util.Output;
import org.digijava.module.aim.util.ProgramUtil;

public class AmpActivityProgram implements Versionable, Serializable, Cloneable {

        private Long ampActivityProgramId;
        private Float programPercentage;
        private AmpTheme program;
        private AmpActivityVersion activity;
        private AmpActivityProgramSettings programSetting;
        public Long getAmpActivityProgramId() {
                return ampActivityProgramId;
        }

        public Float getProgramPercentage() {
                return programPercentage;
        }

        public AmpTheme getProgram() {
                return program;
        }

        public AmpActivityVersion getActivity() {
                return activity;
        }

        public AmpActivityProgramSettings getProgramSetting() {
                return programSetting;
        }

        public void setAmpActivityProgramId(Long ampActivityProgramId) {
                this.ampActivityProgramId = ampActivityProgramId;
        }

        public void setProgramPercentage(Float programPercentage) {
                this.programPercentage = programPercentage;
        }

        public void setProgram(AmpTheme program) {
                this.program = program;
        }

        public void setActivity(AmpActivityVersion activity) {
                this.activity = activity;
        }

        public void setProgramSetting(AmpActivityProgramSettings programSetting) {
                this.programSetting = programSetting;
        }

        public String getHierarchyNames() {
                String names = "";
                names = ProgramUtil.printHierarchyNames(this.program);
                names += "[" + this.program.getName() + "]";
                return names;
        }
        
    @Override
	public boolean equalsForVersioning(Object obj) {
		AmpActivityProgram aux = (AmpActivityProgram) obj;
		if (this.program.getAmpThemeId().equals(aux.program.getAmpThemeId())) {
			return true;
		}
		return false;
	}

	@Override
	public Output getOutput() {
		Output out = new Output();
		out.setOutputs(new ArrayList<Output>());
		out.getOutputs().add(new Output(null, new String[] { "Name:&nbsp;" }, new Object[] { this.program.getName() }));
		out.getOutputs()
				.add(new Output(null, new String[] { "Percentage:&nbsp;" }, new Object[] { this.programPercentage }));
		return out;
	}

	@Override
	public Object getValue() {
		String ret = "";
		ret = "" + this.programPercentage;
		return ret;
	}
	
	@Override
	public Object prepareMerge(AmpActivityVersion newActivity) throws CloneNotSupportedException {
		AmpActivityProgram aux = (AmpActivityProgram) clone();
		aux.activity = newActivity;
		aux.ampActivityProgramId = null;
		
		return aux;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	return super.clone();
	}
}
