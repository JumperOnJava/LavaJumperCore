package io.github.JumperOnJava.autocfg.valuetypes;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.string.StringController;
import dev.isxander.yacl3.impl.OptionImpl;
import io.github.JumperOnJava.autocfg.Configurable;
import io.github.JumperOnJava.autocfg.FieldValue;
import io.github.JumperOnJava.autocfg.SerializerContainer;

import java.util.function.Function;

public class StringMenuValue extends MenuValue {
    public Function<String,String> formatter;

    @Override
    public Class<String> getTarget() {
        return String.class;
    }

    public StringMenuValue(String translationKey, String path, FieldValue value, Configurable metadata, SerializerContainer classDataContainer) {
        super(translationKey,path, value, metadata, classDataContainer);
        formatter = s -> s;
    }

    @Override
    public Controller<String> getController(Option<?> option) {
        var c = new StringController((Option<String>) option);
        return c;
    }
}
