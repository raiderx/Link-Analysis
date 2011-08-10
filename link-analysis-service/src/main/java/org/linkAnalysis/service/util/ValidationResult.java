package org.linkAnalysis.service.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class ValidationResult {

    protected boolean succeed;
    private List<MessageInfo> errors = new ArrayList<MessageInfo>();

    public ValidationResult() {
        this.succeed = true;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public boolean isFailed() {
        return !succeed;
    }

    public void addError(MessageInfo messageInfo) {
        errors.add(messageInfo);
        succeed = false;
    }

    public void addError(String errorMessage) {
        addError(new MessageInfo(null, errorMessage));
    }

    public List<MessageInfo> getErrors() {
        return errors;
    }

    public static class MessageInfo {

        private String field;
        private String message;

        public MessageInfo(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }
    }
}
