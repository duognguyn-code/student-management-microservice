package com.backend.student_service.service.impl;

import com.backend.student_service.config.StudentProducer;
import com.backend.student_service.dto.StudentEventDTO;
import com.backend.student_service.entity.Student;
import com.backend.student_service.repo.StudentRepository;
import com.backend.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentProducer studentProducer;
    private static final String TOPIC = "student-events";

    private void sendEvent(String type, Student student) {
        StudentEventDTO event = new StudentEventDTO();
        event.setEventType(type);
        event.setStudentId(student.getId());
        event.setStudentCode(student.getStudentCode());
        event.setFullName(student.getFullName());
        studentProducer.sendStudentEvent(event);
    }
    @Override
    public Student create(Student student) {
        Student savedStudent = studentRepository.save(student);

        // Tạo DTO để gửi đi
        StudentEventDTO event = new StudentEventDTO();
        event.setStudentId(savedStudent.getId());

        // Gọi class Producer bạn đã tạo
        studentProducer.sendStudentEvent(event);

        return savedStudent;
    }

    @Override
    public Student getById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));    }

    @Override
    public List<Student> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Student update(Integer id, Student studentDetails) {
        Student existingStudent = getById(id);
        existingStudent.setFullName(studentDetails.getFullName());
        existingStudent.setEmail(studentDetails.getEmail());
        // cập nhật các field khác...

        Student updated = studentRepository.save(existingStudent);
        sendEvent("UPDATE", updated);
        return updated;    }

    @Override
    public void delete(Integer id) {
        Student student = getById(id);
        studentRepository.delete(student);
        sendEvent("DELETE", student);
    }
}
