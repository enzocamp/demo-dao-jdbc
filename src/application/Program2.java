package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
    public static void main(String[] args){

        System.out.println("\n=== TESTE 1 department insert ======");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department newDepartment = new Department(null, "Operation");
        //departmentDao.insert(newDepartment);
       // System.out.println("Inserted! new id = " + newDepartment.getId());

        System.out.println("\n=== TESTE 2 e 3 department findById e Update ======");
        Department department = departmentDao.findById(6);
        department.setName("Food");
        departmentDao.update(department);
        System.out.println("Update Completed");

        System.out.println("\n=== TESTE 4 department delete ======");
        departmentDao.deleteById(9);
        System.out.println("Delete Completed");

    }

}
