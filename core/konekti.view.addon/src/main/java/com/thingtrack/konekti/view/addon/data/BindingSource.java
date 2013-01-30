package com.thingtrack.konekti.view.addon.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class BindingSource<BEANTYPE> extends BeanItemContainer<BEANTYPE>
		implements Serializable {
	
	private int index = -1;
	private int level = 0;
	
	private List<IndexChangeListener> indexChangeListeners = new ArrayList<IndexChangeListener>();
	private List<IndexChangeListener> indexChangeToolBarListeners = new ArrayList<IndexChangeListener>();

	private IndexChangeListener listenerDataGridView = null;
	private IndexChangeListener listenerFormView = null;

    /**
     * The type of the beans in the container.
     */
    private Class<? super BEANTYPE> type = null;
    
	public BindingSource(Class<? super BEANTYPE> type) throws IllegalArgumentException {
		super(type);

		this.type = type;
	}

	public BindingSource(Class<? super BEANTYPE> type, int level) throws IllegalArgumentException {
		super(type);

		this.type = type;
		this.setLevel(level);
		
	}
	
	public BindingSource(Class<? super BEANTYPE> type, Collection<? extends BEANTYPE> collection) throws IllegalArgumentException {
		this(type, collection, 0);

		this.type = type;
	}

	public BindingSource(Class<? super BEANTYPE> type, Collection<? extends BEANTYPE> collection, int level) throws IllegalArgumentException {
		super(type, collection);

		this.type = type;
		this.setLevel(level);

	}
	
	@Override
	public BeanItem<BEANTYPE> addItem(Object itemId) {
		BeanItem<BEANTYPE> beanItem = super.addItem(itemId);

		// get the index itemId
		index = indexOfId(itemId);

		// Notify Component listeners
		setItemId(beanItem.getBean());

		return beanItem;
	}

	@Override
	public boolean removeItem(Object itemId) {
		if (!containsId(itemId))
			return false;

		int tempIndex = indexOfId(itemId);

		// update the index if success
		if (super.removeItem(itemId)) {
			if (tempIndex > 0)
				index = tempIndex - 1;
			else
				index = tempIndex;
			
			BEANTYPE previousItemId = null;
			
			if(getItemIds().size() > 0)
				previousItemId = getIdByIndex(index);

			// Notify Component listeners
			setItemId(previousItemId);

			return true;
		}

		return false;
	}

	public BEANTYPE firstItem() {
		BEANTYPE result = firstItemId();

		if (result == null)
			return null;

		index = 0;
		
		// Notify Component listeners
		setItemId(result);

		return result;
	}

	public BEANTYPE nextItem() {
		if (index == size() - 1) {
			
			// Notify Component listeners
			setItemId(lastItemId());
			
			return lastItemId();
		}

		index++;

		BEANTYPE bean =  getIdByIndex(index);
		
		// Notify Component listeners
		setItemId(bean);
		
		return bean;
	}

	public BEANTYPE prevItem() {
		if (index == 0) {
			
			//Notify Component Listeners
			setItemId(firstItemId());
			
			return firstItemId();
		}

		index--;

		BEANTYPE bean = getIdByIndex(index);
		
		//Notify Component Listeners
		setItemId(bean);
		
		return bean;
	}

	public BEANTYPE lastItem() {
		BEANTYPE result = lastItemId();

		if (result != null)
			index = size() - 1;

		setItemId(result);
		
		return result;
	}

	public BEANTYPE getItemId() {
		return getIdByIndex(index);
		
	}

	public int setItemId(Object itemId) {
		return setItemId(null, false, itemId);
		
	}
	
	public int setItemId(Object source, boolean executable, Object itemId) {		
		index = indexOfId(itemId);
		
		// send events to ToolBar Components subscribed
		sendToolBarEvents(source, executable, itemId, index);

		// send events to FormView Components subscribed
		if (listenerFormView != null)			
			listenerFormView.bindingSourceIndexChange(new IndexChangeEvent(source, executable, getItem(itemId), index));		

		// send events to DataGridView Components subscribed
		if (listenerDataGridView != null)
			listenerDataGridView.bindingSourceIndexChange(new IndexChangeEvent(itemId, index));


		// send events to rest of Components subscribed
		sendEvents(source, executable, itemId, index);

		return index;

	}

	public int getIndex() {
		return index + 1;

	}
	
	private void sendToolBarEvents(Object source, boolean executable, Object itemId, int index) {
		for (IndexChangeListener listener : indexChangeToolBarListeners)
			listener.bindingSourceIndexChange(new IndexChangeEvent(source, executable, itemId, index));

	}
	
	private void sendEvents(Object source, boolean executable, Object itemId, int index) {
		for (IndexChangeListener listener : indexChangeListeners)
			listener.bindingSourceIndexChange(new IndexChangeEvent(source, executable, itemId, index));

	}

	public BEANTYPE Initialize() {		
		return firstItem();
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
	
	public void addListenerToolBar(IndexChangeListener listener) {		
		indexChangeToolBarListeners.add(listener);
		
		//index = -1;
		//BEANTYPE result = Initialize();
		
		BEANTYPE result = index == -1 ? Initialize() : getItemId();
		
		listener.bindingSourceIndexChange(new IndexChangeEvent(result, index));

	}

	public void addListenerDataGridView(IndexChangeListener listener) {
		this.listenerDataGridView = listener;

	}

	public void addListenerFormView(IndexChangeListener listener) {
		this.listenerFormView = listener;

	}

	public void addListener(IndexChangeListener listener) {
		indexChangeListeners.add(listener);

	}

	public interface IndexChangeListener extends Serializable {
		public void bindingSourceIndexChange(IndexChangeEvent event);

	}

	public static class IndexChangeEvent {
		private Object source;
		private Object register;
		private int index;
		private boolean executable;

		public IndexChangeEvent(Object register, int index) {
			this(null, false, register, index);

		}

		public IndexChangeEvent(Object source, boolean executable, Object register, int index) {
			this.source = source;
			this.executable = executable;
			this.index = index;
			this.register = register;
		}

		public Object getSource() {
			return this.source;

		}

		public boolean isExecutable() {
			return this.executable;

		}

		public int getIndex() {
			return this.index;

		}

		public Object getRegister() {
			return this.register;

		}

	}
	
}