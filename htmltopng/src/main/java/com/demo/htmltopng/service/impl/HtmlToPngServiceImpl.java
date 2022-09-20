package com.demo.htmltopng.service.impl;

import com.demo.htmltopng.service.HtmlToPngService;


import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

/**
 * @author AnYuan
 * 生成png服务实现
 */

@Slf4j
@Service
public class HtmlToPngServiceImpl implements HtmlToPngService {

    @Override
    public void htmlToPng() throws Exception {

        // 设置ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
        driver.get("http://www.baidu.com");

//        WebElement element = driver.findElement(By.id("id_all"));
//        int x = element.getLocation().getX();
//        int y = element.getLocation().getY();
//        int elementWidth =  element.getSize().getWidth();
//        int elementHeight =  element.getSize().getHeight();

        //2.设置等待时间
        Thread.sleep(3000);

        //3.当前页面截图并存储到本地（原图片）
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = UUID.randomUUID().toString();
            File destFile = new File("./" + new File(fileName + ".png"));
            FileUtils.copyFile(srcfile,destFile );
//            Thumbnails.of(srcfile).sourceRegion(x,y,elementWidth,elementHeight)
//                    .scale(1).toFile(new File("./" + new File(fileName + "-2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.关闭驱动
        driver.close();

    }
}
