package yishan.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import net.sf.json.JSONArray;
import yishan.Dao.UserDao;
import yishan.Po.Deal;
import yishan.Po.Goods;
import yishan.Po.Swap;
import yishan.Po.User;
import yishan.Po.Vote;
import yishan.Util.HibernateUtil;

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
	public String IssueGoods(@RequestParam CommonsMultipartFile file, Goods goods ,HttpSession session,HttpServletRequest req) {
		// TODO Auto-generated method stub
        
		Object b=(Object)req.getParameter("miaoshu");
		System.out.println(b+"23");
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
						goods.setTime(new Date());
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
		
		List<Goods> newIssue=userdao.getGoodsByDateOrder();
		request.setAttribute("date", newIssue);
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
	@RequestMapping("/addFavorite/{PID}/{goodsname}")
	@Override
	public String addFavorite(HttpServletRequest req,HttpSession session,@PathVariable String PID, @PathVariable String goodsname) {
		// TODO Auto-generated method stub
		Goods g=new Goods();
        UserDao userdao=new UserDao();
        g=userdao.getGoodsbyID(PID);
	    if(session.getAttribute("favorite")==null){
	    	ArrayList<Goods> goodslist=new ArrayList<>();
         	goodslist.add(g);
	    	session.setAttribute("favorite", goodslist);
	    }
	    else{
	    	ArrayList<Goods> goodslist=(ArrayList<Goods>) session.getAttribute("favorite");
	    	Iterator<Goods> iterator=goodslist.iterator();
	    	boolean flag=true;
	    	while(iterator.hasNext()){
	    		Goods gg=iterator.next();
	    		if(gg.getId().equals(PID)){
	    			flag=false;
	    			break;
	    		}
	    	}
	    	if(flag){	    		
	    		goodslist.add(g);
	    	}
	    	session.setAttribute("favorite",goodslist);
	    }
		return "redirect:/favorites.jsp";
		
	}
	@RequestMapping("getGoods")
	@Override
	public String getGoods(HttpServletRequest req, HttpSession ses) {//发起认领
		// TODO Auto-generated method stub
	    Deal deal=new Deal();	
	    String gid=req.getParameter("PID");
	    String pic=req.getParameter("pic");
	    String gname=req.getParameter("goodsname");
	    deal.setGoodsID(gid);
	    deal.setGoodsname(gname);
	    deal.setPic(pic);
	    User u=(User) ses.getAttribute("user");
	    UserDao userdao=new UserDao();
	    User uu=userdao.getUserByName(u.getName());
	    deal.setGetter(uu.getId());
	    deal.setGetname(uu.getName());
	    deal.setDistributor(userdao.getUserByGoodsID(gid).getId());
	    deal.setDisname(userdao.getUserByGoodsID(gid).getName());
	    deal.setState("undone");
	    deal.setTime(new Date());
	    if(userdao.isExistDeal(uu.getId(),gid)&&!uu.getId().equals(userdao.getUserByGoodsID(gid).getId())){	    	
	    	userdao.saveDeal(deal);
	    }  
	    return "redirect:index.jsp";
	}
	@RequestMapping("getDeal")
	@Override
	public String getAllDeal(HttpServletRequest req) {
		//TODO Auto-generated method stub
	  UserDao userdao=new UserDao();
	  ArrayList<Deal> doneDeal= (ArrayList<Deal>) userdao.getDoneDeal();
	  ArrayList<Deal> undoneDeal= (ArrayList<Deal>) userdao.getUndoneDeal();
	  req.setAttribute("done",doneDeal);
	  req.setAttribute("undone",undoneDeal);
	  return "TranScation.jsp";
	}
	@RequestMapping("type")
	@Override
	public void type(String type, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserDao userdao =new UserDao();
		ArrayList<String> list= (ArrayList<String>) userdao.getGoodsAllProperty(type);
		JSONArray jsonArray = JSONArray.fromObject(list);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("vote")
	@Override
	public String vote(HttpServletRequest req) {
		// TODO Auto-g enerated method stub
	     UserDao userdao=new UserDao();
	     ArrayList<Vote> vlist=new ArrayList<>();
	     vlist=(ArrayList<Vote>) userdao.getAllVote();
	     for(int i=0;i<vlist.size();i++){
	    	 for(int j=i+1;j<vlist.size();j++){
	    		 if(vlist.get(j).getUser().getVote()>vlist.get(i).getUser().getVote()){
	    			  Collections.swap(vlist, i, j);
	    		 }
	    	 }
	     }
	     req.setAttribute("vote",vlist);
		return "vote.jsp";
	}
	@RequestMapping("dovote")
	@Override
	public String dovote(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String userID=req.getParameter("userID");
		int vote=Integer.parseInt(req.getParameter("vote"))+1;
		UserDao userdao=new UserDao();
		userdao.updataVote(userID, vote);
		return "redirect:vote.do";
	}
	@RequestMapping("ptrasaction")
	@Override
	public String pTrasaction(HttpServletRequest req,HttpSession ses) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		ArrayList<Deal> get=new ArrayList<>();
		ArrayList<Deal> dis=new ArrayList<>();		
		ArrayList<Deal> age=new ArrayList<>();		
		ArrayList<Deal> disagree=new ArrayList<>();		
		User u=(User) ses.getAttribute("user");
		User uu=userdao.getUserByName(u.getName());
		get=(ArrayList<Deal>) userdao.getpersongetDeal(uu.getId());
		dis=(ArrayList<Deal>) userdao.getpersondisDeal(uu.getId());
		age=(ArrayList<Deal>) userdao.getpersonagreeDeal(uu.getId());
		disagree =(ArrayList<Deal>) userdao.getPdisagreeMyDeal(uu.getId());
		req.setAttribute("get",get);
		req.setAttribute("dis",dis);
		req.setAttribute("age",age);
		req.setAttribute("disagree",disagree);
		return "pTranScation.jsp";
	}
	@RequestMapping("agree")
	@Override
	public String aggree(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String dealID=req.getParameter("id");
	    UserDao userdao=new UserDao();
	    userdao.agree(dealID);
		return "redirect:ptrasaction.do";
	}
	@RequestMapping("disagree")
	@Override
	public String disaggree(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String dealID=req.getParameter("id");
	    UserDao userdao=new UserDao();
	    userdao.disagree(dealID);
		return "redirect:ptrasaction.do";
	}
	@RequestMapping("eavluate.do")
	@Override
	public String evaluate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String dealID=req.getParameter("dealID");
		String evaluate=req.getParameter("evaluate");
		UserDao userdao=new UserDao();
		userdao.evaluate(dealID, evaluate);
		return "redirect:ptrasaction.do";
	}
	@RequestMapping("dianzan")
	@Override
	public String dianzan(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String userid=req.getParameter("id");
		
		UserDao userdao=new UserDao();
		userdao.dianzan(userid);
		return "redirect:ptrasaction.do";
	}
	@RequestMapping("swap")
	@Override
	public void swap(String username, HttpServletResponse response) {
		// TODO Auto-generated method stub
     
		UserDao userdao =new UserDao();
		User u=new User();
		u.setName(username);
		ArrayList<Goods> list= (ArrayList<Goods>)userdao.getUserGoods(u);
		System.out.println(list.size()+"dsfsefsef");
		ArrayList<String> namelist= new ArrayList<>();
		for(int i=0;i<list.size();i++){
			namelist.add(list.get(i).getName());
			
		}
		JSONArray jsonArray = JSONArray.fromObject(namelist);
		try {
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("swapgood")
	@Override
	public void swapgood(String a, String b,String c,HttpServletResponse response,HttpServletRequest req,HttpSession ses) {
		// TODO Auto-generated method stub
		String  wantgname=a;//求购的物品名称
		String  swapgname=b;//他人交换用的物品名称
		String wantuname=c;//要交换的人的名字   	
		User u=(User) ses.getAttribute("user");
		UserDao userdao=new UserDao();
		User uu=userdao.getUserByGoodsName(wantgname);
		try {
			boolean isexistSwap=userdao.isexistSwap(userdao.getGoodsbyGoodsName(wantgname).getId(), userdao.getGoodsbyGoodsName(swapgname).getId());
			if(uu.getName().equals(wantuname)|| isexistSwap){
				response.getWriter().print(false);
			}
			else{//保存swap
				Swap swap=new Swap();
				swap.setDisgname(wantgname);
				swap.setDisname(uu.getName());
				swap.setDisgID(userdao.getGoodsbyGoodsName(wantgname).getId());
			    swap.setDistributor(userdao.getUserByGoodsName(wantgname).getId());
				swap.setGetgID(userdao.getGoodsbyGoodsName(swapgname).getId());
				swap.setGetgname(swapgname);
				swap.setGetname(wantuname);
				swap.setGetter(userdao.getUserByName(wantuname).getId());
				swap.setState("undone");
				swap.setTime(new Date());
				swap.setPic(userdao.getGoodsbyGoodsName(swapgname).getPictureAddress());
				swap.setDpic(userdao.getGoodsbyGoodsName(wantgname).getPictureAddress());
				boolean flag=userdao.saveSWap(swap);
				if(flag){					
					response.getWriter().print(true);
				}
				else{
					response.getWriter().print(false);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("exit.do")
	@Override
	public String exit(HttpSession ses) {
		// TODO Auto-generated method stub
		ses.invalidate();
		return "redirect:index.jsp";
	}
	@RequestMapping("allSwap")
	@Override
	public String getAllSwap(HttpServletRequest req) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		ArrayList<Swap> done=new ArrayList<>();
		ArrayList<Swap> undone=new ArrayList<>();	
		done=(ArrayList<Swap>) userdao.getDoneSwap();
		undone=(ArrayList<Swap>) userdao.getUndoneSwap();
		
		req.setAttribute("done", done);
		req.setAttribute("undone", undone);
		return "Swap.jsp";
	}
	@RequestMapping("pSwap")
	@Override
	public String getpAllSwap(HttpServletRequest req,HttpSession ses) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		ArrayList<Swap> done=new ArrayList<>();
		ArrayList<Swap> get=new ArrayList<>();	
		ArrayList<Swap> dis=new ArrayList<>();
		ArrayList<Swap> disagree=new ArrayList<>();		
		User u=(User) ses.getAttribute("user");
		User uu=userdao.getUserByName(u.getName());
		done=(ArrayList<Swap>) userdao.getpdoneSwap(uu.getId());
		get=(ArrayList<Swap>) userdao.getpgetundoneSwap(uu.getId());
		dis=(ArrayList<Swap>) userdao.getpdisundoneSwap(uu.getId());
		disagree=(ArrayList<Swap>) userdao.getDisagreeSwap(uu.getId());
		req.setAttribute("done", done);
		req.setAttribute("dis", dis);
		req.setAttribute("get", get);
		req.setAttribute("disagree",disagree);
		return "pSwap.jsp";
	}
	@RequestMapping("deletSwap")
	@Override
	public String deletSwap(String swapID) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		
		userdao.deleteSwap(swapID);
		return "redirect:pSwap.do";
	}
	@RequestMapping("agreeSwap")
	@Override
	public String agreeSwap(String swapID) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		userdao.agreeSwap(swapID);
		return "redirect:pSwap.do";
	}
	@RequestMapping("disagreeSwap")
	@Override
	public String disagreeSwap(String swapID) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		userdao.disAgreeSwap(swapID);
		return "redirect:pSwap.do";
	}
	@RequestMapping("evaluateSwap")
	@Override
	public String evaluateSwap(HttpServletRequest req,String swapID,String evaluate) {
		// TODO Auto-generated method stub
		
		UserDao userdao=new UserDao();
		userdao.EvaluateSwap(swapID, evaluate);
		return "redirect:pSwap.do";
	}
	@RequestMapping("deleteDeal")
	@Override
	public String deletDeal(String dealID) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		userdao.DeleteDeal(dealID);
		return "redirect:ptrasaction.do";
	}
	@RequestMapping("IssueNeed")
	@Override
	public String IssueNeeds(String type, String discription, HttpServletRequest req) {
		// TODO Auto-generated method stub
		 UserDao userdao=new UserDao();
		 ArrayList<Goods> glist= (ArrayList<Goods>) userdao.getSearchGoods(type);
		 ArrayList<Goods> glist2=(ArrayList<Goods>) userdao.getSearchGoods(discription);	
		 
		 for(int i=0;i<glist2.size();i++){
			 Object o2=glist2.get(i);
			 Goods g2=(Goods) o2;
			 boolean flag=true;
			 for(int j=0;j<glist.size();j++){
				Object o1=glist.get(j);
				Goods g1=(Goods) o1;
				if(g1.getId().equals(g2.getId())){
					flag=false;
					break;
				}
			 }
			 if(flag){
				 glist.add(g2);
			 }
		 }
		 req.setAttribute("sgoods", glist);
			return "forward:Searchpage.jsp";
	}
	@RequestMapping("goodsUpdate")
	@Override
	public String updateGoods(HttpServletRequest request, String PID) {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		Goods g=userdao.getGoodsbyID(PID);
		request.setAttribute("g", g);
		return "updateGoods.jsp";
	}
}
