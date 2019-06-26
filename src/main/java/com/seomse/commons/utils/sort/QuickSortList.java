


package com.seomse.commons.utils.sort;

import java.util.List;

/**
 * <pre>
 *  파 일 명 : QuickSortList.java
 *  설    명 : list 정렬, 퀵정렬 이용
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
@SuppressWarnings("Duplicates")
public class QuickSortList {


	@SuppressWarnings("rawtypes")
	private List list;
//	private double [][] num;
	/**
	 * 생성자
	 * @param list
	 */
	public QuickSortList(@SuppressWarnings("rawtypes") List list){
		if(list.size() <2){
			return;
		}
		this.list = list;
	}
	
	/**
	 * 생성자
	 * 생성과 동시 정렬
	 * @param list
	 * @param num 
	 * @param isAsc
	 */
	public QuickSortList(@SuppressWarnings("rawtypes") List list, int [] num, boolean isAsc){
		if(list.size() <2){
			return;
		}
		this.list = list;
		if(isAsc){
			quicksortAsc(num, 0, num.length-1);
		}else{
			quicksortDesc(num, 0, num.length-1);
			
		}
	}
	/**
	 * 생성자 생성과 동시에 정렬
	 * @param list
	 * @param num
	 * @param isAsc
	 */
	public QuickSortList(@SuppressWarnings("rawtypes") List list, double [] num, boolean isAsc){
		if(list.size() <2){
			return;
		}
		this.list = list;
		if(isAsc){
			quicksortAsc(num, 0, num.length-1);
		}else{
			quicksortDesc(num, 0, num.length-1);
			
		}
	}
	/**
	 * 내림차순 정렬 (double형)
	 * @param num
	 */
	public void sortDesc(double [] num){
		quicksortDesc(num, 0, num.length-1);
	}
	
	/**
	 * 내림차순 정렬 (double형) 정렬범위 지정 
	 * @param num
	 * @param lower
	 * @param hight 
	 */
	public void sortDesc(double [] num, int lower, int hight){
		quicksortDesc(num, lower, hight);
	}
	
	/**
	 * 오름차순 정렬 (int 형)
	 * @param num
	 */
	public void sortAsc(int [] num){
		quicksortAsc(num, 0, num.length-1);
	}
	/**
	 * 오름차순 정렬 (int 형) 정렬범위 지정
	 * @param num
	 * @param lower
	 * @param hight
	 */
	public void sortAsc(int [] num, int lower, int hight){
		quicksortAsc(num, lower, hight);
	}
	/**
	 * 내림차순 정렬( int형)
	 * @param num
	 */
	public void sortDesc(int [] num){
		quicksortDesc(num, 0, num.length-1);
	}
	
	/**
	 * 내림차순 정렬( int형) 정렬범위 지정
	 * @param num
	 * @param lower
	 * @param hight
	 */
	public void sortDesc(int [] num, int lower, int hight){
		quicksortDesc(num, lower, hight);
	}
	
	
//	public QuickSortList(@SuppressWarnings("rawtypes") List list, int [] num, boolean isAsc){
//		if(list.size() <2){
//			return;
//		}
//		this.list = list;
//		if(isAsc){
//			quicksortAsc(num, 0, num.length-1);
//		}else{
//			quicksortDesc(num, 0, num.length-1);
//			
//		}
//	}
//	
//	public QuickSortList(@SuppressWarnings("rawtypes") List list, int [] num){
//		if(list.size() <2){
//			return;
//		}
//		this.list = list;
//		quicksortDesc(num, 0, num.length-1);
//	}
//	
//	public QuickSortList(@SuppressWarnings("rawtypes") List list, int [] num, int lower, int hight){
//		if(list.size() <2){
//			return;
//		}
//		this.list = list;
//		quicksortDesc(num, lower , hight);
//	}
	
