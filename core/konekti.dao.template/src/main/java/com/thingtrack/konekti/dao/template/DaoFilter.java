package com.thingtrack.konekti.dao.template;

import java.util.List;

public class DaoFilter {
	private String entityName;
	private String entityFatherName;
	private String attributeName;
	private Class<?> entityType;
	private List<?> entityList;
	private Comparator comparator;
	private Object firstValue;
	private Object lastValue;	
	
	public enum Comparator {
	    EQUAL, 
	    NOT_EQUAL,
	    GREATER_THAN,
	    GREATER_THAN_OR_EQUAL_TO,
	    LESS_THAN,
	    LESS_THAN_OR_EQUAL_TO,	    
	    BETWEEN,
	    NOT_BETWEEN,
	    CONTAINS,
	    CONTAINS_RIGHT,
	    CONTAINS_LEFT,
	    IS_NULL,
	    IS_NOT_NULL,
	    MEMBER_OF,
	    NOT_MEMBER_OF,
	    EMPTY,
	    NOT_EMPTY
	}
	
	public DaoFilter(String entityName, String entityFatherName, String attributeName, Class<?> entityType, List<?> entityList) {	
		this.entityName = entityName;
		this.entityFatherName = entityFatherName;
		this.attributeName = attributeName;
		this.entityType = entityType;
		this.entityList = entityList;
		
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @return the entityFatherName
	 */
	public String getEntityFatherName() {
		return entityFatherName;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
	
	/**
	 * @return the entityType
	 */
	public Class<?> getEntityType() {
		return entityType;
	}

	/**
	 * @param entityList the entityList to set
	 */
	public void setEntityList(List<?> entityList) {
		this.entityList = entityList;
	}

	/**
	 * @return the entityList
	 */
	public List<?> getEntityList() {
		return entityList;
	}

	/**
	 * @param firstValue the firstValue to set
	 */
	public void setFirstValue(Object firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return the firstValue
	 */
	public Object getFirstValue() {
		return firstValue;
	}

	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}

	/**
	 * @return the comparator
	 */
	public Comparator getComparator() {
		return comparator;
	}

	/**
	 * @param lastValue the lastValue to set
	 */
	public void setLastValue(Object lastValue) {
		this.lastValue = lastValue;
	}

	/**
	 * @return the lastValue
	 */
	public Object getLastValue() {
		return lastValue;
	}

}
