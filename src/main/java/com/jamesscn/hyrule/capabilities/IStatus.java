package com.jamesscn.hyrule.capabilities;

public interface IStatus {
    public int getMana();
    public void setMana(int amount);
    public void fillMana();
    public void addMana(int amount);
    public void useMana(int amount);
    public int getManaLimit();
    public void setManaLimit(int amount);
    public boolean hasEnoughMana(int amount);

    public int getRupees();
    public void setRupees(int amount);
    public void addRupees(int amount);
    public void dropRupees(int amount);
    public int getRupeeLimit();
    public void setRupeeLimit(int amount);
    public boolean hasEnoughRupees(int amount);

    public int getHeartPieces();
    public void setHeartPieces(int amount);
    public void addHeartPiece();
    public int getHeartContainers();
    public void setHeartContainers(int amount);
    public void addHeartContainer();
}