package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
    public static void main(String[] args){
        System.out.println("\n=== TESTE 1 department insert ======");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department newDepartment = new Department(null, "Operation");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! new id = " + newDepartment.getId());
    }

}
