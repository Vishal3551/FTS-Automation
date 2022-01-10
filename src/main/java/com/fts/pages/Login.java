package com.fts.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class Login {

	@Test
	public void loginApplication() throws InterruptedException, IOException, AWTException {
		
		//data from excel
        ReadExcel rd = new ReadExcel();
        rd.initialize("TestData", "TestData.xlsx", "UserLogin");
        
        int users = rd.numberOfUsers();
        
        System.out.println("Number of Users:"+users);
        
        for (int i=6; i<users; i++) {
        	
        	String uname = rd.readData(i, "UserName");
            String pwd = rd.readData(i, "Password");
            String env = rd.readData(i, "Env");
            String user = rd.readData(i, "User");
            String filepath = rd.readData(i, "Files");
            
            System.out.println("Username:"+uname);
            System.out.println("Password:"+pwd);
            System.out.println("Env:"+env);
            
            Thread.sleep(3000);
    		
    		System.setProperty("webdriver.chrome.driver", "C:\\Users\\spand\\eclipse-workspace19\\FTS_Automation\\src\\test\\resources\\chromedriver_v96.exe");
    		
    		ChromeOptions cap = new ChromeOptions();
    		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    		cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    		WebDriver driver = new ChromeDriver(cap);
    		
    		if(env.equals("ST"))
    		{
    			driver.get("https://sso-st.hhs.state.ma.us/");
    		}
    		else
    		{
    			driver.get("https://sso-qa.hhs.state.ma.us/");
    		}
    		driver.manage().window().maximize();
    		
    //Verify page title
    		
    		String expectedresult = driver.getTitle();
    		System.out.println("Expected Title:" + expectedresult);
    		String actualresult = "Virtual Gateway Portal redirect page";
    		if(expectedresult.equals(actualresult))
    		{
    			System.out.println("Test pass");
    			
    		}
    		else
    		{
    			System.out.println("Test Fail");
    	    }
             Thread.sleep(3000);
             
             LoginPom.setUserName(driver).sendKeys(uname);
             
             Thread.sleep(3000);
             
             LoginPom.setPassword(driver).sendKeys(pwd);
             
             Thread.sleep(3000);
             
             LoginPom.clickLogin(driver).click();
             
             Thread.sleep(3000);
             
             if(user.equals("ginc") && env.equals("ST"))
             {
            	 Select drpOrg = new Select(LoginPom.selectOrganization(driver));
            	 drpOrg.selectByVisibleText("Gosnold Inc");
            	 LoginPom.selectOrganizationButton(driver).click();
             }
             
             System.out.println("Parent window:"+driver.getTitle());
             String parent_window_id=driver.getWindowHandle();
             System.out.println("Parent window ID:" +parent_window_id);
             
             if(env.equals("ST"))
             {
            	 LoginPom.clickFTSST(driver).click();
             }
             else
             {
            	 LoginPom.clickFTSQA(driver).click();
             }
             
             Thread.sleep(3000);
             
             Set<String> allWindowHandles = driver.getWindowHandles();
             
             System.out.println(allWindowHandles);
             for(String child_window:allWindowHandles)
             {
            	 if(child_window.equalsIgnoreCase(parent_window_id))
            	 {
            		 System.out.println("Still on Parent window");
            	 }
            	 else
            	 {
            		 System.out.println("New window opened" +child_window);
            		 driver.switchTo().window(child_window);
            		 System.out.println("Switched to New window");
            		 System.out.println("Child window Title" +driver.getTitle());
            		 Thread.sleep(5000);
            	 }
             }
             
            LoginPom.clickUF(driver).click();
            
            Thread.sleep(3000);
            
            String filename = user + "_FileName";
            String filetp = user + "_FileType";
            
            ReadExcel rf = new ReadExcel();
            rf.initialize("TestData", "TestData.xlsx", "FilesToUpload");
            
            int rowCount = rf.rowCount(filename);
            
            for (int j=1; j<rowCount; j++) {
            	
            	String file = rf.readFile(j, filename);
                String filetype = rf.readFile(j, filetp);
                
                System.out.println("File Name:"+file);
                System.out.println("File Type:"+filetype);
                
                Actions act = new Actions(driver);
                act.moveToElement(LoginPom.clickCF(driver)).click().perform();
                
                Thread.sleep(3000);
                
                StringSelection ss = new StringSelection(filepath + "\\" + file);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                
                Robot robot = new Robot();
                robot.delay(300);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.delay(90);
                robot.keyRelease(KeyEvent.VK_ENTER);
                
                Thread.sleep(3000);
                
                if(user.equals("ginc") && env.equals("ST"))
                {
                	if (filetype.equals("PROF"))
                    {
                    	if (LoginPom.chkHIPAA837Pginc(driver).isSelected())
                    	{
                    		if (LoginPom.chkHIPAA837Iginc(driver).isSelected())
                    			LoginPom.chkHIPAA837Iginc(driver).click();
                    	}
                    	else
                    	{
                    		LoginPom.chkHIPAA837Pginc(driver).click();
                    		if (LoginPom.chkHIPAA837Iginc(driver).isSelected())
                    			LoginPom.chkHIPAA837Iginc(driver).click();
                    	}
                    }
                    else
                    {
                    	if (LoginPom.chkHIPAA837Iginc(driver).isSelected())
                    	{
                    		if (LoginPom.chkHIPAA837Pginc(driver).isSelected())
                    			LoginPom.chkHIPAA837Pginc(driver).click();
                    	}
                    	else
                    	{
                    		LoginPom.chkHIPAA837Iginc(driver).click();
                    		if (LoginPom.chkHIPAA837Pginc(driver).isSelected())
                    			LoginPom.chkHIPAA837Pginc(driver).click();
                    	}
                    }
                }
                else
                {
                	if (filetype.equals("PROF"))
                	{
                		if (LoginPom.chkHIPAA837P(driver).isSelected())
                		{
                			if (LoginPom.chkHIPAA837I(driver).isSelected())
                				LoginPom.chkHIPAA837I(driver).click();
                		}
                		else
                		{
                			LoginPom.chkHIPAA837P(driver).click();
                			if (LoginPom.chkHIPAA837I(driver).isSelected())
                				LoginPom.chkHIPAA837I(driver).click();
                		}
                	}
                	else
                	{
                		if (LoginPom.chkHIPAA837I(driver).isSelected())
                		{
                			if (LoginPom.chkHIPAA837P(driver).isSelected())
                				LoginPom.chkHIPAA837P(driver).click();
                		}
                		else
                		{
                			LoginPom.chkHIPAA837I(driver).click();
                			if (LoginPom.chkHIPAA837P(driver).isSelected())
                				LoginPom.chkHIPAA837P(driver).click();
                		}
                	}
                }
                
                Thread.sleep(3000);
                
                LoginPom.clickUpload(driver).click();
                
                Thread.sleep(3000);
                
                System.out.println(LoginPom.statusMessage(driver).getText());
                
                Thread.sleep(3000);
            	
            }
            
            driver.quit();
        }
         
}
}