package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void testGetAllDepartments() {
        Department dep1 = new Department();
        dep1.setIdDepartment(1L);
        dep1.setName("Informatique");

        Department dep2 = new Department();
        dep2.setIdDepartment(2L);
        dep2.setName("Électrique");

        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(dep1, dep2));

        List<Department> result = departmentController.getAllDepartment();

        assertEquals(2, result.size());
        assertEquals("Informatique", result.get(0).getName());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testCreateDepartment() {
        Department dep = new Department();
        dep.setName("Mécanique");

        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        Department result = departmentController.createDepartment(dep);

        assertEquals("Mécanique", result.getName());
        verify(departmentService, times(1)).saveDepartment(dep);
    }
}
