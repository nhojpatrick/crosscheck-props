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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import org.testng.annotations.Test;

/**
 * Test class for CrossCheckProps implementation.
 * 
 * @author nhojpatrick
 */
public class CrossCheckPropsTest {

    @Test(groups = { "compile", "full" })
    public void execute_defaultCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        crossCheckProps.execute();
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_defaultGetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertEquals("Unexpected default", ICrossCheckProps.DEFAULT_PROPERTIES_REGEX,
                crossCheckProps.getPropertiesRegex());
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_defaultSetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default", crossCheckProps.setPropertiesRegex(ICrossCheckProps.DEFAULT_PROPERTIES_REGEX));

        assertEquals("Unexpected default", ICrossCheckProps.DEFAULT_PROPERTIES_REGEX,
                crossCheckProps.getPropertiesRegex());
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_nullSetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertFalse("Unexpected default", crossCheckProps.setPropertiesRegex(null));
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_emptySetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertFalse("Unexpected default", crossCheckProps.setPropertiesRegex(""));
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_simpleRegexAll() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default", crossCheckProps.setPropertiesRegex("[*].properties"));
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_simpleRegexAllPrefix() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default", crossCheckProps.setPropertiesRegex("test_[*].properties"));
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_simpleRegexSingle() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default", crossCheckProps.setPropertiesRegex("[?].properties"));
    }

    @Test(groups = { "compile", "full" })
    public void propertiesRegex_simpleRegexSinglePrefix() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default", crossCheckProps.setPropertiesRegex("test_[?].properties"));
    }

    @Test(groups = { "compile", "full" }, expectedExceptions = PatternSyntaxException.class)
    public void propertiesRegex_simpleRegexSinglePrefixInvalid() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        crossCheckProps.setPropertiesRegex("test_[?.properties");
    }

    @Test(groups = { "compile", "full" })
    public void masterTemplateName_defaultGetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertEquals("Unexpected default", ICrossCheckProps.DEFAULT_MASTER_TEMPLATE_NAME,
                crossCheckProps.getMasterTemplateName());
    }

    @Test(groups = { "compile", "full" })
    public void masterTemplateName_defaultSetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertTrue("Unexpected default",
                crossCheckProps.setMasterTemplateName(ICrossCheckProps.DEFAULT_MASTER_TEMPLATE_NAME));

        assertEquals("Unexpected default", ICrossCheckProps.DEFAULT_MASTER_TEMPLATE_NAME,
                crossCheckProps.getMasterTemplateName());
    }

    @Test(groups = { "compile", "full" })
    public void masterTemplateName_nullSetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertFalse("Unexpected default", crossCheckProps.setMasterTemplateName(null));
    }

    @Test(groups = { "compile", "full" })
    public void masterTemplateName_emptySetCheck() {

        final ICrossCheckProps crossCheckProps = new CrossCheckProps();

        assertFalse("Unexpected default", crossCheckProps.setMasterTemplateName(""));
    }

}
