package streamEx;

import java.util.stream.IntStream;

public class IntStreamEx {
	
	public static void main(String[] args) {
		// IntStream : int 전용 스트림
		IntStream.rangeClosed(1, 10) //1 ~ 10까지 생성 (끝값 포함)
						.filter(n -> n % 2 == 0) //짝수만 남김
						.forEach(System.out::println);
		int sum = IntStream.of(1,2,3,4,5)
							.sum(); //합계 바로 계산
		System.out.println("sum : " + sum);
		double avg = IntStream.of(1,2,3,4,5) //반환 타입: OptionalDouble
							  .average() //평균 계산
							  .orElse(0); //값 없을 수도 있어서 .orElse(0) 필요
		System.out.println("평균 : "+avg);
		
		
	}
}
