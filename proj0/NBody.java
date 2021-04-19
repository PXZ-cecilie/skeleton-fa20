public class NBody {

    public static double readRadius(String filename){
			/* NOTE: Please don't use System.exit() in your code.
			   It will break the autograder. */
            
        /* Start reading in national_salt_production.txt */
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
        }	
        
    public static Body[] readBodies(String filename){
        /* NOTE: Please don't use System.exit() in your code.
            It will break the autograder. */
        /* Start reading in national_salt_production.txt */
        In in = new In(filename);
        int num = in.readInt();
        Body[] body=new Body[num];
        in.readDouble();

        for(int i=0;i<num;i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            body[i] = new Body(xP, yP, xV, yV, m, img);
        }

        return body;
        }
    
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = NBody.readRadius(filename);
        Body[] planets = NBody.readBodies(filename);

        StdDraw.setScale(-radius,radius);
        /* Clears the drawing window. */
		StdDraw.clear();

        StdDraw.picture(0,0,"images/starfield.jpg");

        for (Body i: planets) {
            i.draw();
            StdDraw.show();
        }

    StdDraw.enableDoubleBuffering();

    for(double t=0;t<=T;t+=dt){
        double[] xForce = new double[planets.length];
        double[] yForce = new double[planets.length];
        for (int i=0; i<planets.length;i++){
            xForce[i] = planets[i].calcNetForceExertedByX(planets);
            yForce[i] = planets[i].calcNetForceExertedByY(planets);
            planets[i].update(dt,xForce[i],yForce[i]);
        }
    StdDraw.picture(0,0,"images/starfield.jpg");
    //draw every planet
    for (int i=0;i<planets.length;i++){
        planets[i].draw();
        }
    StdDraw.show();
    StdDraw.pause(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }


    }
    
