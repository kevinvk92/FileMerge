package com.gss.nous;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.ResourceBundle.Control;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.HL7DataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.util.CodeMapper.*;
import ca.uhn.hl7v2.model.v25.message.QRY_A19;
import ca.uhn.hl7v2.model.v25.segment.*;

@SpringBootApplication
public class Demo1Application {
	
	
	Demo1Application(){
		
	}
	@Bean
    private static AggregationStrategy batchAggregationStrategy() {
            return new AggregateStrategy();
    }
	

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		CamelContext ctx = new DefaultCamelContext();
		
		try {
			
            ctx.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					
				from("file://PriceByDateFolder/?autoCreate=false")
				.pollEnrich("file://ProductFolder/?autoCreate=false",batchAggregationStrategy())
				.to("file://outbox/?fileName=MyFile.txt&charset=utf-8");
				}
				});
             ctx.start();
//           Thread.sleep(100000);
//           ctx.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}
