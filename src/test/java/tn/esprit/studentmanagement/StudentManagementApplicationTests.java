package tn.esprit.studentmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.studentmanagement.controllers.DepartmentController;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private IDepartmentService departmentService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void testGetAllDepartment() throws Exception {
        Department dep1 = new Department(1L, "Computer Science");
        Department dep2 = new Department(2L, "Mathematics");
        List<Department> departments = Arrays.asList(dep1, dep2);

        when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(get("/Depatment/getAllDepartment"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(departments)));

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartmentById() throws Exception {
        Department dep = new Department(1L, "Computer Science");

        when(departmentService.getDepartmentById(1L)).thenReturn(dep);

        mockMvc.perform(get("/Depatment/getDepartment/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dep)));

        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testCreateDepartment() throws Exception {
        Department dep = new Department(null, "Physics");
        Department savedDep = new Department(3L, "Physics");

        when(departmentService.saveDepartment(dep)).thenReturn(savedDep);

        mockMvc.perform(post("/Depatment/createDepartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedDep)));

        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Department dep = new Department(1L, "Physics Updated");

        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        mockMvc.perform(put("/Depatment/updateDepartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dep)));

        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/Depatment/deleteDepartment/{id}", 1L))
                .andExpect(status().isOk());

        verify(departmentService, times(1)).deleteDepartment(1L);
    }
}
