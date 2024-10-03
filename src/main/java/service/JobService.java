package service;

import dao.JobDAO;
import entities.Job;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public class JobService implements Services<Job> {
    private final JobDAO jobDAO = new JobDAO();

    @Override
    public Optional<Job> get(long id) {
        return jobDAO.get(id);
    }

    @Override
    public List<Job> getAll() {
        return jobDAO.getAll();
    }

    @Override
    public boolean save(Job job) {
        try {
            jobDAO.save(job);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Job job) {
        try {
            jobDAO.update(job);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Job job) {
        try {
            jobDAO.delete(job);
            return true;
        } catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }
}
