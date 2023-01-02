package practice2;

public class RangeResult {

	private final double maximumValue;
	private final double minimumValue;

	public RangeResult(final double maximumValue, final double minimumValue) {
		this.maximumValue = maximumValue;
		this.minimumValue = minimumValue;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public double getMinimumValue() {
		return minimumValue;
	}
}
