
public class InvestmentInfo {
	private String channelName;
	private Double ROI;
	private Double lowerBound;
	private Double upperBound;
	
	public InvestmentInfo(String channelName, Double lowerBound, Double upperBound) {
		super();
		this.channelName = channelName;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Double getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(Double lowerBound) {
		this.lowerBound = lowerBound;
	}
	public Double getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(Double upperBound) {
		this.upperBound = upperBound;
	}
	
	
}
