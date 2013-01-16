package com.thingtrack.konekti.view.addon.ui;

import java.io.Serializable;

import org.vaadin.addon.formbinder.ViewBoundForm;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.DateField.UnparsableDateString;

@SuppressWarnings("serial")
public class WindowDialog<BEANTYPE> extends Window {
	  private Window parentWindow;

	  @SuppressWarnings("unused")
	  private CloseWindowDialogListener<?> listener;
	  
	  private String leftButtonCaption;
	  private Button btnLeft;
	  private DialogResult leftButtonDialogResult;
	  
	  private String rightButtonCaption;
	  private Button btnRight;
	  private DialogResult rightButtonDialogResult;
	  
	  private ComponentContainer componentContainer;
	  
	  private DialogResult dialogResultSelected;

	  private VerticalLayout mainLayout;
	  private ViewBoundForm formWindow;
	  private HorizontalLayout footerWindow;
	  
	  private BEANTYPE domainEntity;
	  
	  public enum DialogResult {
		  ABORT, 
		  CANCEL,
		  IGNORE,
		  NO,
		  NONE,
		  OK,
		  RETRY,
		  YES,
		  SAVE
	  }
	  
	  public WindowDialog(Window parentWindow, String windowCaption, String okCaption, String cancelCaption, ComponentContainer layout, BEANTYPE domainEntity, CloseWindowDialogListener<BEANTYPE> listener) {
		  this(parentWindow, windowCaption, okCaption, DialogResult.OK, cancelCaption, DialogResult.CANCEL, layout, domainEntity, listener);
		
	  }
	  
	  public WindowDialog(Window parentWindow, String windowCaption, String leftButtonCaption, final DialogResult leftButtonDialogResult, String rightButtonCaption, final DialogResult rightButtonDialogResult, ComponentContainer componentContainer, final BEANTYPE domainEntity, final CloseWindowDialogListener<BEANTYPE> listener) {
		  this(parentWindow, windowCaption, leftButtonCaption, leftButtonDialogResult, rightButtonCaption, rightButtonDialogResult, componentContainer, new BeanItem<BEANTYPE>(domainEntity), listener);
		  
	  }
	  
	  public WindowDialog(final Window parentWindow, String windowCaption, String leftButtonCaption, final DialogResult leftButtonDialogResult, String rightButtonCaption, final DialogResult rightButtonDialogResult, ComponentContainer componentContainer, final BeanItem<BEANTYPE> domainBeanItemEntity, final CloseWindowDialogListener<BEANTYPE> listener) {
		  super(windowCaption);
		  
		  this.parentWindow = parentWindow;
		  this.leftButtonCaption = leftButtonCaption;
		  this.leftButtonDialogResult = leftButtonDialogResult;
		  this.rightButtonCaption = rightButtonCaption;
		  this.rightButtonDialogResult = rightButtonDialogResult;
		  this.componentContainer = componentContainer;		  
		  this.domainEntity = domainBeanItemEntity.getBean();
		  this.listener = listener;
		  
		  // default Dialog Window Configuration
		  center();
		  setModal(true);
		  setResizable(false);
		  setReadOnly(true);
		  
		  // create view bound form		
		  VerticalLayout content = buildMainLayout();
		  content.setMargin(false);
	      setContent(content);
	      
	      // wrap pojo in a bean item and bind it to the form
	      //formWindow.setItemDataSource(domainBeanItemEntity);
	      
		  this.addListener(new Window.CloseListener() {	  
			  public void windowClose(Window.CloseEvent  e) {
				  if (listener != null) {
					  /*if (dialogResultSelected == rightButtonDialogResult)
						  formWindow.discard();
					  else {
							  try {
								  formWindow.commit();
							  } catch (EmptyValueException ex) {
								  return;
							  } catch (UnparsableDateString ex) {
								  return;
							  } catch (InvalidValueException ex) {
								  return;
							  }
					  }*/
					  
					  listener.windowDialogClose(new CloseWindowDialogEvent<BEANTYPE>(getWindow(), dialogResultSelected, domainEntity));
				  }
				  
		      }
		  });
		  
		  this.parentWindow.addWindow(this);
		  
		  // wrap pojo in a bean item and bind it to the form
		  formWindow.setItemDataSource(domainBeanItemEntity);
	  }
	  
	  /**
	   * Show Dialog Window.
	  */
	  public void showDialog() {
		  this.parentWindow.addWindow(this);
		  
	  }
	  
	  public BEANTYPE getDomainEntity() {
		  return this.domainEntity;
		  
	  }
	  
