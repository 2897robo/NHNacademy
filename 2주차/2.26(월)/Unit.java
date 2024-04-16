class Unit {
    private int hp;
    private int location;
    private int attackPower;

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getHp() {
        return this.hp;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public void move(int location) {
        this.location = location;
    }
    
    void attack(Unit unit) {
        System.out.println("공격~");
    }
}
