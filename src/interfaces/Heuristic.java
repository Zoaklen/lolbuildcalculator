package interfaces;

import items.Item;
import main.Champion;

public interface Heuristic
{
	public float getHeuristicValue(Item[] build, Champion c);
	public boolean isValidBuild(Item[] build, Champion c);
}
