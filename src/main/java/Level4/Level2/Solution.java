package Level4.Level2;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Answer based on https://peter-ak.github.io/2020/05/10/Brining_a_gun_to_a_guard_fight.html
     * I saw the symmetry, but my problem solving skills are a bit rusty...
     *
     * @param dimensions       [width, height] of the room
     * @param your_position    [x, y], your coordinates in the room
     * @param trainer_position [x, y] trainer's coordinate in the room
     * @param distance         the maximum distance the beam can travel
     * @return
     */
    public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
        if (distanceBetween(your_position, trainer_position) > distance) {
            return 0;
        }

        //How many times do we need to replicate the room in one direction
        int numberReplicaXAxis = (int) Math.ceil((double) (your_position[0] + distance) / dimensions[0]);
        int numberReplicaYAxis = (int) Math.ceil((double) (your_position[1] + distance) / dimensions[1]);

        //Let's create two arrays to store all the symmetrical position of you and the trainer
        // yourSymmetricalPosition[0][0] is your position in the bottom-left corner quadrant
        int[][][] yourSymmetricalPosition = replicatePositions(dimensions, your_position, numberReplicaXAxis, numberReplicaYAxis);
        int[][][] trainerSymetricalPosition = replicatePositions(dimensions, trainer_position, numberReplicaXAxis, numberReplicaYAxis);

        Map<Double, Double> angleToYourReplicatedPosition = new HashMap<>();
        for (int i = 0; i < 2 * numberReplicaXAxis; i++) {
            for (int j = 0; j < 2 * numberReplicaYAxis; j++) {
                double angleBetween = angleBetween(yourSymmetricalPosition[i][j], your_position);
                double distanceBetween = distanceBetween(yourSymmetricalPosition[i][j], your_position);
                if (!angleToYourReplicatedPosition.containsKey(angleBetween) || angleToYourReplicatedPosition.get(angleBetween) > distanceBetween) {
                    angleToYourReplicatedPosition.put(angleBetween, distanceBetween);
                }
            }
        }

        //we can then check all the distances between the initial position and the target
        //TODO We're storing angles as double in a map. Is there a more precise way to store them?
        Map<Double, Double> angleToTargetReplicatedPosition = new HashMap<>();
        for (int i = 0; i < 2 * numberReplicaXAxis; i++) {
            for (int j = 0; j < 2 * numberReplicaYAxis; j++) {
                int[] trainerReplicatedPosition = trainerSymetricalPosition[i][j];
                double angleBetween = angleBetween(trainerReplicatedPosition, your_position);
                double distanceBetween = distanceBetween(your_position, trainerReplicatedPosition);
                if (distanceBetween <= distance) {

                    // Check if you would get hit first
                    if (angleToYourReplicatedPosition.containsKey(angleBetween)
                            && angleToYourReplicatedPosition.get(angleBetween) <= distanceBetween) {
                        continue;
                    }

                    //Check if we already shot in that direction
                    if (angleToTargetReplicatedPosition.containsKey(angleBetween)
                            && angleToTargetReplicatedPosition.get(angleBetween) <= distanceBetween) {
                        continue;
                    }

                    angleToTargetReplicatedPosition.put(angleBetween, distanceBetween);
                }
            }
        }

        return angleToTargetReplicatedPosition.size();
    }

    /**
     *
     * result[0][0] is the bottom left corner
     * result[numberReplicaXAxis][numberReplicaYAxis] == initialPosition
     *
     * @param dimensions The size of the room
     * @param initialPosition The initial position which needs to be mirrored
     * @param numberReplicaXAxis The number of replicated quandrant along the X axis
     * @param numberReplicaYAxis The number of replicated quandrant along the Y axis
     * @return An array containing the mirrored initial position along all the quandrants
     */
    private static int[][][] replicatePositions(int[] dimensions, int[] initialPosition, int numberReplicaXAxis, int numberReplicaYAxis) {
        int[][][] yourSymmetricalPosition = new int[2 * numberReplicaXAxis][2 * numberReplicaYAxis][2];
        //The first quadrant is at the below coordinates
        yourSymmetricalPosition[numberReplicaXAxis][numberReplicaYAxis] = initialPosition;

        //Let's do the top right quadrant first
        for (int i = numberReplicaXAxis; i < 2 * numberReplicaXAxis; i++) {
            for (int j = numberReplicaYAxis; j < 2 * numberReplicaYAxis; j++) {
                if (i == numberReplicaXAxis && j == numberReplicaYAxis) {
                    //This is the first position, we already filled it
                    continue;
                }

                //We mirror along the y axis
                if (j == numberReplicaYAxis) {
                    yourSymmetricalPosition[i][j][0] = yourSymmetricalPosition[i - 1][j][0]
                            + 2 * ((i - numberReplicaXAxis) * dimensions[0] - yourSymmetricalPosition[i - 1][j][0]);
                    yourSymmetricalPosition[i][j][1] = yourSymmetricalPosition[i - 1][j][1];
                    continue;
                }

                //We mirror along the x axis
                yourSymmetricalPosition[i][j][0] = yourSymmetricalPosition[i][j - 1][0];
                yourSymmetricalPosition[i][j][1] = yourSymmetricalPosition[i][j - 1][1]
                        + 2 * ((j - numberReplicaYAxis) * dimensions[1] - yourSymmetricalPosition[i][j - 1][1]);
            }
        }

        //Let's do the other quadrants

        //Top left, we mirror the top right against the y axis
        for (int i = 0; i < numberReplicaXAxis; i++) {
            for (int j = numberReplicaYAxis; j < 2 * numberReplicaYAxis; j++) {
                //Symmetric point along the y axis has coordinates (2 * numberReplicaXAxis - i, j)
                int symetricX = 2 * numberReplicaXAxis - i - 1;

                yourSymmetricalPosition[i][j][0] = -1 * yourSymmetricalPosition[symetricX][j][0];
                yourSymmetricalPosition[i][j][1] = yourSymmetricalPosition[symetricX][j][1];
            }
        }

        //Bottom left and bottom right, we mirror top left and top right against the x axis
        for (int i = 0; i < 2 * numberReplicaXAxis; i++) {
            for (int j = 0; j < numberReplicaYAxis; j++) {
                //Symmetric point along the x axis has coordinates (i, 2 * numberReplicaYAxis - j)
                int symetricY = 2 * numberReplicaYAxis - j - 1;

                yourSymmetricalPosition[i][j][0] = yourSymmetricalPosition[i][symetricY][0];
                yourSymmetricalPosition[i][j][1] = -1 * yourSymmetricalPosition[i][symetricY][1];
            }
        }
        return yourSymmetricalPosition;
    }

    /**
     * @param p1 A point with [x, y] coordinates
     * @param p2 A point with [x, y] coordinates
     * @return The angle between the x axis and the line made of (p1, p2)
     */
    private static double angleBetween(int[] p1, int[] p2) {
        return Math.atan2(p2[1] - p1[1], p2[0] - p1[0]);
    }

    /**
     * @param p1 A point with [x, y] coordinates
     * @param p2 A point with [x', y'] coordinates
     * @return The distance between both points
     */
    private static double distanceBetween(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

}
