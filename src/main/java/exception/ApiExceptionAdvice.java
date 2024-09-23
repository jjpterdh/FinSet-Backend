package exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<String> handlerIllegalArgumentException(NoSuchElementException e) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .header("Content-Type", "text/plain;charset=UTF-8")
//                .body("해당 ID의 요소가 없습니다");

        return ResponseEntity
                .status(400)
                .header(HttpHeaders.CONTENT_TYPE,"text/plain;charset=UTF-8")
                .body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handelException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Content-Type", "text/plain;charset=UTF-8")
                .body(e.getMessage());
    }
}
