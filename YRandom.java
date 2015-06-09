import java.util.Random;

public class YRandom {
	public double gammaRand(double alpha, double beta) {
		double n, x, g, b1, b2, c1, c2, y, v1, v2, w1, w2;
		Random r = new Random();
		if(alpha <= 0.4)
			n = 1.0/alpha;
		else if(alpha <= 4)
			n = 1.0/alpha + (alpha - 0.4)/(3.6 * alpha);
		else
			n = 1.0/Math.sqrt(alpha);

		b1 = alpha - 1/n;
		b2 = alpha + 1/n;

		if(alpha <= 0.4)
			c1 = 0;
		else
			c1 = b1 * (Math.log(b1) - 1)/2;

		c2 = b2 * (Math.log(b2) - 1)/2;

		y = 0.0;
		x = 0.0;
		w1 = w2 = 0.0;
		do{
			do{
				v1 = r.nextDouble();
				v2 = r.nextDouble();
				w1 = c1 + Math.log(v1);
				w2 = c2 + Math.log(v2);

				y = n*(b1*w2 - b2*w1);
			}while(y < 0);
			x = n*(w2 - w1);
		}while(Math.log(y) < x);
		g = beta*Math.exp(x);
		return g;
	}

	public double normRand(double mu, double sigma) {
		Random r = new Random();
		double u1, u2, p;
		u1 = r.nextDouble();
		u2 = r.nextDouble();

		p = Math.sqrt(sigma) * Math.sqrt(-2 * Math.log(u1)) * Math.cos(2 * Math.PI * u2) + mu;
		return p;
	}

	public double[] dirRand(double[] alpha){
		int K = alpha.length;
		double[] dir = new double[K];
		double sum = 0.0;

		for(int i = 0;i < K;i++){
			dir[i] = gammaRand(alpha[i], 1);
			sum += dir[i];
		}

		for(int i = 0;i < K;i++)
			dir[i] /= sum;

		return dir;
	}
}
