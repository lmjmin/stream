package streamEx;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Student {
	private String name;
	private int korean;
	private int english;
	private int math;
	
	public Student(String name, int korean, int english, int math) {
		this.name =name;
		this.korean = korean;
		this.english = english;
		this.math = math;
	}
	
	//int getTotal() 함수 구현
	public int getTotal() {
	    return IntStream.of(korean, english, math).sum();
	}
	
	
	//double getAverage() 함수 구현
	public double getAverage() {
	    return IntStream.of(korean, english, math)
	                    .average()
	                    .orElse(0);
	}
	
	
	@Override
	public String toString() {
		return name + " 총점:" + getTotal() + " 평균:" + getAverage()+"\n";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	
}
