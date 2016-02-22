package training.spring.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class VelocitySpringSecurity {
    public static String getUserName() {
        String userName = "";
        try {
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(object instanceof UserDetails) {
                userName = ((UserDetails) object).getUsername();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }
}
