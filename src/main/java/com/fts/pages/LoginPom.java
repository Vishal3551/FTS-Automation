package com.fts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPom {
	public static WebElement setUserName(WebDriver driver)
	{
     WebElement txtUserName = driver.findElement(By.xpath("//*[@id='userName']"));
		return txtUserName;
	}
	
	public static WebElement setPassword(WebDriver driver)
	{
     WebElement txtPassword = driver.findElement(By.xpath("//*[@id='password']"));
		return txtPassword;
	}
	
	public static WebElement clickLogin(WebDriver driver)
	{
     WebElement btnLogin = driver.findElement(By.xpath("//input[@value='Login']"));
		return btnLogin;
	}
	
	public static WebElement selectOrganization(WebDriver driver)
	{
     WebElement dropdownOrg = driver.findElement(By.xpath("//*[@id='aims2Location']"));
		return dropdownOrg;
	}
	
	public static WebElement selectOrganizationButton(WebDriver driver)
	{
     WebElement btnSelect = driver.findElement(By.xpath("//*[@id='orgSelect']"));
		return btnSelect;
	}
	
	public static WebElement clickFTSST(WebDriver driver)
	{
	 WebElement linkFTSST = driver.findElement(By.xpath("//*[text()='File Transfer Service']"));
	 	return linkFTSST;
	}
	
	public static WebElement clickFTSQA(WebDriver driver)
	{
	 WebElement linkFTSQA = driver.findElement(By.xpath("//*[text()='File Transfer Service (FTS)']"));
	 	return linkFTSQA;
	}
	
	public static WebElement clickUF(WebDriver driver)
	{
	 WebElement linkUF = driver.findElement(By.xpath("//*[text()=' Upload a File']"));
	 	return linkUF;
	}
	
	public static WebElement clickCF(WebDriver driver)
	{
	 WebElement btnCF = driver.findElement(By.xpath("//tbody//td//input[@name='uploadForm:_id12']"));
	 	return btnCF;
	}
	
	public static WebElement chkHIPAA837P(WebDriver driver)
	{
		WebElement chkHIPAA837P = driver.findElement(By.xpath("//*[@name='uploadForm:_id15']"));
	 	return chkHIPAA837P;
	}
	
	public static WebElement chkHIPAA837I(WebDriver driver)
	{
	 WebElement chkHIPAA837I = driver.findElement(By.xpath("//*[@name='uploadForm:_id19']"));
	 	return chkHIPAA837I;
	}
	
	public static WebElement chkHIPAA837Pginc(WebDriver driver)
	{
	 WebElement chkHIPAA837P = driver.findElement(By.xpath("//*[@name='uploadForm:_id19']"));
	 	return chkHIPAA837P;
	}
	
	public static WebElement chkHIPAA837Iginc(WebDriver driver)
	{
	 WebElement chkHIPAA837I = driver.findElement(By.xpath("//*[@name='uploadForm:_id23']"));
	 	return chkHIPAA837I;
	}
	
	public static WebElement clickUpload(WebDriver driver)
	{
	 WebElement btnUpload = driver.findElement(By.xpath("//*[@name='uploadForm:uploadButton']"));
	 	return btnUpload;
	}
	
	public static WebElement statusMessage(WebDriver driver)
	{
	 WebElement statusMsg = driver.findElement(By.xpath("//*[@id='uploadForm:statusMessage']"));
	 	return statusMsg;
	}
}
