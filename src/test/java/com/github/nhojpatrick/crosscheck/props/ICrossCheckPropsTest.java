package com.github.nhojpatrick.crosscheck.props;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

/**
 * Test class for ICrossCheckProps interface.
 * 
 * @author john
 */
public class ICrossCheckPropsTest {

    /**
     * Confirm the default properties regex compiles.
     */
    @Test(groups = { "compile", "full" })
    public void propertiesRegex_defaultCompileCheck() {

        Pattern.compile(ICrossCheckProps.DEFAULT_PROPERTIES_REGEX);
    }

}
