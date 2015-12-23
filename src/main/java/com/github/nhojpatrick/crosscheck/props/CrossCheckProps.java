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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Implementation of Cross Check Props.
 * 
 * @author nhojpatrick
 */
public class CrossCheckProps implements ICrossCheckProps {

    private static volatile Logger LOG = Logger.getLogger(CrossCheckProps.class);

    /**
     * The active master template name.
     */
    private String masterTemplateName = ICrossCheckProps.DEFAULT_MASTER_TEMPLATE_NAME;

    /**
     * The active properties regex.
     */
    private String propertiesRegex = ICrossCheckProps.DEFAULT_PROPERTIES_REGEX;

    /**
     * {@inheritDoc}
     */
    @Override
	public void execute() {

        final File resourcesDir = agileGetDir();

        /* load and cache template properties file key's */
        final File template = new File(resourcesDir, this.masterTemplateName);
        LOG.debug("template resource = [" + template.toString() + "]");
        final Properties templateProperties = new Properties();
        try {
            final Reader templateReader = new InputStreamReader(new FileInputStream(template), "UTF-8");
            templateProperties.load(templateReader);
            templateReader.close();

        } catch (FileNotFoundException e) {
            LOG.error("TODO [CCP-CORE-000003]", e);
            return;

        } catch (IOException e) {
            LOG.error("TODO [CCP-CORE-000004]", e);
            return;
        }
        final Set<Object> templatePropertiesKeySet = templateProperties.keySet();
        LOG.debug("template properties key set = [" + templatePropertiesKeySet + "]");

        /* all key's */
        final Set<Object> allKeys = new HashSet<Object>();
        allKeys.addAll(templatePropertiesKeySet);

        LOG.debug("#");

        /* load other properties file */
        final FilenameFilter resourcesFilter = new PropertiesRegexFilter(this.propertiesRegex, this.masterTemplateName);
        final File[] rawResources = resourcesDir.listFiles(resourcesFilter);

        final File[] resources = rawResources != null ? rawResources : new File[] {};
        final Results results = new Results();
        for (final File resource : resources) {
            if (!resource.equals(template)) {

                final ResultFile resultsFile = new ResultFile(resource);

                final Properties resourceProperties = new Properties();

                try {
                    final Reader resourceReader = new InputStreamReader(new FileInputStream(resource), "UTF-8");
                    resourceProperties.load(resourceReader);
                    resourceReader.close();

                } catch (FileNotFoundException e) {
                    LOG.error("TODO [CCP-CORE-000001]", e);
                    return;

                } catch (IOException e) {
                    LOG.error("TODO [CCP-CORE-000002]", e);
                    return;
                }

                final Set<Object> resourcePropertiesKeySet = resourceProperties.keySet();
                allKeys.addAll(resourcePropertiesKeySet);
                resultsFile.allKeys.addAll(resourcePropertiesKeySet);

                final Set<Object> resourcePropertiesKeySetAdditions = new HashSet<Object>();
                resourcePropertiesKeySetAdditions.addAll(resourcePropertiesKeySet);
                resourcePropertiesKeySetAdditions.removeAll(templatePropertiesKeySet);
                resultsFile.additionalKeys.addAll(resourcePropertiesKeySetAdditions);

                final Set<Object> resourcePropertiesKeySetMissing = new HashSet<Object>();
                resourcePropertiesKeySetMissing.addAll(templatePropertiesKeySet);
                resourcePropertiesKeySetMissing.removeAll(resourcePropertiesKeySet);
                resultsFile.missingKeys.addAll(resourcePropertiesKeySetMissing);

                LOG.debug(resultsFile.toString());
                results.resultFiles.add(resultsFile);
            }
        }
        LOG.debug("template resource files = [" + results + "]");

        
        /* missing from template */
        final Set<Object> templatePropertiesKeySetMissing = new HashSet<Object>();
        templatePropertiesKeySetMissing.addAll(allKeys);
        templatePropertiesKeySetMissing.removeAll(templatePropertiesKeySet);
        LOG.debug("template properties key set missing = [" + templatePropertiesKeySetMissing + "]");

    }

