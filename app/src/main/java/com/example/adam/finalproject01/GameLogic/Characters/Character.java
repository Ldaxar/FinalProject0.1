package com.example.adam.finalproject01.GameLogic.Characters;

/**
 * Created by Adam on 26/05/16.
 */
public class Character
{

    protected Integer health;
    protected Boolean isDead=false;

    protected Character (Integer health)
    {
        this.health=health;
    }

    public Integer getHealth() {
        return health;
    }


    public Boolean changeHealth (Integer hp)
    {
        this.health+=hp;
        if (this.health<=0) return !isDead;
        return isDead;
    }
    public void kill (){isDead=false;}

}
