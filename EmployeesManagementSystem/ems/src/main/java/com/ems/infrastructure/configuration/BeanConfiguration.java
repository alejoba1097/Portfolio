package com.ems.infrastructure.configuration;

import com.ems.domain.model.Employee;
import com.ems.domain.port.IEmployeeRepositoryPort;
import com.ems.domain.port.IEmployeeServicePort;
import com.ems.domain.usecase.EmployeeUseCase;
import com.ems.infrastructure.output.jpa.adapter.EmployeeJpaAdapter;
import com.ems.infrastructure.output.jpa.mapper.IEmployeeEntityMapper;
import com.ems.infrastructure.output.jpa.repositiory.IEmployeeRepository;
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
    public IEmployeeServicePort employeeServicePort(){
        return new EmployeeUseCase(employeeRepositoryPort());
    }
}
