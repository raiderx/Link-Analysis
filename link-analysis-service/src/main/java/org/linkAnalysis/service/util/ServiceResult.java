package org.linkAnalysis.service.util;

/**
 * @author Pavel Karpukhin
 */
public class ServiceResult<T> extends ValidationResult {

    private T result;

    public ServiceResult(T result) {
        this(result, true);
    }

    public ServiceResult(T result, boolean succeed) {
        this.result = result;
        this.succeed = succeed;
    }

    public static <T> ServiceResult<T> fail(String errorMessage) {
        ServiceResult<T> serviceResult = new ServiceResult<T>(null, false);
        serviceResult.addError(errorMessage);
        return serviceResult;
    }

    public T getResult() {
        return result;
    }

}