//	public QuickSortList(@SuppressWarnings("rawtypes") List list, double [][] num){
//		if(list.size() <2){
//			return;
//		}
//		if(num == null)
//			return;
//		
//		this.list = list;
//		this.num = num;
//		
//		int end =num[0].length-1;
//		sort(0, end, 0) ;
//		sortToNext( 0, end, 0);
//	}
	
//	private void sortToNext(int start,int end,int index){
//		int cnt=0, changevalue=0;
//		for(int sorti=start;sorti<end;sorti++){		
//				try{
//					if(num[index][sorti]==num[index][sorti+1]){
//						cnt++;
//						changevalue++;
//					}else{	break;}
//				}catch(java.lang.StringIndexOutOfBoundsException e){logger.error(org.moara.common.util.ExceptionUtil.getStackTrace(e)); return ;}		
//		}
//		cnt+=start;
//		if(index<num.length-1 && changevalue!=0){
//			index++;
//			sort(start,cnt,index);
//		}
//		
//		if(start<cnt && index!=num.length-1)sortToNext(start, cnt, index);
//		if(index>0 && changevalue!=0)index--;
//		start=cnt+1;
//		if(start<end)sortToNext(start, end, index);
//
//	}
	
//	private void sort( int lo, int hi, int index){
//	    
//		int i=lo, j=hi;
//		double h; 
////		int [] a= num[index];
//	    double x=num[index][(lo+hi)/2];
//	    Object temp;
//	    do
//	    {    
//	        while (num[index][i]>x) i++; 
//	        while (num[index][j]<x) j--;
//	        if (i<=j)
//	        {
////	            h=num[index][i];   
//	            temp = list.get(i); 
////	            num[index][i]=num[index][j]; 
//	            insert(j,i);
////	            num[index][j]=h;
//	            insert(temp,j);
//	            
//	            for(int k=0 ; k<num.length ; k++){
//	            	   h=num[k][i];   
//	            	   num[k][i]=num[k][j]; 
//	            	   num[k][j]=h;
//	            }
//	            
//	            i++; j--;
//	            
//	        }
//	    } while (i<=j);
//
//	    if (lo<j) sort( lo, j, index);
//	    if (i<hi) sort( i, hi, index);
//	}
	
	@SuppressWarnings("unchecked")
	private  void insert(int x,int y){
		Object xData = list.get(x);
		
		list.add(y,xData);
		list.remove(y+1);
	} 
	@SuppressWarnings("unchecked")
	private  void insert(Object pivotData ,int y){
		list.add(y,pivotData);
		list.remove(y+1);
	}
	private void quicksortDesc (int [] a,  int lo, int hi){
	    int i=lo, j=hi, h;
	    int x=a[(lo+hi)/2];
	    Object temp;
	    do
	    {    
	        while (a[i]>x) i++; 
	        while (a[j]<x) j--;
	        if (i<=j)
	        {
	            h=a[i]; 
	            temp = list.get(i); 
	            a[i]=a[j]; 
	            insert(j,i);
	            a[j]=h;
	            insert(temp,j);
	            
	            i++; j--;
	        }
	    } while (i<=j);

	
	    if (lo<j) quicksortDesc(a, lo, j);
	    if (i<hi) quicksortDesc(a, i, hi);
	    
	}
	
	//내림차순정렬
	private void quicksortDesc (double [] a,  int lo, int hi){
	    int i=lo, j=hi;
	    double h;
	    double x=a[(lo+hi)/2];
	    Object temp;
	    do
	    {    
	        while (a[i]>x) i++; 
	        while (a[j]<x) j--;
	        if (i<=j)
	        {
	            h=a[i]; 
	            temp = list.get(i); 
	            a[i]=a[j]; 
	            insert(j,i);
	            a[j]=h;
	            insert(temp,j);
	            
	            i++; j--;
	        }
	    } while (i<=j);

	    if (lo<j) quicksortDesc(a, lo, j);
	    if (i<hi) quicksortDesc(a, i, hi);
	}
	
	
	//오름차순정렬
	private  void quicksortAsc(int[] a, int lo, int hi)
	{
		// BASE CASE
		if (lo >= hi)
			// lo has equalled (or crossed) hi, so we have
			// either a singleton or an empty array, either
			// way, it is sorted
			return;
		// first, set the pivot to the first element
		int pivot = a[lo];
		// now, keep pivoting until the left and right indexes cross
		int leftIndex = lo + 1;
		int rightIndex = hi;
		while (rightIndex > leftIndex)
		{
			// 1. if the element on the right is in the right place,
			// move the rightIndex one to the left
			if (a[rightIndex] > pivot)
				rightIndex--;
			
			// 2. else if the element on the left is in the right place,
			// move the leftIndex one to the right
			else if (a[leftIndex] <= pivot)
				leftIndex++;
			
			// 3. otherwise, both sides are wrong, so SWAP them
			else
				swap(a,leftIndex,rightIndex);
		}
		// at this point, leftIndex and rightIndex equal each
		// other, and the pivot is stored in a[lo].
		// We now want to swap the pivot (a[0]) with the last
		// element on the left.  This will either be the element
		// where the two indexes met, or the one just to its left.
		int newPivotIndex;
		if (a[leftIndex] > pivot)
			newPivotIndex = leftIndex - 1;
		else
			newPivotIndex = leftIndex;
		// now just swap the pivot into its new location
		swap(a,lo,newPivotIndex);
		// now recursively sort the left and right sides, being
		// sure not to sort the pivot again!
		quicksortAsc(a,lo,newPivotIndex-1);
		quicksortAsc(a,newPivotIndex+1,hi);
	}
	
	private  void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		Object tempOjbect = list.get(i);  
		a[i] = a[j];
		insert(j,i);
		a[j] = temp;
        insert(tempOjbect,j);
