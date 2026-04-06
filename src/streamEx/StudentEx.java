package streamEx;

import java.util.Arrays;
import java.util.List;

public class StudentEx {
	public static void main(String[] args) {
		List<Student> students = Arrays.asList(
				new Student("Ann", 90, 85, 88),
				new Student("Benjamin", 75 , 80, 70),
				new Student("Cindy", 95, 92, 96),
				new Student("David", 65, 70, 68),
				new Student("Eve", 88, 90, 85)
				);
		
		// 1. 평균 80점 이상인 학생
		System.out.println("==========| 평균 80점 이상인 학생 |==============");
		students.stream()
		        .filter(s -> s.getAverage() >= 80)
		        .forEach(System.out::println);
//		System.out.println(list); //이거 할려면 List 만들어서 반환하게 해야함 .toList사용해야해
		
		// 2. 총점 높은 순으로 정렬하여 상위 3명
		System.out.println("==========| 총점 높은 순으로 정렬하여 상위 3명 |==============");
		students.stream()
		        .sorted((s1, s2) -> s2.getTotal() - s1.getTotal()) //큰 값이 앞으로 오게 만들려고 하는 방법
		        .limit(3)
		        .forEach(System.out::println);
//		System.out.println(top3);
		
		// 3. 모든 학생 이름 리스트
		System.out.println("==========| 모든 학생 이름 리스트 |==============");
		students.stream()
		        .map(Student::getName)
		        .forEach(System.out::println);
//		System.out.println(names);
		
		
	}
}
