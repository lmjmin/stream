package streamEx;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List <String> names = Arrays.asList("Ann","Ben","Cindy","David","Ben");
		//1. filter - 조건에 맞는 요소만
		//글자수가 이상(중간연산)인 이름만 출력(최종연산)하자.
		System.out.println("==============| filter() :  특정 글자수 이상 되었을때 출력 |==================");
		names.stream() //stream() : 리스트(names)를 스트림 형태로 변환
			.filter(name -> name.length() >= 4) //filter : 각 요소를 검사해서 조건 만족하는 것만 남김/ 중간연산
//			.forEach(name -> System.out.println(name)); //forEach: 각요소를 어떻게 처리 할것인지/ 남은 데이터를 하나씩 꺼내서 출력/ 최종 연산
			.forEach(System.out::println);// 이런식으로 사용할수 있음!(결과출력), 최종 연산
		//리스트 -> 스트림화 -> 조건부(자료 거르는 작업 - 중간 연산) -> 순서대로 처리(최종 연산)
		// 리스트 스트림 -> 중간 연산 -> 최종 연산
		System.out.println("==============| map() : 대문자 변환 하기 |==================");
		// 2. map - 변환 : 대문자로 변환 하기
		names.stream()
			 .map(s -> s.toUpperCase())
			 .forEach(str -> System.out.println(str));
		
		names.stream() // 줄여서 이렇게 사용 가능
			 .map(String :: toUpperCase)
			 .forEach(System.out::println);
		
		// 3. flatMap - 스트림 평탄화
		System.out.println("==============| flatMap() : 문자열을 문자로 |==================");
		List<String> words = Arrays.asList("Hello","World");
		words.stream()
			 .flatMap(word -> Arrays.stream(word.split("")))
			 .forEach(System.out::print);
		System.out.println();
		
		// 4. distinct - 중복 제거
		System.out.println("==============| distinct() : 중복 제거 |==================");
		names.stream()
			 .filter(name -> name.length() < 4) //중간 연산은 여러번 가능함
			 .distinct()
			 .forEach(System.out::println);
		
		// 5. sort() - 정렬
		System.out.println("==============| sort() : 정렬 |==================");
		names.stream()
			 .distinct() //중복제거
			 .sorted()
			 .forEach(System.out::println);
		
		// 6. limit() - 개수 제한
		System.out.println("==============| limit() : 개수 제한 |==================");
		names.stream()
			 .limit(3)
			 .forEach(System.out::println);
		
		// 7. skip() - 건너뛰기
		System.out.println("==============| skip() : 건너뛰기 |==================");
		names.stream()
			 .skip(2)
			 .forEach(System.out::println);
		
		// 8. peek() - 디버깅
		System.out.println("==============| peek() : 디버깅 |==================");
		long count = names.stream()
						  .peek(s -> System.out.println("처리 전 : "+s))
						  .filter(s -> s.length() >= 4)
						  .peek(s -> System.out.println("처리 후 : "+s))
						  .count();
		System.out.println("개수 : "+count);
		
		// 9. 중간 연산 체이닝
		System.out.println("==============| 중간 연산 체이닝 |==================");
		names.stream()
			 .filter(s -> s.length() >= 4)
			 .map(String::toUpperCase)
			 .distinct()
			 .sorted()
			 .limit(2)
			 .forEach(System.out::println);
		
		
		
	}

}
