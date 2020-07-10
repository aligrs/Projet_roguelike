package rogue;


class Class {

    private int maxHp;
    private int hpRegen;
    private int maxMana;
    private int manaRegen;
    private int attackValue;
    private int defenseValue;
    private int strength;
    private int dexterity;
    private Inventory startingItems;

    Class(int maxHp, int hpRegen, int maxMana, int manaRegen, int attack, int defense, int strength, int dexterity, Item[] items) {
        this.maxHp = maxHp;
        this.hpRegen = hpRegen;
        this.maxMana = maxMana;
        this.manaRegen = manaRegen;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.strength = strength;
        this.dexterity = dexterity;
        startingItems = new Inventory(20);
        for (Item i : items) {
            if (i != null) {
                startingItems.add(i);
            }
        }
    }

    int maxHp() {
        return maxHp;
    }

    int hpRegen() {
        return hpRegen;
    }

    int maxMana() {
        return maxMana;
    }

    int manaRegen() {
        return manaRegen;
    }

    int attackValue() {
        return attackValue;
    }

    int defenseValue() {
        return defenseValue;
    }

    int strength() {
        return strength;
    }

    int dexterity() {
        return dexterity;
    }

    Inventory startingItems() {
        return startingItems;
    }
}
