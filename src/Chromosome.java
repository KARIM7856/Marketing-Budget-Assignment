import java.util.ArrayList;

public class Chromosome {
	
	ArrayList<Double> chromosome;
	Double fitness;

	public Chromosome(ArrayList<Double> chromosome, FitnessCalculator fc) {
		super();
		this.chromosome = chromosome;
		this.fitness = fc.getFitness(this);
	}

	public ArrayList<Double> getChromosome() {
		return chromosome;
	}

	public void setChromosome(ArrayList<Double> chromosome, FitnessCalculator fc) {
		this.chromosome = chromosome;
		this.fitness = fc.getFitness(this);
	}

	public double getFitness() {
		// TODO Auto-generated method stub
		return fitness;
	}
	
	
	
}
