package designpattern.structuralpattern;

import java.util.ArrayList;
import java.util.List;

// ===========================
// 1. 공통 컴포넌트 인터페이스
// ===========================
interface FileComponent {
    void showDetails(String indent);
}

// ===========================
// 2. Leaf 클래스: 개별 파일
// ===========================
class File implements FileComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- 📄 File: " + name);
    }
}

// ===========================
// 3. Composite 클래스: 폴더 (다른 컴포넌트를 포함할 수 있음)
// ===========================
class Folder implements FileComponent {
    private String name;
    private List<FileComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    // 폴더에 파일 또는 다른 폴더 추가
    public void add(FileComponent component) {
        children.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "+ 📁 Folder: " + name);
        for (FileComponent child : children) {
            child.showDetails(indent + "  "); // 들여쓰기 2칸 추가
        }
    }
}

// ===========================
// 4. 테스트 실행 코드
// ===========================
public class CompositePattern {
    public static void main(String[] args) {

        // 루트 폴더 생성
        Folder root = new Folder("root");

        // 루트에 파일 추가
        File file1 = new File("a.txt");
        File file2 = new File("b.txt");
        root.add(file1);
        root.add(file2);

        // 서브 폴더 생성 및 내부 파일 추가
        Folder subFolder = new Folder("sub");
        subFolder.add(new File("c.txt"));
        subFolder.add(new File("d.txt"));

        // 루트에 서브 폴더 추가
        root.add(subFolder);

        // 출력: 트리 구조 확인
        System.out.println("📦 File System Structure:");
        System.out.println("-------------------------");
        root.showDetails("");
    }
}

