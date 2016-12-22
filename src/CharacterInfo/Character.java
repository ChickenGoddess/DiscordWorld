/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterInfo;

/**
 *
 * @author User
 */
public abstract class Character {
    
    private String name;
    private int age;
    private int position;
    
    //stats
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int luck;
    private int magic;
    
    //is main character?
    private boolean isMain;
    
    public Character(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public Character(String name){
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @return the dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @param dexterity the dexterity to set
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * @return the constitution
     */
    public int getConstitution() {
        return constitution;
    }

    /**
     * @param constitution the constitution to set
     */
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    /**
     * @return the intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @param intelligence the intelligence to set
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @return the wisdom
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     * @param wisdom the wisdom to set
     */
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    /**
     * @return the charisma
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * @param charisma the charisma to set
     */
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    /**
     * @return the luck
     */
    public int getLuck() {
        return luck;
    }

    /**
     * @param luck the luck to set
     */
    public void setLuck(int luck) {
        this.luck = luck;
    }

    /**
     * @return the magic
     */
    public int getMagic() {
        return magic;
    }

    /**
     * @param magic the magic to set
     */
    public void setMagic(int magic) {
        this.magic = magic;
    }

    /**
     * @return the isMain
     */
    public boolean isIsMain() {
        return isMain;
    }

    /**
     * @param isMain the isMain to set
     */
    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }
    
}
