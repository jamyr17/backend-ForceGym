package una.force_gym.util;

public class StraightLineDeprecation {

    public static float calculate(float initialCost, int serviceLifeYears) {
        if (serviceLifeYears <= 0) {
            return 0f;
        }
        return initialCost / serviceLifeYears;
    }
    
}