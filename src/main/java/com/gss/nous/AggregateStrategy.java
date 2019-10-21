package com.gss.nous;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
@Component
public class AggregateStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		    String[] strBuild1 = {};
			String[] strBuild2 = {};
			
			strBuild1=StringUtils.splitByWholeSeparator(newExchange.getIn().getBody(String.class),"\n");
			strBuild2=StringUtils.splitByWholeSeparator(oldExchange.getIn().getBody(String.class),"\n");
			
			List<FinalData> finalData= new ArrayList<FinalData>();
		for(int i=0;i<strBuild1.length-1;i++) {
			String[] str1=StringUtils.splitByWholeSeparator(strBuild1[i],",");
			FinalData data = new FinalData();
		if(str1.length ==2) {
			data.setProductId(str1[0].trim());
			data.setProductName(str1[1].trim());
			finalData.add(data);
			}
			
			for(int j=0;j<strBuild2.length;j++) {
				String[] str2=StringUtils.splitByWholeSeparator(strBuild2[j],",");
				if(finalData.get(i).getProductId().equals(str2[0].trim())) {
					finalData.get(i).addPrice(str2[2].trim());						
			}

		}
			}
	    newExchange.getIn().setBody(finalData.toString());
		return newExchange;
	}

}
