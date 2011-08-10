package org.linkAnalysis.web.controller;

import org.linkAnalysis.service.util.ServiceResult;
import org.linkAnalysis.service.util.ValidationResult;
import org.linkAnalysis.web.util.AjaxResult;

/**
 * @author Pavel Karpukhin
 */
public abstract class AbstractController {

    protected ValidationResult validationResult;

    public <T> ServiceResult<T> executeServiceMethod(Invokable<T> invokable) {
        try {
            return processServiceResult(invokable.invoke());
        } catch (Exception e) {
            return processException(e);
        }
    }

    public <T> ServiceResult<T> processServiceResult(ServiceResult<T> serviceResult) {
        for (ValidationResult.MessageInfo messageInfo : serviceResult.getErrors()) {
            validationResult.addError(messageInfo);
        }
        return serviceResult;
    }

    public <T> ServiceResult<T> processException(Exception e) {
        ServiceResult<T> r = new ServiceResult<T>(null, false);
        validationResult.addError(e.getMessage());
        return r;
    }

    public AjaxResult ajaxResult() {
        if (validationResult == null) {
            validationResult = new ValidationResult();
        }
        return AjaxResult.fromValidationResult(validationResult);
    }
}
