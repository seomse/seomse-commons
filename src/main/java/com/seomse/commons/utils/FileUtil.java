/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.commons.utils;

import com.seomse.commons.exception.IORuntimeException;
import com.seomse.commons.utils.string.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 파일처리 관련 유틸성 클래스
 * @author macle
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);


	/**
	 * 파일 내용을 줄바꿈 단위로 가져 온다
	 * @param file File target text file
	 * @param charSet String 파일 케릭터셋
	 * @return List 파일 라인 리스트
	 */
	@SuppressWarnings("unused")
	public static  List<String> getFileContentsList(File file, String charSet){
		List<String> dataList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet))){
			 String line;
	          while ((line = br.readLine()) != null) {
	        	  dataList.add(line);
	          }
		}catch(IOException e){
			throw new IORuntimeException(e);
		}


		return dataList;
	}
	

	/**
	 * 파일 내용을 줄바꿈 단위로 가져온다
	 * @param file File target text file
	 * @param charSet String 파일 케릭터셋
	 * @return String 파일 라인 리스트
	 */
	@SuppressWarnings({"unused", "WeakerAccess"})
	public static String getFileContents(File file, String charSet){
		
		StringBuilder sb = new StringBuilder();

		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet))){
			 String line;

	         while ((line = br.readLine()) != null) {
	        	  sb.append("\n");
	        	  sb.append(line);
	         }
	     
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
		if(sb.length() == 0){
			return "";
		}
		
		return sb.substring(1);
	}
	
	
	/**
	 * 경로내에 있는 모들 파일을 파일형태로 불러온다.
	 * @param path String 폴더경로 또는 파일경로
	 * @return List 파일리스트
	 */
	@SuppressWarnings("unused")
	public static List<File> getFileList(String path){
		List<File> fileList = new ArrayList<>();
		File file = new File(path);
		addFiles(fileList, file);
		return fileList;
	}

	/**
	 * list에 파일 누적
	 * @param fileList List 파일을 누적 하는 list
	 * @param file File dir or file
	 */
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
	 * @param path String 파일경로
	 * @param fileExtension String 확장자명 ex: .txt
	 * @return List 파일리스트
	 */
	@SuppressWarnings("unused")
	public static List<File> getFileList(String path, String fileExtension){
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
	 * @param path String 파일경로
	 * @param regex String 정규식
	 * @return List 파일리스트
	 */
	@SuppressWarnings("unused")
	public static List<File> getRegexFileList(String path, String regex){
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
	 * @param outValue String 내용
	 * @param fileName String 파일명
	 * @param isAppend boolean 기존내용에 추가할지 새로만들지에 대한 여부
	 */
	@SuppressWarnings({"unused"})
	public static void fileOutput(String outValue, String fileName, boolean isAppend){
		fileOutput(outValue ,"UTF-8", fileName,  isAppend);
		
	}
	
	/**
	 * 파일에 내용을 기입한다.
	 * @param outValue String 내용
	 * @param charSet String 케릭터셋
	 * @param fileName String 파일명
	 * @param isAppend boolean 기존내용에 추가할지 새로만들지에 대한 여부
	 */
	@SuppressWarnings("WeakerAccess")
	public static void fileOutput(String outValue, String charSet, String fileName, boolean isAppend){
		
		try(FileOutputStream out =  new FileOutputStream(fileName, isAppend)){
			out.write(outValue.getBytes(charSet));
			out.flush();
			out.getFD().sync();
		}catch(IOException e){
			//io, nio 패키지를 같이쓰면 잘 써지고도 에러나는 경우가 있으므로 예외처리
			logger.error(ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 경로 전체 복사
	 * @param inPath String 입력경로
	 * @param outPath String 출력경로
	 * @return boolean success, fail flag
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
	 * @param inFileName String 복사대상
	 * @param outFileName String 복사파일
	 * @return boolean success, fail flag
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
			 //io, nio 패키지를 같이쓰면 잘 되도 에러나는 경우가 있으므로 로그처리
			 logger.error(ExceptionUtil.getStackTrace(e));
			 return false;
		 }
	}

	/**
	 * nio 와 io 둘다 활용 하여 체크
	 * 둘중에 하나만 사용 하면 각자 다른 nio, io 이용 했을때 인식을 못할 떄 가 있음
	 * @param filePath String
	 * @return boolean
	 */
	public static boolean isFile(String filePath){
		return Files.isRegularFile(Paths.get(filePath)) || new File(filePath).isFile();
	}

	/**
	 * nio 와 io 둘다 활용 하여 체크
	 * 둘중에 하나만 사용 하면 각자 다른 nio, io 이용 했을때 인식을 못할 떄 가 있음
	 * @param directoryPath String
	 * @return boolean
	 */
	public static boolean isDirectory(String directoryPath){
		return Files.isDirectory(Paths.get(directoryPath)) || new File(directoryPath).isDirectory();
	}

	/**
	 * nio 와 io 둘다 활용 하여 체크
	 * 둘중에 하나만 사용 하면 각자 다른 nio, io 이용 했을때 인식을 못할 떄 가 있음
	 * @param path String
	 * @return boolean
	 */
	public static boolean exists(String path){
		return Files.exists(Paths.get(path)) || new File(path).exists();
	}


	/**
	  * 파일 이동
	  * @param inFileName String 이동대상
	  * @param outFileName String 이동파일
	  * @param isNameMake boolean 파일명이 존재하면 이름을 자동생서할지 여부 (1) 형태로 붙어서 생성됨
	  * @return boolean success, fail flag
	  */
	@SuppressWarnings({"unused", "UnusedReturnValue"})
	public static boolean move(String inFileName, String outFileName, boolean isNameMake) {
		
		try{
			if(isNameMake){
				File file = new File(outFileName);
				if(isFile(outFileName)){
					outFileName = makeName(file);
				}
			}
			
			Path file = Paths.get(inFileName);
			Path movePath = Paths.get(outFileName);
			Files.move(file , movePath);
			return true;
		}catch(Exception e){
			//io, nio 패키지를 같이쓰면 잘 되도 에러나는 경우가 있으므로 로그처리

			logger.error(ExceptionUtil.getStackTrace(e));
			return false;
		}
	}
	
	/**
	 * 이름이 중복일때 이름생성 확장자전에 (1) 을 붙여서생성
	 * @param file File 파일
	 * @return String 사용될 파일명
	 */
	public static String makeName(File file){

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

		while (isFile(parentPath + "/" + fileName + "(" + makeIndex + ")" + extension)) {
			makeIndex++;
		}
		
		
		
		return parentPath + "/" + fileName + "(" + makeIndex +")" + extension;
	}
	
	
	 
	/**
	 * 파일의 내용을 변경한다.
	 * @param path String 변경할 최상위 경로
	 * @param charSet String 케릭터셋
	 * @param value String 변경전값
	 * @param newValue String 변경후값
	 * @param isFileName boolean 파일명 변경여부
	 * @param isRegex boolean 정규식 사용여부
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
	 * @param path String 경로
	 * @param charSet String 변경전 언어셋
	 * @param newCharSet String 병견된 언어셋
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
	 * @param filePath String 파일경로
	 * @return boolean isReadableFile
	 */
	@SuppressWarnings("unused")
	public static boolean isReadableFile(String filePath) {
		File file = new File(filePath);
		return (file.exists() && file.canRead() && !file.isDirectory());
	}

	/**
	 * 디렉토리가 비어있는지 여부
	 * @param dirPath String dir path
	 * @return boolean empty dir flag
	 */
	@SuppressWarnings("unused")
	public static boolean isEmptyDir(String dirPath) {
		
		File file = new File(dirPath);	
		return isEmptyDir(file);
		
	}
	
	/**
	 * 디렉토리가 비어있는지 여부
	 * @param dirFile File dirFile
	 * @return boolean isEmptyDir 디렉토리가 비어있는지 여부
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
	 * @param dirPath String 디렉토리 경로
	 * @return boolean 성공실패 여부
	 */
	public static boolean emptyDir(String dirPath){
		return emptyDir(new File(dirPath));
	}

	/**
	 * 이렉토리를 비어있게 만들기
	 * @param dirFile String 디렉토리 파일
	 * @return boolean 성공실패 여부
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
	 * @param path String 경로
	 * @return boolean 삭제 성공 여부
	 */
	@SuppressWarnings("unused")
	public static boolean delete(String path) {
		return delete(new File(path));
	}
	
	/**
	 * 폴더 및 모든파일 삭제
	 * @param file File 삭제대상 파일
	 * @return boolean 삭제 성공 여부
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


	private final static Comparator<File> FILE_SORT_ASC = Comparator.comparingLong(File::length);


	private final static Comparator<File> FILE_SORT_DESC = (f1, f2) -> Long.compare(f2.length(), f1.length());

	/**
	 * 파일을 length (byte 크기) 로 정렬
	 * @param files File [] 정렬대상 파일 목록
	 * @param isAsc boolean 오름차순여부
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


	/**
	 * 라인카운트에 맞게 파일쪼개기
	 * 파일명은 확장자없는 숫자
	 * 생긴 숫자형 파일이 덮어쓰일 수 있으므로 숫자이름의 파일이 없는 폴더로 실행할 것
	 * 기본경로 split_line
	 * @param file File
	 * @param lineCount int line count
	 * @param charSet String char set
	 */
	public static void splitLine(File file, int lineCount, String charSet){
		File dirFile = file.getParentFile();
		String defaultPath = dirFile.getAbsolutePath() +"/split_line";
		splitLine(file, defaultPath, lineCount, charSet);
	}



	/**
	 * 라인카운트에 맞게 파일쪼개기
	 * 파일명은 확장자없는 숫자
	 * 생긴 숫자형 파일이 덮어쓰일 수 있으므로 숫자이름의 파일이 없는 폴더로 실행할 것
	 * @param file File file
	 * @param outDirPath String outPath
	 * @param lineCount int line count
	 * @param charSet String char set
	 */
	public static void splitLine(File file, String outDirPath, int lineCount, String charSet){
		StringBuilder sb = new StringBuilder();

		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charSet)) ){

			int fileNameIndex = 1;

			File dirFile = new File(outDirPath);
			if(!dirFile.isDirectory()){
				//noinspection ResultOfMethodCallIgnored
				dirFile.mkdirs();
			}


			String line;

			int count = 0;

			while ((line = br.readLine()) != null) {
				sb.append("\n");
				sb.append(line);
				count ++;
				if(count >=lineCount){
					fileOutput(sb.substring(1),charSet,outDirPath+"/"+fileNameIndex, false);
					fileNameIndex++;
					count =0;
					sb.setLength(0);
				}
			}

			if(count > 0){
				fileOutput(sb.substring(1),charSet,outDirPath+"/"+fileNameIndex, false);
			}


		}catch(IOException e){
			throw new IORuntimeException(e);
		}

	}


	/**
	 * 앞에 문자를 지정하여 파일명 변경
	 * 하위폴더에 있는 모든 파일이 대상임
	 * @param path String 경로
	 * @param prefix String 앞에 붙일 이름
	 */
	public static void renamePrefix(String path, String prefix){

		List<File> fileList = getFileList(path);

		for(File file :fileList){
			if(file.isDirectory()){
				continue;
			}
			move(file.getAbsolutePath(), file.getParentFile().getAbsolutePath()+"/" +prefix + file.getName(),true);
		}

	}


	/**
	 * nio를 활용한 파일 라인 읽기
	 * @param path String String
	 * @param lineIndex int start index 0
	 * @return String line text
	 */
	public static String getLineNio(String path, int lineIndex){
		try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
			//noinspection OptionalGetWithoutIsPresent
			return lines.skip(lineIndex).findFirst().get();
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
	}

	/**
	 * nio를 활용한 파일 라인 읽기
	 * @param path String
	 * @param cs Charset
	 * @param lineIndex int start index 0
	 * @return String line text
	 */
	public static String getLineNio(String path, Charset cs, int lineIndex){
		try (Stream<String> lines = Files.lines(Paths.get(path), cs)) {
			//noinspection OptionalGetWithoutIsPresent
			return lines.skip(lineIndex).findFirst().get();
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
	}

	/**
	 * io를 활용한 파일 라인 읽기
	 * core 엔진을 위한 메소드로 빠르게 처리할 수 있는 방법으로함
	 * BufferReader 를 이요한 방법과
	 * nio를 이용한 방법보다 5배 가까이 빠름
	 * @param path String
	 * @param lineIndex int start index 0
	 * @return String line text
	 */
	public static String getLine(String path, int lineIndex){
		return getLine(path, StandardCharsets.UTF_8, lineIndex);
	}

	/**
	 * io를 활용한 파일 라인 읽기
	 * core 엔진을 위한 메소드로 빠르게 처리할 수 있는 방법으로함
	 * BufferReader 를 이요한 방법과
	 * nio를 이용한 방법보다 10배 가까임 빠름
	 * @param path String
	 * @param cs Charset
	 * @param lineIndex int start index 0
	 * @return String line text
	 */
	public static String getLine(String path, Charset cs, int lineIndex){
		try (
				FileInputStream stream = new FileInputStream(path)
		){

			int lastLineIndex = 0;

			boolean isMake = lineIndex == lastLineIndex;

			List<byte[]> byteList = new ArrayList<>();
			int size = 0;

			byte[] buffer = new byte[10240];
			int n;

			while ((n = stream.read(buffer)) > 0) {

				int start = 0;

				for (int i = 0; i < n; i++) {

					if (buffer[i] == '\n') {
						lastLineIndex++;
						if(isMake){

							byte [] add = Arrays.copyOfRange(buffer, start, i);
							size += add.length;
							byteList.add(add);

							return toStringBytesList(byteList, size, cs);
						}
						isMake = lineIndex == lastLineIndex;
						start = i+1;
					}
				}

				if(isMake){
					byte [] add = Arrays.copyOfRange(buffer, start, n);
					size += add.length;
					byteList.add(add);

				}
			}


			if(size == 0){
				return "";
			}
			return toStringBytesList(byteList, size, cs);
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
	}

	private static String toStringBytesList(List<byte[]> byteList, int size,  Charset cs){

		int i=0;
		byte [] buffer = new byte[size];

		for(byte [] bs : byteList){
			for (byte b : bs) {
				buffer[i++] = b;
			}
		}
		return new String(buffer, cs);
	}

	/**
	 * 파일 라인 수 얻기 java.io 활용
	 * core 엔진 용으로 최적의 성능을 내는 소스로 개발
	 * LineNumberReader 를 활용한 방법과
	 * Nio 메소드 보다도 10배 가까이 빠름
	 * @param path String filePath
	 * @return long
	 */
	public static long getLineCount(String path){

		File file = new File(path);
		if(file.length() == 0){
			return 0;
		}

		try (
				FileInputStream stream = new FileInputStream(file)
		){
			byte[] buffer = new byte[10240];
			long count = 0;
			int n;
			while ((n = stream.read(buffer)) > 0) {
				for (int i = 0; i < n; i++) {
					if (buffer[i] == '\n') count++;
				}
			}
			return ++count;
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
	}

	/**
	 * 파일 라인 수 얻기 java.nio 활용
	 * @param path String filePath
	 * @return long
	 */
	public static long getLineCountNio(String path){
		try {
			return Files.lines(Paths.get(path)).count();
		}catch(IOException e){
			throw new IORuntimeException(e);
		}
	}



}