package chap01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/beans.xml");
		
		Greeter g = (Greeter)ctx.getBean("greeter");
		System.out.println(g.greet("홍길동"));
		
		ctx.close();
	}
}
