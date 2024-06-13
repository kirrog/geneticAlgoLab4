package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {

    int[][] coordinates;
    int[] bestPath;
    double bestPathMetric;


    public TspFitnessFunction(String problem) throws Exception {
        Path coordsPath = Paths.get("data/" + problem + "/" + problem + ".tsp");
        Path bestPathPath = Paths.get("data/" + problem + "/" + problem + ".tour");
        String coords = Files.readString(coordsPath);
        String bestPath = Files.readString(bestPathPath);

        List<String> coordsList = Arrays.asList(coords.split("\r\n"));
        List<String> bestPathList = Arrays.asList(bestPath.split("\n"));

        this.coordinates = new int[coordsList.size()][3];
        this.bestPath = new int[bestPathList.size()];

        for (int i = 0; i < coordsList.size(); i++) {
            String[] elems = coordsList.get(i).split(" ");

            if (elems.length != 3) {
                throw new Exception("Wrong amount of elements");
            }
            this.coordinates[i][0] = Integer.parseInt(elems[0]);
            this.coordinates[i][1] = Integer.parseInt(elems[1]);
            this.coordinates[i][2] = Integer.parseInt(elems[2]);
        }

        for (int i = 0; i < bestPathList.size(); i++) {
            this.bestPath[i] = Integer.parseInt(bestPathList.get(i));
        }

        this.bestPathMetric = calcFitness(this.bestPath);
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        int[] solutionPath = solution.getSolutionPath();
        return this.calcFitness(solutionPath) - this.bestPathMetric;
    }

    private double calcFitness(int[] solutionPath) {
        double accumulator = 0.0;
        int prev = solutionPath[0];
        for (int i = 1; i < solutionPath.length; i++) {
            int next = solutionPath[i];
            int[] coordsPrev = this.coordinates[prev - 1];
            int[] coordsNext = this.coordinates[next - 1];
            double distance = Math.sqrt(
                    Math.pow(coordsPrev[1] - coordsNext[1], 2) +
                            Math.pow(coordsPrev[2] - coordsNext[2], 2)
            );
            prev = next;
            accumulator += distance;
        }
        return accumulator;
    }

    public boolean isNatural() {
        return false;
    }
}
