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
    public String IssueGoods(CommonsMultipartFile file,Goods goods ,HttpSession session,HttpServletRequest req);
    public String PersonalGoodsManager(HttpSession session,HttpServletRequest request);
    public String doSearch(HttpServletRequest request,String keyword);
    public String indexSearch(HttpServletRequest request);
    public String deleteGoods(HttpServletRequest request,String ID);
    public String addFavorite(HttpServletRequest req, HttpSession session,String PID,String goodsname);
    public String getGoods(HttpServletRequest req,HttpSession session);
    public String getAllDeal(HttpServletRequest req);
    public void type(String name, HttpServletResponse response);
    public String vote(HttpServletRequest req);
    public String dovote(HttpServletRequest req);
    public String pTrasaction(HttpServletRequest req,HttpSession ses);
    public String aggree(HttpServletRequest req);
    public String disaggree(HttpServletRequest req);
    public String evaluate(HttpServletRequest req);
    public String dianzan(HttpServletRequest req);    
    
}
