package com.github.nhojpatrick.crosscheck.props;

/**
 * Interface definition of Cross Check Props.
 * 
 * @author john
 */
public interface ICrossCheckProps {

    /**
     * Default
     */
    public static final String DEFAULT_MASTER_TEMPLATE_NAME = "resource_TEMPLATE.properties";

    /**
     * Default properties regex.
     */
    public static final String DEFAULT_PROPERTIES_REGEX = "resource_.*.properties";

    /**
     * Execute Cross Check Props.
     */
    public void execute();

    /**
     * Get the active master template name.
     * 
     * @return the current active master template name.
     */
    public String getMasterTemplateName();

    /**
     * Get the active properties regex.
     * 
     * @return the current active properties regex.
     */
    public String getPropertiesRegex();

    /**
     * Set a new properties regex.
     * 
     * @param pMasterTemplateName
     *            set a new master template name.
     * @return was the new value accepted and set.
     */
    public boolean setMasterTemplateName(final String pMasterTemplateName);

    /**
     * Set a new properties regex.
     * 
     * @param pPropertiesRegex
     *            set a new properties regex.
     * @return was the new value accepted and set.
     */
    public boolean setPropertiesRegex(final String pPropertiesRegex);

}
