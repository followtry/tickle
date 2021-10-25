package cn.followtry.common.exception;

/**
 * @author followtry
 * @since 2021/10/25 8:53 下午
 */
public class JsonParseInvalidException extends RuntimeException {
    public JsonParseInvalidException() {
        super();
    }

    public JsonParseInvalidException(String message) {
        super(message);
    }

    public JsonParseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParseInvalidException(Throwable cause) {
        super(cause);
    }

    protected JsonParseInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
