package pl.mg.doorsgame.liferay.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import pl.mg.doorsgame.model.LiferayUser;

@Component
public class UserService {

    private static String webId = "liferay.com";

    public LiferayUser findUserByScreenname(String screenname) {
        LiferayUser liferayUser = null;
        try {
            User user = UserLocalServiceUtil.fetchUserByScreenName(getDefaultCompanyId(), screenname);
            int authenticateByScreenName = UserLocalServiceUtil.authenticateByScreenName(getDefaultCompanyId(), "test", "test", null, null, null);
            System.out.println("authentication result=" + authenticateByScreenName);
            if (user != null) {
                liferayUser = new LiferayUser();
                liferayUser.setUsername(screenname);
                liferayUser.setPassword(user.getPassword());
                ArrayList<String> roles = new ArrayList<>();
                for (Role role : user.getRoles()) {
                    roles.add(role.getTitle());
                }
                liferayUser.setRoles(roles);
            }
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return liferayUser;
    }

    public boolean authenticateByScreename(String screenname, String password) {
        boolean result = false;
        int authenticateByScreenName;
        try {
            authenticateByScreenName = UserLocalServiceUtil.authenticateByScreenName(getDefaultCompanyId(), screenname, password, null, null, null);
            System.out.println("logged in result=" + authenticateByScreenName);
             if (authenticateByScreenName == 1) {
                result = true;
            }
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return result;
    }

    public long getDefaultCompanyId() throws PortalException, SystemException {
        Company company = CompanyLocalServiceUtil.getCompanyByWebId(webId);
        return company.getCompanyId();
    }

}
