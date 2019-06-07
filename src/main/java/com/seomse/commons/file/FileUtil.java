
package com.seomse.commons.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.commons.utils.string.Check;

/**
 * <pre>
 *  파 일 명 : FileUtil.java
 *  설    명 : File을 사용할때 공통으로 사용할만한 메소드들을 정의
 *
 *
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.2
 *  수정이력 :  2018.04, 2019.05.28(sortToLength 추가)
 *  기타사항 :
 * </pre>
 * @author Copyrights 2017 ~ 2019 by ㈜섬세한사람들. All right reserved.
 */

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);


	/**
	 * 파일 내용을 줄바꿈 단위로 가져온다
	 * @param charSet 파일 케릭터셋
	 * @return 파일 라인 리스트
	 */
	@SuppressWarnings("unused")
	public static  List<String> getFileContentsList(File file, String charSet){
		List<String> dataList = new ArrayList<>();
		
		BufferedReader br = null;
		try{
			 String line;
	         br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet));
	          while ((line = br.readLine()) != null) {
	        	  dataList.add(line);
	          }
	          br.close();	          
	     
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
		}finally {
			//noinspection CatchMayIgnoreException
			try{if(br != null)br.close();}catch(Exception e){}
		}

		
		return dataList;
	}
	
	
	/**
	 * 파일 내용을 줄바꿈 단위로 가져온다
	 * @param charSet 파일 케릭터셋
	 * @return 파일 라인 리스트
	 */
	@SuppressWarnings({"unused", "WeakerAccess"})
	public static String getFileContents(File file, String charSet){
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		
		try{
			 String line;
	         br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet));
	         
	         while ((line = br.readLine()) != null) {
	        	  sb.append("\n");
	        	  sb.append(line);
	         }
	     
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
		}finally{
			//noinspection CatchMayIgnoreException
			try{if(br != null)br.close();}catch(Exception e){}
		}
		
		if(sb.length() == 0){
			return "";
		}
		
		return sb.toString().substring(1);
	}
	
	
	/**
	 * 경로내에 있는 모들 파일을 파일형태로 불러온다.
	 * @param path 폴더경로 또는 파일경로
	 * @return 파일리스트
	 */
	@SuppressWarnings("unused")
	public static  List<File> getFileList(String path){
		List<File> fileList = new ArrayList<>();
		File file = new File(path);
		addFiles(fileList, file);
		return fileList;
	}

	private static void addFiles(List<File> fileList, File file){
		fileList.add(file);
		if(file.isDirectory()){
			File [] files = file.listFiles();

			if(files == null){
				return;
			}

			for(File f : files){
				addFiles(fileList, f);
			}
		}
	}
	
	/**
	 * 경로내에 있는 파일중에 확장자를 지정하여 불러온다
	 * @param path 파일경로
	 * @param fileExtension 확장자명 ex: .txt
	 * @return 파일리스트
	 */
	@SuppressWarnings("unused")
	public static  List<File> getFileList(String path, String fileExtension){
		List<File> fileList = new ArrayList<>();
		File file = new File(path);
		addFiles(fileList, file);
		
		List<File> resultFileList = new ArrayList<>();
		for(File f : fileList){
			if(f.getName().endsWith(fileExtension)){
				resultFileList.add(f);
			}
		}
		fileList.clear();

		return resultFileList;
	}
	
	
	/**
	 * 경로내에 있는 파일중에 파일명이 정규식에 해당하는 파일들을 불러온다.
	 * @param path 파일경로
	 * @param regex 정규식
	 * @return 파일리스트
	 */
	@SuppressWarnings("unused")
	public static  List<File> getRegexFileList(String path, String regex){
		List<File> fileList = new ArrayList<>();
		File file = new File(path);
		addFiles(fileList, file);
		
		Pattern pattern = Pattern.compile(regex);
		List<File> resultFileList = new ArrayList<>();
		for(File f : fileList){
			Matcher matcher = pattern.matcher(f.getName());
			
			if(matcher.find()){
				resultFileList.add(f);
			}
		}
		fileList.clear();

		return resultFileList;
	}
	

	/**
	 * 파일에 내용을 기입한다.
	 * 기본케릭터셋 utf-8
	 * @param outValue 내용
	 * @param fileName 파일명
	 * @param isAppend 기존내용에 추가할지 새로만들지에 대한 여부
	 */
	@SuppressWarnings({"unused"})
	public static void fileOutput(String outValue, String fileName, boolean isAppend){
		fileOutput(outValue ,"UTF-8", fileName,  isAppend);
		
	}
	
	/**
	 * 파일에 내용을 기입한다.
	 * @param outValue 내용
	 * @param charSet 케릭터셋
	 * @param fileName 파일명
	 * @param isAppend 기존내용에 추가할지 새로만들지에 대한 여부
	 */
	@SuppressWarnings("WeakerAccess")
	public static void fileOutput(String outValue, String charSet, String fileName, boolean isAppend){
		
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(fileName, isAppend);
			out.write(outValue.getBytes(charSet));	
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
		}finally{
			try{if(out!= null)out.close(); }catch(Exception e){logger.error(ExceptionUtil.getStackTrace(e));}
		}
	}
	
	/**
	 * 경로 전체 복사
	 * @param inPath 입력경로
	 * @param outPath 출력경로
	 * @return 복사 성공여부
	 */
	@SuppressWarnings({"unused"})
	public static boolean copy(String inPath, String outPath){
		File file = new File(inPath);
		if(!file.exists()){
			return false;
		}
		
		String filePath = file.getAbsolutePath();
		
		if(file.isDirectory()){
			//하위까지 전체복사
			List<File> fileList = getFileList(inPath);
			for(File subFile : fileList){	
				String subPath = subFile.getAbsolutePath();
				String newPath = subPath.substring(filePath.length());
				newPath = outPath + "/" + newPath;
					
					
				File newFile = new File(newPath);
				//noinspection ResultOfMethodCallIgnored
				newFile.getParentFile().mkdirs();
				if(subFile.isDirectory())//noinspection SingleStatementInBlock
				{
					//noinspection ResultOfMethodCallIgnored
					newFile.mkdir();
				}else{
					fileCopy(subPath, newPath);
				}
							
			}
			fileList.clear();
			
		}else{
			return fileCopy(inPath, outPath);
		}
		
		return true;
	}
	
	/**
	 * 파일을 복사한다.
	 * @param inFileName 복사대상
	 * @param outFileName 복사파일
	 */
	@SuppressWarnings("WeakerAccess")
	public static boolean fileCopy(String inFileName, String outFileName) {
		 try {
			 FileInputStream fis = new FileInputStream(inFileName);
			 FileOutputStream fos = new FileOutputStream(outFileName);
	   
			 
			 FileChannel fcin =  fis.getChannel();
			 FileChannel fcout = fos.getChannel();
			 
			 
			 long size = fcin.size();
			 fcin.transferTo(0, size, fcout);

			 fcin.close();
			 fcout.close();
			 
			 fis.close();
			 fos.close();
			 return true;
		 } catch (Exception e) {
			 logger.error(ExceptionUtil.getStackTrace(e));
			 return false;
		 }
	}

	 /**
	  * 파일 이동
	  * @param inFileName 이동대상
	  * @param outFileName 이동파일
	  * @param isNameMake 파일명이 존재하면 이름을 자동생서할지 여부 (1) 형태로 붙어서 생성됨
	  */
	@SuppressWarnings("unused")
	public static boolean move(String inFileName, String outFileName, boolean isNameMake) {
		
		try{
			if(isNameMake){
				File file = new File(outFileName);
				if(file.isFile()){
					outFileName = makeName(file);
				}
			}
			
			Path file = Paths.get(inFileName);
			Path movePath = Paths.get(outFileName);
			Files.move(file , movePath);
			return true;
		}catch(Exception e){
			logger.error(ExceptionUtil.getStackTrace(e));
			return false;
		}
	}
	
	/**
	 * 이름이 중복일때 이름생성 확장자전에 (1) 을 붙여서생성
	 * @param file 파일
	 * @return 사용될 파일명
	 */
	private static String makeName(File file){
		
		String parentPath ;
		
		File parent = file.getParentFile();
		if(parent == null){
			parentPath ="";
		}else{
			parentPath = parent.getAbsolutePath();
		}
		
		String fileName = file.getName();
		//확장자
		String extension ;
		int index = fileName.lastIndexOf('.');
		
		if(index != -1){
			extension = fileName.substring(index);
			
			fileName = fileName.substring(0, index);
			
		}else{
			extension  = "";
		}
		
		int makeIndex =2;
		
		int startIndex ;
		if(fileName.charAt(fileName.length()-1) == ')'){
			startIndex = fileName.lastIndexOf('(');
			
			
			String numValue ;
			if(startIndex != -1){
				numValue = fileName.substring(startIndex+1, fileName.length()-1);
				if(Check.isNumber(numValue)){
					fileName = fileName.substring(0, startIndex);
					makeIndex = Integer.parseInt(numValue);
					makeIndex ++ ;
				}
				
				
			}
			

		}

		while (new File(parentPath + "/" + fileName + "(" + makeIndex + ")" + extension).isFile()) {
			makeIndex++;
		}
		
		
		
		return parentPath + "/" + fileName + "(" + makeIndex +")" + extension;
	}
	
	
	 
	/**
	 * 파일의 내용을 변경한다.
	 * @param path 변경할 최상위 경로
	 * @param charSet 케릭터셋
	 * @param value 변경전값
	 * @param newValue 변경후값
	 * @param isFileName 파일명 변경여부
	 * @param isRegex 정규식 사용여부
	 */
	@SuppressWarnings("unused")
	public static void fileContentsChange(String path, String charSet, String value, String newValue, boolean isFileName, boolean isRegex){
		List<File> fileList = getFileList(path);
		for(File file : fileList){
			if(file.isDirectory()){
				
				continue;
			}
			
			String fileName = file.getAbsolutePath();
			
			boolean isFileNameChange = false;
			
			if(isFileName){
				//파일명 변경
				if(file.getName().contains(value)){
					File newfile = new File( file.getAbsolutePath());
					File parentFile = newfile.getParentFile();
					fileName =file.getName().replace(value, newValue);
					if(parentFile != null){
						fileName = newfile.getParentFile().getAbsolutePath() + "/" + fileName ;
					}
					
					isFileNameChange = true;
				}
			}
			
			
			
			String contents = getFileContents(file, charSet);
			if(isRegex){
				contents = contents.replaceAll(value, newValue);
			}else{
				contents = contents.replace(value, newValue);			
			}		
			
			fileOutput(contents, charSet , fileName, false);

			if(isFileNameChange){
				//noinspection ResultOfMethodCallIgnored
				file.delete();
			}
		}
		
	}
	 

	/**
	 * 경로에 있는 파일을 불러와서 언어셋을 변경한다.
	 * @param path  경로
	 * @param charSet 변경전 언어셋
	 * @param newCharSet 병견된 언어셋
	 */
	@SuppressWarnings("unused")
	public static void charSetChange(String path, String charSet, String newCharSet){
		List<File> fileList = getFileList(path);
		for(File file : fileList){
			if(file.isDirectory()){
				continue;
			}
			
			String fileContents = getFileContents(file, charSet);
			fileOutput(fileContents,newCharSet,file.getAbsolutePath(),false);
		}
	}

	/**
	 * 파일이 읽을 있는 상태 여부
	 * @param filePath 파일경로
	 * @return isReadableFile
	 */
	@SuppressWarnings("unused")
	public static boolean isReadableFile(String filePath) {
		File file = new File(filePath);
		return (file.exists() && file.canRead() && !file.isDirectory());
	}
	
	/**
	 * 디렉토리가 비어있는지 여부
	 * @return isEmptyDir 디렉토리가 비어있는지 여부
	 */
	@SuppressWarnings("unused")
	public static boolean isEmptyDir(String dirPath) {
		
		File file = new File(dirPath);	
		return isEmptyDir(file);
		
	}
	
	/**
	 * 디렉토리가 비어있는지 여부
	 * @param dirFile dirFile
	 * @return isEmptyDir 디렉토리가 비어있는지 여부
	 */
	@SuppressWarnings("WeakerAccess")
	public static boolean isEmptyDir(File dirFile) {
		if(!dirFile.isDirectory()) {
			return false;
		}


		//noinspection ConstantConditions
		return dirFile.list() != null && dirFile.list().length == 0;

	}

	/**
	 * 이렉토리를 비어있게 만들기
	 * @param dirPath 디렉토리 경로
	 * @return 성공실패 여부
	 */
	public static boolean emptyDir(String dirPath){
		return emptyDir(new File(dirPath));
	}

	/**
	 * 이렉토리를 비어있게 만들기
	 * @param dirFile 디렉토리 파일
	 * @return 성공실패 여부
	 */
	public static boolean emptyDir(File dirFile){
		File [] files = dirFile.listFiles();
		if(files == null){
			return true;
		}

		for(File file : files){
			delete(file);
		}

		return isEmptyDir(dirFile);
	}

	/**
	 * 폴더 및 모든파일 삭제
	 * @param path 경로
	 * @return 삭제 성공 여부
	 */
	@SuppressWarnings("unused")
	public static boolean delete(String path) {
		return delete(new File(path));
	}
	
	/**
	 * 폴더 및 모든파일 삭제
	 * @param file 삭제대상 파일
	 * @return 삭제 성공 여부
	 */
	@SuppressWarnings("WeakerAccess")
	public static boolean delete(File file) {
		if(file.isDirectory()) {

			File [] files = file.listFiles();

			if(files == null){
				logger.error("file delete fail: " + file.getAbsolutePath());
				return false;
			}
			boolean isResult = true;

			for(File cFile : files) {
				if(cFile.isDirectory()) {
					boolean chkResult = delete(cFile);
					if(!chkResult) {
						logger.error("file delete fail: " + cFile.getAbsolutePath());
						
						isResult= false;	
					}
				}else {
					boolean chkResult =cFile.delete();
					if(!chkResult) {
						logger.error("file delete fail: " + cFile.getAbsolutePath());
						
						isResult= false;	
					}
				}
			}
			
			
			boolean chkResult = file.delete();
			if(!chkResult) {
				logger.error("file delete fail: " + file.getAbsolutePath());
				
				isResult= false;	
			}
			
			return isResult;
		}else {
			boolean chkResult =  file.delete();
			if(!chkResult) {
				logger.error("file delete fail: " + file.getAbsolutePath());
			}
			
			return chkResult;
		}
	}


	private final static Comparator<File> FILE_SORT_ASC =  new Comparator<File>() {
		@Override
		public int compare(File f1, File f2 ) {
			return Long.compare(f1.length(), f2.length());
		}
	};


	private final static Comparator<File> FILE_SORT_DESC =  new Comparator<File>() {
		@Override
		public int compare(File f1, File f2 ) {
			return Long.compare(f2.length(), f1.length());
		}
	};

	/**
	 * 파일을 length (byte 크기) 로 정렬
	 * @param files 정렬대상 파일 목록
	 * @param isAsc 오름차순여부
	 */
	public static void sortToLength(File [] files, boolean isAsc){
		Comparator<File> sort;
		if(isAsc){
			sort = FILE_SORT_ASC;
		}else{
			sort = FILE_SORT_DESC;
		}
		Arrays.sort(files, sort);
	}

}