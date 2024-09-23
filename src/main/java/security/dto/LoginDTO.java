package security.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginDTO {
    private String userId;
    private String password;

    public static LoginDTO of(HttpServletRequest request)throws AuthenticationException {
        ObjectMapper om=new ObjectMapper();

        try{
            return om.readValue(request.getInputStream(),LoginDTO.class);
        }catch (Exception e){
            e.printStackTrace();
            throw new BadCredentialsException("username 또는 password 가 없습니다");
        }
    }
}
