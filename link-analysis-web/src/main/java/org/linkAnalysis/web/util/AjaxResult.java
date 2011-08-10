package org.linkAnalysis.web.util;

import org.linkAnalysis.service.util.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Pavel Karpukhin
 */
public class AjaxResult {

    private boolean succeed;
    private Map<String, String> fieldErrors = new TreeMap<String, String>();
    private List<String> globalErrors = new ArrayList<String>();
    //private List<String> globalWarnings;
    private List<String> globalMessages;
    //private Map<String, Object> additionalData;

    public static AjaxResult fromValidationResult(ValidationResult validationResult) {
        AjaxResult ajaxResult = new AjaxResult();

        ajaxResult.succeed = validationResult.isSucceed();
        for (ValidationResult.MessageInfo messageInfo : validationResult.getErrors()) {
            if (messageInfo.getField() == null) {
                ajaxResult.globalErrors.add(messageInfo.getMessage());
            } else {
                ajaxResult.fieldErrors.put(messageInfo.getField(), messageInfo.getMessage());
            }
        }

        return ajaxResult;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public List<String> getGlobalMessages() {
        return globalMessages;
    }
}
