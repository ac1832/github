package Excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class domainIpToExcel {
	static ArrayList<String> mappingDetail = new ArrayList<String>();
	static ArrayList<String> noMappingDetail = new ArrayList<String>();
	static ArrayList<ArrayList<String>> excelDataStatusNoDataOne = new ArrayList<ArrayList<String>>();
	private static final String FILE_Excel = "D:\\workExcel\\domainUrl.xlsx";
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) throws IOException {
		// txt檔
		FileReader fr;
		
		try {

			fr = new FileReader("D:\\workExcel\\toReady.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while (br.ready()) {
				
				mappingDetail.add(br.readLine());
				
			}

			fr.close();

			// 讀excel檔
			File myFile = new File("D:\\workExcel\\test.xlsx");
			FileInputStream fis = new FileInputStream(myFile);

			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			ArrayList<ArrayList<String>> mappingExcel = new ArrayList<ArrayList<String>>();
			
			for (int i = 0; i < mappingDetail.size(); i++) {
				
				ArrayList<String> addMappingUrl = new ArrayList<String>();
				
				addMappingUrl.add(mappingDetail.get(i));
				
				for (int j = 0; j < myWorkBook.getNumberOfSheets(); j++) {
					
					XSSFSheet mySheet = myWorkBook.getSheetAt(j);
					Iterator<Row> rowIterator = mySheet.iterator();
					
					while (rowIterator.hasNext()) {

						Row row = rowIterator.next();

						Iterator<Cell> cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {

							Cell cell = cellIterator.next();
							String cellStr[] = cell.toString().split("/");



							if (cellStr.length > 2) {

								String httpArrayStr[] = cellStr[2].split("\\.");
								String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
								mappingHttpStr = mappingHttpStr.toLowerCase();

								String url = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
								
								if (mappingDetail.get(i).equals(mappingHttpStr)) {
									
									addMappingUrl.add(url);

									
								}
								

							}
							
							

						}
						

					}
					
					
				}

				mappingExcel.add(addMappingUrl);
			}
			
			int aSize = 0;
			int bSize = 0;
			
			XSSFWorkbook workbook = new XSSFWorkbook(); // 建立Excel物件
			XSSFSheet sheet = workbook.createSheet("有用到的url");
			XSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index); // 顏色
			font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗細體
			CellStyle style = workbook.createCellStyle();
			style.setFont(font);

			XSSFFont font2 = workbook.createFont();
			font2.setColor(HSSFColor.RED.index); // 顏色
			font2.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗細體
			CellStyle style2 = workbook.createCellStyle();
			style2.setFont(font2);

			XSSFFont font3 = workbook.createFont();
			font3.setColor(HSSFColor.BLUE.index); // 顏色
			font3.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗細體
			CellStyle style3 = workbook.createCellStyle();
			style3.setFillForegroundColor(IndexedColors.AQUA.getIndex());

			style3.setFont(font3);
			
			for (int i = 0; i < mappingExcel.size(); i++) {

				ArrayList<String> urlArray = mappingExcel.get(i);
				removeDuplicate(mappingExcel.get(i));
		
				XSSFRow rowContent = sheet.createRow(i + 1); // 建立儲存格

				for (int j = 0; j < mappingExcel.get(i).size(); j++) {

					sheet.autoSizeColumn(j);

					String cellStr[] = mappingExcel.get(i).get(j).split(":");

					String cellStr2[] = mappingExcel.get(i).get(j).split("\\.");

					if (cellStr.length > 1 || cellStr2.length < 3) {

						XSSFCell cellContent = rowContent.createCell(j);

						cellContent.setCellValue(mappingExcel.get(i).get(j));

						if (mappingExcel.get(i).size() == 1) {

							cellContent.setCellStyle(style2);

					

						} else {

							cellContent.setCellStyle(style);
						}

						if (cellStr.length > 1) {
							cellContent.setCellStyle(style3);
						}

						aSize = aSize + 1;

			

					} else {

						XSSFCell cellContent = rowContent.createCell(j);

						String url = cellStr2[1] + "." + cellStr2[2];

						if (url.equals("falows.com")) {
							cellContent.setCellValue((mappingExcel.get(i).get(j)));
						} else {

							cellContent.setCellValue(InetAddress.getByName(mappingExcel.get(i).get(j)).toString());
						}

						cellContent.setCellStyle(style);

						bSize = bSize + 1;


					}



				}
			}
		
			
			
			XSSFSheet sheet2 = workbook.createSheet("沒有用到的url");
			
			for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {
				XSSFSheet mySheet = myWorkBook.getSheetAt(i);
				readySheet( mySheet);
			}
			

			ArrayList<String> useList = new ArrayList<String>();
			ArrayList<String> notUseList = new ArrayList<String>();
			
			for (int i = 0; i < excelDataStatusNoDataOne.size(); i++) {

				ArrayList<String> urlArray = excelDataStatusNoDataOne.get(i);

				

				for (int j = 0; j < excelDataStatusNoDataOne.get(i).size(); j++) {
					
					String cellStr[] = excelDataStatusNoDataOne.get(i).get(j).split(":");

					String cellStr2[] = excelDataStatusNoDataOne.get(i).get(j).split("\\.");

					


					String url = cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2];
					
					sheet2.autoSizeColumn(j);
					
					if(!useList.contains(url)) {
						useList.add(url);
						System.out.println(useList);
					}
					


				}
			}
			
			
			
			for (int k = 0; k < useList.size(); k++) {
				XSSFRow rowContent = sheet2.createRow(k + 1); // 建立儲存格
				
				XSSFCell cellContent = rowContent.createCell(0);
				String cellStr[] = useList.get(k).split(":");

				String cellStr2[] = useList.get(k).split("\\.");

				


				String url = cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2];
				
				if (url.equals("www.falows.com")) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.zhengxiong.net"
						.equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("track.zhengxiong.net"
						.equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.ysgmn.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("check.ysgmn.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.peiqu.top".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.lpker.top".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.grhvd.top".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.fjkmt.top".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.fomsy.top".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.szjsdg.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.szwxjb.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.szyymf.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else if ("app.szyymf.cn".equals(cellStr2[0] + "." + cellStr2[1] + "." + cellStr2[2])) {
					cellContent.setCellValue((useList.get(k)));
				} else {
					
					cellContent.setCellValue(
							InetAddress.getByName(useList.get(k)).toString());
				}
				
				cellContent.setCellStyle(style);
				
				bSize = bSize + 1;
		}
			
			
			
			
			
			
			

//			System.out.println(bSize);

			FileOutputStream fosTrue = new FileOutputStream(FILE_Excel);

			workbook.write(fosTrue);
			fosTrue.flush();

			fosTrue.close();
			System.out.println("文件生成");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			

	}
	
	public static void readySheet(XSSFSheet mySheet) {

		Iterator<Row> rowIterator = mySheet.iterator();

		int totalAll = 0;
		int allFalsetotal = 0;
		
		while (rowIterator.hasNext()) {
			
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();

			int allTrue = 0;
			int allFalse = 0;

			ArrayList<String> dataAllFalseArrayList = new ArrayList<String>();
			
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				String cellStr[] = cell.toString().split("/");



				if (cellStr.length > 2) {

					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
					mappingHttpStr = mappingHttpStr.toLowerCase();
					
					if (isTrue(mappingHttpStr)) {
						allTrue = allTrue + 1;

					} else {

						allFalse = allFalse + 1;
						dataAllFalseArrayList.add(httpArrayStr[0]+"."+httpArrayStr[1] + "." + httpArrayStr[2]);

					}

				} 

			}
				

			//判斷資料	
			
			if (allFalse == 4) {

				excelDataStatusNoDataOne.add(dataAllFalseArrayList);
				allFalsetotal = allFalsetotal + 1;

			}

			totalAll = totalAll + 1;
		

		}
			

	
	}

	
	// 判斷兩邊資料
	public static boolean isTrue(String excelHttpStr) {
		for (int i = 0; i < mappingDetail.size(); i++) {
			String mapStr = mappingDetail.get(i);

			if (mapStr != null && !mapStr.trim().equals("")) {
				if (mapStr.equals(excelHttpStr)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public static void removeDuplicate(List list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		System.out.println(list);
	}

}