    private File agileGetDir() {
        final File rootDir;
        try {
            rootDir = new File(new File("./src/test/resources").getCanonicalPath());
            LOG.debug("root dir = [" + rootDir.toString() + "]");
        } catch (IOException e) {
            final RuntimeException newE = new RuntimeException("TODO [CCP-CORE-000005] Unable to determine root dir", e);
            LOG.error(newE);
            throw newE;
        }
        final File resourcesDir = new File(rootDir, "default");
        LOG.debug("resources dir = [" + resourcesDir.toString() + "]");
        return resourcesDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String getMasterTemplateName() {

        final String rtn = this.masterTemplateName;
        LOG.trace("GET [" + rtn + "]");
        return rtn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String getPropertiesRegex() {

        final String rtn = this.propertiesRegex;
        LOG.trace("GET [" + rtn + "]");
        return rtn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean setMasterTemplateName(final String pMasterTemplateName) {

        LOG.trace("SET [" + pMasterTemplateName + "]");

        boolean setMasterTemplateName = true;

        /* validation checks */
        setMasterTemplateName = setMasterTemplateName && pMasterTemplateName != null;
        setMasterTemplateName = setMasterTemplateName && !pMasterTemplateName.equals("");

        if (setMasterTemplateName) {
            LOG.debug("setting masterTemplateName to '" + pMasterTemplateName + "'");
            this.masterTemplateName = pMasterTemplateName;
        }

        LOG.trace("SET [" + setMasterTemplateName + "]");
        return setMasterTemplateName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean setPropertiesRegex(final String pPropertiesRegex) {

        LOG.trace("SET [" + pPropertiesRegex + "]");

        boolean setPropertiesRegex = true;

        /* validation checks */
        setPropertiesRegex = setPropertiesRegex && pPropertiesRegex != null;
        setPropertiesRegex = setPropertiesRegex && !pPropertiesRegex.equals("");

        boolean validRegex = true;
        try {
            Pattern.compile(pPropertiesRegex);

        } catch (NullPointerException npe) {
            validRegex = false;
        }

        if (!validRegex) {
            LOG.debug("invalid regex for '" + pPropertiesRegex + "'");
        }

        if (setPropertiesRegex) {
            LOG.debug("setting propertiesRegex to '" + pPropertiesRegex + "'");
            this.propertiesRegex = pPropertiesRegex;
        }

        LOG.trace("SET [" + setPropertiesRegex + "]");
        return setPropertiesRegex;
    }

    static class PropertiesRegexFilter implements FilenameFilter {

        final Pattern propertiesPattern;

        final String templateName;

        PropertiesRegexFilter(final String pPropertiesRegex, final String pTemplateName) {

            this.propertiesPattern = Pattern.compile("^" + pPropertiesRegex + "$");
            this.templateName = pTemplateName;
        }

        @Override
		public boolean accept(final File pDir, final String pName) {
            LOG.debug("resources filename filter, dir [" + pDir + "], name [" + pName + "]");
            final Matcher propertiesMatcher = this.propertiesPattern.matcher(pName);
            final boolean matches = propertiesMatcher.matches();
            LOG.debug("resources filename filter, dir [" + pDir + "], name [" + pName + "], matches [" + matches + "]");

            final boolean isTemplate = this.templateName.equals(pName);
            LOG.debug("resources filename filter, dir [" + pDir + "], name [" + pName + "], template [" + isTemplate
                    + "]");

            final boolean accept = matches && !isTemplate;
            LOG.debug("resources filename filter, dir [" + pDir + "], name [" + pName + "], accept [" + accept + "]");
            return accept;
        }

    }

    static class Results {

        final List<ResultFile> resultFiles = new ArrayList<ResultFile>();
    }

    static class ResultFile {

        final File resultFile;

        final Set<Object> allKeys = new HashSet<Object>();

        final Set<Object> additionalKeys = new HashSet<Object>();

        final Set<Object> missingKeys = new HashSet<Object>();

        ResultFile(final File pResultFile) {

            this.resultFile = pResultFile;
        }

        @Override
        public String toString() {

            final StringBuilder sbuf = new StringBuilder();

            sbuf.append("ResultFile[");
            sbuf.append("File=" + (this.resultFile == null ? "null" : this.resultFile.toString()) + ";");
            sbuf.append("current=" + (this.allKeys == null ? "null" : this.allKeys) + ";");
            sbuf.append("additions=" + (this.additionalKeys == null ? "null" : this.additionalKeys) + ";");
            sbuf.append("missing=" + (this.missingKeys == null ? "null" : this.missingKeys) + ";");
            sbuf.append("]");

            final String str = sbuf.toString();

            return str;
        }

    }

}
