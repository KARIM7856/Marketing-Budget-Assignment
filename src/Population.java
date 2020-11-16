import java.util.ArrayList;
import java.util.Comparator;
import java.math.*;
public class Population {
	private ArrayList<Chromosome> population;
	private FitnessCalculator fc;
	private Integer population_size;
	private IMutator mutator;
	private int nGeneration;
	private ArrayList<InvestmentInfo> investmentInfo;
	
	
	
	/**
	 * @param population
	 * @param fc
	 * @param population_size
	 * @param mutator
	 * @param investmentInfo
	 */
	public Population(ArrayList<Chromosome> population , FitnessCalculator fc, Integer population_size, IMutator mutator,
			ArrayList<InvestmentInfo> investmentInfo) {
		super();
		this.population = population;
		this.fc = fc;
		this.population_size = population_size;
		this.mutator = mutator;
		this.investmentInfo = investmentInfo;
	}


	public void initializeGeneration0(int totalBudget) {
		for(int i = 0; i < population_size; i++) {
			
			ArrayList<Double> currentChromosome = new ArrayList<Double>();
			int currentTotal = totalBudget;
			for(int j = 0; j < investmentInfo.size(); j++) {
				
				Double lowerBound = investmentInfo.get(j).getLowerBound();
				
				lowerBound = (lowerBound.equals((double)-1))? 0 : lowerBound;
				
				Double upperBound = investmentInfo.get(j).getUpperBound();
				
				upperBound = (upperBound.equals( (double)-1 ))? currentTotal : upperBound*currentTotal;
				
				Double gene = lowerBound + Math.random()*(upperBound-lowerBound);
				
				currentChromosome.add(gene);
				
				currentTotal -= gene;
				
				nGeneration = 0;
				
			}
			
			population.add(new Chromosome(currentChromosome, fc));
		}
	}
	
	public void newGeneration() {
		ArrayList<Chromosome> newGeneration = getMatingPool();
		
		newGeneration = crossover(newGeneration);
		
		newGeneration = mutation(newGeneration);
		
		this.population = newGeneration;
		
		nGeneration++;
	}
	
	private ArrayList<Chromosome> mutation(ArrayList<Chromosome> newGeneration) {
		
		return null;
	}


	private ArrayList<Chromosome> crossover(ArrayList<Chromosome> arr){
		ArrayList<Chromosome> result = new ArrayList<Chromosome>();
		
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		for(int i = 0; i < population_size; i++) {
			candidates.add(i);
		}
		
		for(int i = 0; i < population_size; i+=2) {
			
			int i1 = candidates.remove(randIndex(candidates.size()));
			int i2 = candidates.get(randIndex(candidates.size()));
			
			Chromosome c1 = arr.get(i1);
			Chromosome c2 = arr.get(i2);
			
			Chromosome[] offsprings = twoPointCrossover(c1, c2);
			
			result.add((offsprings[0].getFitness() > c1.getFitness())? offsprings[0] : c1);
			result.add((offsprings[1].getFitness() > c2.getFitness())? offsprings[2] : c2);
			
		}
		
		return result;
		
		
	}
	
	private Chromosome[] twoPointCrossover(Chromosome c1, Chromosome c2) {
		double r0 = Math.random();
		
		if(r0 < 0.4) {
			return new Chromosome[] {c1,c2};
		}else {
			ArrayList<Double> newC1 = new ArrayList<Double>(), newC2 = new ArrayList<Double>();
			int size = c1.getChromosome().size();
			int point1 = (int)Math.floor(Math.random()*(size/2));
			int point2 = (int)Math.floor(Math.random()*size/2)+ size/2;
			
			for(int i = 0; i < point1; i++) {
				newC1.add(c1.getChromosome().get(i));
				newC2.add(c2.getChromosome().get(i));
				
			}
			
			for(int i = point1; i < point2; i++) {
				newC1.add(c2.getChromosome().get(i));
				newC2.add(c1.getChromosome().get(i));
				
			}
			for(int i = point2; i < c1.getChromosome().size(); i++) {
				
				newC1.add(c1.getChromosome().get(i));
				newC2.add(c2.getChromosome().get(i));
			}
			return new Chromosome[] {new Chromosome(newC1, fc), new Chromosome(newC2, fc)};
			
		}
		
	}
	
	
	private ArrayList<Chromosome> getMatingPool() {
		ArrayList<Chromosome> matingPool = new ArrayList<Chromosome>();
		for(int i = 0; i < population.size(); i++) {
			matingPool.add( tournament(population.get(randIndex(population_size)), population.get(randIndex(population_size))) );
		}
		return matingPool;
		
	}
	
	private int randIndex(int upperBound) {
		return (int)Math.floor(Math.random()*upperBound);
	}
	
	private Chromosome tournament(Chromosome c1, Chromosome c2) {
		return (c1.getFitness() > c2.getFitness())? c1 : c2;
	}


	public ArrayList<Chromosome> getPopulation() {
		return population;
	}
	
	public void setPopulation(ArrayList<Chromosome> population) {
		this.population = population;
	}
	
	public FitnessCalculator getFc() {
		return fc;
	}
	
	public void setFc(FitnessCalculator fc) {
		this.fc = fc;
	}
	
	public IMutator getMutator() {
		return mutator;
	}
	
	public void setMutator(IMutator mutator) {
		this.mutator = mutator;
	}
	
	
	
}
