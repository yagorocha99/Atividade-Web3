package br.edu.ifpr.foz.ifprstore.repositories;
import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.foz.ifprstore.models.Department;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {
    private Connection connection;
    public DepartmentRepository(){
        connection = ConnectionFactory.getConnection();
    }

    public void delete(Integer id){
        String sql = "DELETE FROM department WHERE Id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();
            if(rowsDeleted >0){
                System.out.println("Rows deleted: " + rowsDeleted);
            }

        } catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Department insert(Department department){
        String sql = "INSERT INTO department (Name) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,department.getName());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted >0){
                ResultSet id = statement.getGeneratedKeys();
                id.next();
                int departmentId = id.getInt(1);
                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Department Id: " + departmentId);
                department.setId(departmentId);
            }

        } catch (Exception e){
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return department;

    }

    public void updateDepartment(Integer departmentId, String name){
        String sql = "UPDATE department SET Name = ? WHERE Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setInt(2,departmentId);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated >0){
                System.out.println("Rows updated: " + rowsUpdated);
            }
        } catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public List<Department> getDepartments(){
        List<Department> departments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM department");
            while(result.next()){
                Department department = instantiateDepartment(result);
                departments.add(department);
            }
            result.close();
        } catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return departments;
    }
    
    public Department getDepartmentById(Integer id){
        Department department = null;
        String sql = "SELECT * FROM department WHERE Id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                department = instantiateDepartment(result);
            }
        }catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return department;
    }

    public List<Department> getFilter(String filtro){
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department WHERE Name LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+filtro+"%");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Department department = instantiateDepartment(result);
                departments.add(department);
            }
            result.close();
        } catch (SQLException e){
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return departments;
    }

    public List<Department> getNoSellers(){
        List<Department> departments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM department WHERE Id NOT IN (SELECT DepartmentId FROM seller)");
            while(result.next()){
                Department department = instantiateDepartment(result);
                departments.add(department);
            }
            result.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return departments;
    }
    
    public Department instantiateDepartment(ResultSet resultSet)throws SQLException{
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));

        return department;
    }
}
