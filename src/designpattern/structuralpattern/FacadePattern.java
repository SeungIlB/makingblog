package designpattern.structuralpattern;
// === 복잡한 서브시스템 (Subsystem) ==================================
// 각 부품은 이제 서로 데이터를 주고받는다는 가정하에 조금 더 복잡해졌습니다.

class CPU {
    public void processData(String data) {
        System.out.println(" 2. CPU가 데이터를 처리합니다: " + data);
    }
    public void stop() {
        System.out.println(" 4. CPU 작업을 중지합니다.");
    }
}

class Memory {
    public void load(long position, String data) {
        System.out.println(" 1. 메모리에 '" + data + "'를 로드합니다. (from position: " + position + ")");
    }
    public void clear() {
        System.out.println(" 5. 메모리를 비웁니다.");
    }
}

class HardDrive {
    public String readBootSector() {
        String data = "부팅 데이터";
        System.out.println(" 0. 하드 드라이브의 부트 섹터를 읽습니다: " + data);
        return data;
    }
    public void powerOff() {
        System.out.println(" 6. 하드 드라이브 전원을 내립니다.");
    }
}


// === 퍼사드 (Facade) ================================================
// 복잡한 서브시스템을 감싸서 단순한 인터페이스를 제공합니다.

class ComputerFacade {
    // 외부에서 생성된 객체를 주입받습니다 (의존성 주입).
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade(CPU cpu, Memory memory, HardDrive hardDrive) {
        this.cpu = cpu;
        this.memory = memory;
        this.hardDrive = hardDrive;
    }

    /**
     * 클라이언트가 호출할 단순화된 '컴퓨터 켜기' 메서드입니다.
     * 복잡한 부팅 과정을 캡슐화합니다.
     */
    public void boot() {
        System.out.println("컴퓨터 부팅을 시작합니다...");
        String bootData = hardDrive.readBootSector();
        memory.load(0, bootData);
        cpu.processData(bootData);
        System.out.println("부팅 완료!\n");
    }

    /**
     * 클라이언트가 호출할 단순화된 '컴퓨터 끄기' 메서드입니다.
     */
    public void shutdown() {
        System.out.println("컴퓨터 종료를 시작합니다...");
        cpu.stop();
        memory.clear();
        hardDrive.powerOff();
        System.out.println("종료 완료!");
    }
}


// === 클라이언트 (Client) ==============================================

public class FacadePattern {
    public static void main(String[] args) {
        // --- 퍼사드를 사용하지 않는 경우 (복잡함) ---
        // 클라이언트는 컴퓨터를 켜기 위해 모든 부품의 동작 순서와 의존성을 알아야 합니다.
        // 만약 부팅 순서가 바뀌면 이 코드 전체를 수정해야 합니다.
        /*
            System.out.println("--- 수동으로 부팅 시도 ---");
            HardDrive hdd = new HardDrive();
            Memory ram = new Memory();
            CPU processor = new CPU();

            String bootData = hdd.readBootSector();
            ram.load(0, bootData);
            processor.processData(bootData);
            System.out.println("----------------------\n");
        */


        // --- 퍼사드를 사용하는 경우 (간결함) ---
        // 1. 클라이언트는 필요한 부품들(서브시스템)을 준비합니다.
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        // 2. 준비된 부품들을 '컴퓨터'라는 통합된 창구(Facade)에 전달합니다.
        ComputerFacade computer = new ComputerFacade(cpu, memory, hardDrive);

        // 3. 클라이언트는 이제 복잡한 과정을 전혀 몰라도 됩니다.
        //    단순히 '켜기'와 '끄기'만 요청하면 됩니다.
        System.out.println(">> 클라이언트: 컴퓨터 전원 버튼 ON");
        computer.boot();

        System.out.println(">> 클라이언트: 컴퓨터 전원 버튼 OFF");
        computer.shutdown();

        // 만약 HardDrive가 SSD로 바뀌어도, FacadePatternDemo의 코드는 수정할 필요가 없습니다.
        // ComputerFacade 내부만 수정하면 되므로 유지보수가 매우 용이해집니다.
    }
}