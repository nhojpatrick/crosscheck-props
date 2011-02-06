package com.github.nhojpatrick.crosscheck.props;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.regex.PatternSyntaxException;

import org.testng.annotations.Test;

/**
 * Test class for CrossCheckProps implementation.
 * 
 * @author john
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
