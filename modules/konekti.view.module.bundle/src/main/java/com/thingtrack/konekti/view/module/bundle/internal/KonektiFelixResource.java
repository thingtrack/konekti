package com.thingtrack.konekti.view.module.bundle.internal;

import org.apache.felix.bundlerepository.Capability;
import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.Requirement;
import org.apache.felix.bundlerepository.Resource;
import org.osgi.framework.Bundle;

public class KonektiFelixResource {
	private String id;
	private Long bundleId;
	private String symbolicName;
	private String version;
	private String presentationName;	
	private Repository repository;
	private String url;	
	private Requirement[] requeriments;
	private Capability[] capabilities;
	private String[] categories;
	private String licenseUrl;
	private String description;
	private String documentation;
	private String copyright;
	private String sourceUrl;
	private String size;
	private String[] keys;
	private Resource resource;
	private String bundleStatus;
	private Bundle bundle;
	
	public KonektiFelixResource() {	
	}
	
	public KonektiFelixResource(String id, Long bundleId, String symbolicName, String version,
			String presentationName, Repository repository, String url,
			Requirement[] requeriments, Capability[] capabilities, String[] categories,
			String licenseUrl, String description, String documentation,
			String copyright, String sourceUrl, String size, String[] keys, Resource resource,
			String bundleStatus, Bundle bundle) {
		super();
		this.id = id;
		this.bundleId = bundleId;
		this.symbolicName = symbolicName;
		this.version = version;
		this.presentationName = presentationName;
		this.repository = repository;
		this.url = url;
		this.requeriments = requeriments;
		this.capabilities = capabilities;
		this.categories = categories;
		this.licenseUrl = licenseUrl;
		this.description = description;
		this.documentation = documentation;
		this.copyright = copyright;
		this.sourceUrl = sourceUrl;
		this.size = size;
		this.keys = keys;
		this.resource = resource;
		this.bundleStatus = bundleStatus;
		this.bundle = bundle;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the presentationName
	 */
	public String getPresentationName() {
		return presentationName;
	}
	/**
	 * @param presentationName the presentationName to set
	 */
	public void setPresentationName(String presentationName) {
		this.presentationName = presentationName;
	}
	/**
	 * @return the symbolicName
	 */
	public String getSymbolicName() {
		return symbolicName;
	}
	/**
	 * @param symbolicName the symbolicName to set
	 */
	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the repository
	 */
	public Repository getRepository() {
		return repository;
	}
	/**
	 * @param repository the repository to set
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the requeriments
	 */
	public Requirement[] getRequeriments() {
		return requeriments;
	}
	/**
	 * @param requeriments the requeriments to set
	 */
	public void setRequeriments(Requirement[] requeriments) {
		this.requeriments = requeriments;
	}
	/**
	 * @return the capabilities
	 */
	public Capability[] getCapabilities() {
		return capabilities;
	}
	/**
	 * @param capabilities the capabilities to set
	 */
	public void setCapabilities(Capability[] capabilities) {
		this.capabilities = capabilities;
	}
	/**
	 * @return the licenseUrl
	 */
	public String getLicenseUrl() {
		return licenseUrl;
	}
	/**
	 * @param licenseUrl the licenseUrl to set
	 */
	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return documentation;
	}
	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	/**
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}
	/**
	 * @param sourceUrl the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the keys
	 */
	public String[] getKeys() {
		return keys;
	}
	/**
	 * @param keys the keys to set
	 */
	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	/**
	 * @return the categories
	 */
	public String[] getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the bundleStatus
	 */
	public String getBundleStatus() {
		return bundleStatus;
	}

	/**
	 * @param bundleStatus the bundleStatus to set
	 */
	public void setBundleStatus(String bundleStatus) {
		this.bundleStatus = bundleStatus;
	}

	/**
	 * @return the bundle
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * @param bundle the bundle to set
	 */
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * @return the bundleId
	 */
	public Long getBundleId() {
		return bundleId;
	}

	/**
	 * @param bundleId the bundleId to set
	 */
	public void setBundleId(Long bundleId) {
		this.bundleId = bundleId;
	}
	
	
}
