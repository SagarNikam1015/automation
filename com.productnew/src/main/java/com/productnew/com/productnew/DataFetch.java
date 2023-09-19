package com.productnew.com.productnew;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.github.bonigarcia.wdm.WebDriverManager;

public class DataFetch {
	public static WebDriver driver;
	public static int l;
	public static String filePath = "E:\\Excel reading\\Book1.xlsx";
	public static int totalProducts = 100;

	public static void main(String[] args) throws InterruptedException {

		ArrayList<ProductData> productList = new ArrayList<ProductData>();

//		System.setProperty("webdriver.chrome.driver","E:\\Software\\Chrome Driver for selenium\\chromedriver.exe");

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://shop.crown.com/crown/en/search?q=%3Arelevance%3AallCategories%3Abattery_and_charger_parts_and_accessories&page=22");
		//Thread.sleep(3000);
		
		
		for(int i = 1 ; i < 9; i++) {

			try {

				// click on view more
				driver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div/div[3]/a/span[1]")).click();
				Thread.sleep(1500);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				
				System.out.println(i+" : "+(10*i));

			} catch (Exception s) {
				System.out.println(s.getMessage());
			}
		}


		for (int j = 1; j <= totalProducts; j++) {

			String productPrice = "NA";
			String partNumber = "NA";
			String productDescription = "NA";
					
			By productNameLocator = By.xpath("/html/body/main/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div/ul/li["+j+"]/div/a");
			By productPriceLocator = By	.xpath("/html/body/main/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div/ul/li["+j+"]/div/div[2]/div");
			By partNumberLocators = By.xpath("/html/body/main/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div/div/div/ul/li["+j+"]/div/div[1]/div");
			By productDescriptionLocator = By.xpath("/html/body/main/div[3]/div[8]");

			
			// get product cost element
			try {
				WebElement element = driver.findElement(productPriceLocator);
				productPrice = element.getText();
			} catch (Exception e) {
				// element did not become visible within the specified time
				System.out.println(e.getMessage());
			}
		
			

			// get part number element
			try {
				WebElement element = driver.findElement(partNumberLocators);
				partNumber = element.getText().substring(13);
			} catch (Exception e) {
				// element did not become visible within the specified time
				System.out.println(e.getMessage());
			}

			
			// get product name element
			try {
				WebElement productNameElement = driver.findElement(productNameLocator);
				productNameElement.click();
			} catch (Exception e) {
				// element did not become visible within the specified time
				System.out.println(e.getMessage());
			}

			
			// get Product Description element
			try {
				WebElement element = driver.findElement(productDescriptionLocator);
				productDescription = element.getText();

			} catch (Exception e) {
				// element did not become visible within the specified time
				System.out.println(e.getMessage());
			}

			
			productList.add(new ProductData(partNumber, productPrice, productDescription));

			

			System.out.println("Opened : " + j);
			
			
			
			if (j == totalProducts) {

				// Excel Writing

				try (FileInputStream fileInputStream = new FileInputStream(filePath);
						Workbook workbook = new XSSFWorkbook(fileInputStream)) {

					// Get the sheet you want to work with (for example, the first sheet)
					Sheet sheet = workbook.getSheetAt(1);

					for (int i = 0; i < totalProducts; i++) {

						// Create a new row and add data to it
						Row newRow = sheet.createRow(sheet.getLastRowNum() + 1); // Appends to the end of the sheet

						newRow.createCell(0).setCellValue(productList.get(i).getPartNumber());
						newRow.createCell(1).setCellValue(productList.get(i).getPrice());
						newRow.createCell(2).setCellValue(productList.get(i).getDescription());
				
					

						// Save the changes back to the same file
						try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
							workbook.write(outputStream);
							Thread.sleep(200);
							System.out.println("Saved : " + (i+1));
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
			}
			

			Thread.sleep(500);

			driver.navigate().back();

		}
	}


}
