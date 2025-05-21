package student;

import java.util.HashSet;
import java.util.Set;

public class StudentManager {
    private Set<String> students = new HashSet<>();

    // 학생 추가
    public void addStudent(String name) {
        if (students.contains(name)) {
            throw new IllegalArgumentException("이미 존재하는 학생입니다: " + name);
        }
        students.add(name);
    }

    // 학생 제거
    public void removeStudent(String name) {
        if (!students.contains(name)) {
            throw new IllegalArgumentException("존재하지 않는 학생입니다: " + name);
        }
        students.remove(name);
    }

    // 학생 유무 확인
    public boolean hasStudent(String name) {
        return students.contains(name);
    }
    
    // 프로젝트 세팅 완료 - 문덕영
    // 커밋 테스트
    // 푸시 테스트2 -박소은 
}
