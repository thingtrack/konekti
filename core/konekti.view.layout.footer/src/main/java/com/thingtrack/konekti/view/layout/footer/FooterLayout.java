package com.thingtrack.konekti.view.layout.footer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.service.api.ConfigurationService;
import com.thingtrack.konekti.view.kernel.ui.layout.IFooterLayout;

@SuppressWarnings("serial")
public class FooterLayout extends CustomComponent implements IFooterLayout {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel pnFooter;
	@AutoGenerated
	private HorizontalLayout footerHorizontalLayout;
	@AutoGenerated
	private Label lblKonekti;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ConfigurationService configurationService;
	
	private String name;
	private String version;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FooterLayout() {
		getServices();
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		getConfiguration();
		lblKonekti.setValue(name + " Version " + version);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(FooterLayout.class).getBundleContext();
			
			ServiceReference clientTypeServiceReference = bundleContext.getServiceReference(ConfigurationService.class.getName());
			configurationService = ConfigurationService.class.cast(bundleContext.getService(clientTypeServiceReference));
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void getConfiguration() {
		Configuration configuration = null;
		try {
			configuration = configurationService.getByTag(Configuration.TAG.NAME.name());
			name = configuration.getValue();
		} catch (Exception e) {
			name = "KONEKTI";
		}	
		
		try {
			configuration = configurationService.getByTag(Configuration.TAG.VERSION.name());
			version = configuration.getValue();
		} catch (Exception e) {
			version = "0.0.1.SNAPSHOT";
		}
	}
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("30px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("30px");
		
		// pnFooter
		pnFooter = buildPnFooter();
		mainLayout.addComponent(pnFooter);
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPnFooter() {
		// common part: create layout
		pnFooter = new Panel();
		pnFooter.setStyleName("header");
		pnFooter.setImmediate(false);
		pnFooter.setWidth("100.0%");
		pnFooter.setHeight("100.0%");
		
		// footerHorizontalLayout
		footerHorizontalLayout = buildFooterHorizontalLayout();
		pnFooter.setContent(footerHorizontalLayout);
		
		return pnFooter;
	}

	@AutoGenerated
	private HorizontalLayout buildFooterHorizontalLayout() {
		// common part: create layout
		footerHorizontalLayout = new HorizontalLayout();
		footerHorizontalLayout.setImmediate(false);
		footerHorizontalLayout.setWidth("100.0%");
		footerHorizontalLayout.setHeight("100.0%");
		footerHorizontalLayout.setMargin(false);
		footerHorizontalLayout.setSpacing(true);
		
		// lblTime
		lblKonekti = new Label();
		lblKonekti.setStyleName("header-logon");
		lblKonekti.setImmediate(false);
		lblKonekti.setWidth("-1px");
		lblKonekti.setHeight("-1px");
		lblKonekti.setValue("11:20:30");
		footerHorizontalLayout.addComponent(lblKonekti);
		footerHorizontalLayout
				.setComponentAlignment(lblKonekti, new Alignment(34));
		
		return footerHorizontalLayout;
	}

}
