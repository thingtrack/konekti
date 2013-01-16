package com.thingtrack.konekti.view.addon.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Attachment;
import com.thingtrack.konekti.service.api.AttachmentService;
import com.thingtrack.konekti.view.addon.data.BindingSource;

import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class AttachmentViewForm extends Window {
	private VerticalLayout mainLayout;
	private VerticalLayout verticalLayoutFile;
	private HorizontalLayout horizontalLayoutFileToolbox;
	private Button btnRemoveFile;
	private Button btnDownloadFile;
	private Upload uploadFile;
	private TextArea textAreaFile;
	private Table tableFiles;
	
	private AttachmentService attachmentService;
	private BindingSource<Attachment> attachmentBindingSource;
		
	private String entityName;
	private Integer entityId;
	private String fileName;
	
	private byte[] fileArray;
	
	public AttachmentViewForm() {		
		buildMainLayout();
		setContent(mainLayout);

		// TODO add user code here
		setCaption("Adjuntar ficheros");
		center();
		setModal(true);
		setWidth("550px");
		setHeight("300px");
		setResizable(false);
		
		tableFiles.setImmediate(true);
		tableFiles.setSelectable(true);
		
		uploadFile.setButtonCaption("Subir");
		
		final FileUploader uploader = new FileUploader(); 
		uploadFile.setReceiver(uploader);
		uploadFile.addListener(uploader);
		
		//Retrieve enterprise services
		getServices();
		
		// configure datasource table data
		attachmentBindingSource = new BindingSource<Attachment>(Attachment.class);
		
		tableFiles.setContainerDataSource(attachmentBindingSource);
		tableFiles.setVisibleColumns(new String[]{"fileName", "comment" });
		tableFiles.setColumnHeaders(new String[] { "Nombre Fichero", "Comentarios" } );
		
		btnRemoveFile.addListener(new ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				final Object selected = tableFiles.getValue();
				
				if (selected == null)
					return;
				
				ConfirmDialog.show(getApplication().getMainWindow(), "Borrar Fichero", "¿Estás seguro?",
						"Si", "No", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									try {	
										attachmentService.delete((Attachment) selected);
										
										// refresh
										getEntities();
									} catch (Exception e) {
										throw new RuntimeException("¡No se pudo borrar el fichero seleccionado!", e);
									}
								}
							}
				});
				
			}
		});
		
		btnDownloadFile.addListener(new ClickListener() {		
			@Override
			public void buttonClick(ClickEvent event) {
				final Object selected = tableFiles.getValue();
				
				if (selected == null)
					return;
				
				 StreamSource ss = new StreamSource() {

			            byte[] bytes = ((Attachment) selected).getFile();
			            InputStream is = new ByteArrayInputStream(bytes);

			            @Override
			            public InputStream getStream() {
			                return is;
			            }
			     };
			     
			     StreamResource sr = new StreamResource(ss, ((Attachment) selected).getFileName(), getApplication());
			     getApplication().getMainWindow().open(sr, "_blank");
			}
		});
	}

	public void setEntity(Object entity) throws IllegalArgumentException, IllegalAccessException {				
		// get name and identification entity from reflection
		getEntityMetadata(entity);
		
		// get all documents  for this entity name&Id
		getEntities();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {		
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		
		ServiceReference attachmentServiceReference = bundleContext.getServiceReference(AttachmentService.class.getName());
		attachmentService =  AttachmentService.class.cast(bundleContext.getService(attachmentServiceReference));
		
	}
	
	private void getEntities() {
		try {
			attachmentBindingSource.removeAllItems();
			attachmentBindingSource.addAll(attachmentService.getAllByEntity(entityName, entityId));
			
			textAreaFile.setValue("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getEntityMetadata(Object entity) throws IllegalArgumentException, IllegalAccessException {
    	for (Field field : entity.getClass().getDeclaredFields()) {
    		for (Annotation annotation : field.getAnnotations()) {
    			if (annotation.toString().equals("@javax.persistence.Id()")) {
    				entityName = entity.getClass().getSimpleName();
    				field.setAccessible(true);
    				entityId = (Integer)field.get(entity);
    				
    				return;
    			}
    		}
    	}
	}
	
	// Implement both receiver that saves upload in a file and listener for successful upload
	private class FileUploader implements Receiver, SucceededListener {
		private File file;
		
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
	    	fileArray = getFile(file);
	    	
	    	try {
	    		// save the new file in database
				attachmentService.save(new Attachment(entityName, entityId, fileName, textAreaFile.getValue().toString(), fileArray));
				
				// refresh
				getEntities();
			} catch (Exception e) {
				throw new RuntimeException("¡No se pudo adjuntar el fichero seleccionado!", e);
			}
	    }
	}
	
	private byte[] getFile(File file) {
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
	
	@Override
	public void attach() {
		super.attach();
		
		
	}
	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("500px");
		setHeight("240px");
		
		// tableFiles
		tableFiles = new Table();
		tableFiles.setCaption("Ficheros Adjuntos");
		tableFiles.setImmediate(false);
		tableFiles.setWidth("100.0%");
		tableFiles.setHeight("100.0%");
		mainLayout.addComponent(tableFiles);
		mainLayout.setExpandRatio(tableFiles, 1.0f);
		
		// verticalLayoutFile
		verticalLayoutFile = buildVerticalLayoutFile();
		mainLayout.addComponent(verticalLayoutFile);
		mainLayout.setExpandRatio(verticalLayoutFile, 1.0f);
		
		return mainLayout;
	}

	private VerticalLayout buildVerticalLayoutFile() {
		// common part: create layout
		verticalLayoutFile = new VerticalLayout();
		verticalLayoutFile.setImmediate(false);
		verticalLayoutFile.setWidth("100.0%");
		verticalLayoutFile.setHeight("100.0%");
		verticalLayoutFile.setMargin(false);
		
		// textAreaFile
		textAreaFile = new TextArea();
		textAreaFile.setCaption("Comentarios");
		textAreaFile.setImmediate(false);
		textAreaFile.setWidth("100.0%");
		textAreaFile.setHeight("-1px");
		verticalLayoutFile.addComponent(textAreaFile);
		
		// horizontalLayoutFileToolbox
		horizontalLayoutFileToolbox = buildHorizontalLayoutFileToolbox();
		verticalLayoutFile.addComponent(horizontalLayoutFileToolbox);
		
		return verticalLayoutFile;
	}

	private HorizontalLayout buildHorizontalLayoutFileToolbox() {
		// common part: create layout
		horizontalLayoutFileToolbox = new HorizontalLayout();
		horizontalLayoutFileToolbox.setImmediate(false);
		horizontalLayoutFileToolbox.setWidth("100.0%");
		horizontalLayoutFileToolbox.setHeight("-1px");
		horizontalLayoutFileToolbox.setMargin(false);
		
		// uploadFile
		uploadFile = new Upload();
		uploadFile.setImmediate(false);
		uploadFile.setWidth("-1px");
		uploadFile.setHeight("-1px");
		horizontalLayoutFileToolbox.addComponent(uploadFile);
		
		// buttonDownloadFile
		btnDownloadFile = new Button();
		btnDownloadFile.setCaption("Bajar");
		btnDownloadFile.setImmediate(false);
		btnDownloadFile.setWidth("-1px");
		btnDownloadFile.setHeight("-1px");
		horizontalLayoutFileToolbox.addComponent(btnDownloadFile);
		horizontalLayoutFileToolbox.setComponentAlignment(btnDownloadFile,
				new Alignment(9));
		
		// btnRemoveFile
		btnRemoveFile = new Button();
		btnRemoveFile.setCaption("Eliminar");
		btnRemoveFile.setImmediate(true);
		btnRemoveFile.setWidth("-1px");
		btnRemoveFile.setHeight("-1px");
		horizontalLayoutFileToolbox.addComponent(btnRemoveFile);
		horizontalLayoutFileToolbox.setExpandRatio(btnRemoveFile, 1.0f);
		horizontalLayoutFileToolbox.setComponentAlignment(btnRemoveFile,
				new Alignment(9));
		
		return horizontalLayoutFileToolbox;
	}
}
