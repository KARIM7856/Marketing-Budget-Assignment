import java.util.ArrayList;
import java.util.Arrays;

public class FitnessCalculator {
	
	ArrayList<InvestmentInfo> investmentInfo;
	
	

	/**
	 * @param investmentInfo
	 */
	public FitnessCalculator(ArrayList<InvestmentInfo> investmentInfo) {
		super();
		this.investmentInfo = investmentInfo;
	}



	public Double getFitness(Chromosome chromosome) {
		ArrayList<Double> c = chromosome.getChromosome();
		double fitness = 0.0;
		for(int i = 0; i < c.size(); i++) {
			fitness += c.get(i);
		}
		
		return fitness;
	}

	
	
}
