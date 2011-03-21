package am.ik.categolj.util;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class BindingResultUtils {
    public static boolean addModelIfErrorsExist(BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return false;
        }
        return true;
    }
}
