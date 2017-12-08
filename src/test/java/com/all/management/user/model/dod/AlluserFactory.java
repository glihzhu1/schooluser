package com.all.management.user.model.dod;
import com.all.management.user.model.Alluser;
import org.springframework.roo.addon.jpa.annotations.entity.factory.RooJpaEntityFactory;

/**
 * = AlluserFactory
 TODO Auto-generated class documentation
 *
 */
@RooJpaEntityFactory(entity = Alluser.class)
public class AlluserFactory {

	/**
     * Creates a new {@link Alluser} with the given index.
     * 
     * @param index position of the Alluser
     * @return a new transient Alluser
     */
    public Alluser create(int index) {
        Alluser obj = new Alluser();
        setAppRole(obj, index);
        setEmail(obj, index);
        setVersion(obj, index);
        setLoginId(obj, index);
        setPasswordHash(obj, index);
        setUserActive(obj, index);
        return obj;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setAppRole(Alluser obj, int index) {
        String appRole = "appRole_" + index;
        if (appRole.length() > 50) {
            appRole = appRole.substring(0, 50);
        }
        obj.setAppRole(appRole);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setEmail(Alluser obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 200) {
            email = email.substring(0, 200);
        }
        obj.setEmail(email);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setVersion(Alluser obj, int index) {
        Integer version = new Integer(1);
        obj.setVersion(version);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setLoginId(Alluser obj, int index) {
        String loginId = "loginId_" + index;
        if (loginId.length() > 200) {
            loginId = loginId.substring(0, 200);
        }
        obj.setLoginId(loginId);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setPasswordHash(Alluser obj, int index) {
        String passwordHash = "passwordHash_" + index;
        if (passwordHash.length() > 200) {
            passwordHash = passwordHash.substring(0, 200);
        }
        obj.setPasswordHash(passwordHash);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param obj
     * @param index
     */
    public void setUserActive(Alluser obj, int index) {
        Boolean userActive = Boolean.TRUE;
        obj.setUserActive(userActive);
    }
}
