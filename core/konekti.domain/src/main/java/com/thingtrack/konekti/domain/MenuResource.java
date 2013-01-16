package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MENU_RESOURCE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("RESOURCE")
public class MenuResource implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_RESOURCE_ID")
	private Integer menuResourceId;
		
	@Column(name = "POSITION")
	private int position;
	
	@Column(name = "CAPTION")
	private String caption;
	
	@Column(name = "SHORTCUT")
	private String shortCut;
	
	@Column(name = "ICON")
	@Lob
	private byte[] icon;
	
	@Column(name = "VISIBLE")
	private boolean visible;

	/**
	 * @return the menuResourceId
	 */
	public Integer getMenuResourceId() {
		return menuResourceId;
	}

	/**
	 * @param menuResourceId the menuResourceId to set
	 */
	public void setMenuResourceId(Integer menuResourceId) {
		this.menuResourceId = menuResourceId;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}
		
	/**
	 * @return the shortCut
	 */
	public String getShortCut() {
		return shortCut;
	}

	/**
	 * @param shortCut the shortCut to set
	 */
	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	/**
	 * @return the icon
	 */
	public byte[] getIcon() {
		return icon;
	}
	
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.caption;
		
	}

}
