package xin.session;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int WIDTH = 60;  
    private static int HEIGHT = 20;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		response.setHeader("Param", "No-cache");		// 设置浏览器不要缓存此图片
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);		// 创建内存图像并获得其图形上下文
		Graphics g = image.getGraphics();
		char[] rands = generateCheckCode();
		drawBackground(g); 			// 产生图像
		drawRands(g, rands); 
		g.dispose(); 				// 完成绘图
		ByteArrayOutputStream bos = new ByteArrayOutputStream();		// 把图像输出到客户端
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length); 
		bos.writeTo(sos);
		bos.close();
		sos.close();
		session.setAttribute("check_code", new String(rands));
	}
	
	private char[] generateCheckCode() {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] rands = new char[4];
		for(int i = 0; i < 4; i++) {
			int rand = (int)(Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}
	
	private void drawRands(Graphics g, char[] rands) {			// 画验证码字符
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		g.drawString("" + rands[0], 1, 17);
		g.drawString("" + rands[1], 16, 15);
		g.drawString("" + rands[2], 31, 18);
		g.drawString("" + rands[3], 46, 16);
	}
	
	private void drawBackground(Graphics g) {		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		for(int i = 0; i < 120; i++) {				// 画120个点进行干扰
			int x = (int)(Math.random() * WIDTH);
			int y = (int)(Math.random() * HEIGHT);
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0); 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
