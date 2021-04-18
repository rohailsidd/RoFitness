package com.rofitness.fitnessapp.Utils;

import com.rofitness.fitnessapp.database.entities.Exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseComparator implements Comparator<Exercise> {
    private static Map<String, List<Integer>> orderMap;
    private List<Integer> orderRuleList;

    public ExerciseComparator(List<Integer> list) {
        this.orderRuleList = list;
    }

    public static Map<String, List<Integer>> getOrderRuleMap() {
        Map<String, List<Integer>> map = orderMap;
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        orderMap = hashMap;
        hashMap.put("Middle Chest", Arrays.asList(18251, 18041, 18059, 17869, 18327, 17906, 18341, 18299));
        orderMap.put("Front Shoulder", Arrays.asList(17963, 18514, 18365, 17842, 17975, 18295, 18332));
        orderMap.put("Lower Back", Arrays.asList(18904, 17950, 18687, 18219, 19041, 18981, 18007, 18016, 17977));
        orderMap.put("Calfs", Arrays.asList(18031, 18201, 18202, 18668, 18204));
        orderMap.put("Hips", Arrays.asList(18024, 18682, 18684, 18800, 18757, 18758));
        orderMap.put("Back Shoulder", Arrays.asList(18010, 18300, 18332, 18000, 17972, 18061, 18815));
        orderMap.put("Lower Chest", Arrays.asList(18886, 18317, 17879, 18318, 18041, 18092, 18083, 18253));
        orderMap.put("Front Thighs", Arrays.asList(18955, 17881, 18768, 18765, 18799, 18670));
        orderMap.put("V-Shape", Arrays.asList(19067, 19102, 18057, 18219, 18417, 19041, 17858, 17877));
        orderMap.put("Wings Extension", Arrays.asList(18057, 18417, 18299, 18178, 17858, 18144));
        orderMap.put("Upper Abs", Arrays.asList(18079, 17807, 17851, 18796, 19046, 18873));
        orderMap.put("Back Thighs", Arrays.asList(18955, 17992, 18799, 18771, 18367, 17961, 18738));
        orderMap.put("Upper Back", Arrays.asList(18882, 18886, 17858, 18417, 18057, 18342, 17877, 17976));
        orderMap.put("Forearm", Arrays.asList(17971, 17853, 18855, 17949, 18148, 17861, 18048));
        orderMap.put("Traps", Arrays.asList(18987, 18787, 18562, 18517, 18201, 17976, 18194, 17977));
        orderMap.put("V-Shape Back", Arrays.asList(19067, 19102, 18220, 18057, 18219, 18417, 17858, 17877));
        orderMap.put("Upper Chest", Arrays.asList(18883, 18341, 17903, 18298, 17855, 18022, 18184, 18299));
        orderMap.put("Biceps", Arrays.asList(17875, 18490, 18027, 18047, 18335, 18642));
        orderMap.put("Triceps", Arrays.asList(18223, 18188, 17869, 17969, 17954, 18254, 18253));
        orderMap.put("Side Cutting", Arrays.asList(18227, 18247, 19085, 18908, 18844, 18845, 18249, 18696, 18915, 18914, 17978));
        orderMap.put("Lower Abs", Arrays.asList(18677, 18248, 18079, 17806, 17816, 18238, 18867, 18244, 19073, 19097, 18873));
        orderMap.put("Aerobic", Arrays.asList(18699, 19050, 19039, 19026, 18716, 18690, 18903, 18911));
        orderMap.put("Stretching", Arrays.asList(18674, 17811, 17821, 18205, 18939, 19053, 18567, 18836, 18910, 18866, 18918, 19006));
        orderMap.put("Yoga", Arrays.asList(18873, 19017, 18231, 19072, 18261, 19065, 18028, 18035, 18039, 18207));
        return orderMap;
    }

    public int compare(Exercise exercise, Exercise exercise2) {
        exercise.getId().longValue();
        exercise2.getId().longValue();
        if (this.orderRuleList.contains(Integer.valueOf(exercise.getId().intValue())) && this.orderRuleList.contains(Integer.valueOf(exercise2.getId().intValue()))) {
            return this.orderRuleList.indexOf(Integer.valueOf(exercise.getId().intValue())) - this.orderRuleList.indexOf(Integer.valueOf(exercise2.getId().intValue()));
        }
        if (this.orderRuleList.contains(Integer.valueOf(exercise.getId().intValue()))) {
            return -1;
        }
        if (this.orderRuleList.contains(Integer.valueOf(exercise2.getId().intValue()))) {
            return 1;
        }
        return exercise.getExerciseTitle().compareTo(exercise2.getExerciseTitle());
    }
}
