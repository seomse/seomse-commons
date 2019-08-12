package com.seomse.commons.utils;

import com.seomse.commons.file.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <pre>
 *  파 일 명 : ExcelGet.java
 *  설    명 : 코드체계 관련 유틸성 클래스
 *
 *  작 성 자 : macle
 *  작 성 일 : 2018.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2018 by ㈜섬세한사람들. All right reserved.
 */
public class ExcelGet {
    private static final Logger logger = LoggerFactory.getLogger(ExcelGet.class);

    private FormulaEvaluator formulaEvaluator;


    public void setFormulaEvaluator(FormulaEvaluator formulaEvaluator){
        this.formulaEvaluator = formulaEvaluator;
    }


    public void setXSSFWorkbook(XSSFWorkbook xSSFWorkbook){
        formulaEvaluator = xSSFWorkbook.getCreationHelper().createFormulaEvaluator();
    }


    public String getCellValue( XSSFSheet sheet, int rowNum, int cellNum){
        return getCellValue(sheet, rowNum, cellNum, null);
    }

    public String getCellValue(XSSFRow row, int cellNum){

        return getCellValue(row, cellNum, null );
    }

    /**
     * cell의 값을 스트링 형태로 반환한다
     * @param row row
     * @param cellNum 열번호
     * @param dateFormat 테이터 포멧(ex:yyyy.MM.dd HH:mm:ss) 날짜형식이 아닐경우 null 전달
     * @return cell의값을 스트링형태로 반환
     */
    public String getCellValue(XSSFRow row, int cellNum, String dateFormat){
        if(row == null){
            return null;
        }
        return getCellValue(row.getCell(cellNum), dateFormat);
    }

    /**
     * cell의 값을 스트링 형태로 반환한다
     * @param sheet sheet
     * @param rowNum 행번호
     * @param cellNum 열번호
     * @param dateFormat 테이터 포멧(ex:yyyy.MM.dd HH:mm:ss) 날짜형식이 아닐경우 null 전달
     * @return cell의값을 스트링형태로 반환
     */
    public String getCellValue( XSSFSheet sheet, int rowNum, int cellNum, String dateFormat){
        XSSFRow row = sheet.getRow(rowNum);
        if(row == null){
            return null;
        }
        return getCellValue(row.getCell(cellNum), dateFormat);
    }


    public String getCellValue(XSSFCell cell){
        return getCellValue(cell, null);
    }

    /**
     * cell의 값을 스트링 형태로 반환한다
     * @param cell cell
     * @param dateFormat 테이터 포멧(ex:yyyy.MM.dd HH:mm:ss) 날짜형식이 아닐경우 null 전달
     * @return cell의값을 스트링형태로 반환
     */
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public String getCellValue(XSSFCell cell, String dateFormat){
        if(cell == null){
            return null;
        }

        switch(cell.getCellTypeEnum()){


            case NUMERIC:
                return cellNumber(cell, dateFormat);
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case ERROR:
                return cell.getErrorCellString();
            case BLANK:
                return null;
            case _NONE:
                return null;
            case FORMULA:
                try{
                    switch(formulaEvaluator.evaluateFormulaCellEnum(cell)){
                        case NUMERIC:
                            return cellNumber(cell, dateFormat);
                        case STRING:
                            return cell.getStringCellValue();
                        case BOOLEAN:
                            return cell.getBooleanCellValue() + "";
                        case ERROR:
                            return cell.getErrorCellString();
                        default:
                            return null;
                    }
                }catch(Exception e){
                    switch(cell.getCachedFormulaResultTypeEnum()){
                        case NUMERIC:
                            return cellNumber(cell, dateFormat);
                        case STRING:
                            return cell.getStringCellValue();
                        case BOOLEAN:
                            return cell.getBooleanCellValue() + "";
                        case ERROR:
                            return cell.getErrorCellString();
                        default:
                            return null;
                    }
                }
            default:
                return null;
        }


    }

    private String cellNumber(XSSFCell cell, String dateFormat){
        if(HSSFDateUtil.isCellDateFormatted(cell) && dateFormat != null){
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            return formatter.format(cell.getDateCellValue());
        }
        String cellValue = Double.toString(cell.getNumericCellValue());
        if(cellValue.endsWith(".0"))
            cellValue = cellValue.substring(0, cellValue.length()-2);

        return cellValue ;
    }


    /**
     * 경로내에 있는 모들 엑셀파일을 파일형태로 불러온다.
     * @param path 폴더경로 또는 파일경로
     * @return 파일리스트
     */
    public List<File> getExcelFileList(String path){
        return FileUtil.getFileList(path, ".xlsx");
    }


    /**
     * row 개수 얻기
     * last row num bug fix
     * @return row count
     */
    public int getRowCount(XSSFSheet sheet){
        XSSFRow row;
        int rowCount = sheet.getLastRowNum();
        //엑셀 라스트 로우넘 버그처리
        while(true){
            try{
                row = sheet.getRow(rowCount);
                if(row == null){
                    break;
                }
                rowCount ++;
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
                break;
            }


        }
        return rowCount;
    }

    /**
     * Column 개수얻기
     * bug fix
     * @return Column count
     */
    public int getColumnCount(XSSFRow row){
        int columnCount = row.getLastCellNum();
        //컬럼 마지막인덱스가져오기 poi자체에대한 버그처리
        while(true){
            try{
                XSSFCell cell = row.getCell(columnCount);

                if(cell == null){
                    break;
                }
                columnCount ++;
            }catch(Exception e){
                logger.error(ExceptionUtil.getStackTrace(e));
                break;
            }
        }
        return columnCount;
    }
}
