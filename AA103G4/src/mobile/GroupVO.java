package mobile;

public class GroupVO {
	private Integer g_No;
	private String g_Name;
	private String g_Intro;
	private Integer lead_No;
	private String lead_Name;
	public Integer getG_No() {
		return g_No;
	}
	public void setG_No(Integer g_No) {
		this.g_No = g_No;
	}
	public String getG_Name() {
		return g_Name;
	}
	public void setG_Name(String g_Name) {
		this.g_Name = g_Name;
	}
	public String getG_Intro() {
		return g_Intro;
	}
	public void setG_Intro(String g_Intro) {
		this.g_Intro = g_Intro;
	}
	public Integer getLead_No() {
		return lead_No;
	}
	public void setLead_No(Integer lead_No) {
		this.lead_No = lead_No;
	}
	public String getLead_Name() {
		return lead_Name;
	}
	public void setLead_Name(String lead_Name) {
		this.lead_Name = lead_Name;
	}
	@Override
	public String toString() {
		return "GroupVO [g_No=" + g_No + ", g_Name=" + g_Name + ", g_Intro=" + g_Intro + ", lead_No=" + lead_No
				+ ", lead_Name=" + lead_Name + "]";
	}
	
	
}
