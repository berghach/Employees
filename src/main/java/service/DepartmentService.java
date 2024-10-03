package service;

import dao.DepartmentDAO;
import entities.Department;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class DepartmentService implements Services<Department> {
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    public Optional<Department> get(long id) {
        return departmentDAO.get(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentDAO.getAll();
    }

    @Override
    public boolean save(Department department) {
        try {
            departmentDAO.save(department);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        try {
            departmentDAO.update(department);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Department department) {
        try {
            departmentDAO.delete(department);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }
}
