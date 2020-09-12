package com.todo.mytodo.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	// get only value1 and value2 only with this request
	@GetMapping("/filter")
	public MappingJacksonValue retrieveBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		Set<String> properties = new HashSet<>(Arrays.asList("field1", "field2"));
		
		MappingJacksonValue mappingJacksonValue = createDynamicFilter(properties, someBean);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filterlist")
	public MappingJacksonValue retrieveBeanList() {
		List<SomeBean> someBeanlist = (List<SomeBean>) Arrays.asList(
				new SomeBean("value11", "value12", "value13"), 
				new SomeBean("value21", "value22", "value23"));
		Set<String> properties = new HashSet<>(Arrays.asList("field2", "field3"));

		MappingJacksonValue mapping = createDynamicFilter(properties, someBeanlist);
		return mapping;
	}
	
	private MappingJacksonValue createDynamicFilter(Set<String> properties, Object list) {		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
		FilterProvider Filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(Filters);
		
		return mapping;
	}

}
