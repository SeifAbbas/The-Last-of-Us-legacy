package model.characters;


import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {
	//Heal amount  attribute - quiz idea

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}
	public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException,NotEnoughActionsException{
		super.useSpecial();
		if(this.getTarget() instanceof Hero) {
			if(this.getAdjacentCells().contains(this.getTarget().getLocation())){
				this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
			}
			else
				throw new InvalidTargetException("You can't heal a zombie");
		}
		else
			throw new InvalidTargetException("You can't heal heroes that far away");
	}
}
