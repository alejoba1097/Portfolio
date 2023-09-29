package com.ems.Employee.infrastructure.configuration;

import com.ems.Employee.domain.api.IEmployeeRepositoryPort;
import com.ems.Employee.domain.api.IEmployeeServicePort;
import com.ems.Employee.domain.usecase.EmployeeUseCase;
import com.ems.Employee.infrastructure.output.jpa.adapter.EmployeeJpaAdapter;
import com.ems.Employee.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.ems.Employee.infrastructure.output.jpa.repository.IEmployeeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Bean
    public IEmployeeRepositoryPort employeeRepositoryPort(){
        return new EmployeeJpaAdapter(employeeRepository, employeeEntityMapper);
    }

    @Bean
    public EmployeeUseCase employeeUseCase(){
        return new EmployeeUseCase(employeeRepositoryPort());
    }
}
