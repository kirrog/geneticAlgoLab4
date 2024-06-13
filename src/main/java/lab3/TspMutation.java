package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class TspMutation implements EvolutionaryOperator<TspSolution> {
    private final static double changeCoefficient = 0.5;
    private final static double insertCoefficient = 0.5;
    private final static double scrambleCoefficient = 0.5;
    private final static double inversionCoefficient = 0.5;

    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        // your implementation:
        for (TspSolution tspSolution : population) {
            mutateSolutionByChange(tspSolution, random);
            mutateSolutionByInsert(tspSolution, random);
            mutateSolutionByScramble(tspSolution, random);
            mutateSolutionByInversion(tspSolution, random);
        }
        return population;
    }

    private void mutateSolutionByChange(TspSolution tspSolution, Random random) {
        if (random.nextDouble() < changeCoefficient) {
            int[] solutionPath = tspSolution.getSolutionPath();
            int one_pos = random.nextInt(solutionPath.length-1);
            int sec_pos = random.nextInt(solutionPath.length-1);
            int tmp = solutionPath[one_pos];
            solutionPath[one_pos] = solutionPath[sec_pos];
            solutionPath[sec_pos] = tmp;
            tspSolution.setSolutionPath(solutionPath);
        }
    }

    private void mutateSolutionByInsert(TspSolution tspSolution, Random random) {
        if (random.nextDouble() < insertCoefficient) {
            int[] solutionPath = tspSolution.getSolutionPath();
            int one_pos = random.nextInt(solutionPath.length-1);
            int sec_pos = random.nextInt(solutionPath.length-1);
            if (sec_pos < one_pos) {
                int temp = sec_pos;
                sec_pos = one_pos;
                one_pos = temp;
            }
            if (sec_pos == one_pos) {
                if (one_pos == solutionPath.length) {
                    one_pos -= 1;
                } else {
                    sec_pos += 1;
                }
            }
            int tmp = solutionPath[sec_pos];
            for (int i = sec_pos; i > one_pos; i--) {
                solutionPath[i] = solutionPath[i - 1];
            }
            solutionPath[one_pos + 1] = tmp;
            tspSolution.setSolutionPath(solutionPath);
        }
    }

    private void mutateSolutionByScramble(TspSolution tspSolution, Random random) {
        if (random.nextDouble() < scrambleCoefficient) {
            int[] solutionPath = tspSolution.getSolutionPath();
            int one_pos = random.nextInt(solutionPath.length-1);
            int sec_pos = random.nextInt(solutionPath.length-1);
            if (sec_pos < one_pos) {
                int temp = sec_pos;
                sec_pos = one_pos;
                one_pos = temp;
            }
            if (sec_pos == one_pos) {
                sec_pos += 1;
            }
            int[] tmp = new int[sec_pos - one_pos];
            for (int i = one_pos; i < sec_pos; i++) {
                tmp[i - one_pos] = solutionPath[i];
            }
            shuffleArray(tmp, random);
            for (int i = one_pos; i < sec_pos; i++) {
                solutionPath[i] = tmp[i - one_pos];
            }
            tspSolution.setSolutionPath(solutionPath);
        }
    }

    private void mutateSolutionByInversion(TspSolution tspSolution, Random random) {
        if (random.nextDouble() < inversionCoefficient) {
            int[] solutionPath = tspSolution.getSolutionPath();
            int one_pos = random.nextInt(solutionPath.length-1);
            int sec_pos = random.nextInt(solutionPath.length-1);
            if (sec_pos < one_pos) {
                int temp = sec_pos;
                sec_pos = one_pos;
                one_pos = temp;
            }
            if (sec_pos == one_pos) {
                sec_pos += 1;
            }
            int[] tmp = new int[sec_pos - one_pos];
            for (int i = one_pos; i < sec_pos; i++) {
                tmp[i - one_pos] = solutionPath[i];
            }
            for (int i = sec_pos - 1; i >= one_pos; i--) {
                solutionPath[i] = tmp[i - one_pos];
            }
            tspSolution.setSolutionPath(solutionPath);
        }
    }

    private static void shuffleArray(int[] ar, Random rnd) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
