package com.jamesscn.hyrule.capabilities;

public class StatusHandler implements IStatus {
    private int mana;
    private int manaLimit;
    private int rupees;
    private int rupeeLimit;
    private int heartPieces;
    private int heartContainers;

    public StatusHandler() {
        mana = 100;
        manaLimit = 100;
        rupees = 0;
        rupeeLimit = 100;
        heartPieces = 0;
        heartContainers = 3;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int amount) {
        if(amount > manaLimit) {
            amount = manaLimit;
        }
        if(amount < 0) {
            amount = 0;
        }
        mana = amount;
    }

    @Override
    public void fillMana() {
        mana = manaLimit;
    }

    @Override
    public void addMana(int amount) {
        setMana(mana + amount);
    }

    @Override
    public void useMana(int amount) {
        setMana(mana - amount);
    }

    @Override
    public int getManaLimit() {
        return manaLimit;
    }

    @Override
    public void setManaLimit(int amount) {
        manaLimit = amount;
    }

    @Override
    public boolean hasEnoughMana(int amount) {
        return amount <= mana;
    }

    @Override
    public int getRupees() {
        return rupees;
    }

    @Override
    public void setRupees(int amount) {
        if(amount > rupeeLimit) {
            amount = rupeeLimit;
        } else if (amount < 0) {
            amount = 0;
        }
        rupees = amount;
    }

    @Override
    public void addRupees(int amount) {
        setRupees(rupees + amount);
    }

    @Override
    public void dropRupees(int amount) {
        setRupees(rupees - amount);
    }

    @Override
    public int getRupeeLimit() {
        return rupeeLimit;
    }

    @Override
    public void setRupeeLimit(int amount) {
        rupeeLimit = amount;
    }

    @Override
    public boolean hasEnoughRupees(int amount) {
        return amount <= rupees;
    }

    @Override
    public int getHeartPieces() {
        return heartPieces;
    }

    @Override
    public void setHeartPieces(int amount) {
        heartPieces = amount;
    }

    @Override
    public void addHeartPiece() {
        if(heartPieces == 3) {
            heartPieces = 0;
            addHeartContainer();
        } else {
            heartPieces++;
        }
    }

    @Override
    public int getHeartContainers() {
        return heartContainers;
    }

    @Override
    public void setHeartContainers(int amount) {
        heartContainers = amount;
    }

    @Override
    public void addHeartContainer() {
        heartContainers++;
    }
}