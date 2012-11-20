/**
 * This class represents a digital camera with an autofocus feature.
 *
 * @author Markus Iser, Martin Thoma
 *
 */
class Camera {
    /** This epsilon is used for internal float comparisons. */
    private static final double EPSILON = 1E-6;

    /** The objective that is currently used by the camera. */
    private final Objective objective;

    /**
     * The constructor for objective.
     *
     * @param objective
     *            an objective
     */
    public Camera(final Objective objective) {
        this.objective = objective;
    }

    /**
     * Adjust objective to get the optimum focus.
     * The optimum focus is determined by the highest contrast.
     */
    public void autofocus() {
        boolean stepLeft;
        double contrast;

        // determine direction
        contrast = objective.getContrast();
        objective.stepLeft();

        if (objective.getContrast() > contrast) {
            stepLeft = true;
        } else {
            contrast = objective.getContrast();
            objective.stepRight();
            stepLeft = false;
        }

        while (isOptimumFound(contrast)) {
            contrast = objective.getContrast();
            if (stepLeft) {
                objective.stepLeft();
            } else {
                objective.stepRight();
            }
        }

        if (isOptionalMoveBackRequired(contrast)) {
            if (stepLeft) {
                objective.stepRight();
            } else {
                objective.stepLeft();
            }
        }
    }

    /**
     * Check two doubles for equality.
     *
     * @param fp1 first floating point number
     * @param fp2 second floating point number
     * @return {@code true} if both floats are equal, otherwise {@code false}
     */
    private boolean fpEquals(final double fp1, final double fp2) {
        return Math.abs(fp1 - fp2) < EPSILON;
    }

    private boolean isOptimumFound(final double contrast) {
        return objective.getContrast() > contrast
                && !fpEquals(contrast, objective.getContrast());
    }

    private boolean isOptionalMoveBackRequired(final double contrast) {
        return !fpEquals(contrast, objective.getContrast());
    }
}

