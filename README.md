# lolbuildcalculator
League of Legends Build Calculator calculates the best build for a desired heuristic with at least 95% accuracy (even when not considering runes values are still a little off the ones seen in-game).

# How to use
1. You may want to define for the software to account or not certain things at the Settings tab (e.g. Hullbreaker alone or with your team and Mejai stacks).
2. At the Heuristic tab update the getHeuristicValue method return value to your desired heuristic (e.g. return c.getTotalAd() for maximum AD build).
3. If you want any conditions to be applied to your build update the isValidBuild method return value to your desired condition (e.g. return c.getBonusHealth() >= 500 to consider valid only builds with at least 500 bonus health).
4. The software will start calculating your build.
5. The best build with be shown along with its heuristic in the Evaluation tab.

# Optimization
I tried to optimized the build calculation so it only try building items that will increase your heuristic. Items are also sorted by heuristic increase, so it is very likely that the best build will be found after a short period of time, but League of Legends items are not that simple and because of passives and item interactions a better build might be found after some time, so if you want to guarantee the best build to be found let the program run until it finishes the calculation.
