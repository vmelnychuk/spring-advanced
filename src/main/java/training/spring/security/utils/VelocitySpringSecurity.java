package training.spring.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class VelocitySpringSecurity {
    public static String getUserName() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if(object instanceof UserDetails) {
            userName = ((UserDetails) object).getUsername();
        } else {
            userName = "";
        }
        return userName;
    }
}
