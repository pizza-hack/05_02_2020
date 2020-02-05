package jfont;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SumSetIndex {
	
	static int[] index;
	
	static int bestSoFar = 0;
	static List<Integer> bestIndexSoFar;
	
	static void sum_up_recursive(List<Integer> solution, int target, List<Integer> partial) {
		int s = 0;
		for (int x : partial)
			s += index[x];
//		if (s == target)
//			System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
		
		
		if(s > bestSoFar && s <= target) {
			bestSoFar = s;
			bestIndexSoFar = partial;
			System.out.println("("+s+"/"+target+") - "+partial.toString());
		}
		
		if (s >= target)
			return;
		
		for (int i = 0; i < solution.size(); i++) {
			ArrayList<Integer> remaining = new ArrayList<Integer>();
			int n = solution.get(i);
			for (int j = i + 1; j < solution.size(); j++)
				remaining.add(solution.get(j));
			
			ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
			partial_rec.add(n);
			sum_up_recursive(remaining, target, partial_rec);
		}
	}

	static void sum_up(int[] index, int target) {
		SumSetIndex.index = index;
		
		List<Integer> solution = IntStream.rangeClosed(0, index.length-1001)
			    .boxed().collect(Collectors.toList());
		List<Integer> partial = IntStream.rangeClosed(index.length-1000,index.length-1).boxed().collect(Collectors.toList());
		sum_up_recursive(solution, target, partial);
	}
}
