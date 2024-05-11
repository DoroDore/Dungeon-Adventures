public class Weapon extends Items{
    private int attack;
    private int defense;
    private String skill;
    private int skillCost;
    private String skillDescription;
    public Weapon(int id, String name, int size, String rarity, int value, int attack, int defense, String skill, int skillCost, String skillDescription) {
        super(id, name, size, rarity, value);
        this.attack = attack;
        this.defense = defense;
        this.skill = skill;
        this.skillCost = skillCost;
        this.skillDescription = skillDescription;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }
    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public String getSkill() {
        return skill;
    }
    public int getSkillCost() {
        return skillCost;
    }
    public String getSkillDescription() {
        return skillDescription;
    }
}
