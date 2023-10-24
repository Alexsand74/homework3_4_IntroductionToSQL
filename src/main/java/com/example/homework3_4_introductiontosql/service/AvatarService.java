package com.example.homework3_4_introductiontosql.service;
import com.example.homework3_4_introductiontosql.exception.StudentNotFoundException;
import com.example.homework3_4_introductiontosql.model.Avatar;
import com.example.homework3_4_introductiontosql.model.Student;
import com.example.homework3_4_introductiontosql.repository.AvatarRepository;
import com.example.homework3_4_introductiontosql.repository.StudentRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AvatarService {

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final String avatarsDir;

    public AvatarService(StudentRepository studentRepository,
                         AvatarRepository avatarRepository,
                         @Value("${avatars.dir}") String avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
    }

    @Transactional
    public void upload(long studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException ());

        var dir = Path.of(avatarsDir);
        if (!dir.toFile().exists()) {
            Files.createDirectories(dir);
        }
        var path = saveFile(file, student);

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }

    private String saveFile(MultipartFile file, Student student) {
        var dotIndex = file.getOriginalFilename().lastIndexOf('.');
        var ext = file.getOriginalFilename().substring(dotIndex + 1);
        var path = avatarsDir + "/" + student.getId() + "_" + student.getName() + "." + ext;
        try (var in = file.getInputStream();
             var out = new FileOutputStream(path)) {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    @Transactional
    public Avatar find (long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }
}