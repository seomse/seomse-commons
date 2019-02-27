/** 
 * <pre>
 *  파 일 명 : QuickSortStringArray.java
 *  설    명 : 문자열을을 받은 순서대로 정렬 퀵정렬알고리즘 이용
 *  
 *                    
 *  작 성 자 : macle
 *  작 성 일 : 2017.09
 *  버    전 : 1.0
 *  수정이력 : 
 *  기타사항 :
 * </pre>
 * @author  Copyrights 2017 by ㈜섬세한사람들. All right reserved.
 */

package com.seomse.commons.utils.sort;

public class QuickSortStringArray {


	private String [] array;

	
	/**
	 * 생성자
	 * 생성과 동시 정렬
	 * @param array
	 * @param num
	 * @param isAsc
	 */
	public QuickSortStringArray(String [] array, int [] num, boolean isAsc){
		
		this.array = array;
		
		if(isAsc){
			sortAsc(num, 0, num.length-1);
		}else{
			sortDesc(num, 0, num.length-1);
			
		}
	}
	
	/**
	 * 생성자
	 * 생성과 동시 정렬
	 * @param array
	 * @param num
	 * @param isAsc
	 */
	public QuickSortStringArray(String [] array, double [] num, boolean isAsc){
		
		this.array = array;
		
		if(isAsc){
			sortAsc(num, 0, num.length-1);
		}else{
			sortDesc(num, 0, num.length-1);
			
		}
	}
	
	/**
	 * 생성자
	 * @param array
	 */
	public QuickSortStringArray(String [] array){
		this.array = array;
	}
	
	/**
	 * 내림차순정렬
	 * @param num
	 */
	public void sortDesc (int [] num){
		sortDesc(num, 0, num.length-1);
	}
	/**
	 * 내림차순정렬
	 * @param num
	 * @param lo
	 * @param hi
	 */
	public void sortDesc (int [] num,  int lo, int hi){
	    int i=lo, j=hi, h;
	    int x=num[(lo+hi)/2];
	    String temp;
	    do
	    {    
	        while (num[i]>x) i++; 
	        while (num[j]<x) j--;
	        if (i<=j)
	        {
	            h=num[i]; 
	            temp = array[i]; 
	            num[i]=num[j]; 
	            array[i] = array[j];
	            num[j]=h;
	            array[j] = temp;
	            
	            i++; j--;
	        }
	    } while (i<=j);

	
	    if (lo<j) sortDesc(num, lo, j);
	    if (i<hi) sortDesc(num, i, hi);
	    
	}
	
	
	/**
	 * 오름차순 정렬
	 * @param num
	 */
	public void sortAsc(int[] num){
		sortAsc(num, 0, num.length-1);
	}
	
	/**
	 * 오름차순정렬
	 * @param num
	 * @param lo
	 * @param hi
	 */
	public  void sortAsc(int[] num, int lo, int hi){
		// BASE CASE
		if (lo >= hi)
			// lo has equalled (or crossed) hi, so we have
			// either a singleton or an empty array, either
			// way, it is sorted
			return;
		// first, set the pivot to the first element
		int pivot = num[lo];
		// now, keep pivoting until the left and right indexes cross
		int leftIndex = lo + 1;
		int rightIndex = hi;
		while (rightIndex > leftIndex)
		{
			// 1. if the element on the right is in the right place,
			// move the rightIndex one to the left
			if (num[rightIndex] > pivot)
				rightIndex--;
			
			// 2. else if the element on the left is in the right place,
			// move the leftIndex one to the right
			else if (num[leftIndex] <= pivot)
				leftIndex++;
			
			// 3. otherwise, both sides are wrong, so SWAP them
			else
				swap(num,leftIndex,rightIndex);
		}
		// at this point, leftIndex and rightIndex equal each
		// other, and the pivot is stored in a[lo].
		// We now want to swap the pivot (a[0]) with the last
		// element on the left.  This will either be the element
		// where the two indexes met, or the one just to its left.
		int newPivotIndex;
		if (num[leftIndex] > pivot)
			newPivotIndex = leftIndex - 1;
		else
			newPivotIndex = leftIndex;
		// now just swap the pivot into its new location
		swap(num,lo,newPivotIndex);
		// now recursively sort the left and right sides, being
		// sure not to sort the pivot again!
		sortAsc(num,lo,newPivotIndex-1);
		sortAsc(num,newPivotIndex+1,hi);
	}
	
