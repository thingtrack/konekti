package com.thingtrack.konekti.view.addon.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.tepi.filtertable.FilterTable;
import org.tepi.filtertable.datefilter.DateInterval;

import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeEvent;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeListener;
import com.thingtrack.konekti.view.addon.data.BindingSourceComponent;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickAddButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickEditButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickRemoveButtonListener;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class DataGridView extends FilterTable implements BindingSourceComponent, IndexChangeListener, ItemClickListener, Handler {
	private BindingSource<?> bindingSource;	
    private BindingSourceChangeDataGridViewListener listener;
    
	// Actions for the context menu
    private static final Action ACTION_ADD_REGISTER = new Action("Añadir Registro");
    private static final Action ACTION_EDIT_REGISTER = new Action("Editar Registro");
    private static final Action ACTION_DELETE_REGISTER = new Action("Borrar Registro");
    private static final Action ACTION_ADD_FILTER_REGISTER = new Action("Usar como Filtro");
    private static final Action ACTION_REMOVE_FILTER_REGISTER = new Action("Borrar Filtro");
    private static final Action ACTION_REMOVE_ALL_FILTER_REGISTER = new Action("Borrar todos los Filtro");
    
	private ClickAddButtonListener listenerAddButton = null;
	private ClickEditButtonListener listenerEditButton = null;
	private ClickRemoveButtonListener listenerRemoveButton = null;
	private ClickFilterDataGridViewListener listenerFilterButton = null;
	
	private Object propertyIdSelected = null;
	private Object cellSelected = null;
	
	private boolean isEnabledFilters = false;
	
	public DataGridView() {
		this(null);
		
	}
	
	public DataGridView(String caption) {
		super(caption);
				
		// define default DataGridView properties
		DefaultDataGridViewConfig();
		
		// add Shortcuts listeners
		addShortCuts();
		
		// add Item Click Listener
		addListener((ItemClickListener)this);
		
		//this.setImmediate(true);
		
		addActionHandler(this);
	}
	
	private void DefaultDataGridViewConfig() {		
		// inmediate datagrid events
		setImmediate(true);
		
		// selectable table
		setSelectable(true);
		
		// disable multiselectable table
		setMultiSelect(false);
		
		// not deselected a selected register
		setNullSelectionAllowed(false);
		
		// reorder columns
		setColumnReorderingAllowed(true);
		
		// collapsable columns 
		setColumnCollapsingAllowed(true);
	
	}
	
	private void addShortCuts() {
		this.addShortcutListener(new ShortcutListener("Enter", ShortcutAction.KeyCode.ENTER, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
				
				if (!(target instanceof DataGridView))
					return;
				
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null)
					slideComponent.getBindingSource().setItemId(this, true, slideComponent.getValue());
				
			}
		});
					
		this.addShortcutListener(new ShortcutListener("Down", ShortcutAction.KeyCode.ARROW_DOWN, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
			
				if (!(target instanceof DataGridView))
					return;
				
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null) {					
					for (int index = 0; index < slideComponent.getContainerPropertyIds().size(); index ++) {
						if (slideComponent.getIdByIndex(index) == slideComponent.getValue()) {						
							slideComponent.getBindingSource().setItemId(slideComponent.getIdByIndex(index + 1));
							
							return;
						}
					}
					
				}
				
			}
		});
		
		this.addShortcutListener(new ShortcutListener("Up", ShortcutAction.KeyCode.ARROW_UP, new int[0]) {		
			@Override
			public void handleAction(Object sender, Object target) {
				if (!(target instanceof DataGridView))
					return;
				
				DataGridView slideComponent = (DataGridView)target;
				
				if (slideComponent == null)
					return;
				
				// send bound data and execute the slide movement
				if (slideComponent.getValue() != null) {					
					for (int index = 0; index < slideComponent.getContainerPropertyIds().size(); index ++) {
						if (slideComponent.getIdByIndex(index) == slideComponent.getValue()) {
							slideComponent.getBindingSource().setItemId(slideComponent.getIdByIndex(index - 1));
							
							return;
						}
					}
					
				}
				
			}
		});
		
	}
		
	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
	
		bindingSource.addListenerDataGridView((IndexChangeListener)this);
		
		setContainerDataSource(bindingSource);
		
		if (listener != null)
			listener.bindingSourceChangeDataGridView(new BindingSourceChangeDataGrifViewEvent(this, bindingSource));
		
	}
	
	@Override
	public BindingSource<?> getBindingSource() {
		return this.bindingSource;
		
	}
	
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (bindingSource != null) {
			select(event.getRegister());
			
			//setCurrentPageFirstItemId(event.getRegister());
		}
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		propertyIdSelected = event.getPropertyId();
		
		if (propertyIdSelected != null && event.getItem() != null && event.getItem().getItemProperty(propertyIdSelected) != null)
			cellSelected = event.getItem().getItemProperty(propertyIdSelected).getValue();
		else
			cellSelected = "";
				
		bindingSource.setItemId(this, true, event.getItemId());
		
		if (event.getItemId() != null && event.isDoubleClick()) {
			if (listenerEditButton != null) {
				EditionToolbar.ClickNavigationEvent eventClick = (new EditionToolbar(0, null)).new ClickNavigationEvent((Component) event.getSource(), event.getItemId(), 0);
				listenerEditButton.editButtonClick(eventClick);
			}
		}
		
		/*if (event.getItemId() != null && event.getButton() == MouseEventDetails.BUTTON_RIGHT)
			// navigate down bindingsource
			bindingSource.setItemId(this, true, event.getItemId());
		else if (event.getItemId() != null && event.getButton() == MouseEventDetails.BUTTON_LEFT)
			// set the item selected
			bindingSource.setItemId(event.getItemId());
		else if (event.getItemId() != null && event.isDoubleClick()) {
			if (listenerEditButton != null) {
				EditionToolbar.ClickNavigationEvent eventClick = (new EditionToolbar(0, null)).new ClickNavigationEvent((Component) event.getSource(), event.getItemId(), 0);
				listenerEditButton.editButtonClick(eventClick);
			}
		}*/
		
	}

	public void print(String reportTittle) {
		// STEP01: create PrintViewForm Window
		PrintViewForm printViewForm = new PrintViewForm();		
		printViewForm.setImmediate(false);
		printViewForm.setWidth("100.0%");
		printViewForm.setHeight("100.0%");
				
		
		// STEP 02: add column types to the report of visible columns
		ArrayList<String> visibleColumns = new ArrayList<String>();
		ArrayList<String> tittleColumns = new ArrayList<String>();
		ArrayList<Class<?>> types = new ArrayList<Class<?>>();
		
		for (String visibleColumn : Arrays.copyOf(getVisibleColumns(), getVisibleColumns().length, String[].class)) {
			try {				
				if (!isCollapsable(visibleColumn)) {
					Class<?> type = getType(visibleColumn);
					
					if (type.equals(float.class))
						types.add(Float.class);
					else if (type.equals(int.class))
						types.add(Integer.class);
					else if (type.equals(boolean.class))
						types.add(Boolean.class);
					else if (type.equals(double.class))
						types.add(Double.class);
					else if (type.equals(short.class))
						types.add(Short.class);
					else if (type.equals(byte.class))
						types.add(Byte.class);
					else if (type.equals(long.class))
						types.add(Long.class);					
					else
						types.add(type);
					
					visibleColumns.add(visibleColumn);
					tittleColumns.add(getColumnHeader(visibleColumn));
				}
			}
			catch (Exception e) {
				// do nothing for Columns Generated
			}
			
		}
		
		printViewForm.setReportTittle(reportTittle);		
		printViewForm.setColumnProperties(visibleColumns.toArray(new String[0]));
		printViewForm.setColumnClasses(types.toArray(new Class<?>[0]));
		printViewForm.setColumnTitles(tittleColumns.toArray(new String[0]));
		
		// STEPP 03: add datasource to the report
		printViewForm.setData(getBindingSource().getItemIds());
		
		// STEP 04: generate report
		printViewForm.generateReport();
		
		// STEP 05: show report
		getApplication().getMainWindow().addWindow(printViewForm);
	}
	
	private boolean isCollapsable(String columnId) {
		for(Object propertyId : getContainerPropertyIds()) {
			if(propertyId.toString().equals(columnId)) 
				return isColumnCollapsed(propertyId);
		}
		
		return false;
	}
	
	public void addListener(BindingSourceChangeDataGridViewListener listener) {
		this.listener = listener;
		
	}
	
    public interface BindingSourceChangeDataGridViewListener extends Serializable {
        public void bindingSourceChangeDataGridView(BindingSourceChangeDataGrifViewEvent event);

    }
    
    public static class BindingSourceChangeDataGrifViewEvent {
    	private DataGridView source;
		private BindingSource<?> bindingSource;
		
		public BindingSourceChangeDataGrifViewEvent(DataGridView source, BindingSource<?> bindingSource) {
			this.setSource(source);
			this.setBindingSource(bindingSource);
			
		}

		public void setBindingSource(BindingSource<?> bindingSource) {
			this.bindingSource = bindingSource;
			
		}

		public BindingSource<?> getBindingSource() {
			return bindingSource;
			
		}

		public void setSource(DataGridView source) {
			this.source = source;
		}

		public DataGridView getSource() {
			return source;
		}
		
	 }

    public void setFilterBarVisible() {
    	isEnabledFilters = !isEnabledFilters;
    	
		//		if (isEnabledFilters)
		//		event.getButton().setStyleName("filter-pressed");
		//	else
		//		event.getButton().removeStyleName("filter-pressed");
    	
    	super.setFilterBarVisible(isEnabledFilters);
    }
    
	@Override
	public Action[] getActions(Object target, Object sender) {
		List<Action> actions = new ArrayList<Action>();
		 
		actions.add(ACTION_ADD_FILTER_REGISTER);
		actions.add(ACTION_REMOVE_FILTER_REGISTER);
		actions.add(ACTION_REMOVE_ALL_FILTER_REGISTER);				 
		 
		if (listenerAddButton != null)
			 actions.add(ACTION_ADD_REGISTER);

		if (listenerEditButton != null)
			 actions.add(ACTION_EDIT_REGISTER);

		if (listenerRemoveButton != null)
			 actions.add(ACTION_DELETE_REGISTER);
				
		return actions.toArray(new Action[actions.size()]);
		
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action == ACTION_ADD_REGISTER) {					
			if (listenerAddButton != null) {
				EditionToolbar.ClickNavigationEvent event = (new EditionToolbar(0, null)).new ClickNavigationEvent((Component) sender, target, 0);
				listenerAddButton.addButtonClick(event);
			}
		}
		else if (action == ACTION_EDIT_REGISTER) {			
			if (listenerEditButton != null) {
				EditionToolbar.ClickNavigationEvent event = (new EditionToolbar(0, null)).new ClickNavigationEvent((Component) sender, target, 0);
				listenerEditButton.editButtonClick(event);
			}
		}
		else if (action == ACTION_DELETE_REGISTER) {
			if (listenerRemoveButton != null) {
				EditionToolbar.ClickNavigationEvent event = (new EditionToolbar(0, null)).new ClickNavigationEvent((Component) sender, target, 0);
				listenerRemoveButton.deleteButtonClick(event);
			}
		}
		else if (action == ACTION_ADD_FILTER_REGISTER) {	
			if (propertyIdSelected == null)
				return;
			
			setFilterBarVisible(true);			
			
			if (getType(propertyIdSelected).equals(Date.class)) {
				Date from = (java.util.Date)cellSelected;
				Date to = (java.util.Date)cellSelected;
				
				DateInterval dateInterval = new DateInterval(from, to);
				
				setFilterFieldValue(propertyIdSelected, dateInterval);
				
				if (listenerFilterButton != null)
					listenerFilterButton.filterDataGridViewClick(new ClickFilterEvent((Component) sender, propertyIdSelected, dateInterval));
			}
			else {	
				setFilterFieldValue(propertyIdSelected, cellSelected);
				
				if (listenerFilterButton != null)
					listenerFilterButton.filterDataGridViewClick(new ClickFilterEvent((Component) sender, propertyIdSelected, cellSelected));
			}			
			
		}
		else if (action == ACTION_REMOVE_FILTER_REGISTER) {
			setFilterBarVisible(true);
			setFilterFieldValue(propertyIdSelected, "");
			
			if (listenerFilterButton != null)				
				listenerFilterButton.filterDataGridViewClick(new ClickFilterEvent((Component) sender, propertyIdSelected, cellSelected));
			
		}
		else if (action == ACTION_REMOVE_ALL_FILTER_REGISTER) {
			resetFilters();
			setFilterBarVisible(false);
			
			if (listenerFilterButton != null) 				
				listenerFilterButton.filterDataGridViewClick(new ClickFilterEvent((Component) sender, propertyIdSelected, cellSelected));
			
		}
	}

	public interface ClickFilterDataGridViewListener extends Serializable {
        public void filterDataGridViewClick(ClickFilterEvent event);

    }
	
	public void addListenerAddButton(ClickAddButtonListener listener) {
		this.listenerAddButton = listener;
		
	}
	
	public void addListenerEditButton(ClickEditButtonListener listener) {
		this.listenerEditButton = listener;
		
	}
	
	public void addListenerDeleteButton(ClickRemoveButtonListener listener) {
		this.listenerRemoveButton = listener;
		
	}
	
	public void addListenerFilterButton(ClickFilterDataGridViewListener listener) {
		this.listenerFilterButton = listener;
		
	}
	
	public class ClickFilterEvent extends Event {
		private Object register;
		private Object value;

		public ClickFilterEvent(Component source, Object register, Object value) {
			super(source);

			this.register = register;
			this.value = value;
		}
	
		public Object getRegister() {
			return this.register;
			
		}
		
		public Object getValue() {
			return this.value;
			
		}
	  }
}