//        h=a[i]; 
//        temp = list.get(i); 
//        a[i]=a[j]; 
//        insert(j,i);
//        a[j]=h;
//        insert(temp,j);
	}

	
	
	//=====

	public  void quicksortAsc(double[] a, int lo, int hi)
	{
		// BASE CASE
		if (lo >= hi)
			// lo has equalled (or crossed) hi, so we have
			// either a singleton or an empty array, either
			// way, it is sorted
			return;
		// first, set the pivot to the first element
		double pivot = a[lo];
		// now, keep pivoting until the left and right indexes cross
		int leftIndex = lo + 1;
		int rightIndex = hi;
		while (rightIndex > leftIndex)
		{
			// 1. if the element on the right is in the right place,
			// move the rightIndex one to the left
			if (a[rightIndex] > pivot)
				rightIndex--;
			
			// 2. else if the element on the left is in the right place,
			// move the leftIndex one to the right
			else if (a[leftIndex] <= pivot)
				leftIndex++;
			
			// 3. otherwise, both sides are wrong, so SWAP them
			else
				swap(a,leftIndex,rightIndex);
		}
		// at this point, leftIndex and rightIndex equal each
		// other, and the pivot is stored in a[lo].
		// We now want to swap the pivot (a[0]) with the last
		// element on the left.  This will either be the element
		// where the two indexes met, or the one just to its left.
		int newPivotIndex;
		if (a[leftIndex] > pivot)
			newPivotIndex = leftIndex - 1;
		else
			newPivotIndex = leftIndex;
		// now just swap the pivot into its new location
		swap(a,lo,newPivotIndex);
		// now recursively sort the left and right sides, being
		// sure not to sort the pivot again!
		quicksortAsc(a,lo,newPivotIndex-1);
		quicksortAsc(a,newPivotIndex+1,hi);
	}
	
	
	
	public  void swap(double[] a, int i, int j)
	{
		double temp = a[i];
		Object tempOjbect = list.get(i);  
		a[i] = a[j];
		insert(j,i);
		a[j] = temp;
        insert(tempOjbect,j);
//        h=a[i]; 
//        temp = list.get(i); 
//        a[i]=a[j]; 
//        insert(j,i);
//        a[j]=h;
//        insert(temp,j);
	}
	
}