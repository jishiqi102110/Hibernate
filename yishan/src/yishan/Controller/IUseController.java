package yishan.Controller; 

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yishan.Po.User;

public interface IUseController {
    public String login(User user,HttpSession session,String code);
    public String register(User user,String code,HttpSession session);
    public void ajaxUserExists(String name,HttpServletResponse response);
}
