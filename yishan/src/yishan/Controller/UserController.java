package yishan.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import yishan.Dao.UserDao;
import yishan.Po.Goods;
import yishan.Po.GoodsState;
import yishan.Po.User;
import yishan.Util.HibernateUtil;
import yishan.Util.RandomName;

@Controller
public class UserController implements IUseController {
	@RequestMapping("login")
	@Override
	public   String login(User user,HttpSession session,String code) {
		// TODO Auto-generated method stub
		String scode=(String)session.getAttribute("validateCode");
		System.out.println(scode);
		String msg="";
		if(scode.toLowerCase().equals(code.toLowerCase())){
			UserDao userdao=new UserDao();
			if(userdao.isExistsUser(user)==3){
				//用户名和密码都正确
				User u=new User();
				u.setName(user.getName());
				u.setPassword(user.getPassword());
				session.setAttribute("user",u);
				return "redirect:index.jsp";
			}
			if(userdao.isExistsUser(user)==0){
				//用户不存在
				return "redirect:login.jsp?error="+"0";
			}
			if(userdao.isExistsUser(user)==2){
				//密码不正确
				return "redirect:login.jsp?error="+"2";
			}
			return null;
		}
		else{
			//验证码错误
			return "redirect:login.jsp?error="+"4";
		}
		
	}
	@RequestMapping("register")
	@Override
	public String register(User user,String code,HttpSession session) {
		// TODO Auto-generated method stub
		String scode=(String)session.getAttribute("validateCode");
		
		if(scode.toLowerCase().equals(code.toLowerCase())){
			UserDao userdao=new UserDao();
			if(userdao.isExistsUser(user)>0){
				return "redirect:register.jsp?error="+"1";//存在该用户
			}
			else{
			     userdao.register(user);
			     return "redirect:login.jsp?msg=1&&name="+user.getName();
			}
		}
		else{
			//验证码错误
			return "redirect:register.jsp?error="+"4";
		}
		
	}
	@RequestMapping("exist")
	@Override
	public void ajaxUserExists(String name, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setName(name);
		//System.out.println(u.getName());
		UserDao userdao=new UserDao();
		
		int i=userdao.isExistsUser2(u);
		if(i>0){
			try {
				response.getWriter().print(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				response.getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("IssueGoods")
	@Override
	public String IssueGoods(@RequestParam CommonsMultipartFile file, Goods goods ,HttpSession session) {
		// TODO Auto-generated method stub
		if(file.isEmpty()){
			return "redirect:IssueHeart.jsp?error=1";
		}
		 //判断是不是图片
	    String fileName=file.getOriginalFilename();
	    System.out.println(fileName);
	    String ss=fileName.substring(fileName.indexOf('.')+1);
	    String[] imageArrary={"bmp","dib","gif","jpg","jpeg","png","ico" };
	    boolean flag=false;
	    for(int i=0;i<imageArrary.length;i++){
	    	if(ss.equals(imageArrary[i])){
	    	    flag=true;
	    	    break;
	    	}
	    }
	    if(flag){
	    	//如果是图片，将进行存储
	    	String saveFileDir = "upload";
			  String webRoot=session.getServletContext().getRealPath("/");//其中\表示项目的根
			  System.out.println(webRoot);
			  try {
				System.out.println(Class.forName("yishan.Po.Goods").getResource("/").getPath());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
				File saveDir = new File(webRoot+"//"+saveFileDir);
				if(!saveDir.exists()) {
					saveDir.mkdir();
				}
			    System.out.println("saveDir: " + saveDir); 
				try {
					InputStream input=file.getInputStream();
					
					OutputStream os=new FileOutputStream(new File(saveDir,fileName));
					int length=0;
					byte[] buffer=new byte[400];
				
					while((length=input.read(buffer))!=-1){
						os.write(buffer, 0, length);
					}
					os.close();
					input.close();
					//然后进行存储信息到数据库
					HibernateUtil util=HibernateUtil.getHibernateUtil();
					Session Hsession=null;
					Transaction tras=null;
					try {
						
						 Hsession=util.Openssion();
						 tras=Hsession.beginTransaction();
						User u=(User) session.getAttribute("user");
					    //
						System.out.println(u);
						UserDao userdao=new UserDao();
					    User u2=userdao.getUserByName(u.getName());
					    goods.setUser(u2);
					    System.out.println(fileName);
					    //先不加自己加的名字					    
					    //goods.setPictureAddress(saveFileDir+"/"+RandomName.getRandomName().append(fileName));
						goods.setPictureAddress(saveFileDir+"/"+fileName);
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						goods.setTime(df.format(new Date()));
						goods.setGoodsState("normal");
						System.out.println(goods);
						
						Hsession.save(goods);
						tras.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(tras!=null){
							tras.rollback();
						}
						return "redirect:IssueHeart.jsp?error=4";//服务器存储错误
						
					}finally {
						Hsession.close();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:IssueHeart.jsp?error=3";
					
				}
		   
	    }else{
	    	return "redirect:IssueHeart.jsp?error=2";
	    } 
		  

		return "getAllGoods.do";
	}
	@RequestMapping("PersonalGoodsMagager")
	@Override
	public String PersonalGoodsManager(HttpSession session,HttpServletRequest request) {
		// TODO Auto-generated method stub
		User u=(User) session.getAttribute("user");
		UserDao userdao=new UserDao();
		List<Goods> list=userdao.getUserGoods(u);
		request.setAttribute("pgoods",list);
		System.out.println(list.size());
		return "forward:PersonGoodsManager.jsp";
	}
	@RequestMapping("SearchGoods")
	@Override
	public String doSearch(HttpServletRequest request,String keyword) {
		// TODO Auto-generated method stub
		System.out.println(keyword);
		UserDao userdao=new UserDao();
		List<Goods> glist=userdao.getSearchGoods(keyword);
		request.setAttribute("sgoods", glist);
		return "forward:Searchpage.jsp";
	}
	@RequestMapping("getAllGoods")
	@Override
	public String indexSearch(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		List<Goods> goodList=userdao.getAllGoods();
		request.setAttribute("goodList",goodList);
		return "forward:index.jsp";
	}
	@RequestMapping("goodsDelet/{ID}")
	@Override
	public String deleteGoods(HttpServletRequest request,@PathVariable String ID) {
		// TODO Auto-generated method stub
		System.out.println(ID);
		UserDao userdao =new UserDao();
		String result="";
		
		if(userdao.deleteGoods(ID)){
			result="redirect:/PersonGoodsManager.jsp";
		}
		return result;
	}
}
