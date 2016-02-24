package training.spring.security.utils;

import org.springframework.security.core.authority.AuthorityUtils;
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
        } catch (Exception ignore) {
        }
        return userName;
    }

    public static boolean isUser() {
        return checkRole("ROLE_USER");
    }

    public static boolean isManager() {
        return checkRole("ROLE_MANAGER");
    }

    public static boolean isAdmin() {
        return checkRole("ROLE_ADMIN");
    }

    private static boolean checkRole(String roleName) {
        boolean result = false;
        try {
            result = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(AuthorityUtils.createAuthorityList(roleName).get(0));
        } catch (Exception ignore) {
        }
        return result;
    }
}
