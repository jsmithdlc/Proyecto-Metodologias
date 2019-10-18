package model.items;

import model.units.IUnit;

public abstract class AbstractItemFactory implements ItemFactory{

    private String name;
    private int power;
    private int maxRange;
    private int minRange;
    private IUnit owner;

    public AbstractItemFactory(){}

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setPower(int power){
        this.power = power;
    }

    @Override
    public int getPower(){
        return this.power;
    }

    @Override
    public void setMinRange(int minRange){
        this.minRange = minRange;
    }

    @Override
    public int getMinRange(){
        return this.minRange;
    }

    @Override
    public void setMaxRange(int maxRange){
        this.maxRange = maxRange;
    }

    @Override
    public int getMaxRange(){
        return this.maxRange;
    }


}
