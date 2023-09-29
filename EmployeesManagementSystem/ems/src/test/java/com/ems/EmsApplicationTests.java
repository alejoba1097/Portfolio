package com.ems;

import com.ems.Employee.application.dto.EmployeeResponseDto;
import com.ems.Employee.application.mapper.IEmployeeResponseMapper;
import com.ems.Employee.application.mapper.IEmployeeResponseMapperImpl;
import com.ems.Employee.domain.model.Employee;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmsApplicationTests {

	@Test
	public void shouldMapResponseDto(){
		Employee employee = new Employee(
				1L,
				"Alejandro",
				"Barrientos",
				"alejandro.barrientos@gmail.com"
		);

		EmployeeResponseDto target = new EmployeeResponseDto();
		target.setId(employee.getId());
		target.setFirstName(employee.getFirstName());
		target.setLastName(employee.getLastName());
		target.setEmail(employee.getEmail());

		EmployeeResponseDto actual = IEmployeeResponseMapper.INSTANCE.toEmployeeResponseDto(employee);

		assertThat(actual).hasSameClassAs(target);
		assertThat(actual).usingRecursiveComparison().isEqualTo(target);
	}

	@Test
	public void should(){

	}
}
