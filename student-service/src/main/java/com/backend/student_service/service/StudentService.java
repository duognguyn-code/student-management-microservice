package com.backend.student_service.service;

import com.backend.student_service.entity.Student;

import java.util.List;

public interface StudentService {
    public Student create(Student student);
    Student getById(Integer id);

    // Lấy danh sách tất cả sinh viên (thường dùng)
    List<Student> getAll();

    // Cập nhật thông tin sinh viên
    Student update(Integer id, Student studentDetails);

    // Xóa sinh viên
    void delete(Integer id);
}
