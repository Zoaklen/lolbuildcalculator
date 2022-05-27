# lolbuildcalculator
League of Legends Build Calculator calculates the best build for a desired heuristic with at least 95% accuracy (even when not considering runes values are still a little off the ones seen in-game).

# How to use
1. You may want to define for the software to account or not certain things at the Settings tab (e.g. Hullbreaker alone or with your team and Mejai stacks).
2. At the Heuristic tab update the getHeuristicValue method return value to your desired heuristic (e.g. return c.getTotalAd() for maximum AD build).
3. If you want any conditions to be applied to your build update the isValidBuild method return value to your desired condition (e.g. return c.getBonusHealth() >= 500 to consider valid only builds with at least 500 bonus health) (read Observations section).
4. The software will start calculating your build.
5. The best build will be shown along with its heuristic in the Evaluation tab.

# Observations
- Runes are not considered in any way.
- Level 18
- 6 build items always have a mythic (optimization) (mythic items are very cost effective and at least one will be useful for your heuristic)
- Items that do not affect your heuristic are discarded (optimization)
- Items are sorted by heuristic value, so the best build is LIKELY to be found quickly (optimization)
- If any of your build conditions differs from your heuristic you need to add at least a very small heuristic influence for it to not be discarded. (e.g. maximum AD build with at least 500 bonus health you need to add some bonus health to your heuristic, like c.getTotalAd() + c.getBonusHealth * 0.001f)
