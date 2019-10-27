package com.ms.test.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Message pojo class
 * @author Manish
 *
 */
@Entity 
@Table(name = "table_message")
public class Message
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MSG_ID")
	private int messageid;
	
	@Column(name="MSG_USR_ID")
	private int messageuserid;

	@Column(name="MSG_TEXT")
	private String messagetext;
	
	public Message() {
		super();
	}
	
	public Message(String messagetext) {
		super();
		this.messagetext = messagetext;
	}
	
	public Message(int messageid, String messagetext) {
		super();
		this.messageid = messageid;
		this.messagetext = messagetext;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}

	public int getMessageuserid() {
		return messageuserid;
	}

	public void setMessageuserid(int messageuserid) {
		this.messageuserid = messageuserid;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}

}
