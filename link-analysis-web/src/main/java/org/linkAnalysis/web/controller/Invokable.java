package org.linkAnalysis.web.controller;

import org.linkAnalysis.service.util.ServiceResult;

/**
 * @author Pavel Karpukhin
 */
public interface Invokable<T> {

    ServiceResult<T> invoke();
}
