package Excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//整理excel用 BS-42
//Organize - Sort out the data of domains
public class checkForExcel {

	static ArrayList<String> mappingDetail = new ArrayList<String>();
	static ArrayList<ArrayList<String>> excelDataStatusOne = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> excelDataStatusTwo = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> excelDataStatusNoDataOne = new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> excelDataStatusNoDataTwo = new ArrayList<ArrayList<String>>();
	private static final String FILE_Excel = "D:\\workExcel\\active_link_Excel.xlsx";

	public static void main(String[] args) throws IOException {
		try {

			textToExcel();

		} catch (ParseException e) {

			e.printStackTrace();

		}
	}

	public static void textToExcel() throws IOException, ParseException {
		// txt檔
		FileReader fr = new FileReader("D:\\workExcel\\toReady.txt");

		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			mappingDetail.add(br.readLine());
		}

		//
		fr.close();
		// 讀excel檔
		File myFile = new File("D:\\workExcel\\test.xlsx");
		FileInputStream fis = new FileInputStream(myFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		// END
		XSSFWorkbook workbook = new XSSFWorkbook(); // 建立Excel物件

		// 字體格式
		XSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index); // 顏色
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL); // 粗細體

		// 設定儲存格格式
		XSSFCellStyle styleRow1 = workbook.createCellStyle();
		styleRow1.setFont(font); // 設定字體
		styleRow1.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平置中
		styleRow1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直置中

		// 設定框線
		styleRow1.setBorderBottom((short) 1);
		styleRow1.setBorderTop((short) 1);
		styleRow1.setBorderLeft((short) 1);
		styleRow1.setBorderRight((short) 1);
		styleRow1.setWrapText(true); // 自動換行

		
		
		
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		
		CellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		
		CellStyle style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
		CellStyle style4 = workbook.createCellStyle();
		style4.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style4.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		
		XSSFSheet sheetC = workbook.createSheet("對照帳號");
		CreateExcelComparisonu(sheetC, workbook, myWorkBook);

		ArrayList<ArrayList<String>> excelDataTrue = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> excelDataFalse = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> excelDataHaveTrue = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Object>> excelData = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<String>> excelDataBankUrlAndAppUrlSame = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> excelDataBankUrlAndAppUrlNoSame = new ArrayList<ArrayList<String>>();
		
		
		for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {

			XSSFSheet mySheet = myWorkBook.getSheetAt(i);


			readySheet(mySheet, excelDataTrue, excelDataFalse, excelDataHaveTrue, excelData,
					excelDataBankUrlAndAppUrlSame, excelDataBankUrlAndAppUrlNoSame,i);

//			if (i == 0) {
//				/* Title */
//				XSSFSheet sheetFalse = workbook.createSheet("hot_domain_was_not_use_(702-904 USE)");
//				XSSFSheet sheetHaveTrue = workbook.createSheet("hot_domain_was_use_(702-904 USE)");
//
//				XSSFSheet sheetBankAppUrl = workbook.createSheet("Bank_AppUrl_Same_(702-904 USE)");
//				XSSFSheet sheetBankAppUrlNoSame = workbook.createSheet("Bank_AppUrl_Not_same_(702-904 USE)");
//
//				CreateExcelDemo(excelDataFalse, sheetFalse, workbook, "dptype", "bankname", "bankseiraIno",style,style2,style3,style4);
//
//				CreateExcelHaveTrue(excelDataHaveTrue, sheetHaveTrue, workbook, true, "dptype", "bankname",
//						"bankseiraIno",style,style2,style3,style4);
//				CreateExcelHaveTrue(excelDataBankUrlAndAppUrlSame, sheetBankAppUrl, workbook, false, "dptype",
//						"bankname", "bankseiraIno",style,style2,style3,style4);
//				CreateExcelHaveTrue(excelDataBankUrlAndAppUrlNoSame, sheetBankAppUrlNoSame, workbook, false, "dptype",
//						"bankname", "bankseiraIno",style,style2,style3,style4);
//
//			} else if (i == 1) {
//
//				/* Title */
//				XSSFSheet sheetFalse = workbook.createSheet("hot_domain_was_not_use_(status1-status2)");
//				XSSFSheet sheetHaveTrue = workbook.createSheet("hot_domain_was_use_(status1-status2)");
//				XSSFSheet sheetBankAppUrl = workbook.createSheet("Bank_AppUrl_Same_(status1-status2)");
//				XSSFSheet sheetBankAppUrlNoSame = workbook.createSheet("Bank_AppUrl_Not_same_(status1-status2)");
//
//				CreateExcelDemo(excelDataFalse, sheetFalse, workbook, "bankis", "cardeseiraIno", "cardstatus",style,style2,style3,style4);
//
//				CreateExcelHaveTrue(excelDataHaveTrue, sheetHaveTrue, workbook, true, "bankis", "cardeseiraIno",
//						"cardstatus",style,style2,style3,style4);
//				CreateExcelHaveTrue(excelDataBankUrlAndAppUrlSame, sheetBankAppUrl, workbook, false, "bankis",
//						"cardeseiraIno", "cardstatus",style,style2,style3,style4);
//				CreateExcelHaveTrue(excelDataBankUrlAndAppUrlNoSame, sheetBankAppUrlNoSame, workbook, false, "bankis",
//						"cardeseiraIno", "cardstatus",style,style2,style3,style4);
//
//			}

		}

		XSSFSheet sheetFalse = workbook.createSheet("hot_domain_was_not_use_");
		XSSFSheet sheetHaveTrue = workbook.createSheet("hot_domain_was_use");

		XSSFSheet sheetBankAppUrl = workbook.createSheet("Bank_AppUrl_Same");
		XSSFSheet sheetBankAppUrlNoSame = workbook.createSheet("Bank_AppUrl_Not_same");

		CreateExcelDemo(excelDataFalse, sheetFalse, workbook, "dptype", "bankname", "bankseiraIno",style,style2,style3,style4);

		CreateExcelHaveTrue(excelDataHaveTrue, sheetHaveTrue, workbook, true, "dptype", "bankname",
				"bankseiraIno",style,style2,style3,style4);
		CreateExcelHaveTrue(excelDataBankUrlAndAppUrlSame, sheetBankAppUrl, workbook, false, "dptype",
				"bankname", "bankseiraIno",style,style2,style3,style4);
		CreateExcelHaveTrue(excelDataBankUrlAndAppUrlNoSame, sheetBankAppUrlNoSame, workbook, false, "dptype",
				"bankname", "bankseiraIno",style,style2,style3,style4);
		
		
		XSSFSheet sheetStatus1 = workbook.createSheet("Status1_domain_was_use");
		XSSFSheet sheetStatus2 = workbook.createSheet("Status2_domain_was_use");
		CreateExcelHaveTrue(excelDataStatusOne, sheetStatus1, workbook, true, "bankis", "cardeseiraIno", "cardstatus",
				style, style2, style3, style4);
		CreateExcelHaveTrue(excelDataStatusTwo, sheetStatus2, workbook, true, "bankis", "cardeseiraIno", "cardstatus",
				style, style2, style3, style4);

		XSSFSheet sheetStatusNoData1 = workbook.createSheet("Status1_domain_was_no_use");
		XSSFSheet sheetStatusNoData2 = workbook.createSheet("Status2_domain_was__no_use");
		CreateExcelDemo(excelDataStatusNoDataOne, sheetStatusNoData1, workbook, "bankis", "cardeseiraIno", "cardstatus",
				style, style2, style3, style4);
		CreateExcelDemo(excelDataStatusNoDataTwo, sheetStatusNoData2, workbook, "bankis", "cardeseiraIno", "cardstatus",
				style, style2, style3, style4);

		FileOutputStream fosTrue = new FileOutputStream(FILE_Excel);

		workbook.write(fosTrue);
		fosTrue.flush();

		fosTrue.close();
		
		System.out.println("文件生成");

	}

	public static void readySheet(XSSFSheet mySheet, ArrayList<ArrayList<String>> excelDataTrue,
			ArrayList<ArrayList<String>> excelDataFalse, ArrayList<ArrayList<String>> excelDataHaveTrue,
			ArrayList<ArrayList<Object>> excelData, ArrayList<ArrayList<String>> excelDataBankUrlAndAppUrlSame,
			ArrayList<ArrayList<String>> excelDataBankUrlAndAppUrlNoSame,int sheetNum) {

		Iterator<Row> rowIterator = mySheet.iterator();

		int totalAll = 0;
		int allTruetotal = 0;
		int allFalsetotal = 0;
		int haveTrue = 0;

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();

			int allTrue = 0;
			int allFalse = 0;

			ArrayList<String> dataAllTrueArrayList = new ArrayList<String>();
			ArrayList<String> dataAllFalseArrayList = new ArrayList<String>();
			ArrayList<String> dataHaveTrueArrayList = new ArrayList<String>();
			ArrayList<Object> dataeArrayList = new ArrayList<Object>();

			ArrayList<String> dataStatusOneArrayList = new ArrayList<String>();
			ArrayList<String> dataStatusTwoArrayList = new ArrayList<String>();

			int Write = 0;

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				String cellStr[] = cell.toString().split("/");

				if (cellStr.length > 2) {

					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
					mappingHttpStr = mappingHttpStr.toLowerCase();

					// System.out.print(mappingHttpStr + "\t");
					// System.out.print(isTrue(mappingHttpStr) + "\t");

					// String url = httpArrayStr[0]+"."+httpArrayStr[1] + "." + httpArrayStr[2];

					if (Write < 1) {
						dataeArrayList.add(mappingHttpStr);
						Write = Write + 1;
					}

					if (isTrue(mappingHttpStr)) {
						allTrue = allTrue + 1;
						dataAllTrueArrayList.add(cell.toString());
						dataHaveTrueArrayList.add(cell.toString());
						dataeArrayList.add(cell.toString());
						dataeArrayList.add(true);

						dataStatusOneArrayList.add(cell.toString());
						dataStatusTwoArrayList.add(cell.toString());

					} else {

						allFalse = allFalse + 1;
						dataAllFalseArrayList.add(cell.toString());
						dataHaveTrueArrayList.add(cell.toString());
						dataeArrayList.add(cell.toString());
						dataeArrayList.add(false);

						dataStatusOneArrayList.add(cell.toString());
						dataStatusTwoArrayList.add(cell.toString());

					}

				} else {

					dataAllTrueArrayList.add(cell.toString());
					dataAllFalseArrayList.add(cell.toString());
					dataHaveTrueArrayList.add(cell.toString());

					dataeArrayList.add(cell.toString());

					dataStatusOneArrayList.add(cell.toString());

					dataStatusTwoArrayList.add(cell.toString());
				}

			}

			// 判斷資料
			if (allTrue == 4) {

				excelDataTrue.add(dataAllTrueArrayList);
				allTruetotal = allTruetotal + 1;

			}

			if (allFalse == 4) {

				excelDataFalse.add(dataAllFalseArrayList);
				allFalsetotal = allFalsetotal + 1;

			}

			if (allTrue > 0) {

				excelDataHaveTrue.add(dataHaveTrueArrayList);
				haveTrue = haveTrue + 1;

			}

			if (allTrue > 0) {
				String cellStr[] = dataHaveTrueArrayList.get(4).split("/");
				String cellStr2[] = dataHaveTrueArrayList.get(5).split("/");
				// System.out.print(cell.toString() + "\t");

				if (cellStr.length > 2 && cellStr2.length > 2) {

					String httpArrayStr1[] = cellStr[2].split("\\.");
					String mappingHttpStr1 = httpArrayStr1[1] + "." + httpArrayStr1[2];
					String httpArrayStr2[] = cellStr2[2].split("\\.");
					String mappingHttpStr2 = httpArrayStr2[1] + "." + httpArrayStr2[2];
					// bankUrl same as appUrl

					if (mappingHttpStr1.equals(mappingHttpStr2)) {
						System.out.println(mappingHttpStr1);
						excelDataBankUrlAndAppUrlSame.add(dataHaveTrueArrayList);
						// bankUrl != appUrl

					} else {
						excelDataBankUrlAndAppUrlNoSame.add(dataHaveTrueArrayList);

					}

				}

			}

			if (allTrue > 0 && dataHaveTrueArrayList.get(3).equals("1.0")) {
				excelDataStatusOne.add(dataHaveTrueArrayList);
			}

			if (allFalse == 4 && dataAllFalseArrayList.get(3).equals("1.0")) {
				excelDataStatusNoDataOne.add(dataAllFalseArrayList);
			}

			if (allTrue > 0 && dataHaveTrueArrayList.get(3).equals("2.0")) {
				excelDataStatusTwo.add(dataHaveTrueArrayList);
			}

			if (allFalse == 4 && dataAllFalseArrayList.get(3).equals("2.0")) {
				excelDataStatusNoDataTwo.add(dataAllFalseArrayList);
			}

			totalAll = totalAll + 1;
			excelData.add(dataeArrayList);

		}

		// System.out.println("totalAll====>" + totalAll);
		// System.out.println("allTruetotal====>" + allTruetotal);
		// System.out.println("allFalsetotal====>" + allFalsetotal);
		// System.out.println("haveTrue====>" + haveTrue);

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

	/**
	 * 對照帳號
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * 
	 */
	public static void CreateExcelComparisonu(XSSFSheet sheet, XSSFWorkbook workbook, XSSFWorkbook myWorkBook)
			throws ParseException, IOException {

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

					}

					if (cellStr.length > 1) {
						cellContent.setCellStyle(style3);
					}

				}

			}
		}

	}

	/**
	 * 
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void CreateExcelHaveTrue(ArrayList<ArrayList<String>> excelDataTrue, XSSFSheet sheet,
			XSSFWorkbook workbook, boolean isBankAPPUrl, String cell1Str, String cell2Str, String cell3Str,
			CellStyle style, CellStyle style2, CellStyle style3, CellStyle style4) throws ParseException, IOException {

		XSSFRow rowTitle = sheet.createRow(0);
		XSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.WHITE.index); // 顏色
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗細體
		font.setFontHeightInPoints((short) 20);

		XSSFCell cell = rowTitle.createCell(0);
		cell.setCellValue("id");
		cell.setCellStyle(style);

		XSSFCell cell1 = rowTitle.createCell(1);
		cell1.setCellValue(cell1Str);
		cell1.setCellStyle(style);
		
		XSSFCell cell2 = rowTitle.createCell(2);
		cell2.setCellValue(cell2Str);
		cell2.setCellStyle(style);
		
		XSSFCell cell3 = rowTitle.createCell(3);
		cell3.setCellValue(cell3Str);
		cell3.setCellStyle(style);

		XSSFCell cell4 = rowTitle.createCell(4);
		cell4.setCellValue("appurl");
		cell4.setCellStyle(style);

		XSSFCell cell5 = rowTitle.createCell(5);
		cell5.setCellValue("bankurl");
		cell5.setCellStyle(style);

		XSSFCell cell6 = rowTitle.createCell(6);
		cell6.setCellValue("severurl");
		cell6.setCellStyle(style);

		XSSFCell cell7 = rowTitle.createCell(7);
		cell7.setCellValue("sucessurl");
		cell7.setCellStyle(style);

		ArrayList<ArrayList<String>> mappingListString = new ArrayList<ArrayList<String>>();

		int pag = 0;

		ArrayList<String> acount1 = new ArrayList<String>();
		ArrayList<String> acount2 = new ArrayList<String>();
		ArrayList<String> acount3 = new ArrayList<String>();

		for (int i = 0; i < mappingDetail.size(); i++) {

			String url[] = mappingDetail.get(i).split(":");

			if (url[0].equals("linkUrl")) {
				pag = pag + 1;
			}

			if (pag == 1) {
				acount1.add(mappingDetail.get(i));
			} else if (pag == 2) {
				acount2.add(mappingDetail.get(i));
			} else if (pag == 3) {
				acount3.add(mappingDetail.get(i));
			}
		}

		mappingListString.add(acount1);
		mappingListString.add(acount2);
		mappingListString.add(acount3);
		int num = 0;

		for (int X = 0; X < mappingListString.size(); X++) {
			XSSFRow rowContent = sheet.createRow(num + 1); // 建立儲存格
			XSSFCell cellContent = rowContent.createCell(0);
			
			cellContent.setCellStyle(style2);
			
			if (X == 0) {
				cellContent.setCellValue("linkUrl:https://ph.godaddy.com");

				num = num + 1;
			} else if (X == 1) {
				cellContent.setCellValue("linkUrl:https://ph.godaddy.com 2");

				num = num + 1;
			} else if (X == 2) {
				cellContent.setCellValue("linkUrl:https://www.namesilo.com/");

				num = num + 1;
			}

			for (int O = 0; O < mappingListString.get(X).size(); O++) {
				for (int i = 0; i < excelDataTrue.size(); i++) {
					boolean doWriteDate = false;

					String id = "";
					String dptype = "";
					String bankname = "";
					String bankseiraIno = "";
					String appurl = "";
					String bankurl = "";
					String severurl = "";
					String sucessurl = "";

					String appurl2 = "";
					String bankurl2 = "";
					String severurl2 = "";
					String sucessurl2 = "";

					if (excelDataTrue.get(i).get(0) != null) {
						id = excelDataTrue.get(i).get(0);
					}
					if (excelDataTrue.get(i).get(1) != null) {
						dptype = excelDataTrue.get(i).get(1);
					}
					if (excelDataTrue.get(i).get(2) != null) {
						bankname = excelDataTrue.get(i).get(2);
					}
					if (excelDataTrue.get(i).get(3) != null) {
						bankseiraIno = excelDataTrue.get(i).get(3);
					}
					if (excelDataTrue.get(i).get(4) != null) {

						appurl = excelDataTrue.get(i).get(4);
						String cellStr[] = appurl.split("/");
						if (cellStr.length > 2) {
							String httpArrayStr[] = cellStr[2].split("\\.");
							String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
							appurl = mappingHttpStr;
							appurl2 = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
							if (mappingListString.get(X).get(O).equals(mappingHttpStr)) {
								doWriteDate = true;
							}
						}
					}
					if (excelDataTrue.get(i).get(5) != null) {
						bankurl = excelDataTrue.get(i).get(5);
						String cellStr[] = bankurl.split("/");
						if (cellStr.length > 2) {
							String httpArrayStr[] = cellStr[2].split("\\.");
							String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
							bankurl = mappingHttpStr;
							bankurl2 = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
							if (mappingListString.get(X).get(O).equals(mappingHttpStr)) {
								doWriteDate = true;
							}
						}
					}
					if (excelDataTrue.get(i).get(6) != null) {
						severurl = excelDataTrue.get(i).get(6);
						String cellStr[] = severurl.split("/");
						if (cellStr.length > 2) {
							String httpArrayStr[] = cellStr[2].split("\\.");
							String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
							severurl = mappingHttpStr;
							severurl2 = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
							if (mappingListString.get(X).get(O).equals(mappingHttpStr)) {
								doWriteDate = true;
							}
						}
					}
					if (excelDataTrue.get(i).get(7) != null) {
						sucessurl = excelDataTrue.get(i).get(7);
						String cellStr[] = sucessurl.split("/");
						if (cellStr.length > 2) {
							String httpArrayStr[] = cellStr[2].split("\\.");
							String mappingHttpStr = httpArrayStr[1] + "." + httpArrayStr[2];
							sucessurl = mappingHttpStr;
							sucessurl2 = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
							if (mappingListString.get(X).get(O).equals(mappingHttpStr)) {
								doWriteDate = true;
							}
						}
					}

					if (isBankAPPUrl) {

						if (appurl.equals(severurl) || bankurl.equals(severurl)) {
							severurl = "";
						} else {
							severurl = severurl2;
						}

						if (bankurl.equals(sucessurl) || appurl.equals(sucessurl)) {
							sucessurl = "";
						} else {
							sucessurl = sucessurl2;
						}
						appurl = appurl2;

						bankurl = bankurl2;

					} else {
						appurl = appurl2;

						bankurl = bankurl2;

						severurl = severurl2;

						sucessurl = sucessurl2;
					}

					if (doWriteDate) {
						System.out.println(id);
						XSSFRow rowContent1 = sheet.createRow(num + 1); // 建立儲存格
						num = num + 1;
						sheet.autoSizeColumn(i);

						XSSFCell cellContent1 = rowContent1.createCell(0);
						
						styleCell(cellContent1,style3,style4,num);
						

						cellContent1.setCellValue(id);
						cellContent1 = rowContent1.createCell(1);

						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(dptype);
						cellContent1 = rowContent1.createCell(2);

						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(bankname);
						cellContent1 = rowContent1.createCell(3);

						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(bankseiraIno);
						cellContent1 = rowContent1.createCell(4);

						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(appurl);
						cellContent1 = rowContent1.createCell(5);

						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(bankurl);
						cellContent1 = rowContent1.createCell(6);
						styleCell(cellContent1,style3,style4,num);

						cellContent1.setCellValue(severurl);
						cellContent1 = rowContent1.createCell(7);

						styleCell(cellContent1,style3,style4,num);
						
						cellContent1.setCellValue(sucessurl);
					}
				}

			}
		}
	}

	public static void styleCell(XSSFCell cellContent1, CellStyle style3, CellStyle style4, int num) {
		
		if (num % 2 == 0) {

			cellContent1.setCellStyle(style3);
			
		} else if (num % 2 == 1) {

			cellContent1.setCellStyle(style4);
		}
	}
	
	
	
	
	
	/**
	 * 
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void CreateExcelDemo(ArrayList<ArrayList<String>> excelDataTrue, XSSFSheet sheet,
			XSSFWorkbook workbook, String cell1Str, String cell2Str, String cell3Str,CellStyle style, CellStyle style2, CellStyle style3, CellStyle style4)
			throws ParseException, IOException {

		XSSFRow rowTitle = sheet.createRow(0);

		XSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index); // 顏色
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗細體

		XSSFCellStyle myStyle = workbook.createCellStyle();
		myStyle.setFillBackgroundColor(HSSFColor.BLACK.index);

		rowTitle.setRowStyle(myStyle);

		
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setFontHeightInPoints((short) 20);
		style.setFont(font);

		XSSFCell cell = rowTitle.createCell(0);
		cell.setCellValue("id");
		cell.setCellStyle(style);

		XSSFCell cell1 = rowTitle.createCell(1);
		cell1.setCellValue(cell1Str);
		cell1.setCellStyle(style);
		XSSFCell cell2 = rowTitle.createCell(2);
		cell2.setCellValue(cell2Str);
		cell2.setCellStyle(style);
		XSSFCell cell3 = rowTitle.createCell(3);
		cell3.setCellValue(cell3Str);
		cell3.setCellStyle(style);

		XSSFCell cell4 = rowTitle.createCell(4);
		cell4.setCellValue("appurl");
		cell4.setCellStyle(style);
		XSSFCell cell5 = rowTitle.createCell(5);
		cell5.setCellValue("bankurl");
		cell5.setCellStyle(style);
		XSSFCell cell6 = rowTitle.createCell(6);
		cell6.setCellValue("severurl");
		cell6.setCellStyle(style);
		XSSFCell cell7 = rowTitle.createCell(7);
		cell7.setCellValue("sucessurl");
		cell7.setCellStyle(style);

		for (int i = 0; i < excelDataTrue.size(); i++) {
			XSSFRow rowContent = sheet.createRow(i + 1); // 建立儲存格
			sheet.autoSizeColumn(i);
			String id = "";
			String dptype = "";
			String bankname = "";
			String bankseiraIno = "";
			String appurl = "";
			String bankurl = "";
			String severurl = "";
			String sucessurl = "";

			if (excelDataTrue.get(i).get(0) != null) {
				id = excelDataTrue.get(i).get(0);
			}
			if (excelDataTrue.get(i).get(1) != null) {
				dptype = excelDataTrue.get(i).get(1);
			}
			if (excelDataTrue.get(i).get(2) != null) {
				bankname = excelDataTrue.get(i).get(2);
			}
			if (excelDataTrue.get(i).get(3) != null) {
				bankseiraIno = excelDataTrue.get(i).get(3);
			}
			if (excelDataTrue.get(i).get(4) != null) {

				appurl = excelDataTrue.get(i).get(4);
				String cellStr[] = appurl.split("/");
				if (cellStr.length > 2) {
					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
					;
					appurl = mappingHttpStr;

				}
			}
			if (excelDataTrue.get(i).get(5) != null) {
				bankurl = excelDataTrue.get(i).get(5);
				String cellStr[] = bankurl.split("/");
				if (cellStr.length > 2) {
					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
					;
					bankurl = mappingHttpStr;

				}
			}
			if (excelDataTrue.get(i).get(6) != null) {
				severurl = excelDataTrue.get(i).get(6);
				String cellStr[] = severurl.split("/");
				if (cellStr.length > 2) {
					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
					;
					severurl = mappingHttpStr;

				}
			}
			if (excelDataTrue.get(i).get(7) != null) {
				sucessurl = excelDataTrue.get(i).get(7);
				String cellStr[] = sucessurl.split("/");
				if (cellStr.length > 2) {
					String httpArrayStr[] = cellStr[2].split("\\.");
					String mappingHttpStr = httpArrayStr[0] + "." + httpArrayStr[1] + "." + httpArrayStr[2];
					;
					sucessurl = mappingHttpStr;

				}
			}

			XSSFCell cellContent = rowContent.createCell(0);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(id);
			cellContent = rowContent.createCell(1);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(dptype);
			cellContent = rowContent.createCell(2);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(bankname);
			cellContent = rowContent.createCell(3);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(bankseiraIno);
			cellContent = rowContent.createCell(4);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(appurl);
			cellContent = rowContent.createCell(5);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(bankurl);
			cellContent = rowContent.createCell(6);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(severurl);
			cellContent = rowContent.createCell(7);

			styleCell(cellContent,style3,style4,i);

			cellContent.setCellValue(sucessurl);

		}

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
		
		// System.out.println(list);
		
	}


}
