public class Weapon extends Item {
    private int attack, defense, skillCost;
    private String skill, skillDescription, weaponDescription;


    public Weapon(int id, String name, int size, String rarity, int value, int attack, int defense, String skill,
                  int skillCost, String skillDescription, String description) {
        super(id, name, size, rarity, value, description);
        this.attack = attack;
        this.defense = defense;
        this.skill = skill;
        this.skillCost = skillCost;
        this.skillDescription = skillDescription;
        this.weaponDescription = description;
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
    public void setWeaponDescription(String weaponDescription) {
        this.weaponDescription = weaponDescription;
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
    public String getWeaponDescription() {
        return weaponDescription;
    }
    public void printStats() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getID());
        System.out.println("Size: " + getSize());
        System.out.println("Rarity: " + getRarity());
        System.out.println("Value: " + getValue());
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Skill: " + getSkill());
        System.out.println("Skill Cost: " + getSkillCost());
        System.out.println("Skill Description: " + getSkillDescription());
        System.out.println("Weapon Description: " + getWeaponDescription());
    }
}