	private  void swap(int[] num, int i, int j)
	{
		int temp = num[i];
		String tempString = array[i]; 
		num[i] = num[j];
		array[i] = array[j];
		num[j] = temp;
		array[j] = tempString;
       
//        
//        a[i]=a[j]; 
//        
//        a[j]=h;
//        array[j] = temp;
        
	}

	
	
	/**
	 * 오름차순 정렬
	 * @param num
	 */
	public void sortAsc(double [] num){
		sortAsc(num, 0, num.length-1);
	}

	/**
	 * 오름차순정렬
	 * @param num
	 * @param lo
	 * @param hi
	 */
	public  void sortAsc(double [] num, int lo, int hi){
		// BASE CASE
		if (lo >= hi)
			// lo has equalled (or crossed) hi, so we have
			// either a singleton or an empty array, either
			// way, it is sorted
			return;
		// first, set the pivot to the first element
		double pivot = num[lo];
		// now, keep pivoting until the left and right indexes cross
		int leftIndex = lo + 1;
		int rightIndex = hi;
		while (rightIndex > leftIndex)
		{
			// 1. if the element on the right is in the right place,
			// move the rightIndex one to the left
			if (num[rightIndex] > pivot)
				rightIndex--;
			
			// 2. else if the element on the left is in the right place,
			// move the leftIndex one to the right
			else if (num[leftIndex] <= pivot)
				leftIndex++;
			
			// 3. otherwise, both sides are wrong, so SWAP them
			else
				swap(num,leftIndex,rightIndex);
		}
		// at this point, leftIndex and rightIndex equal each
		// other, and the pivot is stored in a[lo].
		// We now want to swap the pivot (a[0]) with the last
		// element on the left.  This will either be the element
		// where the two indexes met, or the one just to its left.
		int newPivotIndex;
		if (num[leftIndex] > pivot)
			newPivotIndex = leftIndex - 1;
		else
			newPivotIndex = leftIndex;
		// now just swap the pivot into its new location
		swap(num,lo,newPivotIndex);
		// now recursively sort the left and right sides, being
		// sure not to sort the pivot again!
		sortAsc(num,lo,newPivotIndex-1);
		sortAsc(num,newPivotIndex+1,hi);
	}
	
	/**
	 * 내림차순정렬
	 * @param num
	 */
	public void sortDesc (double [] num){
		sortDesc(num, 0, num.length-1);
	}
	/**
	 * 내림차순정렬
	 * @param num
	 * @param lo
	 * @param hi
	 */
	public void sortDesc (double [] num,  int lo, int hi){
	    int i=lo, j=hi ;
	    double h;
	    double x=num[(lo+hi)/2];
	    String temp;
	    do
	    {    
	        while (num[i]>x) i++; 
	        while (num[j]<x) j--;
	        if (i<=j)
	        {
	            h=num[i]; 
	            temp = array[i]; 
	            num[i]=num[j]; 
	            array[i] = array[j];
	            num[j]=h;
	            array[j] = temp;
	            
	            i++; j--;
	        }
	    } while (i<=j);

	
	    if (lo<j) sortDesc(num, lo, j);
	    if (i<hi) sortDesc(num, i, hi);
	    
	}
	
	private  void swap(double[] num, int i, int j)
	{
		double temp = num[i];
		String tempString = array[i]; 
		num[i] = num[j];
		array[i] = array[j];
		num[j] = temp;
		array[j] = tempString;
       
//        
//        a[i]=a[j]; 
//        
//        a[j]=h;
//        array[j] = temp;
        
	}

}
