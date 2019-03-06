package com.businessbook.unit.domains.shared;

import org.junit.Assert;
import org.junit.Test;


public class PracticeTest {
	
	public void testCities() {
		Assert.assertArrayEquals(buildCitiesArray(new int[] {9,1,4,9,0,4,8,9,0,1}), new int[] {1,3,2,3,0,0,0,0,0});
	}
	
	private int[] buildCitiesArray(int[] T) {
		int[] M = new int[T.length-1];
		for(int i = 0; i< T.length; i++) {
			
		}
		return M;
	}
	
	
	@Test
	public void testOrchard() {
		Assert.assertEquals(getTotalApples(new int[] {6,1,4,6,3,2,7,4}, 3, 2), 24);
		Assert.assertEquals(getTotalApples(new int[] {1,2,3}, 2, 1), 6);
		Assert.assertEquals(getTotalApples(new int[] {9,9,1,8,2,9}, 3, 2), 37);
		Assert.assertEquals(getTotalApples(new int[] {9,9,1,9,8,2}, 3, 2), 37);
		Assert.assertEquals(getTotalApples(new int[] {9,9}, 1, 2), -1);
		Assert.assertEquals(getTotalApples(new int[] {}, 1, 2), -1);
	}
	
	private int getTotalApples(int[] A, int K, int L) {
		int totalMax = -1;
		for(int i=0; i < A.length; i++) {			
			int totalK = getSum(A, i, K);
			
			for(int j=0; j< A.length; j++) {				
				if(j == i) continue;
				if ((j < i) && !(j+L-1 < i)) continue;
				if ((j > i) && ( (j<i+K) || (j+L > A.length)) ) continue;
				
				
				int totalL = getSum(A, j, L);
				//System.out.println("totalMax = " + totalMax +", totalK = " + totalK + ", totalL = " + totalL + ", i = " + i + ", j = " + j);
				if(totalL > 0 && ((totalK + totalL) > totalMax)) {
					totalMax = totalK + totalL;					
				}
			}		
		}
		
		System.out.println(totalMax);	
		return totalMax;
	}
	
	private int getSum(int[] A, int start, int count) {
		if (A == null || A.length < start + count) {
			return -1;
		}
		
		int sum = 0;
		for(int i=start; i < A.length && i < start+count; i++) {			
			sum += A[i];
		}
		return sum;
	}

}
