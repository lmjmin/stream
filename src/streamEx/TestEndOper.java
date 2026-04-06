package streamEx;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestEndOper {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<String> names = Arrays.asList("James", "Ann", "David", "Benjamin","Eve");
		
		// 1. forEach - 각 요소 처리, 각 요소 출력 (반환 없음)
		System.out.println("===================| forEach |=========================");
		names.stream().forEach(System.out::println);
		
		// 2. collect - 컬렉션으로 수집, 결과를 리스트로 다시 모음
		System.out.println("===================| collect |=========================");
		List<String> longNames = names.stream()
									  .filter(s -> s.length() > 3)
									  .collect(Collectors.toList()); //forEach와 다르게 반환 타입이 있음
		System.out.println(longNames);
		
		// 3. toList (Java 16+) - collect 대신 쓰는 최신 방식
		System.out.println("===================| toList |=========================");
		List<String> upperNames = names.stream()
									   .map(String::toUpperCase)
									   .toList();
		System.out.println(upperNames);
		
		// 4. count - 개수 세기, 조건에 맞는 개수 반환 (long)
		System.out.println("===================| count |=========================");
		long evenCount = numbers.stream()
								.filter(n -> n % 2 == 0)
								.count();
		System.out.println("짝수 개수 : " + evenCount);
		
		// 5. reduce - 값 누적, 값을 하나로 줄임 (합계, 문자열 연결 등)
		System.out.println("===================| reduce |=========================");
		int sum = numbers.stream()
						 .reduce(10, (a,b) -> a +b); // 초기값 0으로 하게되면 리스트에 있는 값만 더함.
		System.out.println("합계 : "+ sum);
		// 문자열 연결
		String concatStr = names.stream()
							 .reduce("Start ", (a,b) -> a+" "+b);
		System.out.println("문자열 연결 : "+ concatStr);
		
		// 6. findFirst - 첫번째 값 구하기, 조건 만족하는 첫 번째 값
		System.out.println("===================| findFirst |=========================");
		Optional<Integer> firstEven = numbers.stream() //Optional을 쓰는 이유는 값이 없을 수도 있어서 안전하게 처리 
											 .filter(n -> n % 2 ==0)
											 .findFirst();
		
		firstEven.ifPresent(System.out::println);
		
		// 7. anyMatch / allMatch / noneMatch
		System.out.println("===================| 매칭 연산 |=========================");
		boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0); //하나라도 조건 만족하면 true
		boolean allPositive = numbers.stream().allMatch(n -> n > 0); //전부 만족하면 true
		boolean noneNagative = numbers.stream().noneMatch(n -> n < 0); //하나도 만족 안 하면 true
		System.out.printf("짝수 있음 : %s , 모두 양수 : %s , 음수 없음 : %s%n",hasEven,allPositive,noneNagative);
		
		// 8. max / min : 최소값 / 최대값 찾기
		System.out.println("===================| max / min |=========================");
		Optional<Integer> min = numbers.stream().min(Integer::compareTo);
		Optional<Integer> max = numbers.stream().max(Integer::compareTo);
		System.out.println("최소 : " + min.orElse(0)); //orElse : 값 없으면 0 반환
		System.out.println("최대 : " + max.orElse(0));
		
		// 9. collect - 그룹 짓기, 기준으로 묶기 (Map으로 반환)
		System.out.println("===================| collect(그룹핑) |=========================");
		Map<Integer, List<String>> groupByLength = names.stream() //문자열 길이별로 그룹핑
														.collect(Collectors.groupingBy(String::length));
		System.out.println("길이별 그룹 : " + groupByLength);
		
		// 10. collect - joining :문자열 합치기
		System.out.println("===================| joining |=========================");
		String joined = names.stream() //문자열 하나로 합침
							 .collect(Collectors.joining(", ","[","]"));	
		System.out.println(joined);
	}

}
