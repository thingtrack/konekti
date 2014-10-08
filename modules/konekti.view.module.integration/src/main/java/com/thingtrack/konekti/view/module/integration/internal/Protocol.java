package com.thingtrack.konekti.view.module.integration.internal;

import java.io.File;
import java.util.Date;

import com.github.peholmst.i18n4vaadin.I18N;

public class Protocol {
	private I18N i18N;
	private String fileName;
	private File file;
	private byte [] fileArray;
	private Date protocolDate;
	private DIRECTION direction;
	
	public enum DIRECTION {
		IMPORT("PROTOCOL_IMPORT"),
		EXPORT("PROTOCOL_EXPORT");
		
		private String key;
		private String direction;
		
		private DIRECTION(String key) { 
			this.key = key;

		}

		public String getKey() {
			return this.key;
			
		}	
		
		public String getDirection() {
			return this.direction;
			
		}
		
		public void setDirection(String direction) {
			this.direction = direction;
			
		}
		
	}
	
	public enum TYPE {
		CLI("PROTOCOL_IMPORT_CLIENT"),
		SUP("PROTOCOL_IMPORT_SUPPLIER"),
		PRO("PROTOCOL_IMPORT_PRODUCT"),
		REC("PROTOCOL_IMPORT_RECEPTION"),
		EXP("PROTOCOL_IMPORT_EXPEDITION"),
		ERR("PROTOCOL_EXPORT_ERROR"),
		CRP("PROTOCOL_EXPORT_CONFIRM_RECEPTION"),
		CSO("PROTOCOL_EXPORT_CONFIRM_EXPEDITON"),
		VST("PROTOCOL_EXPORT_STOCK_VARIATION");
		
		private String key;

		private TYPE(String key) { 
			this.key = key;

		}

		public String getKey() {
			return this.key;
		}		
		
	}
			
	public Protocol(I18N i18N, String fileName, File file, byte[] fileArray, Date protocolDate) {
		this.i18N = i18N;
		this.fileName = fileName;
		this.file = file;
		this.fileArray = fileArray;
		this.protocolDate = protocolDate;
		
		if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CLI.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.SUP.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.PRO.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.REC.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.EXP.name()))
				this.direction = DIRECTION.IMPORT;
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.ERR.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.CRP.name()) ||
			this.fileName.substring(0, 3).equals(Protocol.TYPE.CSO.name()) ||
		    this.fileName.substring(0, 3).equals(Protocol.TYPE.VST.name()))
				this.direction = DIRECTION.EXPORT;
		else 
			this.direction = null;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getProtocolType() {
		if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CLI.name()))
			return i18N.getMessage(Protocol.TYPE.CLI.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.SUP.name()))
			return i18N.getMessage(Protocol.TYPE.SUP.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.PRO.name()))
			return i18N.getMessage(Protocol.TYPE.PRO.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.REC.name()))
			return i18N.getMessage(Protocol.TYPE.REC.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.EXP.name()))
			return i18N.getMessage(Protocol.TYPE.EXP.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.ERR.name()))
			return i18N.getMessage(Protocol.TYPE.ERR.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CRP.name()))
			return i18N.getMessage(Protocol.TYPE.CRP.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CSO.name()))
			return i18N.getMessage(Protocol.TYPE.CSO.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.VST.name()))
			return i18N.getMessage(Protocol.TYPE.VST.getKey());
		
		return null;
	}
	
	public DIRECTION getDirection() {
		return direction;
		
	}
	
	public String getProtocolDirection() {
		if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CLI.name()))
			return i18N.getMessage(DIRECTION.IMPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.SUP.name()))
			return i18N.getMessage(DIRECTION.IMPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.PRO.name()))
			return i18N.getMessage(DIRECTION.IMPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.REC.name()))
			return i18N.getMessage(DIRECTION.IMPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.EXP.name()))
			return i18N.getMessage(DIRECTION.IMPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.ERR.name()))
			return i18N.getMessage(DIRECTION.EXPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CRP.name()))
			return i18N.getMessage(DIRECTION.EXPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.CSO.name()))
			return i18N.getMessage(DIRECTION.EXPORT.getKey());
		else if (this.fileName.substring(0, 3).equals(Protocol.TYPE.VST.name()))
			return i18N.getMessage(DIRECTION.EXPORT.getKey());
		
		return null;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public byte[] getFileArray() {
		return fileArray;
	}

	public void setFilearray(byte[] fileArray) {
		this.fileArray = fileArray;
	}

	public Date getProtocolDate() {
		return protocolDate;
	}

	public void setProtocolDate(Date protocolDate) {
		this.protocolDate = protocolDate;
	}	
	
}
