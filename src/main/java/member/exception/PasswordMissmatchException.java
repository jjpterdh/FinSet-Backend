package member.exception;

public class PasswordMissmatchException extends RuntimeException{
    public PasswordMissmatchException() {
        super("비밀버호가 일치하지 않습니다.");
    }
}
