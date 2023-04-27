package model.characters;

import java.awt.Point;
import engine.Game;
import exceptions.NotEnoughActionsException;
import model.world.*;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public void attack()throws NotEnoughActionsException{ //TO-DO : Set target character in move function for Hero & Zombie //
		
		target.currentHp -= this.attackDmg;
		target.defend(this);
		if(target.currentHp<=0){
			 target.onCharacterDeath();
		}
		 
	}


	public void defend(Character c) {
		c.currentHp = c.currentHp - (this.attackDmg/2);
		if(c.currentHp<=0){
			c.onCharacterDeath();
		}
		
	}


	public void onCharacterDeath() {
		if(this instanceof Zombie){
			Game.zombies.remove(this);
			((CharacterCell)(Game.map[this.location.y][this.location.x])).setSafe(true);
			//(TO-DO) in endTurn method ???//spawnNewZombie();
			
		}
		else{
			Game.heroes.remove(this);
		}
		
	}

//public static void main(String[] args) {
//	Medic x = new Medic("Spider",20,15,5);
//	x.setLocation(new Point(3,1));
//	System.out.println(x.getLocation().x);
//}
	
}
