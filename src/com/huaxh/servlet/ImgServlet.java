package com.huaxh.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

// 用于获取验证码的功能
public class ImgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage bfi = new BufferedImage(68, 25, BufferedImage.TYPE_INT_RGB); //图像缓冲区
        Graphics g = bfi.getGraphics();
        StringBuffer sb = new StringBuffer();
        // 背景框
        Color color = new Color(200, 215, 250);
        g.setColor(color);
        g.fillRect(0, 0, 68, 30);//背景框
        //第一个数字
        Random r = new Random();
        int tmp1 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); //设置字体随机颜色
        g.drawString(tmp1 + "", 3, 18);
        //加号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); //设置随机颜色
        g.drawString("+", 18, 18);
        //第二个数字
        int tmp2 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); //设置随机颜色
        g.drawString(tmp2 + "", 33, 18);
        //等号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); //设置随机颜色
        g.drawString("=", 48, 18);
        //问号
        g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100))); //设置随机颜色
        g.drawString("?", 57, 18);
        //保存到session
        int result = tmp1 + tmp2;
        req.getSession().setAttribute("VerifyCode", result + "");
        // 设置响应头 通知浏览器以图片的形式打开
        resp.setContentType("image/jpeg");
        //设置响应头 控制浏览器不要缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        //写入到response输出流
        ImageIO.write(bfi, "JPG", resp.getOutputStream());//将图像缓冲区 以JPG格式写入 输出图像的response
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
