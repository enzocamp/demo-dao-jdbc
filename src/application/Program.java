package application;


import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.dao.DaoFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        System.out.println("\n=== TESTE 4 seller insert ======");
        Seller newSeller = new Seller(null, "luiz", "luiz@gmail.com", LocalDate.now(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());

        System.out.println("\n=== TESTE 5 seller update ======");
        seller = sellerDao.findById(1);
        seller.setName("Enzo");
        sellerDao.update(seller);
        System.out.println("Update Completed");

        System.out.println("\n=== TESTE 6 seller delete ======");
        System.out.print("Enter the seller id to delete: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete Completed");
    }
}