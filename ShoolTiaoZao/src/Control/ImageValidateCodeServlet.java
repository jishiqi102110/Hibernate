package Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageValidateCodeServlet extends HttpServlet {
	
	private int width;
	
	private int height;
	
	private Font validateCodeFont;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		this.width = 110;
		this.height = 50;
		try {
			this.validateCodeFont = Font.createFont(Font.TRUETYPE_FONT,
					this.getClass().getResourceAsStream("TIMESBI.TTF"));
			this.validateCodeFont = this.validateCodeFont.deriveFont(Font.ITALIC,40);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		System.out.println("ImageValidateCodeServlet ...");
		
		BufferedImage bim = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = bim.getGraphics();
		
		//ȫ������YELLOW
		g.setColor(new Color(32, 167, 240));
		g.fillRect(0, 0, this.width, this.height);
		
		/*随机生成6个字符
		 * 
		 * A: 65
		 * Z: 90	
		 * 0: 48
		 * 9: 57
		 */
		StringBuffer sbRand = new StringBuffer();
		for(int i = 0; i < 4; i++) {
			int randNum = (int)(Math.random() * 100);
			char randChar;
			if(randNum % 2 == 0) {
				//生成字母
				randChar = (char)((int)(Math.random() * 26) + 65);
			} else {
				//生成随机数字
				randChar = (char)((int)(Math.random() * 10) + 48);
			}
			sbRand.append(randChar);
		}
		String randStr = sbRand.toString();
		HttpSession session=(HttpSession)req.getSession();
	    session.setAttribute("validateCode",randStr);
		 Color mycolor = new Color(0,0, 0);
		 g.setColor(mycolor);
		 g.setFont(new Font("Times New Roman", Font.BOLD, 25));
	     g.drawString(randStr, 3, 33);
	     g.setColor(new Color(221, 25, 37));
		 //随机画线 
		 
         Random random=new Random();
		 for (int i = 0; i < 80; i++) { 

		 int x = random.nextInt(width); 

		 int y = random.nextInt(height); 

		 int xl = random.nextInt(20); 

		 int yl = random.nextInt(20); 

		 g.drawLine(x, y, x + xl, y + yl); 
		 }
				
		 g.dispose();
		resp.setContentType("image/png");
		ImageIO.write(bim, "PNG", resp.getOutputStream());		
	}
}
