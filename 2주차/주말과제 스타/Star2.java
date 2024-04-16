import java.util.*;

// 유닛의 기본 클래스
class Unit {
    String name;
    int power;
    int defense;
    boolean canFly;
    boolean canAttackAir;
    boolean alive = true;

    public void attack(Unit unit) {
        if(unit.canFly) {
            if(this.canAttackAir) {
                unit.defense = unit.defense - this.power;
                if(unit.defense <= 0) {
                    unit.alive = false;
                }
            }
            else {
                System.out.println(this.name + "은(는) 공중 공격이 불가능합니다.");
            }
        }
        else if(!unit.canFly) {
            unit.defense = unit.defense - this.power;
            if(unit.defense <= 0) {
                unit.alive = false;
            }
        }

    }

    @Override
    public String toString() {
        return this.name + " (현재 방어력: " + this.defense + ")";
    }
}

// Terran 종족의 유닛들
class Marine extends Unit { 
    Marine() {
        this.name = "Marine";
        this.power = 3;
        this.defense = 10;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Tank extends Unit { 
    Tank() {
        this.name = "Tank";
        this.power = 7;
        this.defense = 15;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Goliath extends Unit {
    Goliath() {
        this.name = "Goliath";
        this.power = 5;
        this.defense = 15;
        this.canFly = false;
        this.canAttackAir = true;
    }
}
class Wraith extends Unit {
    Wraith() {
        this.name = "Wraith";
        this.power = 3;
        this.defense = 10;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class Valkyrie extends Unit {
    Valkyrie() {
        this.name = "Valkyrie";
        this.power = 4;
        this.defense = 12;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class BattleCruzer extends Unit {
    BattleCruzer() {
        this.name = "BattleCruzer";
        this.power = 20;
        this.defense = 30;
        this.canFly = true;
        this.canAttackAir = true;
    }
}

// Protos 종족의 유닛들
class Zealot extends Unit {
    Zealot() {
        this.name = "Zealot";
        this.power = 5;
        this.defense = 20;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Dragoon extends Unit {
    Dragoon() {
        this.name = "Dragoon";
        this.power = 3;
        this.defense = 15;
        this.canFly = false;
        this.canAttackAir = true;
    }
}
class HighTempler extends Unit {
    HighTempler() {
        this.name = "HighTempler";
        this.power = 10;
        this.defense = 2;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Scout extends Unit {
    Scout() {
        this.name = "Scout";
        this.power = 5;
        this.defense = 10;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class Corsair extends Unit {
    Corsair() {
        this.name = "Corsair";
        this.power = 4;
        this.defense = 12;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class Carrier extends Unit {
    Carrier() {
        this.name = "Carrier";
        this.power = 25;
        this.defense = 40;
        this.canFly = true;
        this.canAttackAir = true;
    }
}

// Zerg 종족의 유닛들
class Zergling extends Unit {
    Zergling() {
        this.name = "Zergling";
        this.power = 2;
        this.defense = 2;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Hydralisk extends Unit {
    Hydralisk() {
        this.name = "Hydralisk";
        this.power = 3;
        this.defense = 7;
        this.canFly = false;
        this.canAttackAir = true;
    }
}
class Ultralisk extends Unit {
    Ultralisk() {
        this.name = "Ultralisk";
        this.power = 5;
        this.defense = 15;
        this.canFly = false;
        this.canAttackAir = false;
    }
}
class Mutalisk extends Unit {
    Mutalisk() {
        this.name = "Mutalisk";
        this.power = 2;
        this.defense = 8;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class Guardian extends Unit {
    Guardian() {
        this.name = "Guardian";
        this.power = 3;
        this.defense = 6;
        this.canFly = true;
        this.canAttackAir = true;
    }
}
class Queen extends Unit {
    Queen() {
        this.name = "Queen";
        this.power = 15;
        this.defense = 25;
        this.canFly = true;
        this.canAttackAir = true;
    }
}



// 종족 클래스
class Race {
    private String name;
    private List<Unit> units;

    public Race(String name) {
        this.setName(name);
        setUnits(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        getUnits().add(unit);
    }

    public void removeUnit(Unit unit) {
        getUnits().remove(unit);
    }

    // ... 추가적으로 필요한 메소드들
}

// 게임 클래스
class Game {
    private Race player;
    private Race computer;
    private Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        // 사용자에게 종족을 선택하도록 요청
        System.out.println("종족을 선택하세요. (1: Terran, 2: Protos, 3: Zerg)");
        int choice = scanner.nextInt();
        if (choice == 1) {
            player = new Race("Terran");
            generateUnits(player, 5);
        } else if (choice == 2) {
            player = new Race("Protos");
            generateUnits(player, 4);
        } else {
            player = new Race("Zerg");
            generateUnits(player, 8);
        }

        // 컴퓨터의 종족을 무작위로 선택
        int computerChoice = random.nextInt(3) + 1;
        if (computerChoice == 1) {
            computer = new Race("Terran");
            generateUnits(computer, 5);
        } else if (computerChoice == 2) {
            computer = new Race("Protos");
            generateUnits(computer, 4);
        } else {
            computer = new Race("Zerg");
            generateUnits(computer, 8);
        }

        // 적군과 아군의 유닛을 표시
        System.out.println("적군: " + computer.getName());
        for (int i = 0; i < computer.getUnits().size(); i++) {
            System.out.println(i + ". " + computer.getUnits().get(i).toString());
        }

        System.out.println("\n아군 " + player.getName());
        for (int i = 0; i < player.getUnits().size(); i++) {
            System.out.println(i + ". " + player.getUnits().get(i).toString());
        }

        while (!checkGameOver()) {
            playTurn();
        }

        if (player.getUnits().size() == 0) {
            System.out.println("당신은 패배하였습니다.");
        } else {
            System.out.println("축하합니다! 당신은 승리하였습니다.");
        }
    }

    private void generateUnits(Race race, int numUnits) {
        for (int i = 0; i < numUnits; i++) {
            int unitType = random.nextInt(6);
            if (race.getName().equals("Terran")) {
                if (unitType == 0) race.addUnit(new Marine());
                else if (unitType == 1) race.addUnit(new Tank());
                else if (unitType == 2) race.addUnit(new Goliath());
                else if (unitType == 3) race.addUnit(new Wraith());
                else if (unitType == 4) race.addUnit(new Valkyrie());
                else race.addUnit(new BattleCruzer());

            } else if (race.getName().equals("Protos")) {
                if (unitType == 0) race.addUnit(new Zealot());
                else if (unitType == 1) race.addUnit(new Dragoon());
                else if (unitType == 2) race.addUnit(new HighTempler());
                else if (unitType == 3) race.addUnit(new Scout());
                else if (unitType == 4) race.addUnit(new Corsair());
                else race.addUnit(new Carrier());

            } else {
                if (unitType == 0) race.addUnit(new Zergling());
                else if (unitType == 1) race.addUnit(new Hydralisk());
                else if (unitType == 2) race.addUnit(new Ultralisk());
                else if (unitType == 3) race.addUnit(new Mutalisk());
                else if (unitType == 4) race.addUnit(new Guardian());
                else race.addUnit(new Queen());
            }
        }
    }

    private void playTurn() {
        try{
            System.out.println("\n아군의 턴입니다. 공격을 수행할 유닛과 공격할 적군 유닛을 선택하세요:");
            int allyIndex = scanner.nextInt();
            int enemyIndex = scanner.nextInt();
            Unit allyUnit = player.getUnits().get(allyIndex);
            Unit enemyUnit = computer.getUnits().get(enemyIndex);
            allyUnit.attack(enemyUnit);
            if (!enemyUnit.alive) {
                computer.getUnits().remove(enemyUnit);
                System.out.println("적군의 " + enemyUnit.name + "이(가) 파괴되었습니다!");
            }
        
            // 아군의 턴이 끝나면 현재 생존한 유닛과 그들의 방어력 출력
            System.out.println("\n아군의 턴이 끝났습니다. 현재 생존해 있는 유닛은 다음과 같습니다:");
            printUnits();
        
            System.out.println("\n적군의 턴입니다.");
            enemyIndex = random.nextInt(player.getUnits().size());
            allyIndex = random.nextInt(computer.getUnits().size());
            enemyUnit = computer.getUnits().get(allyIndex);
            allyUnit = player.getUnits().get(enemyIndex);
            enemyUnit.attack(allyUnit);
            if (!allyUnit.alive) {
                player.getUnits().remove(allyUnit);
                System.out.println("아군의 " + allyUnit.name + "이(가) 파괴되었습니다!");
            }
        
            // 적군의 턴이 끝나면 현재 생존한 유닛과 그들의 방어력 출력
            System.out.println("\n적군의 턴이 끝났습니다. 현재 생존해 있는 유닛은 다음과 같습니다:");
            printUnits();
        }
        catch(IllegalArgumentException e) {
            
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("잘못된 인덱스가 입력되었습니다.");
        }
    }

    private boolean checkGameOver() {
        return player.getUnits().size() == 0 || computer.getUnits().size() == 0;
    }
    
    private void printUnits() {
        System.out.println("\n적군: " + computer.getName());
        for (int i = 0; i < computer.getUnits().size(); i++) {
            System.out.println(i + ". " + computer.getUnits().get(i).toString());
        }
    
        System.out.println("\n아군 " + player.getName());
        for (int i = 0; i < player.getUnits().size(); i++) {
            System.out.println(i + ". " + player.getUnits().get(i).toString());
        }
    }   
}

// 메인 클래스
public class Star2 {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
