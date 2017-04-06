package yishan.Controller; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import yishan.Po.Goods;
import yishan.Po.User;

public interface IUseController {
    public String login(User user,HttpSession session,String code);
    public String register(User user,String code,HttpSession session);
    public void ajaxUserExists(String name,HttpServletResponse response);
    public String IssueGoods(CommonsMultipartFile file,Goods goods ,HttpSession session);
    public String PersonalGoodsManager(HttpSession session,HttpServletRequest request);
    public String doSearch(HttpServletRequest request,String keyword);
    public String indexSearch(HttpServletRequest request);
    public String deleteGoods(HttpServletRequest request,String ID);
}
