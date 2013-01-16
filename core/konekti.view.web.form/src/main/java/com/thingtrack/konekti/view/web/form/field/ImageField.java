package com.thingtrack.konekti.view.web.form.field;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class ImageField extends CustomField {
	private VerticalLayout mainLayout;
	private Upload uploadImage;
	private HorizontalLayout hlImage;
	private Button btnClearImage;	
	private Embedded embeddedImage;
	
	private Application application;
		
	private byte[] image;
	
	public ImageField() {			
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		uploadImage.setButtonCaption("Subir");
		
		final ImageUploader uploader = new ImageUploader(); 
		uploadImage.setReceiver(uploader);
		uploadImage.addListener(uploader);
		
		btnClearImage.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				embeddedImage.setSource(new ThemeResource("images/imageNotAvailable.png"));
				image = null; 
								
			}
		});
		
	}
	
	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return byte[].class;
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		image = (byte[])newDataSource.getValue();
		Random randomGenerator = new Random();
		
		if (image != null)
			embeddedImage.setSource(getImageUser(Integer.toString(randomGenerator.nextInt()), image));
		else
			embeddedImage.setSource(new ThemeResource("images/imageNotAvailable.png"));
		
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {	
		return image;
		
	}
	
    private StreamResource getImageUser(String name, final byte[] byteResource) {
    	if (byteResource == null)
    		return null;
    	
    	// create an instance of our stream source.
    	StreamSource imagesource = new ModuleResource(byteResource);
    	
    	// Create a resource that uses the stream source and give it a name.
    	// The constructor will automatically register the resource in the application.
    	
    	StreamResource imageresource = new StreamResource(imagesource, name + "_user.png", application);
    	
        return imageresource;
    }
    
    private class ModuleResource implements StreamResource.StreamSource {
    	private byte[] resource = null;
    	private ByteArrayOutputStream imagebuffer = null;
   	
    	public ModuleResource(byte[] resource) {
    		this.resource = resource;
   		
    	}
   	
		@Override
		public InputStream getStream() {
			if (resource != null) {
				InputStream in = new ByteArrayInputStream(resource);
				BufferedImage bImageFromConvert = null;
	       	
				try {
					bImageFromConvert = ImageIO.read(in);					
					imagebuffer = new ByteArrayOutputStream();
					
					ImageIO.write(bImageFromConvert, "png", imagebuffer);
					} catch (IOException e) {	
						e.printStackTrace();
						
					}
	               
	               return new ByteArrayInputStream(imagebuffer.toByteArray());
	           }
	           
	           return null;
			}
	   	
   	}
	
	// Implement both receiver that saves upload in a file and listener for successful upload
	private class ImageUploader implements Receiver, SucceededListener {
		private File file;
		
	    public OutputStream receiveUpload(String filename, String mimeType) {
	        // Create upload stream
	        FileOutputStream fos = null; // Output stream to write to
	        try {
	        	String basepath = application.getContext().getBaseDirectory().getAbsolutePath();
	        			
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
	    	embeddedImage.setSource(new FileResource(file, application));
	    	image = getImage(file);
	    }
	}

	private byte[] getImage(File file) {
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
	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout(){
			
			@Override
			public void attach() {
				super.attach();
				ImageField.this.application = getApplication();
			}
		};
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// hlImage
		hlImage = buildHlImage();
		mainLayout.addComponent(hlImage);
		
		// uploadImage
		uploadImage = new Upload();
		uploadImage.setImmediate(false);
		uploadImage.setWidth("-1px");
		uploadImage.setHeight("-1px");
		mainLayout.addComponent(uploadImage);
		mainLayout.setExpandRatio(uploadImage, 1.0f);
		
		return mainLayout;
	}

	private HorizontalLayout buildHlImage() {
		// common part: create layout
		hlImage = new HorizontalLayout();
		hlImage.setImmediate(false);
		hlImage.setWidth("-1px");
		hlImage.setHeight("-1px");
		hlImage.setMargin(false);
		
		// embeddedImage
		embeddedImage = new Embedded();
		embeddedImage.setImmediate(false);
		embeddedImage.setWidth("80px");
		embeddedImage.setHeight("100px");
		embeddedImage.setSource(new ThemeResource(
				"images/imageNotAvailable.png"));
		embeddedImage.setType(1);
		embeddedImage.setMimeType("image/png");
		hlImage.addComponent(embeddedImage);
		
		// btnClearImage
		btnClearImage = new Button();
		btnClearImage.setCaption("X");
		btnClearImage.setImmediate(true);
		btnClearImage.setWidth("-1px");
		btnClearImage.setHeight("-1px");
		hlImage.addComponent(btnClearImage);
		hlImage.setComponentAlignment(btnClearImage, new Alignment(9));
		
		return hlImage;
	}
	
	@Override
	public void attach() {
		super.attach();
		
		application = getApplication();
	}
}
