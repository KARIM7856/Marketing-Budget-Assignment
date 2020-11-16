
public class NonUniformMutator implements IMutator {

	@Override
	public Chromosome mutate(Chromosome c, int t) {
		double r1 = Math.random();
		boolean deltaUp = r1 <= 0.5;
		return null;
	}
	
}
