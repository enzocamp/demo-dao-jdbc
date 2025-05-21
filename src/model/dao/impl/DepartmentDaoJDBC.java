package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO department" +
                    "(Name)" +
                    "VALUES" +
                    "(?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rows = st.executeUpdate();
            if (rows > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE department " +
                    "SET Name = ?" +
                    "WHERE Id = ?");
            st.setString(1,obj.getName());
            st.setInt(2,obj.getId());

            int rows  = st.executeUpdate();

            if (rows == 0){
                throw new DbException("Update failed! No rows affected, check the provided ID");
            }
            conn.commit();
        }
        catch (SQLException e){
            try{
                conn.rollback();
                throw new DbException(e.getMessage());
            }
            catch (SQLException el){
                throw new DbException(el.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
           try {
                conn.setAutoCommit(true); // restaurando o comportamento padrão
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM department \n" +
                    " WHERE Id = ?");
            st.setInt(1,id);

            int rows = st.executeUpdate();

            if(rows ==0){
                throw new DbException("Id doesn't exist!");
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
           st = conn.prepareStatement("SELECT * FROM " +
                   "department " +
                   "WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            else{
                throw new DbException("Id not found!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
