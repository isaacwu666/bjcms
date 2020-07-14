package tomweb.xyz.bjcms.exception;

/**
 * 参数传输错误
 */
public class ParameterException extends RuntimeException {
    public ParameterException(String toString) {
        super(toString);
    }
}
