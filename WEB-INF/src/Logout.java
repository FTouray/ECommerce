import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class Logout extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    @Override
    public String execute() {
        // Invalidate the session
        session.clear();
        session.put("loggedOut", true); // Optional: You can use this flag to display a message on the login page

        return SUCCESS;
    }

    @Override
    public void setSession(Map map) {
        this.session = map;
    }
}
