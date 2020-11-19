import java.util.ArrayList;

public class UniformMutator implements IMutator {
	
	InvestmentInfoArray investmentInfo;
	FitnessCalculator fc;
	
	public UniformMutator(InvestmentInfoArray investmentInfo, FitnessCalculator fc)
	{
		super();
		this.investmentInfo = investmentInfo;
		this.fc = fc;
	}
	
	@Override
	public Chromosome mutate(Chromosome c, int t) {
		ArrayList<Double> genes = c.getChromosome();
		
		for(int i = 0; i < genes.size(); i++) {
			
			int r0 = (int)Math.floor(Math.random() * genes.size());
			double delta = 0;
			double deltaL = investmentInfo.get(i).getLowerBound();
			deltaL = (deltaL == -1.0)? genes.get(i) : genes.get(i) - deltaL;
			//double r1 = Math.random();
			
			delta = (r0 > 0.5)? calcDeltaU(investmentInfo.get(i), genes.get(i), c.getTotal())
					: calcDeltaL(investmentInfo.get(i), genes.get(i), c.getTotal());
			
			double r2 = (int)Math.floor(Math.random() * delta);
			
			if(r0 <= 0.5)
			{
				double newGene = genes.get(i) - r2;
				genes.set(i, newGene);
			}
			else
			{
				double newGene = genes.get(i) + r2;
				genes.set(i, newGene);
			}
		}
		
		c.setChromosome(genes, fc);
		
		return c;
	}
	private double calcDeltaU(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToAdd = info.getUpperBound();
		
		upperLimitToAdd = (upperLimitToAdd == -1.0)? investmentInfo.getTotalBudget()-currentTotal:
			(investmentInfo.getTotalBudget() - currentTotal)*upperLimitToAdd;
		
		return upperLimitToAdd;
		
	}
	
	private double calcDeltaL(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToSubtract =  info.getLowerBound();
		upperLimitToSubtract = (upperLimitToSubtract == -1.0)? current : current-upperLimitToSubtract;
		return upperLimitToSubtract;
	}
}
