package com.event_member.model;

import com.event_info.model.Event_InfoVO;
import com.mem.model.MemVO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Event_MemberVO implements java.io.Serializable{
	//屬性
	private Event_InfoVO event_InfoVO;
	private MemVO memVO;
	private Integer	m_Status;
	
	public Event_InfoVO getEvent_InfoVO() {
		return event_InfoVO;
	}
	public void setEvent_InfoVO(Event_InfoVO event_InfoVO) {
		this.event_InfoVO = event_InfoVO;
	}
	public MemVO getMemVO() {
		return memVO;
	}
	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}
	public Integer getM_Status() {
		return m_Status;
	}
	public void setM_Status(Integer m_Status) {
		this.m_Status = m_Status;
	}
	
	
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof Event_MemberVO)) {
            return false;
        }
 
        Event_MemberVO compareItem = (Event_MemberVO) obj;
        return new EqualsBuilder()
                    .append(this.event_InfoVO.getE_No(), compareItem.event_InfoVO.getE_No())
                    .append(this.memVO.getMem_no(), compareItem.memVO.getMem_no())
                    .isEquals(); 
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.event_InfoVO.getE_No())
                    .append(this.memVO.getMem_no())
                    .toHashCode();
    }
}
