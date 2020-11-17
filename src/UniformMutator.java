import java.util.ArrayList;

public class UniformMutator {
	
	ArrayList<InvestmentInfo> investmentInfo;
	
	public UniformMutator(ArrayList<InvestmentInfo> investmentInfo)
	{
		super();
		this.investmentInfo = investmentInfo;
	}
	
	public Chromosome mutate(Chromosome c) {
		ArrayList<Double> genes = c.getChromosome();
		int r0 = (int)Math.floor(Math.random() * genes.size());
		double delta = 0;
		double deltaL = genes.get(r0) - investmentInfo.get(r0).getLowerBound();
		double deltaU = investmentInfo.get(r0).getUpperBound() - genes.get(r0);
		double r1 = Math.random();
		double newGene;
		
		delta = (r0 > 0.5)? deltaU : deltaL;
		
		double r2 = (int)Math.floor(Math.random() * delta);
		
		if(delta == deltaL)
		{
			newGene = genes.get(r0) - r2;
		}
		else
		{
			newGene = genes.get(r0) + r2;
		}
		
		genes.set(r0, newGene);
		
		c.chromosome = genes;
		
		return c;
	}
}
