/*
 * Copyright (c) 2015 https://github.com/nhojpatrick
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.nhojpatrick.crosscheck.props;

/**
 * Interface definition of Cross Check Props.
 * 
 * @author nhojpatrick
 */
public interface ICrossCheckProps {

    /**
     * Default
     */
    String DEFAULT_MASTER_TEMPLATE_NAME = "resource_TEMPLATE.properties";

    /**
     * Default properties regex.
     */
    String DEFAULT_PROPERTIES_REGEX = "resource_.*.properties";

    /**
     * Execute Cross Check Props.
     */
    void execute();

    /**
     * Get the active master template name.
     * 
     * @return the current active master template name.
     */
    String getMasterTemplateName();

    /**
     * Get the active properties regex.
     * 
     * @return the current active properties regex.
     */
    String getPropertiesRegex();

    /**
     * Set a new properties regex.
     * 
     * @param pMasterTemplateName
     *            set a new master template name.
     * @return was the new value accepted and set.
     */
    boolean setMasterTemplateName(final String pMasterTemplateName);

    /**
     * Set a new properties regex.
     * 
     * @param pPropertiesRegex
     *            set a new properties regex.
     * @return was the new value accepted and set.
     */
    boolean setPropertiesRegex(final String pPropertiesRegex);

}
