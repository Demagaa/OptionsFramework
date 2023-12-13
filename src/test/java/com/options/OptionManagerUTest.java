package com.options;

import com.parameters.Parameter;
import org.junit.Test;

public class OptionManagerUTest {
    @Test
    public void testAddOption(){
        boolean required = true;
        String desc = "my test option";
        String[] alias = {"-t", "--test", "tst"};
        Integer def = 0;

        Parameter parameter = OptionManager.createParametr(1, required, Integer.class, def);
        Option option = OptionManager.createOption(parameter, desc, alias);
        assert (option.getDesc().equals(desc));
        assert (option.getParameter().getValue().equals(1));
        assert (option.getAlias().equals(alias));
    }

}
