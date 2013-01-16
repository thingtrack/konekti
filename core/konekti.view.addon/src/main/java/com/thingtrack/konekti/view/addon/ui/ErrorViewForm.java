package com.thingtrack.konekti.view.addon.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ErrorViewForm extends Window {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout errorFooterLayout;
	@AutoGenerated
	private Button buttonClose;
	@AutoGenerated
	private Button buttonShowStackTrace;
	@AutoGenerated
	private Button buttonCopyStackTrace;
	@AutoGenerated
	private Panel errorStackTracePanel;
	@AutoGenerated
	private VerticalLayout errorStackTraceLayout;
	@AutoGenerated
	private RichTextArea stackTraceValueLabel;
	@AutoGenerated
	private Label stackTraceLabel;
	@AutoGenerated
	private VerticalLayout errorDetailLayout;
	@AutoGenerated
	private Label errorDetailMessageLabel;
	@AutoGenerated
	private Label errorDetailTittleLabel;
	@AutoGenerated
	private AbsoluteLayout errorHeaderLayout;
	@AutoGenerated
	private Label errorHeaderTittleLabel;
	@AutoGenerated
	private Embedded imageErrorImage;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	private Window parentWindow;  // Reference to main window
	private Throwable error;
	
	private boolean stackTraceShowed = false;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ErrorViewForm(Throwable error, Window main) {
		this("Error Konekti", "Konekti exception not captured!", error, main);
	}
	
	public ErrorViewForm(String message, Throwable error, Window main) {
		this("Error Konekti", message, error, main);
	}
	
	public ErrorViewForm(String tittle, String message, Throwable error, Window main) {
		super(tittle);

		buildMainLayout();		
		setContent(mainLayout);
		
		this.parentWindow = main;
		this.error = error;
		
		if (message != null)
			errorHeaderTittleLabel.setValue("<h2>" + message+ "</h2>");
		else
			errorHeaderTittleLabel.setValue("<h2>Konekti exception not captured!</h2>");		
		errorHeaderTittleLabel.setContentMode(Label.CONTENT_XHTML);
		
		errorDetailTittleLabel.setValue("<h3><b>Konekti Internal Error:</b></h3>");
		errorDetailTittleLabel.setContentMode(Label.CONTENT_XHTML);
		
		if (error != null)
			errorDetailMessageLabel.setValue(error.toString());
		
		stackTraceLabel.setValue("<h3><b>Konekti Stack Trace:</b></h3>");
		stackTraceLabel.setContentMode(Label.CONTENT_XHTML);
		
		if (error != null && error.getStackTrace() != null) {
			String errorValue = "<b>Exception:</b>";
			errorValue = errorValue + "<p>" + stackTraceToString(error.getStackTrace()) + "<p>";
			
			stackTraceValueLabel.setValue(errorValue);
		}
		
		stackTraceValueLabel.setReadOnly(true);
		errorStackTracePanel.setVisible(false);
		buttonClose.addListener(new ClickListener() {	
			public void buttonClick(ClickEvent event) {
				close();
				
			}
		});
		
		buttonShowStackTrace.addListener(new ClickListener() {	
			public void buttonClick(ClickEvent event) {
				if (!stackTraceShowed){
					setHeight("500px");
					
					errorStackTracePanel.setVisible(true);
					buttonShowStackTrace.setCaption("Cerrar Stack Trace");
					buttonCopyStackTrace.setVisible(false);
					
					stackTraceShowed = true;
				}
				else {
					setHeight("300px");
					
					errorStackTracePanel.setVisible(false);
					buttonShowStackTrace.setCaption("Mostrar Stack Trace");
					buttonCopyStackTrace.setVisible(false);
					
					stackTraceShowed = false;
				}
			}
		});
		
		buttonCopyStackTrace.addListener(new ClickListener() {			
			public void buttonClick(ClickEvent event) {			
				CopyToClipBoard();
				
			}
		});
		
		this.setReadOnly(true);
		this.setResizable(false);
		this.setHeight("300px");
		this.setWidth("600px");
		this.buttonCopyStackTrace.setVisible(false);
	}

	private void CopyToClipBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append("Exception:");
		sb.append("\n");
		sb.append(stackTraceToString(error.getStackTrace()));
		sb.append("\n");
		sb.append("Caused by:");
		sb.append("\n");
		sb.append(stackTraceToString(error.getCause().getStackTrace()));
		
		//cb.setClipboardText(sb.toString());
		
	}
	
	public String stackTraceToString(StackTraceElement[] st) {
	    StringBuilder sb = new StringBuilder();
	    for (StackTraceElement element : st) {
	        sb.append(element.toString());
	        sb.append("\n");
	    }
	    
	    return sb.toString();
	}
	
	public String causeToString(StackTraceElement[] st) {
	    StringBuilder sb = new StringBuilder();
	    for (StackTraceElement element : st) {
	        sb.append(element.toString());
	        sb.append("\n");
	    }
	    
	    return sb.toString();
	}
	
	public void show() {
		setModal(true);
		parentWindow.addWindow(this);
		
	}
	
	public void close() {
		parentWindow.removeWindow(this);
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// errorHeaderLayout
		errorHeaderLayout = buildErrorHeaderLayout();
		mainLayout.addComponent(errorHeaderLayout);
		
		// errorDetailLayout
		errorDetailLayout = buildErrorDetailLayout();
		mainLayout.addComponent(errorDetailLayout);
		
		// errorStackTracePanel
		errorStackTracePanel = buildErrorStackTracePanel();
		mainLayout.addComponent(errorStackTracePanel);
		mainLayout.setExpandRatio(errorStackTracePanel, 1.0f);
		
		// errorFooterLayout
		errorFooterLayout = buildErrorFooterLayout();
		mainLayout.addComponent(errorFooterLayout);
		
		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildErrorHeaderLayout() {
		// common part: create layout
		errorHeaderLayout = new AbsoluteLayout();
		errorHeaderLayout.setImmediate(false);
		errorHeaderLayout.setWidth("100.0%");
		errorHeaderLayout.setHeight("100px");
		errorHeaderLayout.setMargin(false);
		
		// imageErrorImage
		imageErrorImage = new Embedded();
		imageErrorImage.setImmediate(false);
		imageErrorImage.setWidth("60px");
		imageErrorImage.setHeight("60px");
		imageErrorImage.setSource(new ThemeResource(
				"images/exclamation.png"));
		imageErrorImage.setType(1);
		imageErrorImage.setMimeType("image/png");
		errorHeaderLayout.addComponent(imageErrorImage,
				"top:20.0px;left:20.0px;");
		
		// errorHeaderTittleLabel
		errorHeaderTittleLabel = new Label();
		errorHeaderTittleLabel.setImmediate(false);
		errorHeaderTittleLabel.setWidth("100.0%");
		errorHeaderTittleLabel.setHeight("100.0%");
		errorHeaderTittleLabel.setValue("Error Header");
		errorHeaderLayout.addComponent(errorHeaderTittleLabel,
				"top:20.0px;right:36.0px;bottom:20.0px;left:118.0px;");
		
		return errorHeaderLayout;
	}

	@AutoGenerated
	private VerticalLayout buildErrorDetailLayout() {
		// common part: create layout
		errorDetailLayout = new VerticalLayout();
		errorDetailLayout.setImmediate(false);
		errorDetailLayout.setWidth("100.0%");
		errorDetailLayout.setHeight("100px");
		errorDetailLayout.setMargin(false, true, false, true);
		
		// errorDetailTittleLabel
		errorDetailTittleLabel = new Label();
		errorDetailTittleLabel.setImmediate(false);
		errorDetailTittleLabel.setWidth("-1px");
		errorDetailTittleLabel.setHeight("-1px");
		errorDetailTittleLabel.setValue("Konekti Internal Error:");
		errorDetailLayout.addComponent(errorDetailTittleLabel);
		
		// errorDetailMessageLabel
		errorDetailMessageLabel = new Label();
		errorDetailMessageLabel.setImmediate(false);
		errorDetailMessageLabel.setWidth("100.0%");
		errorDetailMessageLabel.setHeight("100.0%");
		errorDetailMessageLabel.setValue("Konekti Internal Error Value");
		errorDetailLayout.addComponent(errorDetailMessageLabel);
		errorDetailLayout.setExpandRatio(errorDetailMessageLabel, 1.0f);
		
		return errorDetailLayout;
	}

	@AutoGenerated
	private Panel buildErrorStackTracePanel() {
		// common part: create layout
		errorStackTracePanel = new Panel();
		errorStackTracePanel.setImmediate(false);
		errorStackTracePanel.setWidth("100.0%");
		errorStackTracePanel.setHeight("100.0%");
		errorStackTracePanel.setScrollable(true);
		
		// errorStackTraceLayout
		errorStackTraceLayout = buildErrorStackTraceLayout();
		errorStackTracePanel.setContent(errorStackTraceLayout);
		
		return errorStackTracePanel;
	}

	@AutoGenerated
	private VerticalLayout buildErrorStackTraceLayout() {
		// common part: create layout
		errorStackTraceLayout = new VerticalLayout();
		errorStackTraceLayout.setImmediate(false);
		errorStackTraceLayout.setWidth("100%");
		errorStackTraceLayout.setHeight("-1px");
		errorStackTraceLayout.setMargin(false, true, false, true);
		
		// stackTraceLabel
		stackTraceLabel = new Label();
		stackTraceLabel.setImmediate(true);
		stackTraceLabel.setWidth("100.0%");
		stackTraceLabel.setHeight("-1px");
		stackTraceLabel.setValue("Stack trace:");
		errorStackTraceLayout.addComponent(stackTraceLabel);
		
		// stackTraceValueLabel
		stackTraceValueLabel = new RichTextArea();
		stackTraceValueLabel.setImmediate(true);
		stackTraceValueLabel.setWidth("100%");
		stackTraceValueLabel.setHeight("-1px");
		stackTraceValueLabel.setValue("Stack Trace Value");
		errorStackTraceLayout.addComponent(stackTraceValueLabel);
		errorStackTraceLayout.setExpandRatio(stackTraceValueLabel, 1.0f);
		
		return errorStackTraceLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildErrorFooterLayout() {
		// common part: create layout
		errorFooterLayout = new HorizontalLayout();
		errorFooterLayout.setImmediate(false);
		errorFooterLayout.setWidth("100.0%");
		errorFooterLayout.setHeight("50px");
		errorFooterLayout.setMargin(false, true, false, true);
		errorFooterLayout.setSpacing(true);
		
		// buttonCopyStackTrace
		buttonCopyStackTrace = new Button();
		buttonCopyStackTrace.setCaption("Copiar Stack Error");
		buttonCopyStackTrace.setWidth("-1px");
		buttonCopyStackTrace.setHeight("-1px");
		errorFooterLayout.addComponent(buttonCopyStackTrace);
		errorFooterLayout.setExpandRatio(buttonCopyStackTrace, 1.0f);
		errorFooterLayout.setComponentAlignment(buttonCopyStackTrace,
				new Alignment(34));
		
		// buttonShowStackTrace
		buttonShowStackTrace = new Button();
		buttonShowStackTrace.setCaption("Mostrar Stack Error");
		buttonShowStackTrace.setImmediate(true);
		buttonShowStackTrace.setWidth("-1px");
		buttonShowStackTrace.setHeight("-1px");
		errorFooterLayout.addComponent(buttonShowStackTrace);
		errorFooterLayout.setComponentAlignment(buttonShowStackTrace,
				new Alignment(34));
		
		// buttonClose
		buttonClose = new Button();
		buttonClose.setCaption("Cerrar");
		buttonClose.setImmediate(true);
		buttonClose.setWidth("-1px");
		buttonClose.setHeight("-1px");
		errorFooterLayout.addComponent(buttonClose);
		errorFooterLayout.setComponentAlignment(buttonClose, new Alignment(34));
		
		return errorFooterLayout;
	}
}
