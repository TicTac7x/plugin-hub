package tictac7x.motherlode.sectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sectors {
    public static List<Sector> getSectors(final int x, final int y, final boolean multi) {
        final List<Sector> sectors = new ArrayList<>();
        final int[] location = new int[]{x, y};

        if (isInSector(UPSTAIRS_W, location)) {
            sectors.add(Sector.UPSTAIRS_W);
            if (!multi) return sectors;
        }
        if (isInSector(UPSTAIRS_NW, location)) {
            sectors.add(Sector.UPSTAIRS_NW);
            if (!multi) return sectors;
        }
        if (isInSector(UPSTAIRS_SE, location)) {
            sectors.add(Sector.UPSTAIRS_SE);
            if (!multi) return sectors;
        }
        if (isInSector(UPSTAIRS_S, location)) {
            sectors.add(Sector.UPSTAIRS_S);
            if (!multi) return sectors;
        }
        if (isInSector(UPSTAIRS_NE, location)) {
            sectors.add(Sector.UPSTAIRS_NE);
            if (!multi) return sectors;
        }
        if (isInSector(UPSTAIRS_E, location)) {
            sectors.add(Sector.UPSTAIRS_E);
            if (!multi) return sectors;
        }

        if (sectors.isEmpty()) {
            sectors.add(Sector.DOWNSTAIRS);
        }

        return sectors;
    }

    private static boolean isInSector(final int[][] sector, final int[] location) {
        for (int[] point : sector) {
            if (Arrays.equals(point, location)) {
                return true;
            }
        }

        return false;
    }

    private static final int[][] UPSTAIRS_W = {
            {3734, 5685},
            {3735, 5684}, {3735, 5685}, {3735, 5686},
            {3736, 5685},
            {3737, 5682}, {3737, 5683}, {3737, 5684}, {3737, 5685},
            {3738, 5682}, {3738, 5683}, {3738, 5684}, {3738, 5685},
            {3739, 5683}, {3739, 5684}, {3739, 5685},
            {3740, 5684}, {3740, 5685}, {3740, 5686},
            {3741, 5683}, {3741, 5684}, {3741, 5685}, {3741, 5686},
            {3742, 5683}, {3742, 5684}, {3742, 5685}, {3742, 5686},
            {3743, 5684}, {3743, 5686}, {3743, 5687},
            {3744, 5686}, {3744, 5687},
            {3745, 5684}, {3745, 5685}, {3745, 5686},
            {3746, 5684}, {3746, 5685}, {3746, 5686},
            {3747, 5685}, {3747, 5686},
            {3748, 5684}, {3748, 5685}
    };

    private static final int[][] UPSTAIRS_NW = {
            {3747, 5683},
            {3748, 5682}, {3748, 5683}, {3748, 5684},
            {3749, 5682}, {3749, 5683}, {3749, 5684},
            {3750, 5682}, {3750, 5683}, {3750, 5684}, {3750, 5685},
            {3751, 5678}, {3751, 5680}, {3751, 5681}, {3751, 5683}, {3751, 5684},
            {3752, 5676}, {3752, 5677}, {3752, 5678}, {3752, 5679}, {3752, 5680}, {3752, 5681}, {3752, 5682}, {3752, 5683}, {3752, 5684},
            {3753, 5676}, {3753, 5677}, {3753, 5678}, {3753, 5680}, {3753, 5682}, {3753, 5683}, {3753, 5684},
            {3754, 5675}, {3754, 5676}, {3754, 5677},{3754, 5678}, {3754, 5682}, {3754, 5683},
            {3755, 5675}, {3755, 5676}, {3755, 5677}
    };

    private static final int[][] UPSTAIRS_SE = {
            {3755, 5675}, {3755, 5676},
            {3756, 5674}, {3756, 5675}, {3756, 5676},
            {3757, 5673}, {3757, 5674}, {3757, 5675}, {3757, 5676},
            {3758, 5673}, {3758, 5675},
            {3759, 5672}, {3759, 5673}, {3759, 5674},
            {3760, 5671}, {3760, 5672}, {3760, 5673}, {3760, 5674},
            {3761, 5668}, {3761, 5669}, {3761, 5670}, {3761, 5671}, {3761, 5672}, {3761, 5673}, {3761, 5674},
            {3762, 5668}, {3762, 5670}, {3762, 5671}, {3762, 5672}, {3762, 5673}
    };

    private static final int[][] UPSTAIRS_S = {
            {3761, 5660}, {3761, 5661}, {3761, 5662}, {3761, 5663}, {3761, 5664},
            {3762, 5657}, {3762, 5658}, {3762, 5660}, {3762, 5661}, {3762, 5662}, {3762, 5663}, {3762, 5664}, {3762, 5665}, {3762, 5667}, {3762, 5668},
            {3763, 5656}, {3763, 5657}, {3763, 5658}, {3763, 5659}, {3763, 5660}, {3763, 5662}, {3763, 5663}, {3763, 5665}, {3763, 5666}, {3763, 5667}, {3763, 5668}, {3763, 5670},
            {3764, 5656}, {3764, 5657}, {3764, 5658}, {3764, 5659}, {3764, 5665}, {3764, 5667}, {3764, 5668}, {3764, 5669}, {3764, 5670},
            {3765, 5656}, {3765, 5657}, {3765, 5658},
            {3766, 5657}
    };

    private static final int[][] UPSTAIRS_NE = {
            {3755, 5675}, {3755, 5676}, {3755, 5681}, {3755, 5682}, {3755, 5683},
            {3756, 5676}, {3756, 5677}, {3756, 5678}, {3756, 5679}, {3756, 5681}, {3756, 5682}, {3756, 5683}, {3756, 5684},
            {3757, 5676}, {3757, 5677}, {3757, 5678}, {3757, 5679}, {3757, 5680}, {3757, 5681}, {3757, 5682}, {3757, 5683}, {3757, 5684},
            {3758, 5680}, {3758, 5681}, {3758, 5683}, {3758, 5685},
            {3759, 5682}, {3759, 5683}, {3759, 5684}, {3759, 5685},
            {3760, 5683}, {3760, 5684}, {3760, 5685},
            {3761, 5681}, {3761, 5682}, {3761, 5683}, {3761, 5684}, {3761, 5685},
            {3762, 5682}, {3762, 5683}
    };

    private static final int[][] UPSTAIRS_E = {
            {3755, 5675}, {3755, 5676},
            {3756, 5676}, {3756, 5677},
            {3757, 5676}, {3757, 5677},
            {3758, 5677}, {3758, 5678},
            {3759, 5676}, {3759, 5677}, {3759, 5678},
            {3760, 5677}, {3760, 5678}, {3760, 5679}, {3760, 5680},
            {3761, 5675}, {3761, 5676}, {3761, 5677}, {3761, 5678}, {3761, 5679}, {3761, 5680},
            {3762, 5675}, {3762, 5676}, {3762, 5677}, {3762, 5678}, {3762, 5679}, {3762, 5680},
            {3763, 5678}, {3763, 5679}, {3763, 5678}, {3763, 5681}, {3763, 5682}, {3763, 5683},
            {3764, 5677}, {3764, 5678}, {3764, 5679}, {3764, 5680}, {3764, 5681}, {3764, 5682}, {3764, 5683},
            {3765, 5677}, {3765, 5678}, {3765, 5679},
            {3766, 5678}
    };
}
