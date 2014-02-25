package com.thingtrack.konekti.view.addon.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class UploadViewForm extends Window {
	private HorizontalLayout mainLayout;
	private Upload uploadFile;
	private Button downloadFile;
	private Button removeFile;
	private Button closeFile;
	
	private byte[] groupDateFile;
	private String fileName;
	
	public static final String CALENDAR_BASE_FILENAME = "date-group-filename-";
	
	public UploadViewForm(final byte[] file) {		
		buildMainLayout();
		setContent(mainLayout);

		this.groupDateFile = file;
		this.fileName = CALENDAR_BASE_FILENAME + Math.round(Math.random() * 100);
				
		// TODO add user code here
		final FileUploader uploader = new FileUploader(); 
		
		uploadFile.setButtonCaption("Subir");
		uploadFile.setReceiver(uploader);
		uploadFile.addListener(uploader);
		
		setCaption("Adjuntar fichero");
		setModal(true);
		setResizable(false);
		setClosable(true);
		
		downloadFile.addListener(new ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				if (groupDateFile == null)
					return;
				
				 StreamSource ss = new StreamSource() {
			            InputStream is = new ByteArrayInputStream(groupDateFile);

			            @Override
			            public InputStream getStream() {
			                return is;
			            }
			     };
			     			     
			     StreamResource sr = new StreamResource(ss, fileName, getApplication());
			     getApplication().getMainWindow().open(sr, "_blank");
			}

		});
	
		removeFile.addListener(new ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				groupDateFile = null;
				
				getParent().removeWindow(UploadViewForm.this);
			}

		});
		
		closeFile.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) { 
				 getParent().removeWindow(UploadViewForm.this);				 
				
			}
		});
	}
	
	public String getFileName() {
		return this.fileName;
		
	}
	
	public byte[] getFile() {
		return this.groupDateFile;
		
	}
	
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		//mainLayout.setWidth("100%");
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		//setWidth("100%");
		setWidth("-1px");
		setHeight("-1px");
		
		// upload_1
		uploadFile = new Upload();
		uploadFile.setImmediate(false);
		uploadFile.setWidth("-1px");
		uploadFile.setHeight("-1px");
		mainLayout.addComponent(uploadFile);
		
		// btnRemove
		removeFile = new Button();
		removeFile.setCaption("Borrar");
		removeFile.setImmediate(false);
		removeFile.setWidth("-1px");
		removeFile.setHeight("-1px");		
		mainLayout.addComponent(removeFile);
		mainLayout.setComponentAlignment(removeFile, Alignment.BOTTOM_LEFT);
				
		// btnDownload
		downloadFile = new Button();
		downloadFile.setCaption("Bajar");
		downloadFile.setImmediate(false);
		downloadFile.setWidth("-1px");
		downloadFile.setHeight("-1px");		
		mainLayout.addComponent(downloadFile);
		mainLayout.setComponentAlignment(downloadFile, Alignment.BOTTOM_LEFT);
		
		// btnDownload
		closeFile = new Button();
		closeFile.setCaption("Cerrar");
		closeFile.setImmediate(false);
		closeFile.setWidth("-1px");
		closeFile.setHeight("-1px");		
		mainLayout.addComponent(closeFile);
		mainLayout.setComponentAlignment(closeFile, Alignment.BOTTOM_LEFT);
		mainLayout.setExpandRatio(downloadFile, 1.0f);
		
		return mainLayout;
	}
	
	// Implement both receiver that saves upload in a file and listener for successful upload
	private class FileUploader implements Receiver, SucceededListener {
		private File file;
		
		private byte[] getFileArray(File file) {
			byte[] bFile = new byte[(int) file.length()];
			 
	        try {
	            //convert file into array of bytes
		        FileInputStream fileInputStream = new FileInputStream(file);
			    fileInputStream.read(bFile);
			    fileInputStream.close();
		 		   
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return bFile;
		}
		
	    public OutputStream receiveUpload(String filename, String mimeType) {
	        // Create upload stream
	        FileOutputStream fos = null; // Output stream to write to
	        try {
	        	String basepath = getApplication().getContext().getBaseDirectory().getAbsolutePath();
	        			
	        	fileName = filename;
	        	
	            // Open the file for writing.
	            file = new File(basepath + "/" + filename);
	            fos = new FileOutputStream(file);
	        } catch (final java.io.FileNotFoundException e) {
	            getWindow().showNotification("Could not open file<br/>", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
	            return null;
	        }
	        
	        return fos; // Return the output stream to write to
	    }

	    public void uploadSucceeded(SucceededEvent event) {
	    	// transform file to byte array
	    	groupDateFile = getFileArray(file);

	    	getParent().removeWindow(UploadViewForm.this);
	    }
	}
}