	  /**
	   * Left result button
	  */
	  public void btnLeft_Click (Button.ClickEvent event) {		  
		  try {
			  dialogResultSelected = leftButtonDialogResult;
			  formWindow.commit();
			  parentWindow.removeWindow(this);
			  
		  } catch (EmptyValueException ex) {
			  // TODO
		  } catch (UnparsableDateString ex) {
			  // TODO
		  } catch (InvalidValueException ex) {
			  // TODO
		  }
		  	      
	  }
	  
	  /**
	   * Right result button
	  */
	  public void btnRight_Click (Button.ClickEvent event) {
		  dialogResultSelected = rightButtonDialogResult;
		  formWindow.discard();
		  parentWindow.removeWindow(this);
		  
	  }
		
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(false);
		
		// Form Window
		formWindow = buildFormWindow();
		formWindow.getFooter().addComponent(buildFooterWindow());
		formWindow.getFooter().addStyleName("windowdialog-footer");
		
		mainLayout.addComponent(formWindow);
		mainLayout.setExpandRatio(formWindow, 1.0f);
		
		return mainLayout;
	}

	private ViewBoundForm buildFormWindow() {
		formWindow = new ViewBoundForm();
		formWindow.setImmediate(false);
		formWindow.setWidth("100%");
		formWindow.setHeight("100%");
		formWindow.setWriteThrough(false);
		formWindow.setInvalidCommitted(false);
		formWindow.setContent(componentContainer);
		
		return formWindow;
	}
		
	private HorizontalLayout buildFooterWindow() {		
		// common part: create layout
		footerWindow = new HorizontalLayout();
		footerWindow.setImmediate(false);
		footerWindow.setWidth(componentContainer.getWidth(), UNITS_PIXELS);
		footerWindow.setHeight("-1px");
		footerWindow.setMargin(true);
		footerWindow.setSpacing(true);
		
		// btnLeft		
		btnLeft = new Button(leftButtonCaption, this, "btnLeft_Click");
		btnLeft.setImmediate(true);
		btnLeft.setWidth("-1px");
		btnLeft.setHeight("-1px");
		footerWindow.addComponent(btnLeft);
		footerWindow.setExpandRatio(btnLeft, 1.0f);
		footerWindow.setComponentAlignment(btnLeft, new Alignment(34));
		
		// btnRight
		btnRight = new Button(rightButtonCaption, this, "btnRight_Click");
		btnRight.setImmediate(true);
		btnRight.setWidth("-1px");
		btnRight.setHeight("-1px");
		footerWindow.addComponent(btnRight);
		footerWindow.setComponentAlignment(btnRight, new Alignment(34));
		
		return footerWindow;
		
	}
	
     /**
      * An interface used for listening to Window Dialog close events. Add the
      * CloseWindowDialogListener to a browser level window or a sub window and
      * {@link CloseWindowDialogListener#windowDialogClose(CloseWindowDialogEvent)} will be called whenever the
      * user closes the window.
      * 
      * <p>
      * Since Vaadin 6.5, removing windows using {@link #removeWindow(Window)}
      * does fire the CloseListener.
      * </p>
      */
	  public interface CloseWindowDialogListener<BEANTYPE> extends Serializable {
	        /**
	         * Called when the user closes a window. Use
	         * {@link CloseWindowDialogEvent#getWindow()} to get a reference to the
	         * {@link Window} that was closed.
	         * 
	         * @param e
	         *            Event containing
	         */
		  public void windowDialogClose(WindowDialog<BEANTYPE>.CloseWindowDialogEvent<BEANTYPE> event);
	  }
	    
	 @SuppressWarnings("hiding")
	 public class CloseWindowDialogEvent<BEANTYPE> extends CloseEvent {
		private final DialogResult dialogResult;
		private BEANTYPE domainEntity;
			  
		public CloseWindowDialogEvent(Component source) {
			super(source);
			this.dialogResult = DialogResult.CANCEL;
		}
		
		public CloseWindowDialogEvent(Component source, DialogResult dialogResult, BEANTYPE domainEntity) {
			super(source);
			this.dialogResult = dialogResult;
			this.domainEntity = domainEntity;
			
		}
		
	    /**
         * Gets the dialog window result
         * 
         * @return the Source of the event.
         */
		public DialogResult getDialogResult() {
			return this.dialogResult;
		}
		
	    /**
         * Gets the dialog window result
         * 
         * @return the Source of the event.
         */
		public BEANTYPE getDomainEntity() {
			return this.domainEntity;
			
		}
		
	  }
}