package application;


import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.dao.DaoFactory;

import java.util.List;


public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TESTE 2 seller findByDepartment ======");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n=== TESTE 3 seller findAll ======");
        list = sellerDao.findAll();
        list.forEach(System.out::println);
    }
}