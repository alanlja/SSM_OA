package common.excel;

import java.text.DecimalFormat ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import org.apache.poi.ss.usermodel.Cell ;
import org.apache.poi.ss.usermodel.CellStyle ;
import org.apache.poi.ss.usermodel.DateUtil ;
import org.apache.poi.ss.usermodel.Font ;
import org.apache.poi.ss.usermodel.Row ;
import org.apache.poi.ss.usermodel.Sheet ;
import org.apache.poi.ss.usermodel.Workbook ;
import org.apache.poi.ss.util.CellRangeAddress ;

public class ExcelUtil {

	//excel文件的后缀 = .xls
	public static final String EXCEL_FILE_SUFFIX = ".xls" ;

	//默认的excel列宽=20
	public static final int DEFAULT_COLUMN_WIDTH = 250 ;

	/**
	 * 读取Excel列表
	 * @param wsheet
	 * @param startIndex
	 * @param fields
	 * @return
	 * @see
	 * @since
	 */
	public List< Map< String , Object >> readSimple ( Sheet wsheet , int startIndex , String[] fields ) {
		List< Map< String , Object >> list = new ArrayList< Map< String , Object >> () ;
		for ( Row row : wsheet ) {
			// 从数据行开始读
			if ( row.getRowNum () >= startIndex ) {

				Map< String , Object > rowMap = new HashMap< String , Object > () ;
				//startIndex为2，row为视觉第三行时，第二个for循环完成时rowMap里的数据
				//rowMap.put ( "userChName" ,"彦吴祖") ;
				//rowMap.put ( "mobilePhone" ,"13378696977");
				//rowMap.put ( "email" ,"1642483368@qq.com");
				//rowMap.put ( "userSexChName12" ,"男");
				//rowMap.put ( "userName" ,"wuyanzu");
				//rowMap.put ( "orgName1" ,"教学部");
				for ( Cell cell : row ) {
					// fields代表的列以后的数据忽略  fields.length=6
					if ( cell.getColumnIndex () < fields.length ) {
						String key = fields[cell.getColumnIndex ()];
						Object value = getValue ( cell );
						rowMap.put ( key , value ) ;
					}
				}
				list.add ( rowMap ) ;
			}
		}
		return list ;
	}

	/**
	 * 得到Excel中单元格的值
	 * @param cell
	 * @return
	 * @see
	 * @since
	 */
	private Object getValue ( Cell cell ) {
		Object value = null ;
		DecimalFormat df = new DecimalFormat ( "#.####" ) ;
		switch ( cell.getCellType () ) {
			case Cell.CELL_TYPE_STRING :
				value = cell.getRichStringCellValue ().getString () ;
				break ;
			case Cell.CELL_TYPE_NUMERIC :
				if ( DateUtil.isCellDateFormatted ( cell ) ) {
					value = CalendarHelper.formatDatetime ( cell.getDateCellValue () ) ;
				} else {
					value = df.format ( cell.getNumericCellValue () ) ;
				}
				break ;
			case Cell.CELL_TYPE_BOOLEAN :
				value = cell.getBooleanCellValue () ;
				break ;
			case Cell.CELL_TYPE_FORMULA :
				value = cell.getCellFormula () ;
				break ;
			default :
				value = cell.getStringCellValue () ;
		}
		return value ;
	}

	/**
	 * 导出excel格式的文件
	 * @param wsheet
	 * @param parameters
	 * @throws_RowsExceededException
	 * @throws_WriteException
	 * @see
	 * @since
	 */
	public void simpleExport ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		fillHeaders ( wwb , wsheet , parameters ) ;
		fillContent ( wwb , wsheet , parameters ) ;
	}

	private void fillHeaders ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		//合并单元格
		wsheet.addMergedRegion ( new CellRangeAddress ( 0 , 0 , 0 , parameters.getFieldsName ().length - 1 ) ) ;


		Row titleRow = wsheet.createRow ( 0 ) ;// 标题行
		Cell titileCell = titleRow.createCell ( 0 ) ;


		Font titleFont = wwb.createFont () ;// 标题字体
		titleFont.setFontHeightInPoints ( ( short ) 30 ) ;
		titleFont.setFontName ( "Courier New" ) ;
		titleFont.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;

		CellStyle titileStyle = wwb.createCellStyle () ;// 标题单元格格式：居中，底对齐
		titileStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		titileStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;
		titileStyle.setFont ( titleFont ) ;


		titileCell.setCellStyle ( titileStyle ) ;
		titileCell.setCellValue ( parameters.getTitle () ) ;


		wsheet.setDefaultColumnWidth ( 20 * DEFAULT_COLUMN_WIDTH ) ;

		//设置单元格的宽度
		for ( int i = 0 ; i < parameters.getFieldsName ().length ; ++i ) {
			if ( parameters.getWidths () != null ) {
				wsheet.setColumnWidth ( i , Integer.parseInt ( parameters.getWidths ()[i] ) * DEFAULT_COLUMN_WIDTH ) ;
			}
		}


		Font font = wwb.createFont () ;// 列标题行字体
		font.setFontHeightInPoints ( ( short ) 15 ) ;
		font.setFontName ( "Courier New" ) ;
		font.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;

		CellStyle cellStyle = wwb.createCellStyle () ;// 列标题行单元格格式：居中，底对齐
		cellStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		cellStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;
		cellStyle.setFont ( font ) ;


		Row columnRow = wsheet.createRow ( 1 ) ;// 列标题行
		//姓名,性别,电话,省份,地市,区县,生日";
		for ( int i = 0 ; i < parameters.getFieldsName ().length ; ++i ) {
			Cell cell = columnRow.createCell (i) ;
			cell.setCellStyle ( cellStyle ) ;
			cell.setCellValue ( parameters.getFieldsName ()[i] ) ;
		}
	}

	private void fillContent ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		List< Map< String , Object >> list = parameters.getDataList () ;
		String value = "" ;
		//userChName,userSex,mobilePhone,provinceName,cityName,contryName,userBirthday
		String[] field = parameters.getFieldsId () ;

		for ( int i = 0 ; i < list.size () ; i++ ) {

			Row row = wsheet.createRow ( i + 2 ) ;

			for ( int j = 0 ; j < field.length ; j++ ) {
				Cell cell = row.createCell ( j ) ;
				Object origin = list.get ( i ).get ( field[j] ) ;// 原始数据
				if ( origin instanceof java.sql.Date ) {
					java.sql.Date d = ( java.sql.Date ) origin ;
					value = CalendarHelper.formatDatetime ( d ) ;
				} else {
					value = String.valueOf ( origin ) ;
				}
				if ( value == null || value.equalsIgnoreCase ( "null" ) ) {
					value = "" ;
				}
				cell.setCellValue ( value ) ;
			}
		}
	}
}
