package org.linkAnalysis.service;

/**
 * @author Pavel Karpukhin
 */
public class ServiceResult<T> {

    private T result;
    private boolean succeed;

    public ServiceResult(T result) {
        this(result, true);
    }

    public ServiceResult(T result, boolean succeed) {
        this.result = result;
        this.succeed = succeed;
    }

    public static <T> ServiceResult<T> fail(String errorMessage) {
        ServiceResult<T> serviceResult = new ServiceResult<T>(null, false);
        //serviceResult.addError(errorMessage);
        return serviceResult;
    }

    public T getResult() {
        return result;
    }

    public boolean isSucceed() {
        return succeed;
    }
}
