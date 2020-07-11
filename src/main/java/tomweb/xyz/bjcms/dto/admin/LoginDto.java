package tomweb.xyz.bjcms.dto.admin;

import lombok.Data;

@Data
public class LoginDto {

    public String accountSecret;
    public String passwordSecret;
}
