package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.User;

public class GoodsIssue extends HttpServlet {
	private String uploadDir;
	private String saveFileDir;
	private File saveDir=null;
	boolean flag=true;
//	@Override
//	public void init() throws ServletException {
//		this.saveFileDir = "upload";
//		String webRoot=this.getServletContext().getRealPath("/");//其中\表示项目的根
//		uploadDir=webRoot+"\\upload\\";//以后再文件后面直接加
//		File saveDir = new File("d:/javaEE/javaEEWorkSpace/ShoolTiaoZao/WebContent/"+"upload");
//		if(!saveDir.exists()) {
//			saveDir.mkdir();
//		}
//		System.out.println("saveDir: " + saveDir);
//	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String goodsName=null;
		String goodsNum=null;
		String goodsPrice=null;
		String goodsClass=null;
		String filePath=request.getSession().getServletContext().getRealPath("/");
		System.out.println("请求路径"+filePath);
		try {
			List<FileItem> items=upload.parseRequest(request);
			//读取数据
			for(int i=0;i<items.size();i++){
				FileItem fileItem=items.get(i);
				if(fileItem.isFormField()){//如果不是文件
					String name=fileItem.getFieldName();
					//String value=fileItem.getString();
				String  value =new String(fileItem.getString().getBytes("iso-8859-1"), "utf-8");
				    if(i==0){
				    	goodsName=value;//获取商品名称
				    	if(goodsName.equals("")||goodsName==null){
				    		request.setAttribute("error","商品名不能为空！");
					    	request.getRequestDispatcher("goodsIssue.jsp").forward(request, response);
				    	}
				    }
				    if(i==1){
				      goodsNum=value;//获取商品数量
				      if(goodsNum.equals("")||goodsNum==null){
				    		request.setAttribute("error","商品数量不能为空！");
					    	request.getRequestDispatcher("goodsIssue.jsp").forward(request, response);
				    	}
				    }
				    if(i==2){
					      goodsPrice=value;//获取商品价格
					      if(goodsPrice.equals("")||goodsPrice==null){
					    		request.setAttribute("error","商品价格不能为空！");
						    	request.getRequestDispatcher("goodsIssue.jsp").forward(request, response);
					    	}
					    }
				    if(i==3){
					      goodsClass=value;//获取商品类别
					    }
				}
				if(!fileItem.isFormField()){
				//如果是文件
					//初始化文件夹
					
					this.saveFileDir = "upload";
					String webRoot=this.getServletContext().getRealPath("/");//其中\表示项目的根
					uploadDir=webRoot+"\\upload\\";//以后再文件后面直接加
					File saveDir = new File("d:/javaEE/javaEEWorkSpace/ShoolTiaoZao/WebContent/"+"upload");
					if(!saveDir.exists()) {
						saveDir.mkdir();
					}
					System.out.println("saveDir: " + saveDir);
					String filename=fileItem.getName();//取得的名称
					long size=fileItem.getSize();
					fileItem.getInputStream();
					//System.out.println(filename);
					//复制文件
					File saveFile=new File("d:/javaEE/javaEEWorkSpace/ShoolTiaoZao/WebContent/"+"upload/"+filename);
					System.out.println(filename);
					//截取图片名称
					filename = filename.substring(filename.lastIndexOf('\\') + 1);
					System.out.println(filename);
					String imageAddress="upload/"+filename;
					InputStream is= fileItem.getInputStream();
					//System.out.println(file.getAbsolutePath());这是eclipse的路径
					try{						
						FileOutputStream fos=new FileOutputStream(saveFile);
						byte[] data=new byte[10*1024];
					    int len=is.read(data);
					    while(len!=-1){
					    	fos.write(data,0,len);
					    	len=is.read(data);
					    }
					    is.close();
					    fos.close();
					}
					catch(FileNotFoundException e){
						request.setAttribute("error","商品上传失败！必须上传照片！");
						request.getRequestDispatcher("goodsIssue.jsp").forward(request, response);
					}
				    //往数据库中存放数据
				    AddGoodsToDB addGoods=new AddGoodsToDB();
				    //得到上传者的名字
				    User user=new User();
				    HttpSession session=request.getSession();
				    user=(User)session.getAttribute("admin");
				    String userID=user.getUserID();
				    if( addGoods.addGoodsToDb(goodsName, goodsNum, goodsPrice, goodsClass, imageAddress,userID)){				    	
				    	//存入数据库正确，并且文件照片上传了
				    	System.out.println("文件上传成功");
				    	request.getRequestDispatcher("personalGoodsDisplay.do").forward(request, response);
				    }
				    else{
				    	System.out.println("上传失败");
				    	deleteFile de=new deleteFile();//失败后删除上传到工程的图片
				    	de.DeleteFolder(imageAddress);
				    }
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
