package interfaces;

import items.Item;
import main.Champion;

public interface CombinationCondition
{
	public boolean isValidBuild(Item[] build, Champion c);
}
