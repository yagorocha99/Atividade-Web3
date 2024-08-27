package br.edu.ifpr.foz.ifprstore.repositories;
import br.edu.ifpr.foz.ifprstore.models.Department;
import org.junit.jupiter.api.Test;



import java.util.List;

public class DepartmentRepositoryTest {
    @Test
    public void InsertDepartment() {
        DepartmentRepository repository = new DepartmentRepository();
        Department departmentFake = new Department();
        departmentFake.setName("teste");
        repository.insert(departmentFake);
    }
    @Test
    public void DeletDepartmentById() {
        DepartmentRepository repository = new DepartmentRepository();
        repository.delete(5);
    }
    @Test
    public void UpdateDepartmentNameById() {
        DepartmentRepository repository = new DepartmentRepository();
        String name = "testeBooks";
        repository.updateDepartment(6,name);
    }
    @Test
    public void ListDepartment(){
        DepartmentRepository repository = new DepartmentRepository();
        List<Department> department = repository.getDepartments();
        for(Department d : department){
            System.out.println(d);
        }
    }
    @Test
    public void ShowDepartmentById(){
        DepartmentRepository repository = new DepartmentRepository();
        Department department = repository.getDepartmentById(3);
        System.out.println(department);
    }
    @Test
    public void FilterDepartmentByString(){
        DepartmentRepository repository = new DepartmentRepository();
        List<Department> department = repository.getFilter("computers");
        System.out.println(department);
    }
    @Test
    public void ReturnDepartmentWithoutSellers(){
        DepartmentRepository repository = new DepartmentRepository();
        List<Department> department = repository.getNoSellers();
        System.out.println(department);
    }
}
