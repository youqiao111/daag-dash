package daag.util.exception;

/**
 * Created by yq on 2018/4/2.
 */
public class DaagException extends Exception {

    private static final long serialVersionUID = 467808006043627368L;

    private final int errorCode; // 错误编码

    public DaagException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // 获取错误编码
    public int getErrorCode() {
        return errorCode;
    }
}
