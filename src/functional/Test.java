package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import dto.MessageDto;

class Box<T>{
	private T ob;
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

class User{
	
	String username;
	String email;
	
	User(String username, String email){
		this.username = username;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User{username = '"+username+"', email = '"+email+"'}";
	}
	
}


// 문제 : BiConsumer<T, U>를 기반으로 위 클래스의 인스턴스에 String형, int형, double형 데이터를 저장하는 기능의 
// 람다식을 각각 작성하고 , 이를 확인하기 위한 예제를 작성하기.

public class Test {
	
	public static void main(String[] args) {
//		Predicate<Integer> p = n -> n % 2 ==0; // test() 메소드 정의한 코드/ 짝수인지 판단 하는 코드 
//		System.out.println(p.test(6));
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		int s =0;
		s = sum(n -> n % 2 == 0, list);
		System.out.println("짝수의 합 : " + s);
		
		System.out.println("----------------------------");
		
		BiPredicate<String, Integer> bp = (str, n) -> str.length() > n ? true:false;
		
		//test() 호출한 결과 문자열 "Goodbye"의 길이가 3보다 크면 true  반환
		
		if(bp.test("Goodbye", 3)) {
			System.out.println("문자열 길이 3 초과");
		}else {
			System.out.println("문자열 길이 3 이하");
		}
		System.out.println("----------------------------");
		List<MessageDto> list2= Arrays.asList(
			    new MessageDto("안녕하세요 반갑습니다 !", "짱구"),
			    new MessageDto("반갑습니다! 저는 철수 입니다.", "철수"),
			    new MessageDto("hi", "수지")
			);
		for (MessageDto m : list2) {
		    if (bp.test(m.getMessage(), 3)) {
		        System.out.println("메시지가 3자리 이상인 것 : "+ m.getMessage()+" | 작성자 : "+m.getWriter());
		    }else {
		    	System.out.println("메시지가 3자리 이하인 것 : "+ m.getMessage()+" | 작성자 : "+m.getWriter());
		    }
		}
		System.out.println("----------------------------");
		BiPredicate<String, String> bp2 = (msg, con) -> msg.contains(con) ?true:false;
		int cnt=0;
		for(MessageDto m: list2) {
			if(bp2.test(m.getMessage(), "다")) {
				cnt++;
			}
		}
		System.out.println("'다'가 들어간 메시지는 : "+cnt+"개 입니다.");
		System.out.println("----------------------------");
		
		// Superlier<T> 정의 하기
		// 랜덤 수 반환하기 ( 1 ~ 10 )
		
		Supplier<Integer> sp = () -> {
			Random rd = new Random();
		    return rd.nextInt(10) + 1;
		};
		// makeIntList로 새로운 리스트 만들기
		List<Integer> list3 = makeIntList(sp, 5);
		
		// 만들어진 리스트 출력하기
		for(int n : list3) {
			System.out.print(n + "  ");
		}
		System.out.println("\n----------------------------");
		
		Consumer<String> c = x -> System.out.println(x);;
		c.accept("Java"); //반환타입이 없기때문에 앞에 String같은 타입 지정 ㄴㄴ
		
		ObjIntConsumer<String> oc = (str1,i) -> System.out.println(i + "] "+ str1);
		
		int n =1;
		oc.accept("prod1", n++);
		oc.accept("prod2", n++);
		oc.accept("prod3", n);
		System.out.println("----------------------------");
		BiConsumer<Box<String>, String> bx = (box, str2) -> box.set(str2);
		Box<String> box = new Box();
		bx.accept(box, "민듀");
		System.out.println(box.get());
		
		BiConsumer<Box<Integer>, Integer> bx2 = (box2, num) -> box2.set(num);
		Box<Integer> box2 = new Box();
		bx2.accept(box2, 50);
		System.out.println(box2.get());
		
		BiConsumer<Box<Double>, Double> bx3 = (box3, dou) -> box3.set(dou);
		Box<Double> box3 = new Box();
		bx3.accept(box3, 0.13);
		System.out.println(box3.get());
		System.out.println("----------------------------");
		//Function<T, R> 	R apply(T, t)
		Function<String, Integer> f = ss -> ss.length();
		System.out.println(f.apply("Robot"));
		
		//DoubleUnaryOperator		double applyAsDouble(double operand)
		DoubleUnaryOperator cmToInch = d -> d * 0.393701;
		DoubleUnaryOperator inchToCm = d -> d * 2.54;
		
		System.out.println(cmToInch.applyAsDouble(1.0) + "inch");
		System.out.println(inchToCm.applyAsDouble(1.0) + "cm");
		
		// 두 수 중 큰수 구하기 BiFunction<T, U, R>		R apply(T t, U u)
		BiFunction<Integer, Integer, Integer> bf = (a, b) -> a > b ? a : b;
        
        int result = bf.apply(10, 20);
        System.out.println("큰 수: " + result);
        
        //아이디와 이메일로 User객체 생성하기
        BiFunction<String, String, User> bf2 =(username,email) -> new User(username, email);
        System.out.println(bf2.apply("민주", "minju200308@gmail.com"));
	}
	
	public static List<Integer> makeIntList(Supplier<Integer> sp, int n){
		List<Integer> list = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			//리스트에 값을 추가하기 - Supplier의 반환값을 이용.
			list.add(sp.get());
		}
		
		return list;
	}
	
	public static int sum(Predicate<Integer> p, List<Integer> lst) {
		//Predicate<Integer> p = n -> n % 2 ==0; 
		int s = 0;
		
		for (int n: lst) {
			if(p.test(n)) {
				s += n;
			}
		}
		
		return s;
	}
	
	
	
	
}
