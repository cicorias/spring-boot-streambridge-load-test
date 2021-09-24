package stream.load.web;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public Function<Object, Object> outputleft() {
	    return value -> {
	        System.out.println("left: " + value);
	        return value;
	    };
	}

	@Bean
	public Function<Message<JsonNode>, Object> outputright() {
	    return value -> {
	        System.out.println("right: " + value);
	        return value;
	    };
	}

	@Bean
	public Function<Object, Object> outputnowhere() {
	    return value -> {
	        System.out.println("nowhere: " + value);
	        return value;
	    };
	}

}
