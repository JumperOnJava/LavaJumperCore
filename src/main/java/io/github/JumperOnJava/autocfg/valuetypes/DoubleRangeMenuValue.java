package io.github.JumperOnJava.autocfg.valuetypes;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import dev.isxander.yacl3.gui.controllers.string.number.DoubleFieldController;
import io.github.JumperOnJava.autocfg.Configurable;
import io.github.JumperOnJava.autocfg.FieldValue;
import io.github.JumperOnJava.autocfg.SerializerContainer;

public class DoubleRangeMenuValue extends RangeMenuValue {

    public DoubleRangeMenuValue(String translationKey, String path, FieldValue value, Configurable metadata, SerializerContainer classDataContainer) {
        super(translationKey,path, value, metadata, classDataContainer);
    }

    public Class getTarget(){return double.class;}

    public Controller getController(Option<?> option) {
        if(minValue == maxValue)
        {
            return new DoubleFieldController((Option<Double>) option);
        }

        if(interval == 0)
            throw new RuntimeException(String.format("Double field %s have 0 interval", getTranslationKey()));
        return new DoubleSliderController((Option<Double>) option, minValue, maxValue,interval);
    }
}
